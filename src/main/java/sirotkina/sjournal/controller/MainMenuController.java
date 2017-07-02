package sirotkina.sjournal.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private BorderPane mainMenuContainer;
    @FXML
    private Button siteBtn;
    @FXML
    private Button schoolDataBtn;
    @FXML
    private Button personalProfileBtn;

    private Parent createSchedule;
    private AnchorPane currentSchedule;
    private Parent diary;
    private Parent selectLesson;
    private Parent createLesson;
    private Parent journal;
    private Parent site;
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
        journal = FXMLLoader.load(getClass().getClassLoader().getResource("view/fxml/journal.fxml"));
        site = FXMLLoader.load(getClass().getClassLoader().getResource("view/fxml/site.fxml"));
        schoolData = FXMLLoader.load(getClass().getClassLoader().getResource("view/fxml/schoolData.fxml"));
        personalProfile = FXMLLoader.load(getClass().getClassLoader().getResource("view/fxml/personalProfile.fxml"));

        siteBtn.setAlignment(Pos.BASELINE_LEFT);
        schoolDataBtn.setAlignment(Pos.BASELINE_LEFT);
        personalProfileBtn.setAlignment(Pos.BASELINE_LEFT);
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

    public void onSelectLesson() {
        mainMenuContainer.setCenter(selectLesson);
    }

    public void onCreateLesson() {
        mainMenuContainer.setCenter(createLesson);
    }

    public void onJournal(){
        mainMenuContainer.setCenter(journal);
    }

    public void onSite(){
        mainMenuContainer.setCenter(site);
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
