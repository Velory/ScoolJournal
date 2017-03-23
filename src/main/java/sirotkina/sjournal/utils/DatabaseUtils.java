package sirotkina.sjournal.utils;

import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtils {
    private static Connection connection;
    private static Properties dbProps = PropertiesUtils.readProperties();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(DatabaseUtils::closeConnection));
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()){
                 return connection = DriverManager.getConnection(dbProps.getProperty("db.url"),
                            dbProps.getProperty("db.user"), dbProps.getProperty("db.password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
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
            flyway.setDataSource(dbProps.getProperty("db.url"),
                    dbProps.getProperty("db.user"), dbProps.getProperty("db.password"));
            flyway.migrate();
    }

}

