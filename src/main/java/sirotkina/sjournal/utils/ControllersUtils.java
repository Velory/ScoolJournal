package sirotkina.sjournal.utils;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import sirotkina.sjournal.domain.ClassBean;
import sirotkina.sjournal.entity.*;
import sirotkina.sjournal.entity.Class;
import java.util.Arrays;

import static sirotkina.sjournal.utils.ConvertersUtils.*;

public class ControllersUtils {

    private static final ClassBean DEFAULTCLASSBEAN;
    private static final Class DEFAULTCLASS;
    private static final Kurs DEFAULTKURS;

    static {
        DEFAULTCLASSBEAN = new ClassBean(null, "0", "N/A");
        DEFAULTCLASS = new Class(null, 0, "N/A");
        DEFAULTKURS = new Kurs(null, "N/A");
    }

    public static ClassBean defaultClassBean() {
        return DEFAULTCLASSBEAN;
    }

    public static Kurs defaultKurs() {
        return DEFAULTKURS;
    }

    public static Kurs defaultkursFromDB(){
        return kursConverter().checkKursInDB(DEFAULTKURS);
    }

    public static Class defaultClassFromDB() {
        return classConverter().checkClassInDB(DEFAULTCLASS);
    }

    public static void nodeIsActive(boolean b, Node... nodes) {
        if (b) {
            for (Node node : nodes) {
                node.setDisable(false);
                node.setVisible(true);
            }
        } else {
            for (Node node : nodes) {
                node.setDisable(true);
                node.setVisible(false);
            }
        }
    }

    public static void deleteRow (TableView tableView,
                                  TableView.TableViewSelectionModel selectionModel){
        if (selectionModel.isEmpty()) return;

        ObservableList<Integer> list = selectionModel.getSelectedIndices();
        Integer[] selectedIndices = new Integer[list.size()];
        selectedIndices = list.toArray(selectedIndices);
        Arrays.sort(selectedIndices);

        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            selectionModel.clearSelection(selectedIndices[i]);
            tableView.getItems().remove(selectedIndices[i].intValue());
        }
    }
}
