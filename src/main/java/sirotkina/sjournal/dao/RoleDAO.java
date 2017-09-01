package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO extends AbstractDAO<Role>{
    @Override
    protected String getTableName() {
        return "role";
    }

    @Override
    protected Role createEntityFromRS(ResultSet rs) throws SQLException {
        return new Role(rs.getInt("id"), rs.getString("role"));
    }

    @Override
    protected String getSaveQuery() {
        return "INSERT INTO role VALUES(?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE role SET role=? WHERE id=?";
    }

    @Override
    protected void prepareSaveInsertQuery(PreparedStatement ps, Role role) throws SQLException {
        ps.setInt(1, role.getId());
        ps.setString(2, role.getRole());
    }

    @Override
    protected void prepareUpdateInsertQuery(PreparedStatement ps, Role role) throws SQLException {
        ps.setString(1, role.getRole());
        ps.setInt(2, role.getId());
    }
}
