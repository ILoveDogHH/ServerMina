package db.pool;

import db.DBInterface;
import db.DataBase;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlDatabase implements DataBase {

    private ObjectPool<Connection> poolObject;


    public MysqlDatabase(MysqlDatabaseConfig mysqlConfig) throws Exception {
        init(mysqlConfig);
    }


    public MysqlDatabase(String ip, int port, String dbName, String user, String password) throws Exception {
        MysqlDatabaseConfig config = new MysqlDatabaseConfig(ip, port, dbName, user, password);
        init(config);
    }

    private void init(MysqlDatabaseConfig mysqlConfig) throws Exception {
        String url = mysqlConfig.getDBUrl();
        MysqlConnectObjectPoolFactory mysqlConnectObjectPoolFactory = new MysqlConnectObjectPoolFactory(url);
        GenericObjectPool<Connection> genericObjectPool = new GenericObjectPool<>(mysqlConnectObjectPoolFactory, mysqlConfig.getPoolConfig());
        poolObject = genericObjectPool;
    }



    public ObjectPool<Connection> getPoolObject(){
        return poolObject;
    }






}
