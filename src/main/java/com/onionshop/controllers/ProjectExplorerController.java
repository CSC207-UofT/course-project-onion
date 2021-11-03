package com.onionshop.controllers;

import com.onionshop.managers.ProjectManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class ProjectExplorerController implements Initializable {
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

    public List<String> mostRecentProjects;


    /**
     * This function is called when this scene is first initialized
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostRecentProjects = Arrays.asList("Project 1", "Project 2");
    }

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

            SceneSwitcher.switchSceneWithKeyEventsInit(getClass(), event,"/com/onionshop/main-canvas-view.fxml");
        } catch (Exception exception) {
            System.out.println("Error: Could not load project");
            exception.printStackTrace();
        }
    }
}