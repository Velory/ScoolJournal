package sirotkina.sjournal.utils;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtils {
    private static Connection connection;

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(DatabaseUtils::closeConnection));
    }

    public static Connection getConnection() {
        Properties properties = readProperties();
        try {
            if (connection == null || connection.isClosed()){
                 return connection = DriverManager.getConnection(properties.getProperty("db.url"),
                            properties.getProperty("db.user"), properties.getProperty("db.password"));
            }
            return connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.user"), properties.getProperty("db.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void migrate (){
        Flyway flyway = new Flyway();
        Properties properties = readProperties();
            flyway.setDataSource(properties.getProperty("db.url"),
                    properties.getProperty("db.user"), properties.getProperty("db.password"));
            flyway.migrate();
    }


    public static Properties readProperties (){
        Properties properties = new Properties();
        try (InputStream is = DatabaseUtils.class.getClassLoader().getResourceAsStream("db.properties")){
            properties.load(is);
    } catch (IOException e) {
            e.printStackTrace();
        }
    return properties;
    }
}

