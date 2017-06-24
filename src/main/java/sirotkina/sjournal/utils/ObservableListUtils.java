package sirotkina.sjournal.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sirotkina.sjournal.domain.ClassBean;
import sirotkina.sjournal.domain.ScheduleBean;
import sirotkina.sjournal.entity.*;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Role;
import sirotkina.sjournal.utils.enums.DaysOfWeek;
import sirotkina.sjournal.utils.enums.TimeOfLessons;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static sirotkina.sjournal.utils.ConvertersUtils.*;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class ObservableListUtils {

    private static ObservableList<String> timeOfLessonsList;
    private static ObservableList<String> daysList;

    static {
        timeOfLessonsList = FXCollections.observableArrayList();
        daysList = FXCollections.observableArrayList();
    }

    public static ObservableList<String> toStringList(List<?> list){
        return FXCollections.observableList(list.stream()
                .map(Object::toString).collect(Collectors.toList()));
    }

    public static ObservableList<Role> getRoleList() {
        return FXCollections.observableList(roleDAO().getAll());
    }

    public static ObservableList<Users> getTeachersList(){
        return FXCollections.observableList(usersDAO().getAllByRole(2));
    }

    public static ObservableList<Users> getStudentsList() {
        return FXCollections.observableList(usersDAO().getAllByRole(3));
    }

    public static ObservableList<Class> getClassList(){
        return FXCollections.observableList(classDAO().getAll());
    }

    public static ObservableList<Kurs> getKursList(){
        return FXCollections.observableList(kursDAO().getAll());
    }

    public static ObservableList<Schedule> getScheduleList(){
        return FXCollections.observableList(scheduleDAO().getAll());
    }

    public static ObservableList<ClassBean> getClassBeanList() {
        List<ClassBean> classBeanList = new ArrayList<>();
        List<Class> classes = classDAO().getAll();
        for (Class cl: classes){
            classBeanList.add(new ClassBean(cl.getId(), String.valueOf(cl.getNum()), cl.getLetter()));
        }
        return FXCollections.observableList(classBeanList);
    }

    public static ObservableList<ScheduleBean> getSheduleBeanList() {
        List<ScheduleBean> scheduleList = new ArrayList<>();
        List<Schedule> schedules = scheduleDAO().getAll();
        for (Schedule schedule : schedules) {
            scheduleList.add(new ScheduleBean(schedule.getWeekDay(),
                    classConverter().toString(schedule.getScoolClass()),
                    schedule.getId(), schedule.getLessonTime(), kursConverter().toString(schedule.getNameOfKurs()),
                    teacherConverter().toString(schedule.getTeacherOfLesson())));
        }
        return FXCollections.observableList(scheduleList);
    }

    public static ObservableList<String> getTimeOfLessons() {
        for (TimeOfLessons time : TimeOfLessons.values()) {
            timeOfLessonsList.add(time.getValue());
        }
        return timeOfLessonsList;
    }

    public static ObservableList<String> getDaysOfWeek() {
        for (DaysOfWeek day : DaysOfWeek.values()) {
            daysList.add(day.getValue());
        }
        return daysList;
    }
}
