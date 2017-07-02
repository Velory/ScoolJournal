package sirotkina.sjournal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sirotkina.sjournal.domain.JournalBean;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Kurs;
import sirotkina.sjournal.entity.Lesson;
import sirotkina.sjournal.entity.Marks;
import sirotkina.sjournal.utils.enums.Month;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static sirotkina.sjournal.utils.ConvertersUtils.studentConverter;
import static sirotkina.sjournal.utils.DatabaseUtils.*;
import static sirotkina.sjournal.utils.ObservableListUtils.*;

public class JournalController {

    private ObservableList<JournalBean> journalBeansList;
    @FXML
    private ComboBox<String> monthJournal;
    @FXML
    private ComboBox<Class> classJournal;
    @FXML
    private ComboBox<Kurs> kursJournal;
    @FXML
    private TableView<JournalBean> tableJournal;
    @FXML
    private TableColumn<JournalBean, String> numJournal;
    @FXML
    private TableColumn<JournalBean, String> fioJournal;
    @FXML
    private TableColumn<JournalBean, String> day_1;
    @FXML
    private TableColumn<JournalBean, String> day_2;
    @FXML
    private TableColumn<JournalBean, String> day_3;
    @FXML
    private TableColumn<JournalBean, String> day_4;
    @FXML
    private TableColumn<JournalBean, String> day_5;
    @FXML
    private TableColumn<JournalBean, String> day_6;
    @FXML
    private TableColumn<JournalBean, String> day_7;
    @FXML
    private TableColumn<JournalBean, String> day_8;
    @FXML
    private TableColumn<JournalBean, String> day_9;
    @FXML
    private TableColumn<JournalBean, String> day_10;
    @FXML
    private TableColumn<JournalBean, String> day_11;
    @FXML
    private TableColumn<JournalBean, String> day_12;
    @FXML
    private TableColumn<JournalBean, String> day_13;
    @FXML
    private TableColumn<JournalBean, String> day_14;
    @FXML
    private TableColumn<JournalBean, String> day_15;
    @FXML
    private TableColumn<JournalBean, String> day_16;
    @FXML
    private TableColumn<JournalBean, String> day_17;
    @FXML
    private TableColumn<JournalBean, String> day_18;
    @FXML
    private TableColumn<JournalBean, String> day_19;
    @FXML
    private TableColumn<JournalBean, String> day_20;
    @FXML
    private TableColumn<JournalBean, String> day_21;
    @FXML
    private TableColumn<JournalBean, String> day_22;
    @FXML
    private TableColumn<JournalBean, String> day_23;
    @FXML
    private TableColumn<JournalBean, String> day_24;
    @FXML
    private TableColumn<JournalBean, String> day_25;
    @FXML
    private TableColumn<JournalBean, String> day_26;
    @FXML
    private TableColumn<JournalBean, String> day_27;
    @FXML
    private TableColumn<JournalBean, String> day_28;
    @FXML
    private TableColumn<JournalBean, String> day_29;
    @FXML
    private TableColumn<JournalBean, String> day_30;
    @FXML
    private TableColumn<JournalBean, String> day_31;
    @FXML
    private TableColumn<JournalBean, String> summary;

    public void initialize(){
        monthJournal.setItems(getMonthList());
        classJournal.setItems(getClassList());
        kursJournal.setItems(getKursList());

        columnInit(numJournal, fioJournal, day_1, day_2, day_3, day_4, day_5, day_6, day_7,
                day_8, day_9, day_10, day_11, day_12, day_13, day_14, day_15, day_16, day_17,
                day_18, day_19, day_20, day_21, day_22, day_23, day_24, day_25, day_26,
                day_27, day_28, day_29, day_30, day_31, summary);
        //tableJournal.setItems(getJournalBeansList());
    }

    private ObservableList<JournalBean> getJournalBeansList(){
        journalBeansList = FXCollections.observableArrayList();
        List<Lesson> lessons = lessonDAO().getAll();
        List<Marks> marks = marksDAO().getAll();
        String month = monthJournal.getValue();

        Map<String, Integer> marksMap = new HashMap<>();


        List<Lesson> filteredLessons = lessons.stream()
                .filter(lesson -> lesson.getDate().toLocalDate().getMonth().name().equalsIgnoreCase(month))
                .filter(lesson -> lesson.getClassFKId().equals(classJournal.getValue()))
                .filter(lesson -> lesson.getKursFKId().equals(kursJournal.getValue()))
                .collect(Collectors.toList());

        for (Lesson l: filteredLessons){
            Integer day = l.getDate().toLocalDate().getDayOfMonth();
            List<Marks> filteredMarks = marks.stream()
                    .filter(marks1 -> marks1.getLessonFKId().equals(l))
                    .collect(Collectors.toList());

            for (Marks mark: filteredMarks){
                journalBeansList.add(JournalBean.newBuilder()
                        .setNumJournal(null)
                        .setFioJournal(studentConverter().toString(mark.getStudentsFKId()))
                        //.setDay_1()
                        .build());
            }
        }


        return journalBeansList;
    }

    private void columnInit(TableColumn<JournalBean, String>... tableColumns){
        for(TableColumn<JournalBean, String> tableColumn: tableColumns){
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(tableColumn.getText()));
        }
    }

}
