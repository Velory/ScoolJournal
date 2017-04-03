package sirotkina.sjournal.controller.shedule;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sirotkina.sjournal.controller.shedule.comboBox.ClassCell;
import sirotkina.sjournal.controller.shedule.comboBox.ClassComBoxUtil;
import sirotkina.sjournal.controller.shedule.comboBox.ClassStringConverter;
import sirotkina.sjournal.controller.shedule.tableView.ScheduleTableViewUtil;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Schedule;

public class CurrentSchedule extends Application{
    private ScheduleTableViewUtil util;
    private ClassComBoxUtil classUtil;

    public CurrentSchedule() {
        util = new ScheduleTableViewUtil();
        classUtil = new ClassComBoxUtil();
    }

    @Override
    public void start(Stage stage) throws Exception {

        ComboBox<Class> classComboBox = new ComboBox<>();
        classComboBox.setEditable(true);
        classComboBox.setConverter(new ClassStringConverter());
        classComboBox.setCellFactory(new ClassCell());
        classComboBox.getSelectionModel().selectedItemProperty().addListener(classUtil::classChanged);
        classComboBox.getSelectionModel().selectedIndexProperty().addListener(classUtil::indexChanged);
        classComboBox.setOnAction(event -> classUtil.valueChanged(classComboBox));

        TableView<Schedule> scheduleTab = new TableView<>(util.getSheduleList());
        scheduleTab.getColumns().addAll(util.getWeekDayColumn(),
                util.getScoolClassColumn(),
                util.getIdColumn(),
                util.getlessonTime(),
                util.getnameOfKurs(),
                util.getteacherOfLesson());

        VBox root = new VBox();
        root.getChildren().addAll(classComboBox, scheduleTab);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
