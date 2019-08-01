package ru.bkmz.lib;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.bkmz.lib.table.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static ru.bkmz.lib.MainDictionary.appdata;

public class Processes {
    public static void file(String[] url) {
        for (String s :
                url) {
            s = appdata + s;
            File file = new File(s);
            if (!file.exists()) {
                System.out.println(s);
                file.mkdir();
            }
        }
    }

    public static void column(TableView<Table> table, String nameColumn) {
        TableColumn<Table, String> name = new TableColumn<>(nameColumn);
        name.setMinWidth(100);
        name.setCellValueFactory(new PropertyValueFactory<>(nameColumn));
        table.getColumns().addAll(name);
    }

}

class CopyFiles {
    private static String urlout;

    public static void failCopi(String url, String fileName) {
        File f = new File(appdata + "res/" + url + fileName);
        if (!f.exists() || (fileName.equals("language"))) {


            fileResources(appdata + "res");
            fileResources(appdata + "res/" + url);

            try {
                InputStream inpStream = CopyFiles.class.getClassLoader().getResourceAsStream(url + fileName);

                if (inpStream == null) throw new FileNotFoundException(url + fileName + " not found");
                Path target = Paths.get(urlout + fileName);
                Files.copy(inpStream, target, REPLACE_EXISTING);
                inpStream.close();
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    private static void fileResources(String name) {
        File file = new File(name);
        urlout = name;
        if (!file.exists()) {
            file.mkdir();
        }

    }
}