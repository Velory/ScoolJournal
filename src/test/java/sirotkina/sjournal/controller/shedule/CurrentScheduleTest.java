package sirotkina.sjournal.controller.shedule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sirotkina.sjournal.dao.ScheduleDAO;
import sirotkina.sjournal.entity.Schedule;
import sirotkina.sjournal.utils.DatabaseUtils;

import java.sql.PreparedStatement;
import java.sql.Time;

import static org.junit.Assert.*;


public class CurrentScheduleTest {
    private ScheduleDAO scheduleDAO;

    @Before
    public void setUp() throws Exception {
        DatabaseUtils.migrate();
        scheduleDAO = new ScheduleDAO();
        scheduleDAO.save(new Schedule("monday", "1A", 1, Time.valueOf("10:00:00"),
                "math", "Ivanov"));
        scheduleDAO.save(new Schedule("monday", "2B", 2, Time.valueOf("09:30:00"),
                "reading", "Petrov"));
        scheduleDAO.save(new Schedule("tuesday", "3D", 3, Time.valueOf("09:30:00"),
                "reading", "Petrov"));
        scheduleDAO.save(new Schedule("tuesday", "2B", 4, Time.valueOf("09:30:00"),
                "reading", "Petrov"));
    }

    @After
    public void tearDown() throws Exception {
        PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement("DROP DATABASE `scooldb1`");
        ps.executeUpdate();
        ps.close();
        DatabaseUtils.closeConnection();
    }

    @Test
    public void start() throws Exception {
    }

}