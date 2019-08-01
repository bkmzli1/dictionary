package ru.bkmz.lib.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import ru.bkmz.lib.GetDictionary;
import ru.bkmz.lib.Processes;
import ru.bkmz.lib.table.BD;
import ru.bkmz.lib.table.Table;

import static ru.bkmz.lib.MainDictionary.*;


public class Controller {
    public static GetDictionary getDictionary;
    @FXML
    public Button add;
    @FXML
    public Button delete;
    @FXML
    public Button edition;
    @FXML
    public TableView<Table> table;
    public Button search;
    public Button get;
    public Button update;
    public ProgressBar progresBar;
    public Text text;

    @FXML
    public void initialize() {
        upTable();
        tableSetings();
    }

    @FXML
    public void onAdd(ActionEvent actionEvent) {

        add.getScene().getWindow().hide();

        StageStandart stageStandart = new StageStandart("fxml/libAdd.fxml", false);


    }


    @FXML
    public void onDelete(ActionEvent actionEvent) {
        delete.getScene().getWindow().hide();

        StageStandart stageStandart = new StageStandart("fxml/libDelete.fxml", false);
    }

    @FXML
    public void onEdition(ActionEvent actionEvent) {
        edition.getScene().getWindow().hide();

        StageStandart stageStandart = new StageStandart("fxml/libEdit.fxml", false);
    }

    void upTable() {
        bd = new BD();
        bd.sqliteInfo();
        bd.sqlite();
        for (String name :
                BD.nameColumn) {
            if (!name.equals("users"))
                Processes.column(table, name);
        }
        table.setItems(getTable());
        bd = new BD();
        bd.sqliteInfo();
        bd.sqlite();


    }


    public void onSearch(ActionEvent actionEvent) {
        search.getScene().getWindow().hide();
        StageStandart stageStandart = new StageStandart("fxml/libSearch.fxml", true);
    }

    public void onGet(ActionEvent actionEvent) {
        get.getScene().getWindow().hide();
        StageStandart stageStandart = new StageStandart("fxml/libGet.fxml", false);
    }

    public void onUpdate(ActionEvent actionEvent) {
        update.setDisable(true);
        search.setDisable(true);
        get.setDisable(true);
        edition.setDisable(true);
        delete.setDisable(true);
        add.setDisable(true);
        new Thread(() -> {

            getDictionary = new GetDictionary();


            getDictionary.start(text);
            bd.clier(text);

            bd = new BD();
            bd.sqliteInfo();
            bd.sqlite();
            table.setItems(getTable());
            for (int i = 0; i < getDictionary.name.size(); i++) {
                double x;

                x = i;
                x = x / getDictionary.name.size();
                bd = new BD();
                bd.sqliteInfo();
                bd.sqlite();
                table.setItems(getTable());
                progresBar.setProgress(x);
                text.setText((int) (x * 100) + "%  " + i + "/" + getDictionary.name.size());
                bd.sqliteAdd(getDictionary.name.get(i), getDictionary.values.get(i), getDictionary.url.get(i), false);
                tableSetings();
            }


            bd = new BD();
            bd.sqliteInfo();
            bd.sqlite();
            table.setItems(getTable());
            progresBar.setProgress(0);
            text.setText("");
            update.setDisable(false);
            search.setDisable(false);
            get.setDisable(false);
            edition.setDisable(false);
            delete.setDisable(false);
            add.setDisable(false);
            bd = new BD();
            bd.sqliteInfo();
            bd.sqlite();
            table.setItems(getTable());
        }).start();


    }

    void tableSetings() {
        int colOne = 13;

        char[] s = String.valueOf(bd.idAll).toCharArray();

        table.getColumns().get(0).setMinWidth(colOne * s.length);
        table.getColumns().get(0).setMaxWidth(colOne * s.length);
        table.getColumns().get(1).setMaxWidth(colOne * bigName);
        table.getColumns().get(1).setMinWidth((colOne * bigName) / 500);
        table.getColumns().get(3).setMaxWidth(500);
        table.getColumns().get(3).setMinWidth(20);
        table.setItems(getTable());
    }


    public void information(ActionEvent actionEvent) {
        String inf = "";
        inf += "Developer Ilya Egorushkin\n";
        getDictionary = new GetDictionary();
        for (String url :
                getDictionary.urls) {
            inf += "url:" + url + "\n";
        }
        notification("Information", inf);
    }

    private void notification(String name, String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, info);

        alert.setTitle(name);
        alert.setHeaderText(null);
        alert.setContentText(info);
        alert.showAndWait();
    }

}
