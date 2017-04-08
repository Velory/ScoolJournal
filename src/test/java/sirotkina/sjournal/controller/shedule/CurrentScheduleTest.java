package sirotkina.sjournal.controller.shedule;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sirotkina.sjournal.dao.*;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Kurs;
import sirotkina.sjournal.entity.Lesson;
import sirotkina.sjournal.entity.Teachers;
import sirotkina.sjournal.utils.DatabaseUtils;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;


public class CurrentScheduleTest {
    private LessonDAO lessonDAO;
    private ClassDAO classDAO;
    private TeachersDAO teachersDAO;
    private KursDAO kursDAO;

    @BeforeClass
    public static void init() throws Exception{
        Thread thread = new Thread(() -> {
            new JFXPanel();
            Platform.runLater(() -> {
                try {
                    new CreateShedule().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
        thread.start();
        Thread.sleep(5000);
    }


    @Before
    public void setUp() throws Exception {

        DatabaseUtils.migrate();
        lessonDAO = new LessonDAO();
        classDAO = new ClassDAO();
        teachersDAO = new TeachersDAO();
        kursDAO = new KursDAO();
        classDAO.save(new Class(1, 1, "A"));
        classDAO.save(new Class(null, 1, "B"));
        kursDAO.save(new Kurs(null, "math"));
        teachersDAO.save(new Teachers(1, "Tatyana", "Ivanovna", "Smirnova",
                "13141", "tatyana@mail.me", 1, 1));
        lessonDAO.save(new Lesson(1, Date.valueOf("2017-03-16"), Time.valueOf("11:00:00"),
                "hometask", 1, 1, 1));
        lessonDAO.save(new Lesson(null, Date.valueOf("2017-03-10"), Time.valueOf("15:30:00"),
                "hometask1", 2, 1, 1));

    }

    @After
    public void tearDown() throws Exception {
        PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement("DROP DATABASE `scooldb1`");
        ps.executeUpdate();
        ps.close();
        DatabaseUtils.closeConnection();
    }

    @Test
    public void start() throws Exception {
        /*Stage stage = new Stage();
        CreateShedule createShedule = new CreateShedule();
        createShedule.start(stage);*/
    }
}