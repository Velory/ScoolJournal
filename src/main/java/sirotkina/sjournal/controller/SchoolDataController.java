package sirotkina.sjournal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sirotkina.sjournal.entity.Class;
import sirotkina.sjournal.entity.Kurs;

import static sirotkina.sjournal.utils.ControllersUtils.*;

public class SchoolDataController {

    @FXML
    private TableView<Class> classTable;
    @FXML
    private TableColumn<Class, String> idClass;
    @FXML
    private TableColumn<Class, String> numberClass;
    @FXML
    private TableColumn<Class, String> letterClass;
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
    private Button addClass;
    @FXML
    private Button saveClass;
    @FXML
    private Button addKurs;
    @FXML
    private Button saveKurs;

    @FXML
    public void initialize(){
        idClass.setCellValueFactory(new PropertyValueFactory<>("id"));
        numberClass.setCellValueFactory(new PropertyValueFactory<>("num"));
        letterClass.setCellValueFactory(new PropertyValueFactory<>("letter"));
        classTable.setItems(getClassList());

        kursTable.setItems(getKursList());
        idKurs.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameOfKurs.setCellValueFactory(new PropertyValueFactory<>("title"));
    }


}
