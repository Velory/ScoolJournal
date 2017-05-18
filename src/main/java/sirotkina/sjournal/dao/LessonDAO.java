package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Lesson;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class LessonDAO extends AbstractDAO<Lesson> {

    @Override
    protected String getTableName() {
        return "lesson";
    }

    @Override
    protected Lesson createEntityFromRS(ResultSet rs) throws SQLException {
        return new Lesson(rs.getInt("id"), rs.getDate("date"),
                rs.getString("time"), rs.getString("homeTask"),
                classDAO().getById(rs.getInt("classId")),
                teachersDAO().getById(rs.getInt("teachersId")),
                kursDAO().getById(rs.getInt("kursId")));
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
        ps.setString(3, lesson.getTime());
        ps.setString(4, lesson.getHomeTask());
        ps.setInt(5, lesson.getClassFKId().getId());
        ps.setInt(6, lesson.getTeachersFKId().getId());
        ps.setInt(7, lesson.getKursFKId().getId());
    }

    @Override
    protected void prepareUpdateInsertQuery(PreparedStatement ps, Lesson lesson) throws SQLException {
        ps.setDate(1, lesson.getDate());
        ps.setString(2, lesson.getTime());
        ps.setString(3, lesson.getHomeTask());
        ps.setInt(4, lesson.getClassFKId().getId());
        ps.setInt(5, lesson.getTeachersFKId().getId());
        ps.setInt(6, lesson.getKursFKId().getId());
        ps.setInt(7, lesson.getId());
    }
}
