package sirotkina.sjournal.dao;

import sirotkina.sjournal.entity.Schedule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static sirotkina.sjournal.utils.DatabaseUtils.classDAO;
import static sirotkina.sjournal.utils.DatabaseUtils.kursDAO;
import static sirotkina.sjournal.utils.DatabaseUtils.teachersDAO;

public class ScheduleDAO extends AbstractDAO<Schedule> {
    @Override
    protected String getTableName() {
        return "schedule";
    }

    @Override
    protected Schedule createEntityFromRS(ResultSet rs) throws SQLException {
        return new Schedule(rs.getString("weekDay"), classDAO().getById(rs.getInt("scoolClass")),
                rs.getInt("id"), rs.getString("lessonTime"),
                kursDAO().getById(rs.getInt("nameOfKurs")),
                teachersDAO().getById(rs.getInt("teacherOfLesson")));
    }

    @Override
    protected String getSaveQuery() {
        return "INSERT INTO schedule VALUES(?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE schedule SET weekDay=?, scoolClass=?, " +
                "lessonTime=?, nameOfKurs=?, teacherOfLesson=? WHERE id=?";
    }

    @Override
    protected void prepareSaveInsertQuery(PreparedStatement ps, Schedule schedule) throws SQLException {
        ps.setString(1, schedule.getWeekDay());
        ps.setInt(2, schedule.getScoolClass().getId());
        ps.setInt(3, schedule.getId());
        ps.setString(4, schedule.getLessonTime());
        ps.setInt(5, schedule.getNameOfKurs().getId());
        ps.setInt(6, schedule.getTeacherOfLesson().getId());
    }

    @Override
    protected void prepareUpdateInsertQuery(PreparedStatement ps, Schedule schedule) throws SQLException {
        ps.setString(1, schedule.getWeekDay());
        ps.setInt(2, schedule.getScoolClass().getId());
        ps.setString(3, schedule.getLessonTime());
        ps.setInt(4, schedule.getNameOfKurs().getId());
        ps.setInt(5, schedule.getTeacherOfLesson().getId());
        ps.setInt(6, schedule.getId());
    }
}
