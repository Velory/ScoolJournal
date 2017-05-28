package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sirotkina.sjournal.entity.Role;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.*;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

/**
 * Created by Марина on 22.05.2017.
 */
public class RoleDAOTest {
    @Before
    public void setUp() throws Exception {
        migrate();
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
        Role role = roleDAO().getById(1);
        assertNotNull(role);
        assertEquals("Администратор", role.getRole());
    }

    @Test
    public void getById() throws Exception {
        Role role = roleDAO().getById(2);
        assertNotNull(role);
        assertEquals("Учитель", role.getRole());
    }

    @Test
    public void update() throws Exception {
        roleDAO().update(new Role(1, "Директор"));
        Role role = roleDAO().getById(1);
        assertNotNull(role);
        assertEquals("Директор", role.getRole());
    }

    @Test
    public void deleteById() throws Exception {
        roleDAO().deleteById(1);
        Role role = roleDAO().getById(1);
        Role role1 = roleDAO().getById(2);
        assertNull(role);
        assertNotNull(role1);
    }

    @Test
    public void getAll() throws Exception {
        List<Role> roleList = roleDAO().getAll();
        assertEquals("Администратор", roleList.get(0).getRole());
        assertEquals("Учитель", roleList.get(1).getRole());
    }

}