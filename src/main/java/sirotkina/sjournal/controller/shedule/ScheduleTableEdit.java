package sirotkina.sjournal.controller.shedule;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import sirotkina.sjournal.domain.ScheduleBean;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.*;
import java.util.List;

import static sirotkina.sjournal.utils.ObservableListUtils.*;
import static sirotkina.sjournal.utils.ControllersUtils.*;
import static sirotkina.sjournal.utils.ConvertersUtils.*;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

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
    private ComboBox<Users> newTeacherOfLesson;
    @FXML
    private Label msgLbl;

    @Override
    protected void initialize() {
        super.initialize();
        selectionModel = curentSchedule.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

        newDay.getItems().addAll(getDaysOfWeek());
        newLessonTime.getItems().addAll(getTimeOfLessons());
        newScoolClass.getItems().addAll(getClassList());
        newNameOfKurs.getItems().addAll(getKursList());
        newTeacherOfLesson.getItems().addAll(getTeachersList());

        day.setCellFactory(ComboBoxTableCell.forTableColumn(getDaysOfWeek()));
        day.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setWeekDay(event.getNewValue()));

        scoolClass.setCellFactory(ComboBoxTableCell.forTableColumn(toStringList(getClassList())));
        scoolClass.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setScoolClass(event.getNewValue()));

        lessonTime.setCellFactory(ComboBoxTableCell.forTableColumn(getTimeOfLessons()));
        lessonTime.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setLessonTime(event.getNewValue()));

        nameOfKurs.setCellFactory(ComboBoxTableCell.forTableColumn(toStringList(getKursList())));
        nameOfKurs.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setNameOfKurs(event.getNewValue()));

        teacherOfLesson.setCellFactory(ComboBoxTableCell.forTableColumn(toStringList(getTeachersList())));
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
        try{
            List<Schedule> scheduleList = scheduleDAO().getAll();
            for (Schedule schedule: scheduleList){
                scheduleDAO().deleteById(schedule.getId());
            }
            for (ScheduleBean el : getTableElements()){
                scheduleDAO().save(getSchedule(el));
            }
            msgLbl.setText("Ok!");
            msgLbl.setTextFill(Paint.valueOf("#4f9302"));
        } catch (Exception e){
            msgLbl.setText("Ошибка!");
            msgLbl.setTextFill(Paint.valueOf("#fa3242"));
        }
    }

    @FXML
    private void onDeleteClick() {
        deleteRow(curentSchedule, selectionModel);
    }

    @FXML
    private void onRestoreClick() {
        curentSchedule.getItems().clear();
        super.initialize();
    }

    private Schedule getSchedule(ScheduleBean scheduleBean){
        return new Schedule(scheduleBean.getWeekDay(),
                classConverter().checkClassInDB(classConverter().fromString(scheduleBean.getScoolClass())),
                scheduleBean.getId(),
                scheduleBean.getLessonTime(),
                kursConverter().checkKursInDB(kursConverter().fromString(scheduleBean.getNameOfKurs())),
                teacherConverter().checkTeacherInDB(teacherConverter().fromString(scheduleBean.getTeacherOfLesson())));
    }
}