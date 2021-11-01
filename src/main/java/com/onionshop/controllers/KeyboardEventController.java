package com.onionshop.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCombination;
import com.onionshop.managers.UndoRedoManager;

public class KeyboardEventController implements EventHandler<KeyEvent> {
    /**
     * Handles keyboard inputs.
     */

    private final UndoRedoManager UndoRedo = new UndoRedoManager();
    KeyCombination Undo = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
    KeyCombination Redo = new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN);
    KeyCombination Save = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);

    @Override
    public void handle(KeyEvent event) {
        if (Undo.match(event)){
            UndoRedo.undo();
        }
        else if (Redo.match(event)) {
            UndoRedo.redo();
        }
        else if (Save.match(event)){
            //Todo
        }
    }

}
