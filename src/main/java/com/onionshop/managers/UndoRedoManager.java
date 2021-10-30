package com.onionshop.managers;

import java.util.Stack;

import com.onionshop.entities.Pixel;

public class UndoRedoManager {
    /**
     * Store up to five steps of Drawing instance and keep track on canvas state.
     */
    private int maxStackSize = 5 + 1; //number of previous states PLUS the current state (+1)
    private Stack<Pixel[][]> undoStack;
    private Stack<Pixel[][]> redoStack;

    /**
     * Changes the number of previous states of the drawing canvas that are stored at one time;
     * this number is equivalent the number of times a user can undo.
     * @param newSize the new number of undos
     */
    public void changeUndoStackSize(int newSize) {
        maxStackSize = newSize + 1;
    }


    /**
     * Adds a new drawingCanvas state when a change is made to the drawing canvas,
     * allowing a user to undo to this state in the future
     *
     * @param newState the new state to be added
     */
    public void update(Pixel[][] newState) {
        if (undoStack.size() + 1 > maxStackSize) {
            undoStack.remove(0);
        }
        undoStack.push(newState);
        redoStack.clear();
    }

    /**
     * Returns the previous state of the drawing canvas and saves it as a "redo" so you can go back
     *
     * @return returns 2D Pixel array, corresponding to the canvas
     */
    public Pixel[][] undo() {
        redoStack.push(undoStack.pop());
        return undoStack.peek();
    }

    /**
     * Returns an undone state of the drawing canvas, allowing a user to undo an undo.
     *
     * @return returns 2D Pixel array, corresponding to the canvas
     */
    public Pixel[][] redo() {
        this.update(redoStack.pop());
        return redoStack.peek();
    }
}
