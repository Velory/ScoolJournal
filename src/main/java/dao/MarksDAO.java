package dao;

import entity.Marks;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class MarksDAO {

    public void create(Marks mark) {
        String query = "INSERT INTO marks VALUES(?, ?, ?, ?)";
        Properties properties = new Properties();
        ClassLoader cl = MarksDAO.class.getClassLoader();
        try {
            properties.load(cl.getResourceAsStream("db.properties"));
            java.lang.Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"),
                properties.getProperty("db.user"), properties.getProperty("db.password"));
             PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, mark.getMark());
            ps.setString(2, mark.getComment());
            ps.setInt(3, mark.getLessonId());
            ps.setInt(4,mark.getStudentsId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Marks readById(int id) {
        String query = "SELECT * FROM marks WHERE studentsId=?";
        Properties properties = new Properties();
        ClassLoader cl = MarksDAO.class.getClassLoader();
        try {
            properties.load(cl.getResourceAsStream("db.properties"));
            java.lang.Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"),
                properties.getProperty("db.user"), properties.getProperty("db.password"));
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return new Marks(rs.getInt("mark"),
                        rs.getString("comment"),
                        rs.getInt("lessonId"),
                        rs.getInt("studentsId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(Marks mark, int id) {
        String query = "UPDATE marks SET mark=?, comment=?, lessonId=?, studentsId=? WHERE studentsId=?";
        Properties properties = new Properties();
        ClassLoader cl = MarksDAO.class.getClassLoader();
        try {
            properties.load(cl.getResourceAsStream("db.properties"));
            java.lang.Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"),
                properties.getProperty("db.user"), properties.getProperty("db.password"));
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, mark.getMark());
            ps.setString(2, mark.getComment());
            ps.setInt(3, mark.getLessonId());
            ps.setInt(4, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteById(int id) {
        String query = "DELETE FROM marks WHERE studentsId=?";
        Properties properties = new Properties();
        ClassLoader cl = MarksDAO.class.getClassLoader();
        try {
            properties.load(cl.getResourceAsStream("db.properties"));
            java.lang.Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"),
                properties.getProperty("db.user"), properties.getProperty("db.password"));
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Marks> getAll() {
        String query = "SELECT * FROM marks";
        List<Marks> marksList = new LinkedList<>();
        Properties properties = new Properties();
        ClassLoader cl = MarksDAO.class.getClassLoader();
        try {
            properties.load(cl.getResourceAsStream("db.properties"));
            java.lang.Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"),
                properties.getProperty("db.user"), properties.getProperty("db.password"));
             PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                marksList.add(new Marks(rs.getInt("mark"),
                        rs.getString("comment"),
                        rs.getInt("lessonId"),
                        rs.getInt("studentsId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marksList;
    }
}
