package com.onionshop.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    /**
     Switches the scenes from the current event to the given fxml file
     @param currentClass: the class that this method was called from
     @param event:        the click event context
     @param fxml:         the file path to the fxml file that contains the scene we want to switch to
     */
    public static Scene switchScene(Class currentClass, Event event, String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(currentClass.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     Switches the scenes from the current event to the given fxml file
     Sets up the key event handler for the scene the given event occurs in
     @param currentClass: the class that this method was called from
     @param event:        the click event context
     @param fxml:         the file path to the fxml file that contains the scene we want to switch to
     */
    public static void switchSceneWithKeyEventsInit(Class currentClass, Event event, String fxml) {
        Scene scene = switchScene(currentClass, event, fxml);
        if (scene != null) {
            scene.setOnKeyPressed(new KeyboardEventController());
        }
    }
}
