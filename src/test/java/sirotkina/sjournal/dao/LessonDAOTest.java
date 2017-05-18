package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Kurs;
import sirotkina.sjournal.entity.Lesson;
import sirotkina.sjournal.entity.Teachers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.*;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class LessonDAOTest {

    @Before
    public void setUp() throws Exception {
        migrate();
        classDAO().save(new Class(1, 1, "A"));
        classDAO().save(new Class(null, 1, "B"));
        kursDAO().save(new Kurs(null, "math"));
        teachersDAO().save(new Teachers(1, "Tatyana", "Ivanovna", "Smirnova",
                "13141", "tatyana@mail.me", kursDAO().getById(1), classDAO().getById(1), "shgb"));
        lessonDAO().save(new Lesson(1, Date.valueOf("2017-03-16"), "11:00:00",
                "hometask", classDAO().getById(1), teachersDAO().getById(1), kursDAO().getById(1)));
        lessonDAO().save(new Lesson(null, Date.valueOf("2017-03-10"), "15:30:00",
                "hometask1", classDAO().getById(2), teachersDAO().getById(1), kursDAO().getById(1)));
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
        Lesson lesson = lessonDAO().getById(1);
        assertNotNull(lesson);
        assertEquals(Date.valueOf("2017-03-16"), lesson.getDate());
    }

    @Test
    public void getById() throws Exception {
        Lesson lesson = lessonDAO().getById(1);
        assertNotNull(lesson);
        assertEquals(Date.valueOf("2017-03-16"), lesson.getDate());
        assertEquals("11:00:00", lesson.getTime());
    }

    @Test
    public void update() throws Exception {
        lessonDAO().update(new Lesson(1, Date.valueOf("2017-03-10"), "15:30:00",
                "hometask1", classDAO().getById(2), teachersDAO().getById(1), kursDAO().getById(1)));
        Lesson lesson = lessonDAO().getById(1);
        assertNotNull(lesson);
        assertEquals(Date.valueOf("2017-03-10"), lesson.getDate());
        assertEquals("15:30:00", lesson.getTime());
        assertEquals("hometask1", lesson.getHomeTask());
        assertEquals(classDAO().getById(2), lesson.getClassFKId());
    }

    @Test
    public void deleteById() throws Exception {
        lessonDAO().deleteById(1);
        Lesson lesson = lessonDAO().getById(1);
        Lesson lesson1 = lessonDAO().getById(2);
        assertNull(lesson);
        assertNotNull(lesson1);
    }

    @Test
    public void getAll() throws Exception {
        List<Lesson> lessonList = lessonDAO().getAll();
        assertEquals("hometask", lessonList.get(0).getHomeTask());
        assertEquals(Date.valueOf("2017-03-10"), lessonList.get(1).getDate());
    }

}