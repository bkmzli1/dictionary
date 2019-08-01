package ru.bkmz.lib.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import static ru.bkmz.lib.MainDictionary.bd;


public class ControllerEdet {
    @FXML
    public TextArea nameTex;
    public TextArea comintTex;
    public TextArea urlTex;
    public TextArea idEditTex;
    public Button editButton;
    public Button menuButton;
    public Button get;

    @FXML
    public void initialize() {

    }


    public void editButton(ActionEvent actionEvent) {
        editButton.getScene().getWindow().hide();
        try {
            bd.sqliteUp(Integer.parseInt(idEditTex.getText()) - 1, nameTex.getText(), comintTex.getText(), urlTex.getText(), idEditTex.getText());
        } catch (Exception ignored) {
        }
        new StageStandart("fxml/lib.fxml", true);
    }

    public void menuButton(ActionEvent actionEvent) {
        menuButton.getScene().getWindow().hide();
        new StageStandart("fxml/lib.fxml", true);
    }

    public void onGet(ActionEvent actionEvent) {
        try {
            if (!idEditTex.equals("")) {
                int id = Integer.parseInt(idEditTex.getText());
                id--;

                nameTex.setText(bd.getName(id));
                comintTex.setText(bd.getComint(id));
                urlTex.setText(bd.getUrl(id));

            }
        } catch (Exception e) {

        }
    }
}
