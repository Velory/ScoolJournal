package sirotkina.sjournal.controller.journal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import sirotkina.sjournal.controller.MainMenuController;
import sirotkina.sjournal.domain.ScheduleBean;
import sirotkina.sjournal.entity.Schedule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sirotkina.sjournal.utils.ControllersUtils.*;
import static sirotkina.sjournal.utils.ConvertersUtils.*;

public class SelectLessonController {

    @FXML private TableView<ScheduleBean> selectLessonTable;
    @FXML private TableColumn<ScheduleBean, String> timeSelectLesson;
    @FXML private TableColumn<ScheduleBean, String> kursSelectLesson;
    @FXML private TableColumn<ScheduleBean, String> classSelectLesson;
    @FXML private TableColumn<ScheduleBean, String> teacherSelectLesson;
    @FXML private TableColumn<ScheduleBean, String> newLesson;

    private AnchorPane createLesson;

    protected ObservableList<ScheduleBean> getSheduleBeanList(){
        List<ScheduleBean> scheduleList = new ArrayList<>();

        List<Schedule> schedules = getScheduleList();
        for (Schedule schedule: schedules){
            scheduleList.add(new ScheduleBean(schedule.getWeekDay(),
                    classConverter().toString(schedule.getScoolClass()),
                    schedule.getId(), schedule.getLessonTime(),
                    kursConverter().toString(schedule.getNameOfKurs()),
                    teacherConverter().toString(schedule.getTeacherOfLesson())));
        }
        return FXCollections.observableList(scheduleList);
    }

    public void initialize() throws IOException {
        createLesson = FXMLLoader.load(getClass().getClassLoader().getResource("view/lessons/createLesson.fxml"));
        timeSelectLesson.setCellValueFactory(new PropertyValueFactory<>("lessonTime"));
        kursSelectLesson.setCellValueFactory(new PropertyValueFactory<>("nameOfKurs"));
        classSelectLesson.setCellValueFactory(new PropertyValueFactory<>("scoolClass"));
        teacherSelectLesson.setCellValueFactory(new PropertyValueFactory<>("teacherOfLesson"));
        selectLessonTable.setItems(getSheduleBeanList());
        newLesson.setCellFactory(labelCellFactory());
    }

    public void setCreateLesson() {

    }


    public Callback<TableColumn<ScheduleBean, String>, TableCell<ScheduleBean, String>> labelCellFactory() {
        Callback<TableColumn<ScheduleBean, String>, TableCell<ScheduleBean, String>> cellFactory =
        new Callback<TableColumn<ScheduleBean, String>, TableCell<ScheduleBean, String>>() {
            @Override
            public TableCell<ScheduleBean, String> call(final TableColumn<ScheduleBean, String> param) {
                final TableCell<ScheduleBean, String> cell = new TableCell<ScheduleBean, String>() {
                    final Label lbl = new Label("Начать урок");

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            lbl.setOnMouseMoved(event -> lbl.setTextFill(Paint.valueOf("#a3748a")));
                            lbl.setOnMouseExited(event -> lbl.setTextFill(Paint.valueOf("#fa3242")));
                            lbl.setOnMouseClicked(event -> new MainMenuController().getMainMenuContainer().setCenter(createLesson));
                            setGraphic(lbl);
                            setText(null);
                        } else {
                            setGraphic(null);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        return cellFactory;
    }

}