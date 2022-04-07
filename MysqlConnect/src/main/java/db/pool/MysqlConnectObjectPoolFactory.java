package db.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.sql.Connection;
import java.sql.DriverManager;

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
