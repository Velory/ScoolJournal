package sirotkina.sjournal.controller.shedule;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sirotkina.sjournal.domain.ScheduleBean;

import static sirotkina.sjournal.utils.ObservableListUtils.*;

public class ScheduleTableView {

    @FXML
    protected TableView<ScheduleBean> curentSchedule;
    @FXML
    protected TableColumn<ScheduleBean, String> day;
    @FXML
    protected TableColumn<ScheduleBean, String> scoolClass;
    @FXML
    protected TableColumn<ScheduleBean, Integer> id;
    @FXML
    protected TableColumn<ScheduleBean, String> lessonTime;
    @FXML
    protected TableColumn<ScheduleBean, String> nameOfKurs;
    @FXML
    protected TableColumn<ScheduleBean, String> teacherOfLesson;
    private ObservableList<ScheduleBean> tableElements;

    @FXML
    protected void initialize() {
        tableElements = getSheduleBeanList();
        day.setCellValueFactory(new PropertyValueFactory<>("weekDay"));
        scoolClass.setCellValueFactory(new PropertyValueFactory<>("scoolClass"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        lessonTime.setCellValueFactory(new PropertyValueFactory<>("lessonTime"));
        nameOfKurs.setCellValueFactory(new PropertyValueFactory<>("nameOfKurs"));
        teacherOfLesson.setCellValueFactory(new PropertyValueFactory<>("teacherOfLesson"));
        curentSchedule.setItems(tableElements);
    }

    public ObservableList<ScheduleBean> getTableElements() {
        return tableElements;
    }

    public void onRestore(){
        initialize();
    }

}
