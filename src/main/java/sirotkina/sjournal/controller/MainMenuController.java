package sirotkina.sjournal.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private BorderPane mainMenuContainer;
    private Parent createSchedule;
    private AnchorPane currentSchedule;
    private Parent diary;
    private Parent selectLesson;
    private Parent createLesson;

    public void initialize() throws IOException {
        createSchedule = FXMLLoader.load(getClass().getClassLoader().getResource("view/createSchedule.fxml"));
        currentSchedule = FXMLLoader.load(getClass().getClassLoader().getResource("view/currentSchedule.fxml"));
        diary = FXMLLoader.load(getClass().getClassLoader().getResource("view/diary.fxml"));
        selectLesson = FXMLLoader.load(getClass().getClassLoader().getResource("view/lessons/selectLesson.fxml"));
        createLesson = FXMLLoader.load(getClass().getClassLoader().getResource("view/lessons/createLesson.fxml"));
    }

    public void onNewSchedule() {
        mainMenuContainer.setCenter(createSchedule);
    }

    public void onOpenSchedule() {
        mainMenuContainer.setCenter(currentSchedule);
    }

    public void onDiary() {
        mainMenuContainer.setCenter(diary);
    }

    public void onJournal() {
        mainMenuContainer.setCenter(selectLesson);
    }

    public void onCreateLesson() {
        mainMenuContainer.setCenter(createLesson);
    }

}
