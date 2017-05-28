package sirotkina.sjournal.controller.journal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import sirotkina.sjournal.domain.LessonBean;
import sirotkina.sjournal.domain.ScheduleBean;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Lesson;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static sirotkina.sjournal.utils.ConvertersUtils.*;
import static sirotkina.sjournal.utils.DatabaseUtils.lessonDAO;

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
    private String homeTask = "";

    /*public ObservableList<LessonBean> getLessonBeanList() {
        List<LessonBean> lessonBeans = new ArrayList<>();
        List<Students> studentsList = studentsDAO().getAll();
        List<Lesson> lessonList = lessonDAO().getAll();
        Class cl = classConverter().checkClassInDB(classConverter().fromString(lessonOfClass.getText()));
        for (Lesson l : lessonList) {
            if (l.getDate().toLocalDate().isBefore(LocalDate.now()) && l.getClassFKId().equals(cl)) {
                homeTask = l.getHomeTask();
            }
        }
        int i = 1;
        for (Students st : studentsList) {
            if (st.getClassFKId().equals(cl)) {
                lessonBeans.add(new LessonBean(lessonDate.getText(), lessonTime.getText(),
                        lessonOfClass.getText(), homeTask, newHomeTask.getText(), i,
                        studentConverter().toString(st), null, null));
            }
            i++;
        }
        return FXCollections.observableList(lessonBeans);
    }*/


    public void initialize() throws IOException {
        lessonController = this;
        //lessonBeans = getLessonBeanList();
        num.setCellValueFactory(new PropertyValueFactory<>("num"));
        fio.setCellValueFactory(new PropertyValueFactory<>("fio"));
        mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        newLessonTable.setItems(lessonBeans);
    }

    public void onSaveLesson() {
        Lesson lesson = new Lesson(null, Date.valueOf(LocalDate.now()), lessonTime.getText(), newHomeTask.getText(),
                classConverter().checkClassInDB(classConverter().fromString(lessonOfClass.getText())),
                teacherConverter().checkTeacherInDB(teacherConverter().fromString(teacherOfLesson.getText())),
                kursConverter().checkKursInDB(kursConverter().fromString(kursOfLesson.getText())));

        lessonDAO().save(lesson);
    }

    public void setValuesForNewLesson(ScheduleBean scheduleBean){
        kursOfLesson.setText(scheduleBean.getNameOfKurs());
        lessonDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd MM yyyy")));
        lessonTime.setText(scheduleBean.getLessonTime());
        lessonOfClass.setText(scheduleBean.getScoolClass());
        teacherOfLesson.setText(scheduleBean.getTeacherOfLesson());
        lastHomeTask.setText(homeTask);
    }

    public static CreateLessonController getLessonController() {
        return lessonController;
    }
}
