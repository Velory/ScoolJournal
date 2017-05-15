package sirotkina.sjournal.controller.journal;

import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import sirotkina.sjournal.controller.MainMenuController;
import sirotkina.sjournal.domain.ScheduleBean;

public class LabelSellFactory implements Callback<TableColumn<ScheduleBean, String>, TableCell<ScheduleBean, String>>{

    @Override
    public TableCell<ScheduleBean, String> call(final TableColumn<ScheduleBean, String> param) {
        final TableCell<ScheduleBean, String> cell = new TableCell<ScheduleBean, String>(){
            final Label lbl = new Label("Начать урок");

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty){
                    lbl.setOnMouseMoved(event -> lbl.setTextFill(Paint.valueOf("#a3748a")));
                    lbl.setOnMouseExited(event -> lbl.setTextFill(Paint.valueOf("#fa3242")));
                    lbl.setOnMouseClicked(event -> new SelectLessonController().setCreateLesson());
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
}
