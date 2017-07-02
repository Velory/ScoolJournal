package sirotkina.sjournal.controller.lesson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import sirotkina.sjournal.controller.MainMenuController;
import sirotkina.sjournal.domain.*;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Lesson;
import sirotkina.sjournal.entity.Users;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static sirotkina.sjournal.utils.ObservableListUtils.*;
import static sirotkina.sjournal.utils.ConvertersUtils.*;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class SelectLessonController {

    private static SelectLessonController lessonController;

    @FXML
    private TableView<ScheduleBean> selectLessonTable;
    @FXML
    private TableColumn<ScheduleBean, String> lessonTime;
    @FXML
    private TableColumn<ScheduleBean, String> nameOfKurs;
    @FXML
    private TableColumn<ScheduleBean, String> scoolClass;
    @FXML
    private TableColumn<ScheduleBean, String> teacherOfLesson;
    @FXML
    private TableColumn<ScheduleBean, String> newLesson;
    @FXML
    private Label curDateLbl;

    private ObservableList<ScheduleBean> scheduleBeans;
    private ScheduleBean scheduleBean;
    private static ObservableList<LessonBean> lessonBeans;
    private String homeTask = "";

    public void initialize() throws IOException {
        lessonController = this;
        curDateLbl.setText("Сегодня " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MM yyyy")));
        scheduleBeans = getSheduleBeanList();
        lessonTime.setCellValueFactory(new PropertyValueFactory<>("lessonTime"));
        nameOfKurs.setCellValueFactory(new PropertyValueFactory<>("nameOfKurs"));
        scoolClass.setCellValueFactory(new PropertyValueFactory<>("scoolClass"));
        teacherOfLesson.setCellValueFactory(new PropertyValueFactory<>("teacherOfLesson"));
        selectLessonTable.setItems(getScheduleOnCurrentDate());
        newLesson.setCellFactory(labelCellFactory());

    }

    public Callback<TableColumn<ScheduleBean, String>, TableCell<ScheduleBean, String>> labelCellFactory() {
        Callback<TableColumn<ScheduleBean, String>, TableCell<ScheduleBean, String>> cellFactory =
                new Callback<TableColumn<ScheduleBean, String>, TableCell<ScheduleBean, String>>() {
                    @Override
                    public TableCell<ScheduleBean, String> call(final TableColumn<ScheduleBean, String> param) {
                        final TableCell<ScheduleBean, String> cell = new TableCell<ScheduleBean, String>() {
                            final Label lbl = new Label("Начать урок");

                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (!empty) {
                                    lbl.setOnMouseMoved(event -> lbl.setTextFill(Paint.valueOf("#a3748a")));
                                    lbl.setOnMouseExited(event -> lbl.setTextFill(Paint.valueOf("#fa3242")));
                                    lbl.setOnMouseClicked(event -> {
                                        scheduleBean = getTableView().getItems().get(getIndex());
                                        lessonBeans = getLessonBeanList();
                                        System.out.println(scheduleBean.toString());
                                        CreateLessonController.getLessonController()
                                                .setValuesForNewLesson(scheduleBean, lessonBeans.get(0));
                                        MainMenuController.getMenuController().onCreateLesson();
                                        //lbl.setDisable(true);
                                    });
                                    setGraphic(lbl);
                                    setText(null);
                                } else {
                                    setGraphic(null);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        return cellFactory;
    }

    public ObservableList<ScheduleBean> getScheduleOnCurrentDate(){
        ObservableList<ScheduleBean> scheduleOnCurDate =FXCollections.observableArrayList();
        LocalDate currentDate = LocalDate.now();
        Locale locale = new Locale("ru","RU", "Russian (Russia)");
        String day = currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, locale).toLowerCase();
        for (ScheduleBean scBean : scheduleBeans){
            if (scBean.getWeekDay().toLowerCase().equals(day)){
                scheduleOnCurDate.add(scBean);
            }
        }
        return scheduleOnCurDate;
    }

    public ObservableList<LessonBean> getLessonBeanList() {
        List<LessonBean> lessonBeans = new ArrayList<>();
        List<Users> studentsList = getStudentsList();
        List<Lesson> lessonList = lessonDAO().getAll();
        Class cl = classConverter().checkClassInDB(classConverter().fromString(scheduleBean.getScoolClass())) ;
        for (Lesson l : lessonList) {
            if (l.getDate().toLocalDate().isBefore(LocalDate.now()) && l.getClassFKId().equals(cl)) {
                homeTask = l.getHomeTask();
            }
        }
        int i = 1;
        for (Users st : studentsList) {
            if (st.getClassFKId().equals(cl)) {
                lessonBeans.add(new LessonBean(LocalDate.now().format(DateTimeFormatter.ofPattern("dd MM yyyy")),
                        scheduleBean.getLessonTime(),
                        scheduleBean.getScoolClass(), homeTask, null, i,
                        studentConverter().toString(st), null, null));
            }
            i++;
        }
        return FXCollections.observableList(lessonBeans);
    }

    public static ObservableList<LessonBean> getLessonBeans() {
        return lessonBeans;
    }
}