package sirotkina.sjournal.utils;

import org.flywaydb.core.Flyway;
import sirotkina.sjournal.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtils {
    private static Connection connection;
    private static Properties dbProps = PropertiesUtils.readProperties();
    private static ClassDAO classDAO;
    private static KursDAO kursDAO;
    private static LessonDAO lessonDAO;
    private static MarksDAO marksDAO;
    private static ScheduleDAO scheduleDAO;
    private static RoleDAO roleDAO;
    private static UsersDAO usersDAO;

    static {
        classDAO = new ClassDAO();
        kursDAO = new KursDAO();
        lessonDAO = new LessonDAO();
        marksDAO = new MarksDAO();
        scheduleDAO = new ScheduleDAO();
        roleDAO = new RoleDAO();
        usersDAO = new UsersDAO();
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(DatabaseUtils::closeConnection));
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                return connection = DriverManager.getConnection(dbProps.getProperty("db.url"),
                        dbProps.getProperty("db.user"), dbProps.getProperty("db.password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void migrate() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dbProps.getProperty("db.url"),
                dbProps.getProperty("db.user"), dbProps.getProperty("db.password"));
        //flyway.clean();
        flyway.migrate();
    }

    public static ClassDAO classDAO() {
        return classDAO;
    }

    public static KursDAO kursDAO() {
        return kursDAO;
    }

    public static LessonDAO lessonDAO() {
        return lessonDAO;
    }

    public static MarksDAO marksDAO() {
        return marksDAO;
    }

    public static ScheduleDAO scheduleDAO() {
        return scheduleDAO;
    }

    public static RoleDAO roleDAO() {
        return roleDAO;
    }

    public static UsersDAO usersDAO() {
        return usersDAO;
    }
}

