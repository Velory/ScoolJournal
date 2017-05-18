package sirotkina.sjournal.controller.shedule;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import sirotkina.sjournal.domain.ScheduleBean;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Kurs;
import sirotkina.sjournal.entity.Schedule;
import sirotkina.sjournal.entity.Teachers;

import java.util.Arrays;
import java.util.List;

import static sirotkina.sjournal.utils.ControllersUtils.*;
import static sirotkina.sjournal.utils.ConvertersUtils.*;
import static sirotkina.sjournal.utils.DatabaseUtils.scheduleDAO;

public class ScheduleTableEdit extends ScheduleTableView {

    private TableView.TableViewSelectionModel<ScheduleBean> selectionModel;

    @FXML
    private ComboBox<String> newDay;
    @FXML
    private ComboBox<Class> newScoolClass;
    @FXML
    private ComboBox<String> newLessonTime;
    @FXML
    private ComboBox<Kurs> newNameOfKurs;
    @FXML
    private ComboBox<Teachers> newTeacherOfLesson;
    /*@FXML private Button add;
    @FXML private Button save;
    @FXML private Button delete;
    @FXML private Button restore;*/

    @Override
    protected void initialize() {
        super.initialize();

        selectionModel = curentSchedule.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

        newDay.getItems().addAll(getDaysOfWeek());
        newLessonTime.getItems().addAll(getTimeOfLessons());

        newScoolClass.getItems().addAll(getClassList());
        newScoolClass.setConverter(classConverter());

        newNameOfKurs.getItems().addAll(getKursList());
        newNameOfKurs.setConverter(kursConverter());

        newTeacherOfLesson.getItems().addAll(getTeachersList());
        newTeacherOfLesson.setConverter(teacherConverter());

        day.setCellFactory(TextFieldTableCell.forTableColumn());
        day.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setWeekDay(event.getNewValue()));

        scoolClass.setCellFactory(TextFieldTableCell.forTableColumn());
        scoolClass.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setScoolClass(event.getNewValue()));

        lessonTime.setCellFactory(TextFieldTableCell.forTableColumn());
        lessonTime.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setLessonTime(event.getNewValue()));

        nameOfKurs.setCellFactory(TextFieldTableCell.forTableColumn());
        nameOfKurs.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setNameOfKurs(event.getNewValue()));

        teacherOfLesson.setCellFactory(TextFieldTableCell.forTableColumn());
        teacherOfLesson.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setTeacherOfLesson(event.getNewValue()));
    }


    @FXML
    private void onAddClick() {
        getTableElements().add(new ScheduleBean(newDay.getValue(), classConverter().toString(newScoolClass.getValue()),
                null, newLessonTime.getValue(), kursConverter().toString(newNameOfKurs.getValue()),
                teacherConverter().toString(newTeacherOfLesson.getValue())));
    }

    @FXML
    private void onSaveClick() {
        for (ScheduleBean el : getTableElements()) {
            Schedule schedule = new Schedule(el.getWeekDay(),
                    classConverter().checkClassInDB(classConverter().fromString(el.getScoolClass())),
                    el.getId(), el.getLessonTime(),
                    kursConverter().checkKursInDB(kursConverter().fromString(el.getNameOfKurs())),
                    teacherConverter().checkTeacherInDB(teacherConverter().fromString(el.getTeacherOfLesson())));
            List<Schedule> scheduleList = scheduleDAO().getAll();
            for (Schedule sc : scheduleList) {
                if (sc.getId().equals(schedule.getId())) {
                    scheduleDAO().update(schedule);
                }
                if (schedule.getId() == null) {
                    scheduleDAO().save(schedule);
                }
            }
        }
    }

    @FXML
    private void onDeleteClick() {
        selectionModel = curentSchedule.getSelectionModel();
        if (selectionModel.isEmpty()) return;

        ObservableList<Integer> list = selectionModel.getSelectedIndices();
        Integer[] selectedIndices = new Integer[list.size()];
        selectedIndices = list.toArray(selectedIndices);
        Arrays.sort(selectedIndices);

        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            selectionModel.clearSelection(selectedIndices[i]);
            curentSchedule.getItems().remove(selectedIndices[i].intValue());
        }
    }

    @FXML
    private void onRestoreClick() {
        curentSchedule.getItems().clear();
        super.initialize();
    }
}

