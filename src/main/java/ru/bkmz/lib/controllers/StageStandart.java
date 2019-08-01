package ru.bkmz.lib.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static ru.bkmz.lib.MainDictionary.nameStage;

public class StageStandart extends Stage {
    boolean follScren;

    public StageStandart(String name, boolean follScren) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource(name)));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setMaximized(follScren);
        Parent root = loader.getRoot();
        stage.setTitle(nameStage);
        stage.setScene(new Scene(root));
        InputStream inputStream = ClassLoader.class.getResourceAsStream("/icon/icon.png");
        Image image;
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.exit(-1);
            }
        });
        image = new Image(inputStream);

        stage.getIcons().add(image);
        stage.show();
        this.follScren = stage.isMaximized();
    }

    public boolean isFollScren() {
        return follScren;
    }
}
