package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Kurs;
import sirotkina.sjournal.utils.DatabaseUtils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class KursDAO extends AbstractDAO{

    public void save(Kurs kurs) {
        String query = "INSERT INTO kurs VALUES(?, ?)";
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)){
            ps.setInt(1, kurs.getId());
            ps.setString(2, kurs.getTitle());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Kurs getById(int id) {
        String query = "SELECT * FROM kurs WHERE id=?";
        ResultSet rs = null;
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                return new Kurs(rs.getInt("id"),
                         rs.getString("title"));
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

    public void update(Kurs kurs) {
        String query = "UPDATE kurs SET title=? WHERE id=?";
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)) {
            ps.setString(1, kurs.getTitle());
            ps.setInt(2, kurs.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM kurs WHERE id=?";
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Kurs> getAll() {
        String query = "SELECT * FROM kurs";
        List<Kurs> kursList = new LinkedList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()){
                kursList.add(new Kurs(rs.getInt("id"),
                         rs.getString("title")));
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
        return kursList;
    }
}
