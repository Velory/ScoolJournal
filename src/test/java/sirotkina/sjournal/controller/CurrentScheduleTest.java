package sirotkina.sjournal.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sirotkina.sjournal.dao.ScheduleDAO;
import sirotkina.sjournal.entity.Schedule;
import sirotkina.sjournal.utils.DatabaseUtils;

import java.sql.PreparedStatement;
import java.sql.Time;

import static org.junit.Assert.*;

public class CurrentScheduleTest extends Application{
    private ScheduleDAO scheduleDAO;
    private CurrentSchedule currentSchedule;
    @Before
    public void setUp() throws Exception {
        DatabaseUtils.migrate();
        scheduleDAO = new ScheduleDAO();
        scheduleDAO.save(new Schedule("monday", "1A", 1, Time.valueOf("10:00:00"),
                "math", "Ivanov"));
        scheduleDAO.save(new Schedule("monday", "2B", 2, Time.valueOf("09:30:00"),
                "reading", "Petrov"));
        scheduleDAO.save(new Schedule("tuesday", "3D", 3, Time.valueOf("09:30:00"),
                "reading", "Petrov"));
        scheduleDAO.save(new Schedule("tuesday", "2B", 4, Time.valueOf("09:30:00"),
                "reading", "Petrov"));

        currentSchedule = new CurrentSchedule();
    }

    @After
    public void tearDown() throws Exception {
        PreparedStatement ps = DatabaseUtils.getConnection().prepareStatement("DROP DATABASE `scooldb1`");
        ps.executeUpdate();
        ps.close();
        DatabaseUtils.closeConnection();
    }

    @Test
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/currentSchedule.fxml"));

        TableView<Schedule> scheduleTab = new TableView<>(currentSchedule.getSheduleList());
        scheduleTab.getColumns().addAll(currentSchedule.getWeekDayColumn(),
                                        currentSchedule.getScoolClassColumn(),
                                        currentSchedule.getIdColumn(),
                                        currentSchedule.getlessonTime(),
                                        currentSchedule.getnameOfKurs(),
                                        currentSchedule.getteacherOfLesson());

        VBox root = new VBox();
        root.getChildren().addAll(scheduleTab);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule test");
        stage.show();
    }

}