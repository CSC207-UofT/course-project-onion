package com.onionshop.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

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

    private Object[] recentProjects;

    /**
     * Opens a project give a project object.
     * @param project a project object.
     */
    public void openProject(Object project) {

    }

    /**
     * Imports .onion project files from file explorer.
     *
     */
    public void openFileExplorer() {

    }

    /**
     *  Creates a new project.
     *
     * @return TODO: fill this in.
     */
    public Object createNewProject() {
        return null;
    }

}