package sirotkina.sjournal.controller.shedule;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sirotkina.sjournal.dao.ClassDAO;
import sirotkina.sjournal.dao.KursDAO;
import sirotkina.sjournal.dao.LessonDAO;
import sirotkina.sjournal.dao.TeachersDAO;
import sirotkina.sjournal.domain.Schedule;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Kurs;
import sirotkina.sjournal.entity.Lesson;
import sirotkina.sjournal.entity.Teachers;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ScheduleTableView {

    private ObservableList<Schedule> tableElements;

    protected ObservableList<Schedule> getSheduleList (){
        List<Schedule> scheduleList = new ArrayList<>();

        List<Lesson> lessons = lessonDAO().getAll();
        for (Lesson l: lessons){
            Class cl = classDAO().getById(l.getClassFKId().getId());
            Kurs kurs = kursDAO().getById(l.getKursFKId().getId());
            Teachers teacher = teachersDAO().getById(l.getTeachersFKId().getId());
            scheduleList.add(new Schedule(l.getDate().toString(), cl.getNum()+ "-" + cl.getLetter(),
                    l.getId(), l.getTime().toString(), kurs.getTitle(),
                    teacher.getFirstName() + " " + teacher.getLastName()));
        }
        return FXCollections.observableList(scheduleList);
    }

    @FXML
    protected TableView<Schedule> curentSchedule;

    @FXML
    protected TableColumn<Schedule, String> day;

    @FXML
    protected TableColumn<Schedule, String> scoolClass;

    @FXML
    protected TableColumn<Schedule, Integer> id;

    @FXML
    protected TableColumn<Schedule, String> lessonTime;

    @FXML
    protected TableColumn<Schedule, String> nameOfKurs;

    @FXML
    protected TableColumn<Schedule, String> teacherOfLesson;

    @FXML
    protected void initialize(){
        tableElements = getSheduleList();
        day.setCellValueFactory(new PropertyValueFactory<>("weekDay"));
        scoolClass.setCellValueFactory(new PropertyValueFactory<>("scoolClass"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        lessonTime.setCellValueFactory(new PropertyValueFactory<>("lessonTime"));
        nameOfKurs.setCellValueFactory(new PropertyValueFactory<>("nameOfKurs"));
        teacherOfLesson.setCellValueFactory(new PropertyValueFactory<>("teacherOfLesson"));
        curentSchedule.setItems(tableElements);
    }

    public ObservableList<Schedule> getTableElements() {
        return tableElements;
    }
}
