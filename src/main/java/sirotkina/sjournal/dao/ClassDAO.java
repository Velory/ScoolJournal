package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.utils.DatabaseUtils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClassDAO {

    public void save(Class cl) {
        String query = "INSERT INTO class VALUES(?, ?, ?)";
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)){
            ps.setInt(1, cl.getId());
            ps.setInt(2,cl.getNum());
            ps.setString(3, cl.getLetter());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Class getById(int id) {
        String query = "SELECT * FROM class WHERE id=?";
        ResultSet rs = null;
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                return new Class(rs.getInt("id"),
                        rs.getInt("num"), rs.getString("letter"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void update(Class cl) {
        String query = "UPDATE class SET num=?, letter=? WHERE id=?";
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)){
            ps.setInt(1,cl.getNum());
            ps.setString(2,cl.getLetter());
            ps.setInt(3,cl.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM class WHERE id=?";
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Class> getAll() {
        String query = "SELECT * FROM class";
        List<Class> classList = new LinkedList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)){
            rs = ps.executeQuery();
            while (rs.next()){
                classList.add(new Class(rs.getInt("id"),
                        rs.getInt("num"), rs.getString("letter")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return classList;
    }
}
