package db;

import db.pool.MysqlDatabase;
import db.pool.MysqlDatabaseConfig;
import vo.CfgAct112;

import java.util.List;

public class test {


    public static void main(String[] args) throws Exception {
        String ip = "127.0.0.1";
        int port = 3306;
        String dbName = "p16";
        String user = "root";
        String password = "asd123456";
        MysqlDatabaseConfig config = new MysqlDatabaseConfig(ip, port, dbName, user, password);
        MysqlDatabase db = new MysqlDatabase(config);
        String sql = "select * from cfg_act112 where mainlv = ?";
        CfgAct112 c = (CfgAct112) db.getData(CfgAct112.class, sql, 1);
        List<CfgAct112> c2 = db.getListData(CfgAct112.class, sql);
    }
}