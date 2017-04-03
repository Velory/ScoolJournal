package sirotkina.sjournal.controller.shedule.comboBox;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import sirotkina.sjournal.dao.ClassDAO;
import sirotkina.sjournal.entity.Class;

public class ClassComBoxUtil {

    public ObservableList<Class> getClassList (){
        ClassDAO classDAO = new ClassDAO();
        return FXCollections.observableList(classDAO.getAll());
    }

    public void valueChanged (ComboBox<Class> list){
        Class cl = list.getValue();
        String name = cl.getNum() + "-" + cl.getLetter();
        //add code to change the table
    }

    public void classChanged(ObservableValue<? extends Class> observable,
                              Class oldValue, Class newValue) {
        System.out.println("Itemchanged: old = " + oldValue + ", new = " + newValue);
    }

    public void indexChanged(ObservableValue<? extends Number> observable,
                             Number oldValue, Number newValue) {
        System.out.println( "Indexchanged: old = " + oldValue + ",  new = " + newValue);
    }
}
