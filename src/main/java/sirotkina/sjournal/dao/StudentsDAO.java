package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Students;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO extends AbstractDAO{

    public StudentsDAO(DataSource dataSource) {
        super(dataSource);
    }

    public void save(Students student) {
        String query = "INSERT INTO students VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(query)){
            ps.setInt(1, student.getId());
            ps.setString(2,student.getFirstName());
            ps.setString(3, student.getMidName());
            ps.setString(4, student.getLastName());
            ps.setInt(5, student.getAge());
            ps.setString(6, student.getPhone());
            ps.setString(7, student.getEmail());
            ps.setInt(8, student.getClassId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Students getById(int id) {
        String query = "SELECT * FROM students WHERE id=?";
        ResultSet rs = null;
        try (PreparedStatement ps = getConnection().prepareStatement(query)){
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                return new Students(rs.getInt("id"), rs.getString("firstName"),
                        rs.getString("midName"), rs.getString("lastName"),
                        rs.getInt("age"), rs.getString("phone"),
                        rs.getString("email"), rs.getInt("classId"));
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

    public void update(Students student) {
        String query = "UPDATE students SET firstName=?, midName=?," +
                "lastName=?, age=?, phone=?, email=?, classId=? WHERE id=?";
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setString(1,student.getFirstName());
            ps.setString(2, student.getMidName());
            ps.setString(3, student.getLastName());
            ps.setInt(4, student.getAge());
            ps.setString(5, student.getPhone());
            ps.setString(6, student.getEmail());
            ps.setInt(7, student.getClassId());
            ps.setInt(8, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM students WHERE id=?";
        try (PreparedStatement ps = getConnection().prepareStatement(query)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Students> getAll() {
        String query = "SELECT * FROM students";
        List<Students> studentsList = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = getConnection().prepareStatement(query)){
            rs = ps.executeQuery();
            while (rs.next()){
                studentsList.add(new Students(rs.getInt("id"), rs.getString("firstName"),
                        rs.getString("midName"), rs.getString("lastName"),
                        rs.getInt("age"), rs.getString("phone"),
                        rs.getString("email"), rs.getInt("classId")));
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
        return studentsList;
    }
}
