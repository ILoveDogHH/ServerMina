package db.pool;

import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class MysqlDatabaseConfig {

    private String url = "jdbc:mysql://%s:%d/%s?user=%s&password=%s&useUnicode=true&characterEncoding=UTF-8&useSSL=false";

    private String ip;

    private int port;

    private String dbName;

    private String user;

    private String password;

    private GenericObjectPoolConfig poolConfig;

    public MysqlDatabaseConfig(String ip, int port, String dbName, String user, String password){
        this.ip = ip;
        this.port = port;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        poolConfig = new GenericObjectPoolConfig();
    }



    public String getDBUrl(){
        return String.format(this.url, ip, port,dbName,user, password);
    }

    public String getUrl() {
        return url;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getDbName() {
        return dbName;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public GenericObjectPoolConfig getPoolConfig() {
        return poolConfig;
    }
}
