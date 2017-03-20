package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Students;
import sirotkina.sjournal.utils.DatabaseUtils;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.*;

public class StudentsDAOTest {
    private StudentsDAO studentsDAO;
    private DataSource dataSource;
    private Connection connection;
    private ClassDAO classDAO;

    @Before
    public void setUp() throws Exception {
        //DatabaseUtils.migrate();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scooldb1?createDatabaseIfNotExist=true",
                "root","marishach");
        dataSource = Mockito.mock(DataSource.class);
        Mockito.when(dataSource.getConnection()).thenReturn(connection);
        studentsDAO = new StudentsDAO(dataSource);
        classDAO = new ClassDAO();
        classDAO.save(new Class(1, 1, "A"));
        studentsDAO.save(new Students(1,"Ivan", "Ivanovich", "Ivanov",
                9, "293847", "ivan@mail.me", 1));
        studentsDAO.save(new Students(2, "Vasya", "Vasilyevich", "Petrov",
                10, "293847", "vasya@mail.me", 1));
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
        Students students = studentsDAO.getById(1);
        assertNotNull(students);
        assertEquals("Ivanovich", students.getMidName());
        assertEquals(9, students.getAge());
    }

    @Test
    public void getById() throws Exception {
        Students students = studentsDAO.getById(1);
        assertNotNull(students);
        assertEquals("ivan@mail.me", students.getEmail());
        assertEquals("293847", students.getPhone());
    }

    @Test
    public void update() throws Exception {
        studentsDAO.update(new Students(1, "Ivan", "Ivanovich", "Ivanov",
                10, "293847", "ivan@mail.me", 1));
        Students students1 = studentsDAO.getById(1);
        assertEquals(10, students1.getAge());
        assertEquals("293847", students1.getPhone());
    }

    @Test
    public void deleteById() throws Exception {
        studentsDAO.deleteById(1);
        Students students = studentsDAO.getById(1);
        assertNull(students);
        Students students1 = studentsDAO.getById(2);
        assertNotNull(students1);
        assertEquals("Petrov", students1.getLastName());
    }

    @Test
    public void getAll() throws Exception {
        List<Students> studentsList = studentsDAO.getAll();
        assertNotNull(studentsList);
        assertEquals("Ivan", studentsList.get(0).getFirstName());
        assertEquals("vasya@mail.me", studentsList.get(1).getEmail());
    }

}