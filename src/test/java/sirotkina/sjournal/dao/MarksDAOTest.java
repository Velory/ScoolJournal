package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sirotkina.sjournal.entity.*;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.utils.MigrationsUtils;

import javax.sql.DataSource;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;

public class MarksDAOTest {

    private MarksDAO marksDAO;
    private DataSource dataSource;
    private Connection connection;
    private ClassDAO classDAO;
    private TeachersDAO teachersDAO;
    private KursDAO kursDAO;
    private LessonDAO lessonDAO;
    private StudentsDAO studentsDAO;

    @Before
    public void setUp() throws Exception {
        MigrationsUtils.migrate();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scooldb1?createDatabaseIfNotExist=true",
                "root","marishach");
        dataSource = Mockito.mock(DataSource.class);
        Mockito.when(dataSource.getConnection()).thenReturn(connection);
        marksDAO = new MarksDAO(dataSource);
        classDAO = new ClassDAO(dataSource);
        teachersDAO = new TeachersDAO(dataSource);
        kursDAO = new KursDAO(dataSource);
        lessonDAO = new LessonDAO(dataSource);
        studentsDAO = new StudentsDAO(dataSource);
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
        classDAO.save(new Class(1,1,"A"));
        kursDAO.save(new Kurs(1, "math"));
        teachersDAO.save(new Teachers(1, "Tatyana", "Ivanovna", "Smirnova",
                "13141", "tatyana@mail.me", 1, 1));
        lessonDAO.save(new Lesson(1, Date.valueOf("2017-04-10"), Time.valueOf("09:15:00"), "hometask",
                1, 1, 1));
        studentsDAO.save(new Students(1,"Petya", "Petrovich", "Ivanov",
                10, "19389372", "petya@mail.me", 1));
        marksDAO.save(new Marks(1,10, "for reading", 1, 1));
        Marks marks = marksDAO.getById(1);
        assertNotNull(marks);
        assertEquals("for reading", marks.getComment());
    }

    @Test
    public void getById() throws Exception {
        classDAO.save(new Class(1,1,"A"));
        kursDAO.save(new Kurs(1, "math"));
        teachersDAO.save(new Teachers(1, "Tatyana", "Ivanovna", "Smirnova",
                "13141", "tatyana@mail.me", 1, 1));
        lessonDAO.save(new Lesson(1, Date.valueOf("2017-04-10"), Time.valueOf("09:15:00"), "hometask",
                1, 1, 1));
        studentsDAO.save(new Students(1,"Petya", "Petrovich", "Ivanov",
                10, "19389372", "petya@mail.me", 1));
        marksDAO.save(new Marks(1,10, "for reading", 1, 1));
        Marks marks = marksDAO.getById(1);
        assertNotNull(marks);
        assertEquals(10, marks.getMark());
        assertEquals("for reading", marks.getComment());
    }

    @Test
    public void update() throws Exception {
        classDAO.save(new Class(1,1,"A"));
        kursDAO.save(new Kurs(1, "math"));
        teachersDAO.save(new Teachers(1, "Tatyana", "Ivanovna", "Smirnova",
                "13141", "tatyana@mail.me", 1, 1));
        lessonDAO.save(new Lesson(1, Date.valueOf("2017-04-10"), Time.valueOf("09:15:00"), "hometask",
                1, 1, 1));
        studentsDAO.save(new Students(1,"Petya", "Petrovich", "Ivanov",
                10, "19389372", "petya@mail.me", 1));
        studentsDAO.save(new Students(2,"Vasya", "Vasilyevich", "Petrov",
                10, "41247", "vasya@mail.me", 1));
        marksDAO.save(new Marks(1,10, "for reading", 1, 1));
        marksDAO.update(new Marks(1,12, "for reading", 1, 2));
        Marks marks = marksDAO.getById(1);
        assertNotNull(marks);
        assertEquals(12, marks.getMark());
        assertEquals(2, marks.getStudentsId());
    }

    @Test
    public void deleteById() throws Exception {
        classDAO.save(new Class(1,1,"A"));
        kursDAO.save(new Kurs(1, "math"));
        teachersDAO.save(new Teachers(1, "Tatyana", "Ivanovna", "Smirnova",
                "13141", "tatyana@mail.me", 1, 1));
        lessonDAO.save(new Lesson(1, Date.valueOf("2017-04-10"), Time.valueOf("09:15:00"), "hometask",
                1, 1, 1));
        studentsDAO.save(new Students(1,"Petya", "Petrovich", "Ivanov",
                10, "19389372", "petya@mail.me", 1));
        studentsDAO.save(new Students(2,"Vasya", "Vasilyevich", "Petrov",
                10, "41247", "vasya@mail.me", 1));
        marksDAO.save(new Marks(1,10, "for reading", 1, 1));
        marksDAO.save(new Marks(2,12, "for reading", 1, 2));
        marksDAO.deleteById(1);
        Marks marks = marksDAO.getById(1);
        assertNull(marks);
        Marks marks1 = marksDAO.getById(2);
        assertNotNull(marks1);
        assertEquals(12, marks1.getMark());
        assertEquals(2, marks1.getStudentsId());
        assertEquals("for reading", marks1.getComment());
    }

    @Test
    public void getAll() throws Exception {
        classDAO.save(new Class(1,1,"A"));
        kursDAO.save(new Kurs(1, "math"));
        teachersDAO.save(new Teachers(1, "Tatyana", "Ivanovna", "Smirnova",
                "13141", "tatyana@mail.me", 1, 1));
        lessonDAO.save(new Lesson(1, Date.valueOf("2017-04-10"), Time.valueOf("09:15:00"), "hometask",
                1, 1, 1));
        studentsDAO.save(new Students(1,"Petya", "Petrovich", "Ivanov",
                10, "19389372", "petya@mail.me", 1));
        studentsDAO.save(new Students(2,"Vasya", "Vasilyevich", "Petrov",
                10, "41247", "vasya@mail.me", 1));
        marksDAO.save(new Marks(1,10, "for reading", 1, 1));
        marksDAO.save(new Marks(2,12, "for reading", 1, 2));
        List<Marks> marksList = marksDAO.getAll();
        assertNotNull(marksList);
        assertEquals("for reading", marksList.get(0).getComment());
        assertEquals(12, marksList.get(1).getMark());
    }

}