package manager;


import db.DBInterface;
import db.pool.MysqlDatabase;
import db.pool.MysqlDatabaseConfig;
import utils.ConfigCenter;

public class DatabaseManager {
    private static MysqlDatabase db;



    public static void register() throws Exception {
        //数据库注册
        MysqlDatabaseConfig dbConfig = getMysqlConfig();
        db = new MysqlDatabase(dbConfig);
    }

    public static MysqlDatabase getDB(){
        return db;
    }





    private static MysqlDatabaseConfig getMysqlConfig(){
        String ip = ConfigCenter.getConfig("jdbc.ip");
        int port = ConfigCenter.getIntConfig("jdbc.port");
        String dbName = ConfigCenter.getConfig("jdbc.dbname");
        String username = ConfigCenter.getConfig("jdbc.username");
        String password = ConfigCenter.getConfig("jdbc.password");
        MysqlDatabaseConfig dbConfig = new MysqlDatabaseConfig(ip, port, dbName, username,password);
        return dbConfig;
    }




}
