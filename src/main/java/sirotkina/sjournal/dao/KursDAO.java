package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Kurs;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class KursDAO {

    public void create(Kurs kurs) {
        String query = "INSERT INTO kurs VALUES(?, ?)";
        Properties properties = new Properties();
        ClassLoader cl = KursDAO.class.getClassLoader();
        try {
            properties.load(cl.getResourceAsStream("db.properties"));
            java.lang.Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"),
                properties.getProperty("db.user"), properties.getProperty("db.password"));
             PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, kurs.getId());
            ps.setString(2, kurs.getTitle());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Kurs readById(int id) {
        String query = "SELECT * FROM kurs WHERE id=?";
        Properties properties = new Properties();
        ClassLoader cl = KursDAO.class.getClassLoader();
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
                return new Kurs(rs.getInt("id"),
                         rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(Kurs kurs, int id) {
        String query = "UPDATE kurs SET id=?, title=? WHERE id=?";
        Properties properties = new Properties();
        ClassLoader cl = KursDAO.class.getClassLoader();
        try {
            properties.load(cl.getResourceAsStream("db.properties"));
            java.lang.Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(properties.getProperty("db.url"),
                properties.getProperty("db.user"), properties.getProperty("db.password"));
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,kurs.getId());
            ps.setString(2,kurs.getTitle());
            ps.setInt(4,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteById(int id) {
        String query = "DELETE FROM class WHERE id=?";
        Properties properties = new Properties();
        ClassLoader cl = KursDAO.class.getClassLoader();
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


    public List<Kurs> getAll() {
        String query = "SELECT * FROM kurs";
        List<Kurs> kursList = new LinkedList<>();
        Properties properties = new Properties();
        ClassLoader cl = KursDAO.class.getClassLoader();
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
                kursList.add(new Kurs(rs.getInt("id"),
                         rs.getString("title")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kursList;
    }
}
