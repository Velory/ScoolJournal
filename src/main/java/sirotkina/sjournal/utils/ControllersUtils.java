package sirotkina.sjournal.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Kurs;
import sirotkina.sjournal.entity.Schedule;
import sirotkina.sjournal.entity.Teachers;

import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class ControllersUtils {

    private static ObservableList<Class> classList;
    private static ObservableList<Kurs> kursList;
    private static ObservableList<Teachers> teachersList;
    private static ObservableList<Schedule> scheduleList;

    static {
        classList = FXCollections.observableList(classDAO().getAll());
        kursList = FXCollections.observableList(kursDAO().getAll());
        teachersList = FXCollections.observableList(teachersDAO().getAll());
        scheduleList = FXCollections.observableList(scheduleDAO().getAll());
    }

    public static ObservableList<Class> getClassList (){
        return classList;
    }

    public static ObservableList<Kurs> getKursList (){
        return kursList;
    }

    public static ObservableList<Teachers> getTeachersList (){
        return teachersList;
    }

    public static ObservableList<Schedule> getScheduleList() {
        return scheduleList;
    }

    public static ObservableList<String> getRoleList (){
        ObservableList<String> roleList = FXCollections.observableArrayList();
        for (Role r: Role.values()) {
            roleList.add(r.getValue());
        }
        return roleList;
    }

    public static ObservableList<String> getTimeOfLessons (){
        ObservableList<String> timeOfLessonsList = FXCollections.observableArrayList();
        for (TimeOfLessons time: TimeOfLessons.values()){
            timeOfLessonsList.add(time.getValue());
        }
        return timeOfLessonsList;
    }

    public static ObservableList<String> getDaysOfWeek (){
        ObservableList<String> daysList = FXCollections.observableArrayList();
        for (DaysOfWeek day: DaysOfWeek.values()){
            daysList.add(day.getValue());
        }
        return daysList;
    }

    public static void nodeIsActive(boolean b, Node... nodes){
        if (b){
            for (Node node: nodes) {
                node.setDisable(false);
                node.setVisible(true);
            }
        } else{
            for (Node node: nodes) {
                node.setDisable(true);
                node.setVisible(false);
            }
        }
    }
}
