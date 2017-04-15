package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Teachers;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeachersDAO extends AbstractDAO <Teachers>{

    @Override
    protected String getTableName() {
        return "teachers";
    }

    @Override
    protected Teachers createEntityFromRS(ResultSet rs) throws SQLException {
        return new Teachers(rs.getInt("id"), rs.getString("firstName"),
                rs.getString("midName"), rs.getString("lastName"),
                rs.getString("phone"), rs.getString("email"),
                kursDAO().getById(rs.getInt("kursId")),
                classDAO().getById(rs.getInt("classId")));
    }

    @Override
    protected String getSaveQuery() {
        return "INSERT INTO teachers VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE teachers SET firstName=?, midName=?," +
                "lastName=?, phone=?, email=?, kursId=?, classId=? WHERE id=?";
    }

    @Override
    protected void prepareSaveInsertQuery(PreparedStatement ps, Teachers teacher) throws SQLException {
        ps.setInt(1, teacher.getId());
        ps.setString(2, teacher.getFirstName());
        ps.setString(3, teacher.getMidName());
        ps.setString(4, teacher.getLastName());
        ps.setString(5, teacher.getPhone());
        ps.setString(6, teacher.getEmail());
        ps.setInt(7, teacher.getKursFKId().getId());
        ps.setInt(8, teacher.getClassFKId().getId());
    }

    @Override
    protected void prepareUpdateInsertQuery(PreparedStatement ps, Teachers teacher) throws SQLException {
        ps.setString(1, teacher.getFirstName());
        ps.setString(2, teacher.getMidName());
        ps.setString(3, teacher.getLastName());
        ps.setString(4, teacher.getPhone());
        ps.setString(5, teacher.getEmail());
        ps.setInt(6, teacher.getKursFKId().getId());
        ps.setInt(7, teacher.getClassFKId().getId());
        ps.setInt(8, teacher.getId());
    }
}
