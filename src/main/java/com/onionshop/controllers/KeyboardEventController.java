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

    private final UndoRedoManager UndoRedo = new UndoRedoManager();
    private final ProjectManager projectManager = ProjectManager.getInstance();
    KeyCombination Undo = new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN);
    KeyCombination Redo = new KeyCodeCombination(KeyCode.Y, KeyCombination.SHORTCUT_DOWN);
    KeyCombination Save = new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN);

    @Override
    public void handle(KeyEvent event) {
        if (Undo.match(event)){
            UndoRedo.undo();
        }
        else if (Redo.match(event)) {
            UndoRedo.redo();
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
