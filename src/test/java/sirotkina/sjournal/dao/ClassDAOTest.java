package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.utils.MigrationsUtils;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.*;

public class ClassDAOTest {
    private ClassDAO classDAO;
    private DataSource dataSource;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        MigrationsUtils.migrate();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/scooldb1?createDatabaseIfNotExist=true",
                "root","marishach");
        dataSource = Mockito.mock(DataSource.class);
        Mockito.when(dataSource.getConnection()).thenReturn(connection);
        classDAO = new ClassDAO(dataSource);
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
        classDAO.save(new Class(2,1,"B"));
        Class cl = classDAO.getById(1);
        assertNotNull(cl);
        Class cl1 = classDAO.getById(2);
        assertEquals("B", cl1.getLetter());
    }

    @Test
    public void getById() throws Exception {
        classDAO.save(new Class(1,1, "A"));
        Class cl = classDAO.getById(1);
        assertEquals(1, cl.getNum());
        assertEquals("A", cl.getLetter());
    }

    @Test
    public void update() throws Exception {
        classDAO.save(new Class(1, 1, "A"));
        classDAO.update(new Class(1, 2, "B"));
        Class cl = classDAO.getById(1);
        assertEquals(2, cl.getNum());
        assertEquals("B", cl.getLetter());
    }

    @Test
    public void deleteById() throws Exception {
        classDAO.save(new Class(1, 1, "A"));
        classDAO.save(new Class(2, 1, "B"));
        classDAO.deleteById(1);
        Class cl = classDAO.getById(1);
        assertNull(cl);
    }

    @Test
    public void getAll() throws Exception {
        Class cl = new Class(1, 2, "A");
        Class cl1 = new Class(2, 1, "B");
        classDAO.save(cl);
        classDAO.save(cl1);
        List<Class> classList = classDAO.getAll();
        assertEquals(cl, classList.get(0));
        assertEquals(cl.getNum(), classList.get(0).getNum());
        assertEquals(cl1.getLetter(), classList.get(1).getLetter());
    }

}