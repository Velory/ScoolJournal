package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Class;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassDAO extends AbstractDAO<Class> {

    @Override
    protected String getTableName() {
        return "class";
    }

    @Override
    protected Class createEntityFromRS(ResultSet rs) throws SQLException {
        return new Class(rs.getInt("id"),
                rs.getInt("num"), rs.getString("letter"));
    }

    @Override
    protected String getSaveQuery() {
        return "INSERT INTO class VALUES(?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE class SET num=?, letter=? WHERE id=?";
    }

    @Override
    protected void prepareSaveInsertQuery(PreparedStatement ps, Class cl) throws SQLException {
        ps.setInt(1, cl.getId());
        ps.setInt(2, cl.getNum());
        ps.setString(3, cl.getLetter());
    }

    @Override
    protected void prepareUpdateInsertQuery(PreparedStatement ps, Class cl) throws SQLException {
        ps.setInt(1, cl.getNum());
        ps.setString(2, cl.getLetter());
        ps.setInt(3, cl.getId());
    }

}
