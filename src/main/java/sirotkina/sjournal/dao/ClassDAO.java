package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Class;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClassDAO implements CrudDAO{

    @Override
    public void create(Object o) {
        Class cl = (Class) o;
        String query = "INSERT INTO class VALUES(?, ?, ?)";
        try (Connection connection = SqlDAOFactory.createConnection();
             PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, cl.getId());
            ps.setInt(2,cl.getNum());
            ps.setString(3, cl.getLetter());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class read(int id) {
        String query = "SELECT * FROM class WHERE id=?";
        try (Connection connection = SqlDAOFactory.createConnection();
            PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return new Class(rs.getInt("id"),
                        rs.getInt("num"), rs.getString("letter"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Object o, int id) {
        Class cl = (Class) o;
        String query = "UPDATE class SET id=?, num=?, letter=? WHERE id=?";
        try (Connection connection = SqlDAOFactory.createConnection();
             PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1,cl.getId());
            ps.setInt(2,cl.getNum());
            ps.setString(3,cl.getLetter());
            ps.setInt(4,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM class WHERE id=?";
        try (Connection connection = SqlDAOFactory.createConnection();
             PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Class> getAll() {
        String query = "SELECT * FROM class";
        List<Class> classes = new LinkedList<>();

        try (Connection connection = SqlDAOFactory.createConnection();
             PreparedStatement ps = connection.prepareStatement(query)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                classes.add(new Class(rs.getInt("id"),
                        rs.getInt("num"), rs.getString("letter")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

}
