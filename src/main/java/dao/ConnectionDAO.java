package dao;

import java.sql.Connection;

public interface ConnectionDAO {
    Connection getConnection();
    void closeConnection(Connection connection);
}
