package db;

import db.pool.MysqlDatabase;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBbase<T> implements DBInterface<T>{

    DataBase db;

    public DBbase(DataBase db){
        this.db = db;
    }

    private PreparedStatement insteadHolder(PreparedStatement prestat, Object...args) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            prestat.setObject(i+1, args[i]);
        }
        return prestat;
    }

    private <T> T getEntity(ResultSet rs, Class<?> clazz) throws InstantiationException, IllegalAccessException, SQLException, InvocationTargetException {
        T entity = (T) clazz.newInstance();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String key = rsmd.getColumnLabel(i);
            Object val = rs.getObject(key);
            BeanUtils.setProperty(entity, key, val);
        }
        return entity;
    }


    public void close(Connection conn, Statement stat, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                db.getPoolObject().returnObject(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int update(String sql, Object... args) throws SQLException {
        Connection conn = null;
        PreparedStatement prestat = null;
        int count = 0;
        try {
            conn = db.getPoolObject().borrowObject();
            prestat = conn.prepareStatement(sql);
        }catch (Exception e){
            throw new SQLException("pooledObject connect error" + e.getMessage());
        }
        try {
            insteadHolder(prestat, args);
            count =  prestat.executeUpdate();
        } catch (Exception exception) {
            throw new SQLException("sql error" + exception.getMessage());
        } finally {
            close(conn, prestat, null);
        }
        return count;
    }


    public <T> T getData(Class<T> clazz, String sql, Object... args) throws SQLException {
        Connection conn = null;
        PreparedStatement prestat = null;
        ResultSet rs = null;
        T entity = null;
        try {
            conn = db.getPoolObject().borrowObject();
            prestat = conn.prepareStatement(sql);
            insteadHolder(prestat, args);
            rs = prestat.executeQuery();
            if (rs.next()) {
                entity = getEntity(rs, clazz);
            }
        } catch (Exception exception) {
            throw new SQLException("sql error" + exception.getMessage());
        } finally {
            close(conn, prestat, rs);
        }
        return entity;
    }


    public <T> List<T> getListData(Class<T> clazz, String sql, Object... args) throws SQLException {
        Connection conn = null;
        PreparedStatement prestat = null;
        ResultSet rs = null;
        T entity = null;
        List<T> list = new ArrayList<T>();
        try {
            conn = db.getPoolObject().borrowObject();
            prestat = conn.prepareStatement(sql);
            insteadHolder(prestat, args);
            rs = prestat.executeQuery();
            while (rs.next()) {
                entity = getEntity(rs, clazz);
                list.add(entity);
            }
        } catch (Exception exception) {
            throw new SQLException("sql error" + exception.getMessage());
        } finally {
            close(conn, prestat, rs);
        }
        return list;
    }


}
