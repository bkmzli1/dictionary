package ru.bkmz.lib.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import static ru.bkmz.lib.MainDictionary.bd;

public class ControllerGet {


    public Button menuButton;
    public Button search;
    public TextArea txt;
    public TextArea txtId;

    @FXML
    public void initialize() {

    }

    public void menuButton(ActionEvent actionEvent) {
        menuButton.getScene().getWindow().hide();
        new StageStandart("fxml/lib.fxml", true);
    }

    public void onSearch(ActionEvent actionEvent) {
        txt.setText(bd.getUrl(Integer.parseInt(txtId.getText()) - 1));
    }
}
