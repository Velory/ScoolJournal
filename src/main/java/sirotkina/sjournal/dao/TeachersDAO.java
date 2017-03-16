package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Teachers;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TeachersDAO extends AbstractDAO {

    public TeachersDAO(DataSource dataSource) {
        super(dataSource);
    }

    public void save(Teachers teacher) {
        String query = "INSERT INTO teachers VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(query)){
            ps.setInt(1, teacher.getId());
            ps.setString(2, teacher.getFirstName());
            ps.setString(3, teacher.getMidName());
            ps.setString(4, teacher.getLastName());
            ps.setString(5, teacher.getPhone());
            ps.setString(6, teacher.getEmail());
            ps.setInt(7, teacher.getKursId());
            ps.setInt(8, teacher.getClassId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Teachers getById(int id) {
        String query = "SELECT * FROM teachers WHERE id=?";
        ResultSet rs = null;
        try (PreparedStatement ps = getConnection().prepareStatement(query)){
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                return new Teachers(rs.getInt("id"), rs.getString("firstName"),
                        rs.getString("midName"), rs.getString("lastName"),
                        rs.getString("phone"), rs.getString("email"),
                        rs.getInt("kursId"), rs.getInt("classId"));
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

    public void update(Teachers teacher) {
        String query = "UPDATE teachers SET firstName=?, midName=?," +
                "lastName=?, phone=?, email=?, kursId=?, classId=? WHERE id=?";
        try (PreparedStatement ps = getConnection().prepareStatement(query)){
            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getMidName());
            ps.setString(3, teacher.getLastName());
            ps.setString(4, teacher.getPhone());
            ps.setString(5, teacher.getEmail());
            ps.setInt(6, teacher.getKursId());
            ps.setInt(7, teacher.getClassId());
            ps.setInt(8, teacher.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM teachers WHERE id=?";
        try (PreparedStatement ps = getConnection().prepareStatement(query)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Teachers> getAll() {
        String query = "SELECT * FROM teachers";
        List<Teachers> teachersList = new LinkedList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = getConnection().prepareStatement(query)){
            rs = ps.executeQuery();
            while (rs.next()){
                teachersList.add(new Teachers(rs.getInt("id"), rs.getString("firstName"),
                        rs.getString("midName"), rs.getString("lastName"),
                        rs.getString("phone"), rs.getString("email"),
                        rs.getInt("kursId"), rs.getInt("classId")));
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
        return teachersList;
    }
}
