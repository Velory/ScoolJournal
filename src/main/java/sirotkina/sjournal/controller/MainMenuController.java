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
    private Parent schoolData;
    private Parent personalProfile;

    private static MainMenuController menuController;

    public void initialize() throws IOException {
        menuController = this;
        createSchedule = FXMLLoader.load(getClass().getClassLoader().getResource("view/fxml/createSchedule.fxml"));
        currentSchedule = FXMLLoader.load(getClass().getClassLoader().getResource("view/fxml/currentSchedule.fxml"));
        diary = FXMLLoader.load(getClass().getClassLoader().getResource("view/fxml/diary.fxml"));
        selectLesson = FXMLLoader.load(getClass().getClassLoader().getResource("view/fxml/lessons/selectLesson.fxml"));
        createLesson = FXMLLoader.load(getClass().getClassLoader().getResource("view/fxml/lessons/createLesson.fxml"));
        schoolData = FXMLLoader.load(getClass().getClassLoader().getResource("view/fxml/schoolData.fxml"));
        personalProfile = FXMLLoader.load(getClass().getClassLoader().getResource("view/fxml/personalProfile.fxml"));
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

    public void onEditSchoolData(){
        mainMenuContainer.setCenter(schoolData);
    }

    public void onPersonalProfile(){
        mainMenuContainer.setCenter(personalProfile);
    }

    public static MainMenuController getMenuController() {
        return menuController;
    }
}
