package sirotkina.sjournal.utils;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtils {
    private static Connection connection;


    /*public DatabaseUtils() {
        Properties properties = new Properties();
        Flyway flyway = new Flyway();
        try (InputStream is = DatabaseUtils.class.getClassLoader().getResourceAsStream("db.properties")){
            properties.load(is);
            flyway.setDataSource(properties.getProperty("db.url"),
                    properties.getProperty("db.user"), properties.getProperty("db.password"));
            flyway.migrate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Migration failed. cause: " + e.getMessage());
            System.out.println("db.url = " + properties.getProperty("db.url") +
                    "\n db.user = " + properties.getProperty("db.user") +
                    "\n db.password = " + properties.getProperty("db.password"));
        }
    }*/

    public static Connection getConnection() {
        Properties properties = new Properties();
        if (connection == null){
            try (InputStream is = DatabaseUtils.class.getClassLoader().getResourceAsStream("db.properties")){
                properties.load(is);
                connection = DriverManager.getConnection(properties.getProperty("db.url"),
                        properties.getProperty("db.user"), properties.getProperty("db.password"));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection(){
        if (connection != null){
            ShutdownHook shutdownHook = new ShutdownHook();
            Runtime.getRuntime().addShutdownHook(shutdownHook);
        }
    }

    public static void migrate (){
        Flyway flyway = new Flyway();
        Properties properties = new Properties();
        try (InputStream is = DatabaseUtils.class.getClassLoader().getResourceAsStream("db.properties")){
            properties.load(is);
            flyway.setDataSource(properties.getProperty("db.url"),
                    properties.getProperty("db.user"), properties.getProperty("db.password"));
            flyway.migrate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Migration failed. cause:" + e.getMessage());
            System.out.println("db.url = " + properties.getProperty("db.url") +
                    "\n db.user = " + properties.getProperty("db.user") +
                    "\n db.password = " + properties.getProperty("db.password"));
        }
    }

    static class ShutdownHook extends Thread{
        @Override
        public void run() {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
