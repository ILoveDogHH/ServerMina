package dao;

import db.DBbase;
import db.DataBase;
import db.pool.MysqlDatabase;
import entity.CfgAct112;
import j2cache.CacheDataSource;
import manager.DatabaseManager;

import java.sql.SQLException;
import java.util.List;

public class UserDao extends DBbase {
    private static UserDao dao;

    public UserDao(DataBase db) {
        super(db);
    }


    public static UserDao getInstance(){
        if(dao == null){
            synchronized (UserDao.class){
                if(dao == null){
                    dao = new UserDao(DatabaseManager.getDB());
                }
            }
        }
        return dao;
    }


    public List<CfgAct112> getData() throws SQLException {
        List<CfgAct112> data = dao.getListData(CfgAct112.class, "select * from cfg_act112");
        return data;
    }



}

