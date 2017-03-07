package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class ClassDAO implements ConnectionDAO,DAOFactory{

    @Override
    public Connection getConnection() {

        Properties properties = new Properties();
        try (FileReader fr = new FileReader("db.properties")){
            Class.forName("com.mysql.jdbc.Driver");
            properties.load(fr);
            return DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.user"), properties.getProperty("db.password"));
        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Object create() {
        return null;
    }

    @Override
    public Object read() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public Map getAll() {
        return null;
    }

    @Override
    public void closeConnection(Connection connection) {
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
