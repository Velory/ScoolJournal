package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Marks;
import sirotkina.sjournal.utils.DatabaseUtils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MarksDAO extends AbstractDAO{

    public void save(Marks mark) {
        String query = "INSERT INTO marks (id, mark, comment, lessonId, studentsId) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)){
            ps.setInt(1,mark.getId());
            ps.setInt(2, mark.getMark());
            ps.setString(3, mark.getComment());
            ps.setInt(4, mark.getLessonId());
            ps.setInt(5,mark.getStudentsId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Marks getById(int id) {
        String query = "SELECT * FROM marks WHERE id=?";
        ResultSet rs = null;
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                return new Marks(rs.getInt("id"), rs.getInt("mark"),
                        rs.getString("comment"),
                        rs.getInt("lessonId"),
                        rs.getInt("studentsId"));
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

    public void update(Marks mark) {
        String query = "UPDATE marks SET mark=?, comment=?, lessonId=?, studentsId=? WHERE id=?";
        try(PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)){
            ps.setInt(1, mark.getMark());
            ps.setString(2, mark.getComment());
            ps.setInt(3, mark.getLessonId());
            ps.setInt(4, mark.getStudentsId());
            ps.setInt(5, mark.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM marks WHERE id=?";

        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Marks> getAll() {
        String query = "SELECT * FROM marks";
        List<Marks> marksList = new LinkedList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)) {
            rs = ps.executeQuery();
            while (rs.next()){
                marksList.add(new Marks(rs.getInt("id"), rs.getInt("mark"),
                        rs.getString("comment"),
                        rs.getInt("lessonId"),
                        rs.getInt("studentsId")));
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
        return marksList;
    }
}
