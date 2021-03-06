package com.onionshop.managers;

import java.util.Stack;

import com.onionshop.entities.DrawingState;

public class UndoRedoManager {
    /**
     * Store up to five steps instances and keep track of canvas state.
     */

    private int maxStackSize = 5 + 1 + 1; //number of previous states PLUS the current state, plus the initial state
    public final Stack<DrawingState> undoStack = new Stack<>();
    public final Stack<DrawingState> redoStack = new Stack<>();

    /**
     * Changes the number of previous states of the drawing canvas that are stored at one time;
     * this number is equivalent the number of times a user can undo.
     * @param newSize the new number of undos
     */
    public void changeUndoStackSize(int newSize) {
        maxStackSize = newSize + 1 + 1;
    }


    /**
     * Adds a new drawingCanvas state when a change is made to the drawing canvas,
     * allowing a user to undo to this state in the future
     *
     * @param newState the new state to be added
     */
    public void update(DrawingState newState) {
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
    public DrawingState undo() {
        if (undoStack.size() > 1) {
            DrawingState currentState = undoStack.pop();
            redoStack.push(currentState);
        }
        else {
            System.out.println("Error: You can't undo more steps!");
        }
        return undoStack.peek();
    }

    /**
    * Returns an undone state of the drawing canvas, allowing a user to undo an undo.
    *
    * @return returns 2D Pixel array, corresponding to the canvas
    */
    public DrawingState redo() {
        if (redoStack.size() > 0) {
            undoStack.push(redoStack.peek());
            return redoStack.pop();
        }
        else {
            System.out.println("Error: You cannot redo now because there are no steps need to redo!");
            return undoStack.peek();
        }
    }
}
