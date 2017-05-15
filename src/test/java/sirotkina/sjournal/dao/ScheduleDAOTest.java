package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sirotkina.sjournal.entity.*;
import sirotkina.sjournal.entity.Class;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.List;

import static org.junit.Assert.*;
import static sirotkina.sjournal.utils.DatabaseUtils.*;
import static sirotkina.sjournal.utils.DatabaseUtils.kursDAO;

/**
 * Created by Марина on 09.05.2017.
 */
public class ScheduleDAOTest {
    @Before
    public void setUp() throws Exception {
        migrate();
        classDAO().save(new Class(1, 1, "A"));
        classDAO().save(new Class(null, 1, "B"));
        kursDAO().save(new Kurs(null, "math"));
        teachersDAO().save(new Teachers(1, "Tatyana", "Ivanovna", "Smirnova",
                "13141", "tatyana@mail.me", kursDAO().getById(1), classDAO().getById(1), "shgb"));
        scheduleDAO().save(new Schedule("Monday", classDAO().getById(1), null,
                "09:30", kursDAO().getById(1), teachersDAO().getById(1)));
        scheduleDAO().save(new Schedule("Tuesday", classDAO().getById(2), null,
                "10:30", kursDAO().getById(1), teachersDAO().getById(1)));
    }

    @After
    public void tearDown() throws Exception {
        PreparedStatement ps = getConnection().prepareStatement("DROP DATABASE `scooldb1`");
        ps.executeUpdate();
        ps.close();
        closeConnection();
    }

    @Test
    public void save() throws Exception {
        Schedule schedule = scheduleDAO().getById(1);
        assertNotNull(schedule);
        assertEquals("Monday", schedule.getWeekDay());
    }

    @Test
    public void getById() throws Exception {
        Schedule schedule = scheduleDAO().getById(1);
        assertNotNull(schedule);
        assertEquals("Monday", schedule.getWeekDay());
        assertEquals("09:30", schedule.getLessonTime());
    }

    @Test
    public void update() throws Exception {
        scheduleDAO().update(new Schedule("Wednesday", classDAO().getById(2), 1,
                "09:30", kursDAO().getById(1), teachersDAO().getById(1)));
        Schedule schedule = scheduleDAO().getById(1);
        assertNotNull(schedule);
        assertEquals("Wednesday", schedule.getWeekDay());
        assertEquals(classDAO().getById(2), schedule.getScoolClass());
    }

    @Test
    public void deleteById() throws Exception {
        scheduleDAO().deleteById(1);
        Schedule schedule = scheduleDAO().getById(1);
        Schedule schedule1 = scheduleDAO().getById(2);
        assertNull(schedule);
        assertNotNull(schedule1);
    }

    @Test
    public void getAll() throws Exception {
        List<Schedule> scheduleList = scheduleDAO().getAll();
        assertEquals("Monday", scheduleList.get(0).getWeekDay());
        assertEquals(classDAO().getById(2), scheduleList.get(1).getScoolClass());
    }

}