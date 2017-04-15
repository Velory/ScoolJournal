package sirotkina.sjournal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import sirotkina.sjournal.ui.CreateSchedule;
import sirotkina.sjournal.ui.CurrentSchedule;
import sirotkina.sjournal.ui.Diary;
import sirotkina.sjournal.ui.SelectLesson;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private BorderPane mainMenuContainer;
    private AnchorPane createSchedule;
    private AnchorPane currentSchedule;

    public void initialize() throws IOException {
        createSchedule = FXMLLoader.load(getClass().getClassLoader().getResource("view/createSchedule.fxml"));
        currentSchedule = FXMLLoader.load(getClass().getClassLoader().getResource("view/currentSchedule.fxml"));

    }

    public void onNewSchedule () throws IOException {
        mainMenuContainer.setCenter(createSchedule);
    }

    public void onOpenSchedule () throws IOException {
        mainMenuContainer.setCenter(currentSchedule);
    }

    public void onDiary () throws IOException {
        new Diary();
    }

    public void onJournal () throws IOException {
        new SelectLesson();
    }
}
