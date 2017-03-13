package dao;

import entity.Kurs;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class KursDAO {

    public void create(Kurs kurs) {
        String query = "INSERT INTO kurs VALUES(?, ?)";
        Properties properties = new Properties();
        ClassLoader cl = KursDAO.class.getClassLoader();
        try /*(BufferedInputStream in = new )*/{
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


    public Kurs read(int id) {

        return null;
    }


    public void update(Object object, int id) {

    }


    public void delete(int id) {

    }

    
    public List getAll() {
        return null;
    }
}
