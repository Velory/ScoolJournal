package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sirotkina.sjournal.entity.Kurs;
import sirotkina.sjournal.utils.DatabaseUtils;

import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.*;


public class KursDAOTest {

    private KursDAO kursDAO;

    @Before
    public void setUp() throws Exception {
        DatabaseUtils.migrate();
        kursDAO = new KursDAO();
        kursDAO.save(new Kurs(1,"ukr lang"));
        kursDAO.save(new Kurs(2,"math"));
        kursDAO.save(new Kurs(null,"lang"));
    }

    @After
    public void tearDown() throws Exception {
        PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement("DROP DATABASE `scooldb1`");
        ps.executeUpdate();
        ps.close();
        DatabaseUtils.closeConnection();
    }


    @Test
    public void save() throws Exception {
        Kurs kurs = kursDAO.getById(1);
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