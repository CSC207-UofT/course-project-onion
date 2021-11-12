package com.onionshop.managers;

import com.onionshop.entities.*;

public class ToolStateManager {
    /**
     * This class holds the current state of the tools and colours used on canvas, and updates them according to
     * user inputs.
     */

    private final static ToolStateManager instance = new ToolStateManager();
    private Tool currentToolState;
    private Colour currentColourState;

    /**
     * Returns the instance of ProjectManager
     * @return the instance of this singleton global ProjectManager
     */
    public static ToolStateManager getInstance() {
        return instance;
    }

    /**
     * Initiates a ToolStateManager with a default round brush and a default black color
     *
     */
    public ToolStateManager() {
        this.currentToolState = new Pen("round", 10);
        this.currentColourState = new Colour("Black", new int[] {0, 0, 0});
    }


    /**
     * Initiates a ToolStateManager with the current tool state and the current colour state.
     *
     * @param currentToolState      the current state of the tool selected on project
     * @param currentColourState    the current colour selected on project
     */
    public ToolStateManager(Tool currentToolState, Colour currentColourState) {
        this.currentToolState = currentToolState;
        this.currentColourState = currentColourState;
    }

    /**
     * Updates the current tool state using user input of <brushShape> and <brushSize>. Currently the update
     * only works if the Tool is a child of the abstract class Brush.
     *
     * @param brushShape        String of the shape of the brush, all lower case
     * @param brushSize         int of the size of the brush, represents pixel size
     */
    public void updateCurrentToolState(String brushShape, int brushSize) {
        if (this.currentToolState instanceof Brush) {
            ((Brush) this.currentToolState).setBrushShape(brushShape);
            ((Brush) this.currentToolState).setBrushSize(brushSize);
        }
    }

    /**
     * Overloading Method: Updates the current tool state using ONLY user input of <brushShape>. Currently the
     * update only works if the Tool is a child of the abstract class Brush.
     *
     * @param brushShape        String of the shape of the brush, all lower case
     */
    public void updateCurrentToolState(String brushShape) {
        if (this.currentToolState instanceof Brush) {
            ((Brush) this.currentToolState).setBrushShape(brushShape);
        }
    }

    /**
     * Overloading Method: Updates the current tool state using ONLY user input of <brushSize>. Currently the
     * update only works if the Tool is a child of the abstract class Brush.
     *
     * @param brushSize         int of the size of the brush, represents pixel size
     */
    public void updateCurrentToolState(int brushSize) {
        if (this.currentToolState instanceof Brush) {
            ((Brush) this.currentToolState).setBrushSize(brushSize);
        } else if (this.currentToolState instanceof Shape) {
            ((Shape) this.currentToolState).setLineThickness(brushSize);
        }
    }

    /**
     * Updates the current colour using user input of <changes>.
     *
     * @param newRGB            new RGB value of Colour
     */
    public void updateCurrentColourState(int[] newRGB) {
        if (!(this.currentToolState instanceof Eraser)) {
            this.currentColourState.setRGB(newRGB);
        }
    }

    /**
     * Set the current tool state
     *
     * @param currentToolState  the current state of the tool selected on project
     */
    public void setCurrentToolState(Tool currentToolState) {
        this.currentToolState = currentToolState;
    }

    /**
     * Set the current colour
     *
     * @param currentColourState  the current colour selected on project
     */
    public void setCurrentColourState(Colour currentColourState) {
        if (!(this.currentToolState instanceof Eraser)) {
            this.currentColourState = currentColourState;
        }
    }

    /**
     * Returns the current tool state
     *
     * @return  the current state of the tool selected on project
     */
    public Tool getCurrentToolState() {
        return currentToolState;
    }

    /**
     * Returns the current colour
     *
     * @return  the current colour selected on project
     */
    public Colour getCurrentColourState() {
        return currentColourState;
    }

    /**
     * This returns whether the colour is currently locked or not for the eraser
     *
     * @return whether the current tool is the eraser (meaning the colour should not be changed
     */
    public boolean getColourLocked() { return currentToolState instanceof Eraser; }
}
