package sirotkina.sjournal.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class MainMenuController {

    @FXML private BorderPane mainMenuContainer;
    private AnchorPane createSchedule;
    private AnchorPane currentSchedule;
    private AnchorPane diary;
    private AnchorPane selectLesson;
    //private AnchorPane createLesson;

    public void initialize() throws IOException {
        createSchedule = FXMLLoader.load(getClass().getClassLoader().getResource("view/createSchedule.fxml"));
        currentSchedule = FXMLLoader.load(getClass().getClassLoader().getResource("view/currentSchedule.fxml"));
        diary = FXMLLoader.load(getClass().getClassLoader().getResource("view/diary.fxml"));
        selectLesson = FXMLLoader.load(getClass().getClassLoader().getResource("view/lessons/selectLesson.fxml"));
        //createLesson = FXMLLoader.load(getClass().getClassLoader().getResource("view/lessons/createLesson.fxml"));
    }

    public void onNewSchedule () throws IOException {
        mainMenuContainer.setCenter(createSchedule);
    }

    public void onOpenSchedule () throws IOException {
        mainMenuContainer.setCenter(currentSchedule);
    }

    public void onDiary () throws IOException {
        mainMenuContainer.setCenter(diary);
    }

    public void onJournal () throws IOException {
        mainMenuContainer.setCenter(selectLesson);
    }

    /*public void setCreateLesson() {
        mainMenuContainer.setCenter(createLesson);
    }*/

    public BorderPane getMainMenuContainer() {
        return mainMenuContainer;
    }
}
