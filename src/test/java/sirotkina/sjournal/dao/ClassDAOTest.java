package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.utils.DatabaseUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.*;

public class ClassDAOTest {
    private ClassDAO classDAO;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        DatabaseUtils.migrate();
        /*connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scooldb1?createDatabaseIfNotExist=true",
                "root", "marishach");
        Mockito.mock(DatabaseUtils.class);
        Mockito.when(DatabaseUtils.getConnection()).thenReturn(connection);*/
        classDAO = new ClassDAO();
        classDAO.save(new Class(1,1,"A"));
        classDAO.save(new Class(2,1,"B"));
    }

    @After
    public void tearDown() throws Exception {
        PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement("DROP DATABASE `scooldb1`");
        ps.executeUpdate();
        ps.close();
    }
    @Test
    public void save() throws Exception {
        Class cl = classDAO.getById(1);
        assertNotNull(cl);
        Class cl1 = classDAO.getById(2);
        assertEquals("B", cl1.getLetter());
    }

    @Test
    public void getById() throws Exception {
        Class cl = classDAO.getById(1);
        assertEquals(1, cl.getNum());
        assertEquals("A", cl.getLetter());
    }

    @Test
    public void update() throws Exception {
        classDAO.update(new Class(1, 2, "B"));
        Class cl = classDAO.getById(1);
        assertEquals(2, cl.getNum());
        assertEquals("B", cl.getLetter());
    }

    @Test
    public void deleteById() throws Exception {
        classDAO.deleteById(1);
        Class cl = classDAO.getById(1);
        assertNull(cl);
    }

    @Test
    public void getAll() throws Exception {
        List<Class> classList = classDAO.getAll();
        assertNotNull(classList);
        assertEquals(1, classList.get(0).getNum());
        assertEquals("B", classList.get(1).getLetter());
    }

}