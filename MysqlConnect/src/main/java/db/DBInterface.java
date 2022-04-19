package db;

import java.sql.SQLException;
import java.util.List;

public interface DBInterface<T>{


    int update(String sql, Object... args) throws SQLException;

    <T> List<T> getListData(Class<T> clazz, String sql, Object... args) throws SQLException;

    <T> T getData(Class<T> clazz, String sql, Object... args) throws SQLException;
}