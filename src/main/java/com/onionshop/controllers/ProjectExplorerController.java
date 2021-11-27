package com.onionshop.controllers;

import com.onionshop.managers.MostRecentProjectManager;
import com.onionshop.managers.ProjectManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class ProjectExplorerController implements Initializable {
    /**
     * Controller for the project explorer scene
     * In this scene users can choose between:
     * - Open projects from recent projects.
     * - Import .onion project files from file explorer
     * - Create new project -> open new project scene using NewProjectController.
     */

    @FXML public Button btnNewProject;
    @FXML public Button btnOpen;
    @FXML public VBox mostRecentProjectsVBox;

    private final MostRecentProjectManager mostRecentProjectManager = new MostRecentProjectManager();
    private final ProjectManager projectManager = ProjectManager.getInstance();

    /**
     * This function is called when this scene is first initialized
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMostRecentProject();
    }

    private void initMostRecentProject() {
        try {
            String[][] mostRecentProjects = mostRecentProjectManager.getMostRecentProjects();
            for (String[] mostRecentProject : mostRecentProjects) {
                String name = mostRecentProject[0];
                String path = mostRecentProject[1];
                MostRecentProjectUI mostRecentProjectUI = new MostRecentProjectUI(
                        name, path, event -> openMostRecentProject(name, path, event)
                );
                mostRecentProjectsVBox.getChildren().add(mostRecentProjectUI);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the view to the NewProject scene
     */
    @FXML
    private void onBtnNewProjectClick(ActionEvent event) {
        SceneSwitcher.switchScene(getClass(), event,"/com/onionshop/NewProject.fxml");
    }

    /**
     * Opens a most recently edited project
     * @param name name of the project being opened
     * @param path path of the project being opened
     * @param event the event that caused the project to be opened
     */
    private void openMostRecentProject (String name, String path, Event event) {
        try {
            if(name != null && path != null && !name.equals("") && !path.equals("")) {
                projectManager.loadProject(path);
                mostRecentProjectManager.addMostRecentProject(name, path);
                SceneSwitcher.switchSceneWithKeyEventsInit(getClass(), event, "/com/onionshop/main-canvas-view.fxml");
            }
        } catch (Exception e) {
            System.out.println("Error: Could not load project");
            e.printStackTrace();
        }
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

            String selectedFilePath = selectedFile.getAbsolutePath();
            projectManager.loadProject(selectedFilePath);
            mostRecentProjectManager.addMostRecentProject(selectedFile.getName(), selectedFilePath);

            SceneSwitcher.switchSceneWithKeyEventsInit(getClass(), event,"/com/onionshop/main-canvas-view.fxml");
        } catch (Exception exception) {
            System.out.println("Error: Could not load project");
            exception.printStackTrace();
        }
    }
}