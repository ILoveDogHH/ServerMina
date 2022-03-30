package db.pool;

import lombok.SneakyThrows;
import org.apache.commons.pool2.BasePooledObjectFactory;
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

    @SneakyThrows
    @Override
    public PooledObject<Connection> wrap(Connection connection) {
        Connection con = DriverManager.getConnection(url);
        return new DefaultPooledObject<>(con);
    }




    public void destroyObject(PooledObject<Connection> p) throws Exception {
        p.getObject().close();
    }





}
