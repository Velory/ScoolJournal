package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Schedule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleDAO extends AbstractDAO<Schedule> {
    @Override
    protected String getTableName() {
        return "schedule";
    }

    @Override
    protected Schedule createEntityFromRS(ResultSet rs) throws SQLException {
        return new Schedule(rs.getString("weekDay"), rs.getString("scoolClass"),
                rs.getInt("id"), rs.getTime("lessonTime"),
                rs.getString("nameOfKurs"), rs.getString("teacherOfLesson"));
    }

    @Override
    protected String getSaveQuery() {
        return "INSERT INTO schedule VALUES(?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE schedule SET weekDay=?, scoolClass=?, lessonTime=?, nameOfKurs=?, teacherOfLesson=? WHERE id=?";
    }

    @Override
    protected void prepareSaveInsertQuery(PreparedStatement ps, Schedule o) throws SQLException {
        ps.setString(1, o.getWeekDay());
        ps.setString(2, o.getScoolClass());
        ps.setInt(3, o.getId());
        ps.setTime(4, o.getLessonTime());
        ps.setString(5, o.getNameOfKurs());
        ps.setString(6, o.getTeacherOfLesson());
    }

    @Override
    protected void prepareUpdateInsertQuery(PreparedStatement ps, Schedule o) throws SQLException {
        ps.setString(1, o.getWeekDay());
        ps.setString(2, o.getScoolClass());
        ps.setTime(3, o.getLessonTime());
        ps.setString(4, o.getNameOfKurs());
        ps.setString(5, o.getTeacherOfLesson());
        ps.setInt(6, o.getId());
    }
}
