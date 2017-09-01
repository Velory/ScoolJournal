package sirotkina.sjournal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Paint;
import sirotkina.sjournal.domain.ClassBean;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Kurs;
import java.util.ArrayList;
import java.util.Iterator;
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

    public ObservableList<Kurs> getKursList() {
        List<Kurs> kursList = new ArrayList<>();
        List<Kurs> kurses = kursDAO().getAll();
        Iterator<Kurs> itr = kurses.iterator();
        while (itr.hasNext()){
            Kurs k = itr.next();
            if (k.getTitle().equals(defaultKurs().getTitle())){
                itr.remove();
            }
        }
        kursList.add(defaultKurs());
        kursList.addAll(kurses);
        return FXCollections.observableList(kursList);
    }

    public ObservableList<ClassBean> getClassBeanList() {
        List<ClassBean> classBeanList = new ArrayList<>();
        List<Class> classes = classDAO().getAll();
        Iterator<Class> itr = classes.iterator();
        while (itr.hasNext()){
            Class cl1 = itr.next();
            if (cl1.getLetter().equals(defaultClassBean().getLetter())){
                itr.remove();
            }
        }
        classBeanList.add(defaultClassBean());
        for (Class cl: classes){
            classBeanList.add(new ClassBean(cl.getId(), String.valueOf(cl.getNum()), cl.getLetter()));
        }
        return FXCollections.observableList(classBeanList);
    }

    @FXML
    public void initialize() {
        classTable.setEditable(true);

        selectionModelClass = classTable.getSelectionModel();
        selectionModelClass.setSelectionMode(SelectionMode.MULTIPLE);

        selectionModelKurs = kursTable.getSelectionModel();
        selectionModelKurs.setSelectionMode(SelectionMode.MULTIPLE);

        classObservableList = getClassBeanList();
        idClass.setCellValueFactory(new PropertyValueFactory<>("id"));
        numberClass.setCellValueFactory(new PropertyValueFactory<>("num"));
        numberClass.setCellFactory(TextFieldTableCell.forTableColumn());
        numberClass.setOnEditCommit(event -> {
            ClassBean classBean = event.getTableView().getItems()
                    .get(event.getTablePosition().getRow());
            if (classBean.getLetter().equals(defaultClassBean().getLetter())){
                classBean.setNum(event.getOldValue());
            }else {
                classBean.setNum(event.getNewValue());
            }
        });
        letterClass.setCellFactory(TextFieldTableCell.forTableColumn());
        letterClass.setCellValueFactory(new PropertyValueFactory<>("letter"));
        letterClass.setOnEditCommit(event -> {
            ClassBean classBean = event.getTableView().getItems()
                    .get(event.getTablePosition().getRow());
            if (classBean.getLetter().equals(defaultClassBean().getLetter())){
                classBean.setLetter(event.getOldValue());
            }else {
                classBean.setLetter(event.getNewValue());
            }
        });
        classTable.setItems(classObservableList);

        kursObservableList = getKursList();
        idKurs.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameOfKurs.setCellValueFactory(new PropertyValueFactory<>("title"));
        nameOfKurs.setCellFactory(TextFieldTableCell.forTableColumn());
        nameOfKurs.setOnEditCommit(event -> {
            Kurs kurs = event.getTableView().getItems()
                    .get(event.getTablePosition().getRow());
            if (kurs.getTitle().equals(defaultKurs().getTitle())){
                kurs.setTitle(event.getOldValue());
            } else {
                kurs.setTitle(event.getNewValue());
            }
        });
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
            List<Kurs> kursList = kursDAO().getAll();
            for (Kurs k: kursList){
                kursDAO().deleteById(k.getId());
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
