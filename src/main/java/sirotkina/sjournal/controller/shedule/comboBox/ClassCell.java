package sirotkina.sjournal.controller.shedule.comboBox;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import sirotkina.sjournal.entity.Class;

public class ClassCell implements Callback<ListView<Class>, ListCell<Class>>{

    @Override
    public ListCell<Class> call(ListView<Class> param) {
        return new ListCell<Class>(){
            @Override
            protected void updateItem(Class item, boolean empty){
                super.updateItem(item, empty);
                int index = this.getIndex();
                String name =null;
                if (item == null || empty){

                } else {
                    name = (index + 1) + ". " +
                            item.getNum() + "-" +
                            item.getLetter();
                }
                this.setText(name);
                setGraphic(null);
            }
        };
    }
}
