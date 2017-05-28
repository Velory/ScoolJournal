package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static sirotkina.sjournal.utils.DatabaseUtils.classDAO;
import static sirotkina.sjournal.utils.DatabaseUtils.kursDAO;
import static sirotkina.sjournal.utils.DatabaseUtils.roleDAO;

public class UsersDAO extends AbstractDAO<Users>{
    @Override
    protected String getTableName() {
        return "users";
    }

    @Override
    protected Users createEntityFromRS(ResultSet rs) throws SQLException {
        return new Users(rs.getInt("id"),
                rs.getString("firstName"),
                rs.getString("midName"),
                rs.getString("lastName"),
                rs.getInt("age"),
                rs.getString("phone"),
                rs.getString("email"),
                classDAO().getById(rs.getInt("classId")),
                kursDAO().getById(rs.getInt("kursId")),
                rs.getString("password"),
                roleDAO().getById(rs.getInt("roleId")));
    }

    @Override
    protected String getSaveQuery() {
        return "INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE users SET firstName=?, midName=?, lastName=?, age=?, phone=?," +
                " email=?, classId=?, kursId=?, password=?, roleId=? WHERE id=?";
    }

    @Override
    protected void prepareSaveInsertQuery(PreparedStatement ps, Users user) throws SQLException {
        ps.setInt(1, user.getId());
        ps.setString(2, user.getFirstName());
        ps.setString(3, user.getMidName());
        ps.setString(4, user.getLastName());
        ps.setInt(5, user.getAge());
        ps.setString(6, user.getPhone());
        ps.setString(7, user.getEmail());
        ps.setInt(8, user.getClassFKId().getId());
        ps.setInt(9, user.getKursFKId().getId());
        ps.setString(10, user.getPassword());
        ps.setInt(11, user.getRoleId().getId());
    }

    @Override
    protected void prepareUpdateInsertQuery(PreparedStatement ps, Users user) throws SQLException {
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getMidName());
        ps.setString(3, user.getLastName());
        ps.setInt(4, user.getAge());
        ps.setString(5, user.getPhone());
        ps.setString(6, user.getEmail());
        ps.setInt(7, user.getClassFKId().getId());
        ps.setInt(8, user.getKursFKId().getId());
        ps.setString(9, user.getPassword());
        ps.setInt(10, user.getRoleId().getId());
        ps.setInt(11, user.getId());
    }

    public List<Users> getAllByRole(int roleId) {
        String query = "SELECT * FROM " + getTableName() + " WHERE roleId=?";
        List<Users> usersList = new LinkedList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, roleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                usersList.add(createEntityFromRS(rs));
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
        return usersList;
    }

    /*public Users getByRole(int roleId, int id) {
        String query = "SELECT * FROM " + getTableName() + " WHERE (roleId=?, id=?)";
        ResultSet rs = null;
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, roleId);
            ps.setInt(2, id);
            rs = ps.executeQuery();
            while (rs.next()) {
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
    }*/

}