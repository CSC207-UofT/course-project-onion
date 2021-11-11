package com.onionshop.controllers;
import com.onionshop.managers.MostRecentProjectManager;
import com.onionshop.managers.OnionFileLoader;
import com.onionshop.events.NewProjectEvent;
import com.onionshop.managers.ProjectManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class NewProjectController {
    /**
     * Controller for the new project scene
     * In this scene, users input data for their new project including project
     * name, canvas width and height, as well as the directory that their project
     * will be saved to.
     */

    @FXML
    public TextField txtProjectName;
    @FXML
    public TextField txtCanvasWidth;
    @FXML
    public TextField txtCanvasHeight;

    @FXML
    public Button btnSubmit;

    @FXML
    public Button btnChooseDir;

    @FXML
    public Label outDirName;

    private String savDir;

    /**
        When the submit button is clicked, and each field has valid input (integer values for height
        and width, valid directory path), create a new project.
        @param event: the click event context
    */
    @FXML
    public void onBtnSubmitClick(ActionEvent event) {
        try {
            if (savDir != null && !savDir.equals("") && OnionFileLoader.isDirectoryValid(savDir)) {
                String projectName = txtProjectName.getCharacters().toString();
                int width = Integer.parseInt(txtCanvasWidth.getCharacters().toString());
                int height = Integer.parseInt(txtCanvasHeight.getCharacters().toString());
                MostRecentProjectManager mostRecentProjectManager = new MostRecentProjectManager();

                // If there are no errors above, create a new project
                NewProjectEvent newProjectEvent = new NewProjectEvent(projectName, savDir, width, height);
                ProjectManager.getInstance().newProject(newProjectEvent);
                mostRecentProjectManager
                        .addMostRecentProject(projectName, savDir + "/" + projectName + ".onion");

                // switch to the canvas scene
                SceneSwitcher.switchSceneWithKeyEventsInit(getClass(), event, "/com/onionshop/main-canvas-view.fxml");
            } else {
                System.out.println("Error: File directory was not given or invalid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     When the "Choose Directory" button is clicked, users will be able to choose a directory from
     their file explorer to save their project to.
     */
    @FXML
    public void onBtnChooseDirClick() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(null);
        savDir = selectedDirectory.getAbsolutePath();
        outDirName.setText(outDirName.getText() + savDir);
        outDirName.setTooltip(new Tooltip(savDir));
    }
}
