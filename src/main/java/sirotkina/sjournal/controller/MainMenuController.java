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

    public void onNewSchedule () throws IOException {
        new CreateSchedule();
    }

    public void onOpenSchedule () throws IOException {
        new CurrentSchedule();
    }

    public void onDiary () throws IOException {
        new Diary();
    }

    public void onJournal () throws IOException {
        new SelectLesson();
    }
}
