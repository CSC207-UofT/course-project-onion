package com.onionshop.controllers;

import com.onionshop.managers.ProjectManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCombination;
import com.onionshop.managers.UndoRedoManager;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class KeyboardEventController implements EventHandler<KeyEvent> {
    /**
     * Handles keyboard inputs.
     */

    private final ProjectManager projectManager = ProjectManager.getInstance();
    private final ProjectStateController projectStateController;

    KeyCombination Undo = new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN);
    KeyCombination Redo = new KeyCodeCombination(KeyCode.Y, KeyCombination.SHORTCUT_DOWN);
    KeyCombination Save = new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN);

    public KeyboardEventController(ProjectStateController projectStateController) {
        this.projectStateController = projectStateController;
    }

    @Override
    public void handle(KeyEvent event) {
        if (Undo.match(event)){
            int currentlySelectedLayer = projectManager.getCurrentLayerIndex();
            int updatedLayer = projectManager.getUndoDrawingStateIndex();

            int updateType = projectManager.undoDrawingState();

            if (updateType == 0) {
                updatedLayer = projectManager.getUndoDrawingStateIndex();
                projectStateController.selectLayerByIndex(updatedLayer);
                projectStateController.updateCanvas(updatedLayer);
                projectStateController.selectLayerByIndex(currentlySelectedLayer);
            }
            else if (updateType == 1) {
                //undoing a layer deletion
                projectStateController.addLayerAtIndex(updatedLayer);
                projectStateController.selectLayerByIndex(updatedLayer);
                projectStateController.updateCanvas(updatedLayer);
                projectStateController.selectLayerByIndex(currentlySelectedLayer);
            }
            else {
                //undoing a layer creation
                projectStateController.removeLayerAtIndex(updatedLayer);
            }



            /*
             * TODO: Make sure the layers are also reinitialized when we undo
             *  so that creating and removing layers is undoable
             */
        }
        else if (Redo.match(event)) {
            int currentlySelectedLayer = projectManager.getCurrentLayerIndex();
            int updatedLayer = projectManager.getRedoDrawingStateIndex();
            int updateType = projectManager.restoreDrawingState();
            if (updateType == 0) {
                projectStateController.selectLayerByIndex(updatedLayer);
                projectStateController.updateCanvas(updatedLayer);
                projectStateController.selectLayerByIndex(currentlySelectedLayer);
            }
            else if (updateType == 1) {
                //redoing a layer deletion
                projectStateController.removeLayerAtIndex(updatedLayer);
            }
            else {
                //undoing a layer creation
                projectStateController.addLayerAtIndex(updatedLayer);
                projectStateController.selectLayerByIndex(updatedLayer);
                projectStateController.updateCanvas(updatedLayer);
                projectStateController.selectLayerByIndex(currentlySelectedLayer);

            }
        }
        else if (Save.match(event)){
            try {
                projectManager.saveProject();
            } catch (Exception exception) {
                System.out.println("Error: could not save project");
                exception.printStackTrace();
            }
        }
    }

}
