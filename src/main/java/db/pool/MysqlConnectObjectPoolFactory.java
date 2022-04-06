package db.pool;

import logger.JLogger;
import lombok.SneakyThrows;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.sql.DriverManager;
import java.sql.Connection;

public class MysqlConnectObjectPoolFactory extends BasePooledObjectFactory<Connection> {

    String url = "";

    MysqlConnectObjectPoolFactory(String url) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        this.url = url;
    }



    @Override
    public Connection create() throws Exception {
        return DriverManager.getConnection(url);
    }

    @Override
    public PooledObject<Connection> wrap(Connection connection) {
        return new DefaultPooledObject<>(connection);
    }


}
