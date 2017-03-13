package dao;

import entity.Students;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO implements CrudDAO{

    @Override
    public void create(Object o) {
        Students student = (Students)o;
        String query = "INSERT INTO students VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = SqlDAOFactory.createConnection();
            PreparedStatement ps = connection.prepareStatement(query)){
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

    @Override
    public Students read(int id) {
        String query = "SELECT * FROM students WHERE id=?";

        try (Connection connection = SqlDAOFactory.createConnection();
             PreparedStatement ps = connection.prepareStatement(query)){

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return new Students(rs.getInt("id"),
                        rs.getString("firstName"), rs.getString("midName"),
                        rs.getString("lastName"), rs.getInt("age"), rs.getString("phone"),
                        rs.getString("email"), rs.getInt("classId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Object o, int id) {
        Students student = (Students) o;
        String query = "UPDATE students SET id=?, firstName=?, midName=?," +
                "lastName=?, age=?, phone=?, email=?, classId=? WHERE id=?";
        try (Connection connection = SqlDAOFactory.createConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, student.getId());
            ps.setString(2,student.getFirstName());
            ps.setString(3, student.getMidName());
            ps.setString(4, student.getLastName());
            ps.setInt(5, student.getAge());
            ps.setString(6, student.getPhone());
            ps.setString(7, student.getEmail());
            ps.setInt(8, student.getClassId());

            ps.setInt(9, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM students WHERE id=?";
        try (Connection connection = SqlDAOFactory.createConnection();
             PreparedStatement ps = connection.prepareStatement(query)){

            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Students> getAll() {
        String query = "SELECT * FROM students";
        List<Students> studentsList = new ArrayList<>();

        try (Connection connection = SqlDAOFactory.createConnection();
             PreparedStatement ps = connection.prepareStatement(query)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                studentsList.add(new Students(rs.getInt("id"),
                        rs.getString("firstName"), rs.getString("midName"),
                        rs.getString("lastName"), rs.getInt("age"), rs.getString("phone"),
                        rs.getString("email"), rs.getInt("classId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsList;
    }
}
