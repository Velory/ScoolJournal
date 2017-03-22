package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Kurs;

import java.sql.*;

public class KursDAO extends AbstractDAO <Kurs>{

    @Override
    protected String getTableName() {
        return "kurs";
    }

    @Override
    protected Kurs createEntityFromRS(ResultSet rs) throws SQLException {
        return new Kurs(rs.getInt("id"),
                rs.getString("title"));
    }

    @Override
    protected String getSaveQuery() {
        return "INSERT INTO kurs VALUES(?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE kurs SET title=? WHERE id=?";
    }

    @Override
    protected void prepareSaveInsertQuery(PreparedStatement ps, Kurs kurs) throws SQLException {
        ps.setInt(1, kurs.getId());
        ps.setString(2, kurs.getTitle());
    }

    @Override
    protected void prepareUpdateInsertQuery(PreparedStatement ps, Kurs kurs) throws SQLException {
        ps.setString(1, kurs.getTitle());
        ps.setInt(2, kurs.getId());
    }
}
