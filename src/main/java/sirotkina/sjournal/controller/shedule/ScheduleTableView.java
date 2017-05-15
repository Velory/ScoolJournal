package sirotkina.sjournal.controller.shedule;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sirotkina.sjournal.domain.ScheduleBean;
import sirotkina.sjournal.entity.*;

import static sirotkina.sjournal.utils.DatabaseUtils.*;
import static sirotkina.sjournal.utils.ConvertersUtils.*;
import static sirotkina.sjournal.utils.ControllersUtils.*;

import java.util.ArrayList;
import java.util.List;

public class ScheduleTableView {

    private ObservableList<ScheduleBean> tableElements;

    protected ObservableList<ScheduleBean> getSheduleBeanList(){
        List<ScheduleBean> scheduleList = new ArrayList<>();

        List<Schedule> schedules = getScheduleList();
        for (Schedule schedule: schedules){
            scheduleList.add(new ScheduleBean(schedule.getWeekDay(),
                    classConverter().toString(schedule.getScoolClass()),
                    schedule.getId(), schedule.getLessonTime(), kursConverter().toString(schedule.getNameOfKurs()),
                    teacherConverter().toString(schedule.getTeacherOfLesson())));
        }
        return FXCollections.observableList(scheduleList);
    }

    @FXML protected TableView<ScheduleBean> curentSchedule;
    @FXML protected TableColumn<ScheduleBean, String> day;
    @FXML protected TableColumn<ScheduleBean, String> scoolClass;
    @FXML protected TableColumn<ScheduleBean, Integer> id;
    @FXML protected TableColumn<ScheduleBean, String> lessonTime;
    @FXML protected TableColumn<ScheduleBean, String> nameOfKurs;
    @FXML protected TableColumn<ScheduleBean, String> teacherOfLesson;

    @FXML
    protected void initialize(){
        tableElements = getSheduleBeanList();
        day.setCellValueFactory(new PropertyValueFactory<>("weekDay"));
        scoolClass.setCellValueFactory(new PropertyValueFactory<>("scoolClass"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        lessonTime.setCellValueFactory(new PropertyValueFactory<>("lessonTime"));
        nameOfKurs.setCellValueFactory(new PropertyValueFactory<>("nameOfKurs"));
        teacherOfLesson.setCellValueFactory(new PropertyValueFactory<>("teacherOfLesson"));
        curentSchedule.setItems(tableElements);
    }

    public ObservableList<ScheduleBean> getTableElements() {
        return tableElements;
    }
}
