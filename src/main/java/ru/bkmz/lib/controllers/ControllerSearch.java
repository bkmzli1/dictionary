package ru.bkmz.lib.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import ru.bkmz.lib.Processes;
import ru.bkmz.lib.table.BD;
import ru.bkmz.lib.table.Table;

import java.util.ArrayList;

import static ru.bkmz.lib.MainDictionary.bd;

public class ControllerSearch {


    public Button menuButton;
    public TextArea comintTex;
    public TableView<Table> table;
    public ToggleButton dictionaryButoon;
    public ToggleButton nameButton;
    public ToggleButton idButoon;
    private ArrayList<Integer> id, idPowtor;
    private ObservableList<Table> tables;

    @FXML
    public void initialize() {
        for (String name :
                BD.nameColumn) {
            if (!name.equals("users"))
                Processes.column(table, name);
        }
    }

    public void menuButton(ActionEvent actionEvent) {
        menuButton.getScene().getWindow().hide();
        new StageStandart("fxml/lib.fxml", true);
    }


    public void onSearch(ActionEvent actionEvent) {
        search();

    }

    private void search() {
        if (!comintTex.getText().equals("") & !comintTex.getText().equals("\n")) {
            id = new ArrayList<Integer>();
            idPowtor = new ArrayList<Integer>();
            tables = FXCollections.observableArrayList();
            String sr = comintTex.getText().toUpperCase();
            if (nameButton.isSelected()) {
                for (int i = 0; i < bd.idAll; i++) {
                    String s = bd.getName(i).toUpperCase();
                    int col = s.indexOf(sr);
                    if (col != -1) {

                        this.id.add(i);
                    }
                }
            }
            if (dictionaryButoon.isSelected()) {
                for (int i = 0; i < bd.idAll; i++) {
                    String s = bd.getComint(i).toUpperCase();
                    int col = s.indexOf(sr);
                    if (col != -1) {

                        this.id.add(i);
                    }
                }
            }
            if (idButoon.isSelected()) {
                for (int i = 0; i < bd.idAll; i++) {
                    String s = String.valueOf(bd.getId(i));
                    int col = s.indexOf(sr);
                    if (col != -1) {

                        this.id.add(i);
                    }
                }
            }
            for (int i :
                    this.id) {
                boolean powtor = false;
                for (int j :
                        idPowtor) {
                    if (i == j) {
                        powtor = true;
                    }
                }
                if (!powtor) {
                    searchTable(i);
                    idPowtor.add(i);
                }
            }
            table.setItems(tables);
        }
    }

    private ObservableList<Table> searchTable(int i) {
        tables.add(new Table(bd.getId(i), bd.getName(i), bd.getComint(i), bd.getUrl(i)));

        return tables;
    }

    public void nameButton(ActionEvent actionEvent) {
        ofOn(nameButton);

    }

    public void dictionaryButoon(ActionEvent actionEvent) {
        ofOn(dictionaryButoon);
    }

    public void idButoon(ActionEvent actionEvent) {
        ofOn(idButoon);
    }

    private void ofOn(ToggleButton b) {
        if (b.isSelected()) {
            b.setText("on name");
        } else {
            b.setText("off name");
        }
    }
}
