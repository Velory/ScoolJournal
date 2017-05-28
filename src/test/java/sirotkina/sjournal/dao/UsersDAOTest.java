package sirotkina.sjournal.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sirotkina.sjournal.entity.*;
import sirotkina.sjournal.entity.Class;

import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.*;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

/**
 * Created by Марина on 22.05.2017.
 */
public class UsersDAOTest {
    @Before
    public void setUp() throws Exception {
        migrate();
        classDAO().save(new Class(1, 1, "A"));
        classDAO().save(new Class(null, 1, "B"));
        kursDAO().save(new Kurs(null, "math"));
        roleDAO().save(new Role(null, "Teacher"));
        roleDAO().save(new Role(null, "Admin"));
        usersDAO().save(new Users(null, "Ivan", "Ivanovich", "Ivanov",
                30, "3526214", "never@mail.me", classDAO().getById(1), kursDAO().getById(1),
                "djdhsg", roleDAO().getById(1)));
        usersDAO().save(new Users(null, "Tatyana", "Ivanovna", "Smirnova", 35,
                "13141", "tatyana@mail.me", classDAO().getById(1),
                kursDAO().getById(1), "shgb", roleDAO().getById(1)));
        usersDAO().save(new Users(null, "Alexandr", "Olegovich", "Mironov",
                40, "266335", "sfwaf@mail.me", classDAO().getById(1), kursDAO().getById(1),
                "dfsfsgg", roleDAO().getById(2)));
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
        Users users = usersDAO().getById(1);
        assertNotNull(users);
        assertEquals("3526214", users.getPhone());
    }

    @Test
    public void getById() throws Exception {
        Users users = usersDAO().getById(1);
        assertNotNull(users);
        assertEquals("djdhsg", users.getPassword());
        assertEquals(classDAO().getById(1), users.getClassFKId());
    }

    @Test
    public void update() throws Exception {
        usersDAO().update(new Users(1, "John", "Ivanovich", "Ivanov",
                30, "3526214", "never@mail.me", classDAO().getById(2), kursDAO().getById(1),
                "djdhsg", roleDAO().getById(1)));
        Users users = usersDAO().getById(1);
        assertNotNull(users);
        assertEquals("John", users.getFirstName());
        assertEquals(classDAO().getById(2), users.getClassFKId());
        assertEquals(roleDAO().getById(1), users.getRoleId());
        assertEquals("Ivanovich", users.getMidName());
    }

    @Test
    public void deleteById() throws Exception {
        usersDAO().deleteById(1);
        Users users = usersDAO().getById(1);
        Users users1 = usersDAO().getById(2);
        assertNull(users);
        assertNotNull(users1);
    }

    @Test
    public void getAll() throws Exception {
        List<Users> usersList = usersDAO().getAll();
        assertEquals("never@mail.me", usersList.get(0).getEmail());
        assertEquals("tatyana@mail.me", usersList.get(1).getEmail());
    }

    @Test
    public void getAllByRole() throws Exception {
        List<Users> usersList = usersDAO().getAllByRole(2);
        assertEquals("Alexandr", usersList.get(0).getFirstName());
        assertEquals(Integer.valueOf(3), usersList.get(0).getId());
    }

}