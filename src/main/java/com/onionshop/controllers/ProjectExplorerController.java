package com.onionshop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ProjectExplorerController {
    /**
     * Keep track of most recent projects (txt, csv).
     * Open projects from recent projects.
     * Import .onion project files from file explorer
     * Creates new project -> open new project scene using NewProjectController.
     *
     */

    @FXML
    public Button btnNewProject;

    @FXML
    public Button btnOpen;

    /**
     * Changes the view to the NewProject scene
     */
    @FXML
    private void onBtnNewProjectClick(ActionEvent event) {
        switchScene(event,"/com/onionshop/NewProject.fxml");
    }


    /*
     * Todo: when you click open, the file explorer should popup, and the selected
     *  .onion file should be loaded on to the canvas
     */
    @FXML
    public void onBtnOpenClick() {
    }

    private void switchScene(ActionEvent event, String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load());;
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}