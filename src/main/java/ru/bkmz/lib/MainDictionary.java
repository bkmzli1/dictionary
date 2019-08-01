package ru.bkmz.lib;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import ru.bkmz.lib.controllers.StageStandart;
import ru.bkmz.lib.table.BD;
import ru.bkmz.lib.table.Table;

/*TODO: 01.08.2019
 * -увеличь список сайтов и оброботку к ним!
 *
 * */
public class MainDictionary extends Application {

    public static final String nameStage = "dictionary";
    public static final String appdata = System.getenv("APPDATA") + "\\.dictionary\\";
    private static final String[] filesAll = new String[]{""};
    public static int bigName = 0;

    public static BD bd;

    public static void main(String[] args) {
        launch(args);
    }

    public static ObservableList<Table> getTable() {

        ObservableList<Table> tables = FXCollections.observableArrayList();
        for (int i = 0; i < bd.idAll; i++) {
            String name = bd.getName(i);
            tables.add(new Table(bd.getId(i), name, bd.getComint(i), bd.getUrl(i)));
            char[] nameC = name.toCharArray();
            if (nameC.length > bigName) {
                bigName = nameC.length;
            }

        }
        return tables;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new StageStandart("fxml/lib.fxml", true);
    }

    @Override
    public void init() throws Exception {

        System.out.println("start");
        Processes.file(filesAll);
        CopyFiles.failCopi("BD/", "BD");

    }
}
