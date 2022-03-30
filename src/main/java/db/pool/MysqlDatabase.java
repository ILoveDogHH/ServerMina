package db.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.sql.Connection;

public class MysqlDatabase {

    private PooledObject<Connection> pooledObject;


    public MysqlDatabase(MysqlDatabaseConfig mysqlConfig){

    }

    public void init(MysqlDatabaseConfig mysqlConfig) throws Exception {
        String url = mysqlConfig.getDBUrl();
        MysqlConnectObjectPoolFactory mysqlConnectObjectPoolFactory = new MysqlConnectObjectPoolFactory(url);
        GenericObjectPool genericObjectPool = new GenericObjectPool<Connection>(mysqlConnectObjectPoolFactory, mysqlConfig.getPoolConfig());
        pooledObject = genericObjectPool.cr
    }



}
