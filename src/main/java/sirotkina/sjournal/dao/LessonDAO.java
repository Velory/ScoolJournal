package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Lesson;
import sirotkina.sjournal.utils.DatabaseUtils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class LessonDAO extends AbstractDAO {

        public void save(Lesson lesson) {
        String query = "INSERT INTO lesson VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)){
            ps.setInt(1, lesson.getId());
            ps.setDate(2, lesson.getDate());
            ps.setTime(3, lesson.getTime());
            ps.setString(4, lesson.getHomeTask());
            ps.setInt(5, lesson.getClassId());
            ps.setInt(6, lesson.getTeachersId());
            ps.setInt(7, lesson.getKursId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Lesson getById(int id) {
        String query = "SELECT * FROM lesson WHERE id=?";
        ResultSet rs = null;
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)){
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                return new Lesson(rs.getInt("id"), rs.getDate("date"),
                        rs.getTime("time"), rs.getString("homeTask"),
                        rs.getInt("classId"), rs.getInt("teachersId"),
                        rs.getInt("kursId"));
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

    public void update(Lesson lesson) {
        String query = "UPDATE lesson SET date=?, time=?," +
                "homeTask=?, classId=?, teachersId=?, kursId=? WHERE id=?";
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)){
            ps.setDate(1, lesson.getDate());
            ps.setTime(2, lesson.getTime());
            ps.setString(3, lesson.getHomeTask());
            ps.setInt(4, lesson.getClassId());
            ps.setInt(5, lesson.getTeachersId());
            ps.setInt(6, lesson.getKursId());
            ps.setInt(7, lesson.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM lesson WHERE id=?";
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Lesson> getAll() {
        String query = "SELECT * FROM lesson";
        List<Lesson> lessonList = new LinkedList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement(query)){
            rs = ps.executeQuery();
            while (rs.next()){
                lessonList.add(new Lesson(rs.getInt("id"), rs.getDate("date"),
                        rs.getTime("time"), rs.getString("homeTask"),
                        rs.getInt("classId"), rs.getInt("teachersId"),
                        rs.getInt("kursId")));
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
        return lessonList;
    }
}
