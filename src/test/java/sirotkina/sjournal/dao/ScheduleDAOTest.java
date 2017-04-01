package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sirotkina.sjournal.entity.Schedule;
import sirotkina.sjournal.utils.DatabaseUtils;


import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.List;

import static org.junit.Assert.*;

public class ScheduleDAOTest {
    private ScheduleDAO scheduleDAO;

    @Before
    public void setUp() throws Exception {
        DatabaseUtils.migrate();
        scheduleDAO = new ScheduleDAO();
        scheduleDAO.save(new Schedule("monday", "1A", 1, Time.valueOf("10:00:00"),
                "math", "Ivanov"));
        scheduleDAO.save(new Schedule("monday", "2B", 2, Time.valueOf("09:30:00"),
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
    public void save() throws Exception {
        Schedule schedule = scheduleDAO.getById(1);
        assertNotNull(schedule);
        assertEquals("monday", schedule.getWeekDay());
        assertEquals("math", schedule.getNameOfKurs());
    }

    @Test
    public void getById() throws Exception {
        Schedule schedule = scheduleDAO.getById(2);
        assertEquals("2B", schedule.getScoolClass());
        assertEquals("Petrov", schedule.getTeacherOfLesson());
    }

    @Test
    public void update() throws Exception {
        scheduleDAO.update(new Schedule("tuesday", "2B", 1, Time.valueOf("11:00:00"),
                "math", "Ivanov"));
        Schedule schedule = scheduleDAO.getById(1);
        assertEquals("tuesday", schedule.getWeekDay());
        assertEquals("2B", schedule.getScoolClass());
    }

    @Test
    public void deleteById() throws Exception {
        scheduleDAO.deleteById(1);
        Schedule schedule = scheduleDAO.getById(1);
        assertNull(schedule);
    }

    @Test
    public void getAll() throws Exception {
        List<Schedule> scheduleList = scheduleDAO.getAll();
        assertNotNull(scheduleList);
        //assertEquals((Integer) 1, scheduleList.get(0).getId());
        assertEquals(Time.valueOf("10:00:00"), scheduleList.get(0).getLessonTime());
        assertEquals("2B", scheduleList.get(1).getScoolClass());
    }

}