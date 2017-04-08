package sirotkina.sjournal.controller.shedule;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CurrentSchedule extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/currentSchedule"));

        /*ComboBox<Class> classComboBox = new ComboBox<>();
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
        root.getChildren().addAll(classComboBox, scheduleTab);*/

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
