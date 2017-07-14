package sirotkina.sjournal.controller.journal;

import javafx.beans.NamedArg;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import sirotkina.sjournal.domain.JournalBean;

/**
 * Created by Марина on 06.07.2017.
 */
public class JournalPropertyValueFactory extends PropertyValueFactory<JournalBean, String> {
    /**
     * Creates a default PropertyValueFactory to extract the value from a given
     * TableView row item reflectively, using the given property name.
     *
     * @param property The name of the property with which to attempt to
     *                 reflectively extract a corresponding value for in a given object.
     */

    public JournalPropertyValueFactory(@NamedArg("property") String property) {
        super(property);
    }

    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<JournalBean, String> param){
        return new ReadOnlyStringWrapper(param.getValue()
                .getDays()[Integer.valueOf(getProperty().substring(5, 7))]);
    }

}
