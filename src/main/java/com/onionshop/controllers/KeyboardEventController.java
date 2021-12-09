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

    /**
     * Interprets keyboard commands as events that are dealt with in their respective domains
     * @param event
     */
    @Override
    public void handle(KeyEvent event) {
        /*
         * TODO: fix undo redo bug
         */
        if (Undo.match(event)){
        // projectManager.undoDrawingState();
        // projectStateController.reInitLayers();
        }
        else if (Redo.match(event)) {
        // projectManager.restoreDrawingState();
        // projectStateController.reInitLayers();
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
