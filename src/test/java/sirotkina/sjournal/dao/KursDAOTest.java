package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import sirotkina.sjournal.entity.Kurs;
import sirotkina.sjournal.utils.MigrationsUtils;

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
        MigrationsUtils.migrate();
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scooldb1?createDatabaseIfNotExist=true",
                "root",
                "marishach");
        dataSource = Mockito.mock(DataSource.class);
        Mockito.when(dataSource.getConnection()).thenReturn(connection);
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
        kursDAO.save(new Kurs(1,"ukr lang"));
        Kurs kurs = kursDAO.getById(1);
        Mockito.verify(dataSource, Mockito.times(2)).getConnection();
        assertNotNull(kurs);
        assertEquals("ukr lang", kurs.getTitle());
    }


    @Test
    public void getById() throws Exception {
        kursDAO.save(new Kurs(1,"fizika"));
        kursDAO.save(new Kurs(2,"math"));
        Kurs kurs = kursDAO.getById(1);
        assertNotNull(kurs);
        assertEquals("fizika", kurs.getTitle());
    }


    @Test
    public void update() throws Exception {
        kursDAO.save(new Kurs(1,"lang"));
        kursDAO.save(new Kurs(2,"write"));
        kursDAO.update(new Kurs(1,"math"));
        Kurs kurs = kursDAO.getById(1);
        assertNotNull(kurs);
        assertEquals("math", kurs.getTitle());
    }


    @Test
    public void deleteById() throws Exception {
        kursDAO.save(new Kurs(1,"math"));
        kursDAO.save(new Kurs(2,"lang"));
        kursDAO.deleteById(1);
        Kurs kurs = kursDAO.getById(1);
        assertNull(kurs);
    }

    @Test
    public void getAll() throws Exception {
        Kurs kurs = new Kurs(1,"ukr lang");
        Kurs kurs1 = new Kurs(2,"math");
        Kurs kurs2 = new Kurs(3,"lang");
        kursDAO.save(kurs);
        kursDAO.save(kurs1);
        kursDAO.save(kurs2);
        List<Kurs> kursList = kursDAO.getAll();
        assertNotNull(kursList);
        assertEquals("ukr lang", kursList.get(0).getTitle());
        assertEquals("math", kursList.get(1).getTitle());
        assertEquals("lang", kursList.get(2).getTitle());
    }

}