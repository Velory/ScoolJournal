package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.*;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class MarksDAOTest {

    @Before
    public void setUp() throws Exception {
        migrate();
        roleDAO().save(new Role(null, "Teacher"));
        roleDAO().save(new Role(null, "Student"));
        classDAO().save(new Class(1, 1, "A"));
        kursDAO().save(new Kurs(1, "math"));
        usersDAO().save(new Users(1, "Tatyana", "Ivanovna", "Smirnova", Date.valueOf("2017-06-01"),
                "13141", "tatyana@mail.me", classDAO().getById(1),
                kursDAO().getById(1), "13981", roleDAO().getById(1)));
        lessonDAO().save(new Lesson(1, Date.valueOf("2017-04-10"), "09:15:00", "hometask",
                classDAO().getById(1), usersDAO().getById(1), kursDAO().getById(1)));
        usersDAO().save(new Users(1, "Petya", "Petrovich", "Ivanov",
                Date.valueOf("2017-06-01"), "19389372", "petya@mail.me", classDAO().getById(1),
                kursDAO().getById(1), "sgh", roleDAO().getById(2)));
        usersDAO().save(new Users(2, "Vasya", "Vasilyevich", "Petrov",
                Date.valueOf("2017-06-01"), "41247", "vasya@mail.me", classDAO().getById(1),
                kursDAO().getById(1), "agbh", roleDAO().getById(2)));
        marksDAO().save(new Marks(1, 10, "for reading", lessonDAO().getById(1),
                usersDAO().getById(1)));
        marksDAO().save(new Marks(null, 12, "for reading",
                lessonDAO().getById(1), usersDAO().getById(2)));
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
        marksDAO().update(new Marks(1, 12, "for reading",
                lessonDAO().getById(1), usersDAO().getById(2)));
        Marks marks = marksDAO().getById(1);
        assertNotNull(marks);
        assertEquals(12, marks.getMark());
        assertEquals(usersDAO().getById(2), marks.getStudentsFKId());
    }

    @Test
    public void deleteById() throws Exception {
        marksDAO().deleteById(1);
        Marks marks = marksDAO().getById(1);
        assertNull(marks);
        Marks marks1 = marksDAO().getById(2);
        assertNotNull(marks1);
        assertEquals(12, marks1.getMark());
        assertEquals(usersDAO().getById(2), marks1.getStudentsFKId());
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