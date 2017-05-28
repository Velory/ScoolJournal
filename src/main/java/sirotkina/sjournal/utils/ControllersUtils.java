package sirotkina.sjournal.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import sirotkina.sjournal.domain.ScheduleBean;
import sirotkina.sjournal.entity.*;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Role;
import java.util.ArrayList;
import java.util.List;
import static sirotkina.sjournal.utils.ConvertersUtils.*;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class ControllersUtils {

    private static ObservableList<Class> classList;
    private static ObservableList<Kurs> kursList;
    private static ObservableList<String> timeOfLessonsList;
    private static ObservableList<String> roleList;
    private static ObservableList<String> daysList;
    private static ObservableList<Users> teachersList;
    private static ObservableList<Users> studentsList;


    static {
        classList = FXCollections.observableList(classDAO().getAll());
        kursList = FXCollections.observableList(kursDAO().getAll());
        timeOfLessonsList = FXCollections.observableArrayList();
        roleList = FXCollections.observableArrayList();
        daysList = FXCollections.observableArrayList();
        teachersList = FXCollections.observableList(usersDAO().getAllByRole(2));
        studentsList = FXCollections.observableList(usersDAO().getAllByRole(3));
    }

    public static ObservableList<Class> getClassList() {
        return classList;
    }

    public static ObservableList<Kurs> getKursList() {
        return kursList;
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

    public static ObservableList<String> getRoleList() {
        for (Role r : roleDAO().getAll()) {
            roleList.add(r.getRole());
        }
        return roleList;
    }

    public static ObservableList<Users> getTeachersList(){
        return teachersList;
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

    public static void nodeIsActive(boolean b, Node... nodes) {
        if (b) {
            for (Node node : nodes) {
                node.setDisable(false);
                node.setVisible(true);
            }
        } else {
            for (Node node : nodes) {
                node.setDisable(true);
                node.setVisible(false);
            }
        }
    }
}
