package com.onionshop.controllers;

import com.onionshop.managers.MostRecentProjectManager;
import com.onionshop.managers.ProjectManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
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

    @FXML
    public Button btnNewProject;

    @FXML
    public Button btnOpen;

    @FXML
    public Label mostRecentName1;

    @FXML
    public Label mostRecentPath1;

    @FXML
    public Label mostRecentName2;

    @FXML
    public Label mostRecentPath2;

    @FXML
    public Label mostRecentName3;

    @FXML
    public Label mostRecentPath3;

    @FXML
    public Label mostRecentName4;

    @FXML
    public Label mostRecentPath4;

    @FXML
    public Label mostRecentName5;

    @FXML
    public Label mostRecentPath5;

    private MostRecentProjectManager mostRecentProjectManager;
    private final ProjectManager projectManager = ProjectManager.getInstance();
    private String[][] mostRecentProjects;

    /**
     * This function is called when this scene is first initialized
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMostRecentProject();
    }

    private void initMostRecentProject() {
        mostRecentProjects = new String[][] {
            {"Project 1", "/user/directory/path/Project 1"},
            {"Project 2", "/user/directory/path/Project 2"},
            {"Project 3", "/user/directory/path/Project 3"},
            {"Project 4", "/user/directory/path/Project 4"},
            {"Project 5", "/user/directory/path/Project 5"},
        };

        // TODO: when backend is done, and delete the above place holder
        // mostRecentProjects = mostRecentProjectManager.getMostRecentProjects()

        initMostRecentLabel(mostRecentName1, mostRecentPath1, mostRecentProjects[0]);
        initMostRecentLabel(mostRecentName2, mostRecentPath2, mostRecentProjects[1]);
        initMostRecentLabel(mostRecentName3, mostRecentPath3, mostRecentProjects[2]);
        initMostRecentLabel(mostRecentName4, mostRecentPath4, mostRecentProjects[3]);
        initMostRecentLabel(mostRecentName5, mostRecentPath5, mostRecentProjects[4]);
    }

    private void initMostRecentLabel(Label name, Label path, String[] namePathStrArr) {
        name.setText(namePathStrArr[0]);
        path.setText(namePathStrArr[1]);
        path.setTooltip(new Tooltip(namePathStrArr[1]));
    }

    /**
     * Changes the view to the NewProject scene
     */
    @FXML
    private void onBtnNewProjectClick(ActionEvent event) {
        SceneSwitcher.switchScene(getClass(), event,"/com/onionshop/NewProject.fxml");
    }

    /**
     * Opens the first most recently edited project
     */
    @FXML
    public void openMostRecentProject1 (MouseEvent event) {
        openMostRecentProject(0, event);
    }


    /**
     * Opens the second most recently edited project
     */
    @FXML
    public void openMostRecentProject2 (MouseEvent event) {
        openMostRecentProject(1, event);
    }


    /**
     * Opens the third most recently edited project
     */
    @FXML
    public void openMostRecentProject3 (MouseEvent event) {
        openMostRecentProject(2, event);
    }

    /**
     * Opens the forth most recently edited project
     */
    @FXML
    public void openMostRecentProject4 (MouseEvent event) {
        openMostRecentProject(3, event);
    }

    /**
     * Opens the fifth most recently edited project
     */
    @FXML
    public void openMostRecentProject5 (MouseEvent event) {
        openMostRecentProject(4, event);
    }

    /**
     * Opens the (mostRecentProjectNum-1)th most recently edited project
     * Where 0 <= mostRecentProjectNum <= 4
     * @param mostRecentProjectNum the ranking of the project being opened in terms of
     *                             how recently it was edited
     */
    private void openMostRecentProject (int mostRecentProjectNum, Event event) {
        try {
            projectManager.loadProject(mostRecentProjects[mostRecentProjectNum][1]);
            SceneSwitcher.switchSceneWithKeyEventsInit(getClass(), event,"/com/onionshop/main-canvas-view.fxml");
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
            projectManager.loadProject(selectedFile.getAbsolutePath());

            SceneSwitcher.switchSceneWithKeyEventsInit(getClass(), event,"/com/onionshop/main-canvas-view.fxml");
        } catch (Exception exception) {
            System.out.println("Error: Could not load project");
            exception.printStackTrace();
        }
    }
}