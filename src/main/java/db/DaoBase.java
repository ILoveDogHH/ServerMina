package db;

import java.util.List;

public interface DaoBase{

    /**
     * 插入
     * @param sql
     * @param args
     * @return
     */
    int insert(String sql, Object... args);

    /**
     * 删除
     * @param sql
     * @param args
     * @return
     */
    int update(String sql, Object... args);


    /**
     * 查询多个
     * @param sql
     * @param args
     * @return
     */
    List<DaoData> fetch_rows(String sql, Object... args);


    /**
     * 查询多个
     * @param sql
     * @param args
     * @return
     */
    DaoData fetch_one(String sql, Object... args);


}