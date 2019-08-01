package ru.bkmz.lib.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import static ru.bkmz.lib.MainDictionary.bd;

public class ControllerAdd {
    public Button add;
    public TextArea nameText;
    public TextArea comintText;
    public TextArea urlText;
    public Button menuButton;


    public void onAdd(ActionEvent actionEvent) {
        add.getScene().getWindow().hide();
        if (!comintText.getText().equals("") && !nameText.getText().equals("")) {
            bd.sqliteAdd(nameText.getText(), comintText.getText(), urlText.getText(), true);
        }
        new StageStandart("fxml/lib.fxml", true);
    }

    public void menuButton(ActionEvent actionEvent) {
        menuButton.getScene().getWindow().hide();
        new StageStandart("fxml/lib.fxml", true);
    }
}
