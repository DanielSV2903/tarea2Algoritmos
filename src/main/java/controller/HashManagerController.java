package controller;

import domain.hash.HashMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class HashManagerController {

    @javafx.fxml.FXML
    private TableView<List<String>> tview;
    @javafx.fxml.FXML
    private TableColumn<List<String>, String> nameCol;
    @javafx.fxml.FXML
    private TableColumn<List<String>, String> numberCol;

    private HashMap<String, String> contacts;
    private ObservableList<List<String>> data;

    @FXML
    public void initialize() {
        contacts = new HashMap<>();
        contacts.put("Daniel","8888-4444");
        contacts.put("Aidan","4445-3355");
        contacts.put("Andreé","5566-8877");
        contacts.put("Jerome","8791-4452");

        data = FXCollections.observableArrayList();
        data=getData();
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        numberCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));

        tview.setItems(data);
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        Alert addPopup = new Alert(Alert.AlertType.INFORMATION);
        addPopup.setTitle("Add Contact");
        addPopup.setHeaderText(null);
        addPopup.setContentText("Add Contact");

        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.add(new Label("Nombre"), 0, 0);
        gp.add(new Label("Telefono"), 0, 1);

        TextField tfName = new TextField();
        tfName.setPromptText("Nombre");
        gp.add(tfName, 1, 0);

        TextField tfPhone = new TextField();
        tfPhone.setPromptText("Telefono");
        gp.add(tfPhone, 1, 1);

        addPopup.getDialogPane().setContent(gp);
        addPopup.showAndWait();

        String name = tfName.getText();
        String phone = tfPhone.getText();
        if (!name.isEmpty() && !phone.isEmpty()) {
            contacts.put(name, phone);
            updateTableView();
        }
    }

    private void updateTableView() {
        data = getData();
        tview.setItems(data);
    }

    private ObservableList<List<String>> getData() {
        ObservableList<List<String>> data = FXCollections.observableArrayList();
        for (String key : contacts.keySet()) {
            List<String> row = new ArrayList<>();
            row.add(key);
            row.add(contacts.get(key));
            data.add(row);
        }
        return data;
    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {
        Alert removePopup = new Alert(Alert.AlertType.CONFIRMATION);
        removePopup.setTitle("Remove Contact");
        removePopup.setHeaderText(null);
        removePopup.setContentText("Remove Contact");
        ChoiceBox<List<String>> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(data);
        removePopup.getDialogPane().setContent(choiceBox);
        removePopup.showAndWait();
        String key="";
        if (choiceBox.getSelectionModel().getSelectedItem() != null)
            key= choiceBox.getSelectionModel().getSelectedItem().get(0);
        contacts.remove(key);
        updateTableView();
    }
}
