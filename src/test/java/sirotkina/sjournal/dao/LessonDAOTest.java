package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Kurs;
import sirotkina.sjournal.entity.Lesson;
import sirotkina.sjournal.entity.Teachers;
import sirotkina.sjournal.utils.DatabaseUtils;

import javax.sql.DataSource;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;

public class LessonDAOTest {
    private LessonDAO lessonDAO;
    private DataSource dataSource;
    private Connection connection;
    private ClassDAO classDAO;
    private TeachersDAO teachersDAO;
    private KursDAO kursDAO;

    @Before
    public void setUp() throws Exception {
        //DatabaseUtils.migrate();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scooldb1?createDatabaseIfNotExist=true",
                "root","marishach");
        dataSource = Mockito.mock(DataSource.class);
        Mockito.when(dataSource.getConnection()).thenReturn(connection);

        lessonDAO = new LessonDAO(dataSource);
        classDAO = new ClassDAO();
        teachersDAO = new TeachersDAO(dataSource);
        kursDAO = new KursDAO(dataSource);

        classDAO.save(new Class(1,1,"A"));
        classDAO.save(new Class(2,1,"B"));
        kursDAO.save(new Kurs(1, "math"));
        teachersDAO.save(new Teachers(1, "Tatyana", "Ivanovna", "Smirnova",
                "13141", "tatyana@mail.me", 1, 1));
        lessonDAO.save(new Lesson(1, Date.valueOf("2017-03-16"), Time.valueOf("11:00:00"),
                "hometask", 1,1,1));
        lessonDAO.save(new Lesson(2, Date.valueOf("2017-03-10"), Time.valueOf("15:30:00"),
                "hometask1", 2,1,1));
    }

    @After
    public void tearDown() throws Exception {
        PreparedStatement ps = connection.prepareStatement("DROP DATABASE `scooldb1`");
        ps.executeUpdate();
        ps.close();
        connection.close();
    }

    @Test
    public void save() throws Exception {
        Lesson lesson = lessonDAO.getById(1);
        assertNotNull(lesson);
        assertEquals(Date.valueOf("2017-03-16"), lesson.getDate());
    }

    @Test
    public void getById() throws Exception {
        Lesson lesson = lessonDAO.getById(1);
        assertNotNull(lesson);
        assertEquals(Date.valueOf("2017-03-16"), lesson.getDate());
        assertEquals(Time.valueOf("11:00:00"), lesson.getTime());
    }

    @Test
    public void update() throws Exception {
        lessonDAO.update(new Lesson(1, Date.valueOf("2017-03-10"), Time.valueOf("15:30:00"),
                "hometask1", 2,1,1));
        Lesson lesson = lessonDAO.getById(1);
        assertNotNull(lesson);
        assertEquals(Date.valueOf("2017-03-10"), lesson.getDate());
        assertEquals(Time.valueOf("15:30:00"), lesson.getTime());
        assertEquals("hometask1", lesson.getHomeTask());
        assertEquals(2, lesson.getClassId());
    }

    @Test
    public void deleteById() throws Exception {
        lessonDAO.deleteById(1);
        Lesson lesson = lessonDAO.getById(1);
        Lesson lesson1 = lessonDAO.getById(2);
        assertNull(lesson);
        assertNotNull(lesson1);
    }

    @Test
    public void getAll() throws Exception {
        List<Lesson> lessonList = lessonDAO.getAll();
        assertEquals("hometask", lessonList.get(0).getHomeTask());
        assertEquals(Date.valueOf("2017-03-10"), lessonList.get(1).getDate());
    }

}