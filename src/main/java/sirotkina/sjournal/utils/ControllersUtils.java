package sirotkina.sjournal.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import sirotkina.sjournal.domain.ClassBean;
import sirotkina.sjournal.domain.ScheduleBean;
import sirotkina.sjournal.entity.*;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Role;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static sirotkina.sjournal.utils.ConvertersUtils.*;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class ControllersUtils {

    private static final ClassBean DEFAULTCLASSBEAN = new ClassBean(null, "0", "N/A");
    private static final Class DEFAULTCLASS = new Class(null, 0, "N/A");
    private static final Kurs DEFAULTKURS = new Kurs(null, "N/A");

    private static ObservableList<String> timeOfLessonsList;
    private static ObservableList<String> daysList;
    private static ObservableList<Users> teachersList;
    private static ObservableList<Users> studentsList;


    static {
        timeOfLessonsList = FXCollections.observableArrayList();
        daysList = FXCollections.observableArrayList();
        teachersList = FXCollections.observableList(usersDAO().getAllByRole(2));
        studentsList = FXCollections.observableList(usersDAO().getAllByRole(3));
    }

    public static ObservableList<Class> getClassList(){
        return FXCollections.observableList(classDAO().getAll());
    }

    public static ObservableList<ClassBean> getClassBeanList() {
        List<ClassBean> classBeanList = new ArrayList<>();
        List<Class> classes = classDAO().getAll();
        classBeanList.add(defaultClassBean());
        for (Class cl: classes){
            classBeanList.add(new ClassBean(cl.getId(), String.valueOf(cl.getNum()), cl.getLetter()));
        }
        return FXCollections.observableList(classBeanList);
    }

    public static ObservableList<Kurs> getKursList() {
        List<Kurs> kursList = new ArrayList<>();
        kursList.add(defaultKurs());
        kursList.addAll(kursDAO().getAll());
        return FXCollections.observableList(kursList);
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

    public static ObservableList<Role> getRoleList() {
        return FXCollections.observableList(roleDAO().getAll());
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

    public static void deleteRow (TableView tableView,
                                  TableView.TableViewSelectionModel selectionModel){
        if (selectionModel.isEmpty()) return;

        ObservableList<Integer> list = selectionModel.getSelectedIndices();
        Integer[] selectedIndices = new Integer[list.size()];
        selectedIndices = list.toArray(selectedIndices);
        Arrays.sort(selectedIndices);

        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            selectionModel.clearSelection(selectedIndices[i]);
            tableView.getItems().remove(selectedIndices[i].intValue());
        }
    }

    public static ClassBean defaultClassBean() {
        return DEFAULTCLASSBEAN;
    }

    public static Kurs defaultKurs() {
        return DEFAULTKURS;
    }

    public static Kurs defaultkursFromDB(){
        return kursConverter().checkKursInDB(DEFAULTKURS);
    }

    public static Class defaultClassFromDB() {
        return classConverter().checkClassInDB(DEFAULTCLASS);
    }

    public static ObservableList<String> toStringList(List<? extends Object> list){
        return FXCollections.observableList(list.stream()
                .map(Object::toString).collect(Collectors.toList()));
    }
}
