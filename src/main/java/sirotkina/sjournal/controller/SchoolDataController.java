package sirotkina.sjournal.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Paint;
import sirotkina.sjournal.domain.ClassBean;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Kurs;
import java.util.List;

import static sirotkina.sjournal.utils.ControllersUtils.*;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class SchoolDataController {

    private TableView.TableViewSelectionModel<ClassBean> selectionModelClass;
    private TableView.TableViewSelectionModel<Kurs> selectionModelKurs;
    private ObservableList<ClassBean> classObservableList;
    private ObservableList<Kurs> kursObservableList;
    @FXML
    private TableView<ClassBean> classTable;
    @FXML
    private TableColumn<ClassBean, String> idClass;
    @FXML
    private TableColumn<ClassBean, String> numberClass;
    @FXML
    private TableColumn<ClassBean, String> letterClass;
    @FXML
    private TableView<Kurs> kursTable;
    @FXML
    private TableColumn<Kurs, String> idKurs;
    @FXML
    private TableColumn<Kurs, String> nameOfKurs;
    @FXML
    private TextField numberFld;
    @FXML
    private TextField letterFld;
    @FXML
    private TextField kursNameFld;
    @FXML
    private Label msgLblClass;
    @FXML
    private Label msgLblKurs;

    @FXML
    public void initialize() {
        
        selectionModelClass = classTable.getSelectionModel();
        selectionModelClass.setSelectionMode(SelectionMode.MULTIPLE);
        if(selectionModelClass.getSelectedIndex() == 0){
            classTable.setEditable(false);
        } else {
            classTable.setEditable(true);
        }

        selectionModelKurs = kursTable.getSelectionModel();
        selectionModelKurs.setSelectionMode(SelectionMode.MULTIPLE);

        classObservableList = getClassBeanList();
        idClass.setCellValueFactory(new PropertyValueFactory<>("id"));

        numberClass.setCellValueFactory(new PropertyValueFactory<>("num"));
        numberClass.setCellFactory(TextFieldTableCell.forTableColumn());
        numberClass.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setNum(event.getNewValue()));

        letterClass.setCellFactory(TextFieldTableCell.forTableColumn());
        letterClass.setCellValueFactory(new PropertyValueFactory<>("letter"));
        letterClass.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setLetter(event.getNewValue()));
        classTable.setItems(classObservableList);

        kursObservableList = getKursList();
        idKurs.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameOfKurs.setCellValueFactory(new PropertyValueFactory<>("title"));
        nameOfKurs.setCellFactory(TextFieldTableCell.forTableColumn());
        nameOfKurs.setOnEditCommit(event -> event.getTableView()
                .getItems()
                .get(event.getTablePosition().getRow())
                .setTitle(event.getNewValue()));
        kursTable.setItems(kursObservableList);


    }

    public void onAddClass() {
        classObservableList.add(new ClassBean(null, numberFld.getText(), letterFld.getText()));
        classTable.setItems(classObservableList);
    }

    public void onAddKurs() {
        kursObservableList.add(new Kurs(null, kursNameFld.getText()));
        kursTable.setItems(kursObservableList);
    }

    public void onSaveClass() {
        try {
            //classObservableList.add(defaultClassBean());
            List<Class> classes = classDAO().getAll();
            for (Class clDAO : classes) {
                classDAO().deleteById(clDAO.getId());
            }
            for (ClassBean cl : classObservableList) {
                classDAO().save(new Class(null, Integer.valueOf(cl.getNum()), cl.getLetter()));
            }
            msgLblClass.setText("Ок!");
            msgLblClass.setTextFill(Paint.valueOf("#4f9302"));
        } catch (Exception e) {
            e.printStackTrace();
            msgLblClass.setText("Ошибка!");
            msgLblClass.setTextFill(Paint.valueOf("#fa3242"));
        }
    }

    public void onSaveKurs() {
        try {
            //kursObservableList.add(defaultKurs());
            List<Kurs> kursList = kursDAO().getAll();
            for (Kurs kurs: kursList){
                kursDAO().deleteById(kurs.getId());
            }
            for (Kurs k : kursObservableList) {
                kursDAO().save(new Kurs(null, k.getTitle()));
            }
            msgLblKurs.setText("Ок!");
            msgLblKurs.setTextFill(Paint.valueOf("#4f9302"));
        } catch (Exception e) {
            e.printStackTrace();
            msgLblKurs.setText("ошибка");
            msgLblKurs.setTextFill(Paint.valueOf("#fa3242"));
        }
    }

    public void onDeleteClass() {
        if(selectionModelClass.getSelectedIndex() != 0){
            deleteRow(classTable, selectionModelClass);
        }
    }

    public void onDeleteKurs() {
        if(selectionModelKurs.getSelectedIndex() != 0){
            deleteRow(kursTable, selectionModelKurs);
        }
    }

    public void onRestoreClass() {
        classTable.getItems().clear();
        classObservableList = getClassBeanList();
        classTable.setItems(getClassBeanList());
    }

    public void onRestoreKurs() {
        kursTable.getItems().clear();
        kursObservableList = getKursList();
        kursTable.setItems(getKursList());
    }
}
