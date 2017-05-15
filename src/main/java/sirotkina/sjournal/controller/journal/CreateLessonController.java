package sirotkina.sjournal.controller.journal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import sirotkina.sjournal.domain.LessonBean;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Lesson;
import sirotkina.sjournal.entity.Schedule;
import sirotkina.sjournal.entity.Students;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static sirotkina.sjournal.utils.ControllersUtils.*;
import static sirotkina.sjournal.utils.ConvertersUtils.*;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class CreateLessonController {

    @FXML private Label lessonDate;
    @FXML private Label lessonTime;
    @FXML private Label lessonOfClass;
    @FXML private Label lastHomeTask;
    @FXML private TextArea newHomeTask;

    @FXML private TableView<LessonBean> newLessonTable;
    @FXML private TableColumn<LessonBean, Integer> num;
    @FXML private TableColumn<LessonBean, String> fio;
    @FXML private TableColumn<LessonBean, String> mark;
    @FXML private TableColumn<LessonBean, String> comment;

    private ObservableList<LessonBean> lessonBeans;

    public ObservableList<LessonBean> getLessonBeanList(){
        List<LessonBean> lessonBeans = new ArrayList<>();

        List<Schedule> scheduleList = scheduleDAO().getAll();
        List<Students> studentsList = studentsDAO().getAll();
        List<Lesson> lessonList = lessonDAO().getAll();
        String lastHomeTask = "";
        for (Lesson l: lessonList) {
            if (l.getDate().toLocalDate().isBefore(LocalDate.now())){
                lastHomeTask = l.getHomeTask();
            }
        }
        lessonBeans.add(new LessonBean(lessonDate.getText(), lessonTime.getText(),
                lessonOfClass.getText(), lastHomeTask, newHomeTask.getText(), 0,
                studentConverter().toString()));

        return FXCollections.observableList(lessonBeans);
    }



}
