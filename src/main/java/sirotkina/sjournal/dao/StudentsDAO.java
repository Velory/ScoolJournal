package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Students;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static sirotkina.sjournal.utils.DatabaseUtils.classDAO;

public class StudentsDAO extends AbstractDAO<Students> {

    @Override
    protected String getTableName() {
        return "students";
    }

    @Override
    protected Students createEntityFromRS(ResultSet rs) throws SQLException {
        return new Students(rs.getInt("id"),
                rs.getString("firstName"),
                rs.getString("midName"),
                rs.getString("lastName"),
                rs.getInt("age"),
                rs.getString("phone"),
                rs.getString("email"),
                classDAO().getById(rs.getInt("classId")),
                rs.getString("password"));
    }

    @Override
    protected String getSaveQuery() {
        return "INSERT INTO students VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE students SET firstName=?, midName=?," +
                "lastName=?, age=?, phone=?, email=?, classId=?, password=? WHERE id=?";
    }

    @Override
    protected void prepareSaveInsertQuery(PreparedStatement ps, Students student) throws SQLException {
        ps.setInt(1, student.getId());
        ps.setString(2, student.getFirstName());
        ps.setString(3, student.getMidName());
        ps.setString(4, student.getLastName());
        ps.setInt(5, student.getAge());
        ps.setString(6, student.getPhone());
        ps.setString(7, student.getEmail());
        ps.setInt(8, student.getClassFKId().getId());
        ps.setString(9, student.getPassword());
    }

    @Override
    protected void prepareUpdateInsertQuery(PreparedStatement ps, Students student) throws SQLException {
        ps.setString(1, student.getFirstName());
        ps.setString(2, student.getMidName());
        ps.setString(3, student.getLastName());
        ps.setInt(4, student.getAge());
        ps.setString(5, student.getPhone());
        ps.setString(6, student.getEmail());
        ps.setInt(7, student.getClassFKId().getId());
        ps.setString(8, student.getPassword());
        ps.setInt(9, student.getId());
    }
}
