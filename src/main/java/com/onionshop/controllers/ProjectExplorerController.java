package com.onionshop.controllers;

import com.onionshop.managers.ProjectManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;


public class ProjectExplorerController {
    /**
     * Controller for the project explorer scene
     * In this scene users can choose between:
     * - Open projects from recent projects.
     * - Import .onion project files from file explorer
     * - Create new project -> open new project scene using NewProjectController.
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
        SceneSwitcher.switchScene(getClass(), event,"/com/onionshop/NewProject.fxml");
    }


    /**
     * Allows user to choose a .onion file to import into Onionshop for editing.
     * Switches to Main Canvas Scene
     */
    @FXML
    public void onBtnOpenClick(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();

            // Set extension filter
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("ONION files (*.onion)", "*.onion");
            fileChooser.getExtensionFilters().add(extFilter);

            File selectedFile = fileChooser.showOpenDialog(null);
            ProjectManager.getInstance().loadProject(selectedFile);

            SceneSwitcher.switchScene(getClass(), event,"/com/onionshop/main-canvas-view.fxml");
        } catch (Exception exception) {
            System.out.println("Error: Could not load project");
            exception.printStackTrace();
        }
    }
    
}