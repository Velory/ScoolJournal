package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sirotkina.sjournal.entity.*;
import sirotkina.sjournal.entity.Class;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;
import static sirotkina.sjournal.utils.DatabaseUtils.classDAO;
import static sirotkina.sjournal.utils.DatabaseUtils.kursDAO;
import static sirotkina.sjournal.utils.DatabaseUtils.teachersDAO;

public class MarksDAOTest {

    @Before
    public void setUp() throws Exception {
        migrate();

        classDAO().save(new Class(1,1,"A"));
        kursDAO().save(new Kurs(1, "math"));
        teachersDAO().save(new Teachers(1, "Tatyana", "Ivanovna", "Smirnova",
                "13141", "tatyana@mail.me", kursDAO().getById(1), classDAO().getById(1), "13981"));
        lessonDAO().save(new Lesson(1, Date.valueOf("2017-04-10"), "09:15:00", "hometask",
                classDAO().getById(1), teachersDAO().getById(1),kursDAO().getById(1)));
        studentsDAO().save(new Students(1,"Petya", "Petrovich", "Ivanov",
                10, "19389372", "petya@mail.me", classDAO().getById(1), "sgh"));
        studentsDAO().save(new Students(2,"Vasya", "Vasilyevich", "Petrov",
                10, "41247", "vasya@mail.me", classDAO().getById(1), "agbh"));
        marksDAO().save(new Marks(1,10, "for reading", lessonDAO().getById(1),
                studentsDAO().getById(1)));
        marksDAO().save(new Marks(null,12, "for reading",
                lessonDAO().getById(1), studentsDAO().getById(2)));
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
        Marks marks = marksDAO().getById(1);
        assertNotNull(marks);
        assertEquals("for reading", marks.getComment());
    }

    @Test
    public void getById() throws Exception {
        Marks marks = marksDAO().getById(1);
        assertNotNull(marks);
        assertEquals(10, marks.getMark());
        assertEquals("for reading", marks.getComment());
    }

    @Test
    public void update() throws Exception {
        marksDAO().update(new Marks(1,12, "for reading",
                lessonDAO().getById(1), studentsDAO().getById(2)));
        Marks marks = marksDAO().getById(1);
        assertNotNull(marks);
        assertEquals(12, marks.getMark());
        assertEquals(studentsDAO().getById(2), marks.getStudentsFKId());
    }

    @Test
    public void deleteById() throws Exception {
        marksDAO().deleteById(1);
        Marks marks = marksDAO().getById(1);
        assertNull(marks);
        Marks marks1 = marksDAO().getById(2);
        assertNotNull(marks1);
        assertEquals(12, marks1.getMark());
        assertEquals(studentsDAO().getById(2), marks1.getStudentsFKId());
        assertEquals("for reading", marks1.getComment());
    }

    @Test
    public void getAll() throws Exception {
        List<Marks> marksList = marksDAO().getAll();
        assertNotNull(marksList);
        assertEquals("for reading", marksList.get(0).getComment());
        assertEquals(12, marksList.get(1).getMark());
    }

}