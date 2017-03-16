package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Kurs;
import sirotkina.sjournal.entity.Teachers;
import sirotkina.sjournal.utils.MigrationsUtils;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.*;

public class TeachersDAOTest {
    private TeachersDAO teachersDAO;
    private DataSource dataSource;
    private Connection connection;
    private ClassDAO classDAO;
    private KursDAO kursDAO;

    @Before
    public void setUp() throws Exception {
        MigrationsUtils.migrate();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scooldb1?createDatabaseIfNotExist=true",
                "root","marishach");
        dataSource = Mockito.mock(DataSource.class);
        Mockito.when(dataSource.getConnection()).thenReturn(connection);
        teachersDAO = new TeachersDAO(dataSource);
        classDAO = new ClassDAO(dataSource);
        kursDAO = new KursDAO(dataSource);
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
        teachersDAO.save(new Teachers(1, "Olga", "Igorevna", "Petrova",
                "2938475", "olga@mail.me", 1,1));
        Teachers teachers = teachersDAO.getById(1);
        assertNotNull(teachers);
        assertEquals("Olga", teachers.getFirstName());
        assertEquals("olga@mail.me", teachers.getEmail());
    }

    @Test
    public void getById() throws Exception {
        classDAO.save(new Class(1,1,"A"));
        kursDAO.save(new Kurs(1, "math"));
        teachersDAO.save(new Teachers(1, "Olga", "Igorevna", "Petrova",
                "2938475", "olga@mail.me", 1,1));
        Teachers teachers = teachersDAO.getById(1);
        assertNotNull(teachers);
        assertEquals("Olga", teachers.getFirstName());
        assertEquals("2938475", teachers.getPhone());
    }

    @Test
    public void update() throws Exception {
        classDAO.save(new Class(1,1,"A"));
        kursDAO.save(new Kurs(1, "math"));
        teachersDAO.save(new Teachers(1, "Olga", "Igorevna", "Petrova",
                "2938475", "olga@mail.me", 1,1));
        teachersDAO.update(new Teachers(1, "Svetlana", "Ivanovna", "Ivanova",
                "4937262", "svetlana@mail.me", 1, 1));
        Teachers teachers = teachersDAO.getById(1);
        assertNotNull(teachers);
        assertEquals("Svetlana", teachers.getFirstName());
        assertEquals("svetlana@mail.me", teachers.getEmail());
    }

    @Test
    public void deleteById() throws Exception {
        classDAO.save(new Class(1,1,"A"));
        kursDAO.save(new Kurs(1, "math"));
        teachersDAO.save(new Teachers(1, "Olga", "Igorevna", "Petrova",
                "2938475", "olga@mail.me", 1,1));
        teachersDAO.save(new Teachers(2, "Svetlana", "Ivanovna", "Ivanova",
                "4937262", "svetlana@mail.me", 1, 1));
        teachersDAO.deleteById(1);
        Teachers teachers = teachersDAO.getById(1);
        assertNull(teachers);
        Teachers teachers1 = teachersDAO.getById(2);
        assertNotNull(teachers1);
        assertEquals("Svetlana", teachers1.getFirstName());
        assertEquals("svetlana@mail.me", teachers1.getEmail());
    }

    @Test
    public void getAll() throws Exception {
        classDAO.save(new Class(1,1,"A"));
        kursDAO.save(new Kurs(1, "math"));
        teachersDAO.save(new Teachers(1, "Olga", "Igorevna", "Petrova",
                "2938475", "olga@mail.me", 1,1));
        teachersDAO.save(new Teachers(2, "Svetlana", "Ivanovna", "Ivanova",
                "4937262", "svetlana@mail.me", 1, 1));
        List<Teachers> teachersList = teachersDAO.getAll();
        assertNotNull(teachersList);
        assertEquals("Petrova", teachersList.get(0).getLastName());
        assertEquals("Ivanovna", teachersList.get(1).getMidName());
    }

}