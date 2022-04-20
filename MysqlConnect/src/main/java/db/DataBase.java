package db;

import org.apache.commons.pool2.ObjectPool;

import java.sql.Connection;

public interface DataBase {

    ObjectPool<Connection>  getPoolObject();
}
