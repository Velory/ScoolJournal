package sirotkina.sjournal.controller;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import sirotkina.sjournal.dao.ScheduleDAO;
import sirotkina.sjournal.entity.Schedule;

import java.sql.Time;
import java.util.List;

public class CurrentSchedule {

    public ObservableList<Schedule> getSheduleList (){
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        List<Schedule> scheduleList = scheduleDAO.getAll();
        return (ObservableList<Schedule>) scheduleList;
    }

    public TableColumn<Schedule, String> getWeekDayColumn(){
        TableColumn<Schedule, String> weekDayCol = new TableColumn<>("День недели");
        weekDayCol.setCellValueFactory(new PropertyValueFactory<>("weekDay"));
        return weekDayCol;
    }

    public TableColumn<Schedule, String> getScoolClassColumn(){
        TableColumn<Schedule, String> scoolClassCol = new TableColumn<>("Класс");
        scoolClassCol.setCellValueFactory(new PropertyValueFactory<>("scoolClass"));
        return scoolClassCol;
    }

    public TableColumn<Schedule, Integer> getIdColumn(){
        TableColumn<Schedule, Integer> idCol = new TableColumn<>("№");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        return idCol;
    }

    public TableColumn<Schedule, Time> getlessonTime(){
        TableColumn<Schedule, Time> lessonTimeCol = new TableColumn<>("Время урока");
        lessonTimeCol.setCellValueFactory(new PropertyValueFactory<>("lessonTime"));
        return lessonTimeCol;
    }

    public TableColumn<Schedule, String> getnameOfKurs(){
        TableColumn<Schedule, String> nameOfKursCol = new TableColumn<>("Предмет");
        nameOfKursCol.setCellValueFactory(new PropertyValueFactory<>("nameOfKurs"));
        return nameOfKursCol;
    }

    public TableColumn<Schedule, String> getteacherOfLesson(){
        TableColumn<Schedule, String> teacherOfLessonCol = new TableColumn<>("Преподаватель");
        teacherOfLessonCol.setCellValueFactory(new PropertyValueFactory<>("teacherOfLesson"));
        return teacherOfLessonCol;
    }
}
