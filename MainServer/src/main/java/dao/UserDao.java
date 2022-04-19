package dao;

import db.DBbase;
import db.pool.MysqlDatabase;
import manager.DatabaseManager;


public class UserDao extends DBbase {
    private static UserDao instance;

     private UserDao(MysqlDatabase db) {
        super(db);
    }

    public static UserDao getInstance(){
         if(instance == null){
             synchronized (UserDao.class){
                 if(instance == null){
                     instance = new UserDao(DatabaseManager.getDB());
                 }
             }
         }
         return instance;
    }







}
