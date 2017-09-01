package sirotkina.sjournal.controller.lesson;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Paint;
import sirotkina.sjournal.domain.*;
import sirotkina.sjournal.entity.*;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static sirotkina.sjournal.utils.ConvertersUtils.*;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class CreateLessonController {

    private static CreateLessonController lessonController;

    @FXML
    private Label lessonDate;
    @FXML
    private Label lessonTime;
    @FXML
    private Label lessonOfClass;
    @FXML
    private Label lastHomeTask;
    @FXML
    private TextArea newHomeTask;
    @FXML
    private Label teacherOfLesson;
    @FXML
    private Label kursOfLesson;
    @FXML
    private Label msgLblLesson;

    @FXML
    private TableView<LessonBean> newLessonTable;
    @FXML
    private TableColumn<LessonBean, Integer> num;
    @FXML
    private TableColumn<LessonBean, String> fio;
    @FXML
    private TableColumn<LessonBean, String> mark;
    @FXML
    private TableColumn<LessonBean, String> comment;

    private ObservableList<LessonBean> lessonBeans;

    public void initialize() throws IOException {
        lessonController = this;
        lessonBeans = SelectLessonController.getLessonBeans();
        num.setCellValueFactory(new PropertyValueFactory<>("num"));
        fio.setCellValueFactory(new PropertyValueFactory<>("fio"));
        mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
        mark.setCellFactory(TextFieldTableCell.forTableColumn());
        mark.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setMark(event.getNewValue()));
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        comment.setCellFactory(TextFieldTableCell.forTableColumn());
        comment.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setComment(event.getNewValue()));
        newLessonTable.setItems(lessonBeans);
    }

    public void onSaveLesson() {
        try{
            Lesson lesson = new Lesson(null, Date.valueOf(LocalDate.now()), lessonTime.getText(), newHomeTask.getText(),
                    classConverter().checkClassInDB(classConverter().fromString(lessonOfClass.getText())),
                    teacherConverter().checkTeacherInDB(teacherConverter().fromString(teacherOfLesson.getText())),
                    kursConverter().checkKursInDB(kursConverter().fromString(kursOfLesson.getText())));
            lessonDAO().save(lesson);

            saveMarks();
            msgLblLesson.setText("Ok!");
            msgLblLesson.setTextFill(Paint.valueOf("#4f9302"));
        } catch (Exception e){
            e.printStackTrace();
            msgLblLesson.setText("Ошибка!");
            msgLblLesson.setTextFill(Paint.valueOf("#fa3242"));
        }
    }

    public void setValuesForNewLesson(ScheduleBean scheduleBean, LessonBean lessonBean) {
        kursOfLesson.setText(scheduleBean.getNameOfKurs());
        lessonDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd MM yyyy")));
        lessonTime.setText(scheduleBean.getLessonTime());
        lessonOfClass.setText(scheduleBean.getScoolClass());
        teacherOfLesson.setText(scheduleBean.getTeacherOfLesson());
        lastHomeTask.setText(lessonBean.getLastHomeTask());
        try {
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CreateLessonController getLessonController() {
        return lessonController;
    }

    private void saveMarks(){
        List<LessonBean> lessonBeanList = newLessonTable.getItems();
        Lesson lastLesson = lessonDAO().getById(lessonDAO().checkLastId());
        for (LessonBean lesson: lessonBeanList){
            Marks marks = new Marks(null, Integer.valueOf(lesson.getMark()),
                    lesson.getComment(), lastLesson,
                    studentConverter().checkStudentInDB(studentConverter().fromString(lesson.getFio())));
            marksDAO().save(marks);
        }
    }

}
