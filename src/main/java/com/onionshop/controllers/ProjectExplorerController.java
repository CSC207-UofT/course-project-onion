package com.onionshop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


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
    public void onBtnOpenClick() {
    }
    
}