package sirotkina.sjournal.controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import sirotkina.sjournal.domain.DiaryBean;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Lesson;
import sirotkina.sjournal.entity.Marks;
import sirotkina.sjournal.entity.Users;
import sirotkina.sjournal.utils.enums.DaysOfWeek;
import sirotkina.sjournal.utils.myValidator.Validator;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static sirotkina.sjournal.utils.ConvertersUtils.*;
import static sirotkina.sjournal.utils.ObservableListUtils.*;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class DiaryController {

    private List<Button> buttons;
    private Validator validator;

    @FXML    private ComboBox<Users> selectStudentDiary;
    @FXML    private ComboBox<Class> selectClassDiary;
    @FXML    private Button firstDay;
    @FXML    private Button secondDay;
    @FXML    private Button thirdDay;
    @FXML    private Button fourthDay;
    @FXML    private Button fifthDay;
    @FXML    private DatePicker period;
    @FXML    private TableView<DiaryBean> diaryTable;
    @FXML    private TableColumn<DiaryBean, Integer> numDiary;
    @FXML    private TableColumn<DiaryBean, String> kursDiary;
    @FXML    private TableColumn<DiaryBean, String> homeTaskDiary;
    @FXML    private TableColumn<DiaryBean, String> markDiary;
    @FXML    private TableColumn<DiaryBean, String> commentDiary;
    @FXML    private Label msgLabel;

    public void initialize(){
        getButtons();
        selectClassDiary.setItems(getClassList());
        selectClassDiary.getSelectionModel().selectedItemProperty().addListener(this::classChanged);
        period.setOnAction(event -> periodChanged(period.getValue()));
        tableInitialize(LocalDate.now());
        firstDay.setOnKeyPressed(event -> tableInitialize(LocalDate.parse(firstDay.getText().substring(0, 10))));
        secondDay.setOnKeyPressed(event -> tableInitialize(LocalDate.parse(secondDay.getText().substring(0, 10))));
        thirdDay.setOnKeyPressed(event -> tableInitialize(LocalDate.parse(thirdDay.getText().substring(0, 10))));
        fourthDay.setOnKeyPressed(event -> tableInitialize(LocalDate.parse(fourthDay.getText().substring(0, 10))));
        fifthDay.setOnKeyPressed(event -> tableInitialize(LocalDate.parse(firstDay.getText().substring(0, 10))));
    }

    private ObservableList<Users> studentsOfClass(Class cl){
        ObservableList<Users> students = getStudentsList();
        Iterator<Users> itr = students.iterator();
        while (itr.hasNext()){
            if(!itr.next().getClassFKId().equals(cl)){
                itr.remove();
            }
        }
        return students;
    }

    private ObservableList<DiaryBean> diaryBeanList(LocalDate localDate){
        List<DiaryBean> diaryBeansList = new ArrayList<>();
        List<Lesson> lessons = lessonDAO().getAll();
        List<Marks> marks = marksDAO().getAll();
        int i = 0;
        for (Lesson l: lessons) {
            if (l.getDate().toLocalDate().equals(localDate) ){
                List<Marks> filteredMarks = marks.stream().filter(marks1 -> marks1.getLessonFKId().equals(l))
                        .filter(marks1 -> marks1.getStudentsFKId().equals(selectStudentDiary.getValue()))
                        .collect(Collectors.toList());
                if (!filteredMarks.isEmpty()){
                    for (Marks mark: filteredMarks){
                        diaryBeansList.add(new DiaryBean(i++, kursConverter().toString(l.getKursFKId()),
                                l.getHomeTask(), String.valueOf(mark.getMark()),
                                mark.getComment()));
                    }
                }
            }
        }
        return FXCollections.observableList(diaryBeansList);
    }

    private void classChanged(ObservableValue<? extends Class> clItem, Class oldValue, Class newValue){
        selectStudentDiary.setItems(studentsOfClass(newValue));
    }

    private void periodChanged(LocalDate localDate){
        String saturday = DaysOfWeek.SATURDAY.getValue().toLowerCase();
        String sunday = DaysOfWeek.SUNDAY.getValue().toLowerCase();
        List<LocalDate> days = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            days.add(localDate.plusDays(i));
        }
        List<LocalDate> filteredDays = days.stream()
                .filter(localDate1 -> !dateConverter().toDayOfWeek(localDate1).equals(saturday))
                .filter(localDate1 -> !dateConverter().toDayOfWeek(localDate1).equals(sunday))
                .collect(Collectors.toList());
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setText(filteredDays.get(i).toString() + " "
                    + dateConverter().toDayOfWeek(filteredDays.get(i)));
        }
    }

    private void diaryTableInitialize(){
        validator = new Validator(DiaryBean.class);
        Set<String> messages = validator.validate(getDiaryBean());
        if (messages.isEmpty()){
            tableInitialize(period.getValue());
            msgLabel.setText("");
        } else {
            msgLabel.setText(String.valueOf(messages));
        }
    }

    public void onGoBtn(){
        diaryTableInitialize();
    }

    public void tableInitialize(LocalDate localDate){
        numDiary.setCellValueFactory(new PropertyValueFactory<>("numDiary"));
        kursDiary.setCellValueFactory(new PropertyValueFactory<>("kursDiary"));
        homeTaskDiary.setCellValueFactory(new PropertyValueFactory<>("homeTask"));
        markDiary.setCellValueFactory(new PropertyValueFactory<>("markDiary"));
        commentDiary.setCellValueFactory(new PropertyValueFactory<>("commentDiary"));
        diaryTable.setItems(diaryBeanList(localDate));
    }

    private DiaryBean getDiaryBean(){
        return new DiaryBean(studentConverter().toString(selectStudentDiary.getValue()),
                classConverter().toString(selectClassDiary.getValue()),
                dateConverter().toString(period.getValue()));
    }

    public List<Button> getButtons() {
        buttons = new ArrayList<>();
        buttons.add(firstDay);
        buttons.add(secondDay);
        buttons.add(thirdDay);
        buttons.add(fourthDay);
        buttons.add(fifthDay);
        return buttons;
    }
}
