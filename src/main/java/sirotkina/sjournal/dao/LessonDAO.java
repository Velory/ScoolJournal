package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Lesson;

import java.sql.*;

public class LessonDAO extends AbstractDAO <Lesson>{

    @Override
    protected String getTableName() {
        return "lesson";
    }

    @Override
    protected Lesson createEntityFromRS(ResultSet rs) throws SQLException {
        return new Lesson(rs.getInt("id"), rs.getDate("date"),
                rs.getTime("time"), rs.getString("homeTask"),
                rs.getInt("classId"), rs.getInt("teachersId"),
                rs.getInt("kursId"));
    }

    @Override
    protected String getSaveQuery() {
        return "INSERT INTO lesson VALUES(?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE lesson SET date=?, time=?," +
                "homeTask=?, classId=?, teachersId=?, kursId=? WHERE id=?";
    }

    @Override
    protected void prepareSaveInsertQuery(PreparedStatement ps, Lesson lesson) throws SQLException {
        ps.setInt(1, lesson.getId());
        ps.setDate(2, lesson.getDate());
        ps.setTime(3, lesson.getTime());
        ps.setString(4, lesson.getHomeTask());
        ps.setInt(5, lesson.getClassId());
        ps.setInt(6, lesson.getTeachersId());
        ps.setInt(7, lesson.getKursId());
    }

    @Override
    protected void prepareUpdateInsertQuery(PreparedStatement ps, Lesson lesson) throws SQLException {
        ps.setDate(1, lesson.getDate());
        ps.setTime(2, lesson.getTime());
        ps.setString(3, lesson.getHomeTask());
        ps.setInt(4, lesson.getClassId());
        ps.setInt(5, lesson.getTeachersId());
        ps.setInt(6, lesson.getKursId());
        ps.setInt(7, lesson.getId());
    }
}
