package sirotkina.sjournal;
import sirotkina.sjournal.dao.ClassDAO;
import sirotkina.sjournal.entity.Class;
import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Flyway flyway = new Flyway();

        Properties properties = new Properties();

        try {
            java.lang.Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ClassLoader classLoader = Main.class.getClassLoader();

        try {
            properties.load(classLoader.getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        flyway.setDataSource(properties.getProperty("db.url"),
                properties.getProperty("db.user"), properties.getProperty("db.password"));

        flyway.migrate();

        Class cl = new Class(3, 1, "C");
        ClassDAO classDAO = new ClassDAO();
        //classDAO.create(cl);
        /*classDAO.delete(2);
        System.out.println(classDAO.read(1).toString() + "\n");
        classDAO.update(cl, 3);
        System.out.println(classDAO.getAll());*/

        /*Students st = new Students(4,"Anna", "Igorevna","Ivanova", 10,
                                    "4324511", "anna@mail.me", 4);
        StudentsDAO studentsDAO = new StudentsDAO();
        //studentsDAO.create(st);
        //studentsDAO.update(st,4);
        //studentsDAO.delete(2);
        System.out.println(studentsDAO.read(1).toString() + "\n");
        System.out.println(studentsDAO.getAll());*/

    }
}
