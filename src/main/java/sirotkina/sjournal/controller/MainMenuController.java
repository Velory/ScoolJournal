package sirotkina.sjournal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import sirotkina.sjournal.ui.CreateSchedule;
import sirotkina.sjournal.ui.CurrentSchedule;
import sirotkina.sjournal.ui.Diary;
import sirotkina.sjournal.ui.SelectLesson;

import java.io.IOException;

public class MainMenuController {

    public void onNewSchedule (ActionEvent event) throws IOException {
        new CreateSchedule();
    }

    public void onOpenSchedule (ActionEvent event) throws IOException {
        new CurrentSchedule();
    }

    public void onDiary (ActionEvent event) throws IOException {
        new Diary();
    }

    public void onJournal (ActionEvent event) throws IOException {
        new SelectLesson();
    }
}
