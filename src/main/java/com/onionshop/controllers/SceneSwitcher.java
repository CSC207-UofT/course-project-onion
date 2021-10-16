package com.onionshop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    /**
     Switches the scenes from the current event to the given fxml file
     @param currentClass: the class that this method was called from
     @param event:        the click event context
     @param fxml:         the file path to the fxml file that contains the scene we want to switch to
     */
    public static void switchScene(Class currentClass, ActionEvent event, String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(currentClass.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load());;
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
