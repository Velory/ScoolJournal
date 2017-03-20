package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sirotkina.sjournal.entity.Kurs;
import sirotkina.sjournal.utils.DatabaseUtils;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.*;


public class KursDAOTest {

    private KursDAO kursDAO;
    private DataSource dataSource;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        //DatabaseUtils.migrate();
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scooldb1?createDatabaseIfNotExist=true",
                "root",
                "marishach");
        dataSource = Mockito.mock(DataSource.class);
        Mockito.when(dataSource.getConnection()).thenReturn(connection);
        kursDAO = new KursDAO(dataSource);
        kursDAO.save(new Kurs(1,"ukr lang"));
        kursDAO.save(new Kurs(2,"math"));
        kursDAO.save(new Kurs(3,"lang"));
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
        Kurs kurs = kursDAO.getById(1);
        Mockito.verify(dataSource, Mockito.times(4)).getConnection();
        assertNotNull(kurs);
        assertEquals("ukr lang", kurs.getTitle());
    }


    @Test
    public void getById() throws Exception {
        Kurs kurs = kursDAO.getById(1);
        assertNotNull(kurs);
        assertEquals("ukr lang", kurs.getTitle());
    }


    @Test
    public void update() throws Exception {
        kursDAO.update(new Kurs(1,"math"));
        Kurs kurs = kursDAO.getById(1);
        assertNotNull(kurs);
        assertEquals("math", kurs.getTitle());
    }


    @Test
    public void deleteById() throws Exception {
        kursDAO.deleteById(1);
        Kurs kurs = kursDAO.getById(1);
        assertNull(kurs);
    }

    @Test
    public void getAll() throws Exception {
        List<Kurs> kursList = kursDAO.getAll();
        assertNotNull(kursList);
        assertEquals("ukr lang", kursList.get(0).getTitle());
        assertEquals("math", kursList.get(1).getTitle());
        assertEquals("lang", kursList.get(2).getTitle());
    }

}