package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Entity;
import sirotkina.sjournal.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDAO <T extends Entity>{


    public T getById (int id){
        String query = "SELECT * FROM " + getTableName() + " WHERE id=?";
        ResultSet rs = null;
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                return createEntityFromRS(rs);
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

    public void save(T o) {
        checkAndSaveId(o);
        String query = getSaveQuery();
        try (PreparedStatement ps = getConnection().prepareStatement(query)){
            prepareSaveInsertQuery(ps, o);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(T o) {
        String query = getUpdateQuery();
        try (PreparedStatement ps = getConnection().prepareStatement(query)){
            prepareUpdateInsertQuery(ps, o);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM " + getTableName() + " WHERE id=?";
        try (PreparedStatement ps = getConnection().prepareStatement(query)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<T> getAll() {
        String query = "SELECT * FROM " + getTableName();
        List<T> list = new LinkedList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = getConnection().prepareStatement(query)){
            rs = ps.executeQuery();
            while (rs.next()){
                list.add(createEntityFromRS(rs));
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
        return list;
    }

    protected abstract String getTableName();
    protected abstract T createEntityFromRS (ResultSet rs) throws SQLException;
    protected abstract String getSaveQuery();
    protected abstract String getUpdateQuery();
    protected abstract void prepareSaveInsertQuery(PreparedStatement ps, T o) throws SQLException;
    protected abstract void prepareUpdateInsertQuery(PreparedStatement ps, T o) throws SQLException;

    protected Connection getConnection(){
        return DatabaseUtils.getConnection();
    }

    private void checkAndSaveId(T o){
        if (o.getId() == null){
            String query = "SELECT MAX(id) FROM " + getTableName();
            ResultSet rs = null;
            try (PreparedStatement ps = getConnection().prepareStatement(query)){
                rs = ps.executeQuery();
                while (rs.next()){
                    o.setId(rs.getInt("max(id)")+1);
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
        }
    }

}

