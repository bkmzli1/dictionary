package ru.bkmz.lib.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static ru.bkmz.lib.MainDictionary.bd;

public class ControllerDelete {
    @FXML
    public TextField idTex;
    public Button deleteButton;
    public Button menuButton;


    @FXML
    public void initialize() {

    }


    public void deleteButton(ActionEvent actionEvent) {


        try {
            bd.sqliteDelete((Integer.decode(idTex.getText())));
        } catch (Exception ignored) {
        }
        deleteButton.getScene().getWindow().hide();
        new StageStandart("fxml/lib.fxml", true);
    }

    public void menuButton(ActionEvent actionEvent) {
        menuButton.getScene().getWindow().hide();
        new StageStandart("fxml/lib.fxml", true);
    }

}

