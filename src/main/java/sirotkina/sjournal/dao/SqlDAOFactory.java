package sirotkina.sjournal.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqlDAOFactory extends DAOFactory{

    public static Connection createConnection() throws SQLException {
        Properties properties = new Properties();

        try (FileReader fr = new FileReader("D:\\Java\\ScoolJournal\\src\\main\\resources\\db.properties")){
            properties.load(fr);
            java.lang.Class.forName(properties.getProperty("db.driver"));

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(properties.getProperty("db.url"),
                properties.getProperty("db.user"), properties.getProperty("db.password"));
    }

    @Override
    public ClassDAO getClassDAO() {
        return new ClassDAO();
    }

    @Override
    public KursDAO getKursDAO() {
        return new KursDAO();
    }

    @Override
    public LessonDAO getLessonDAO() {
        return new LessonDAO();
    }

    @Override
    public MarksDAO getMarksDAO() {
        return new MarksDAO();
    }

    @Override
    public StudentsDAO getStudentsDAO() {
        return new StudentsDAO();
    }

    @Override
    public TeachersDAO getTeachersDAO() {
        return new TeachersDAO();
    }
}
