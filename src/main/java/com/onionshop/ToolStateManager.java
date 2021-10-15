package com.onionshop;

public class ToolStateManager {
    /**
     * This class holds the current state of the tools and colours used on canvas, and updates them according to
     * user inputs.
     */

    private Tool currentToolState;
    private Colour currentColourState;

    public ToolStateManager(Tool currentToolState, Colour currentColourState) {
        this.currentToolState = currentToolState;
        this.currentColourState = currentColourState;
    }

    public void updateCurrentToolState(String brushShape, int brushSize) {
        if (this.currentToolState instanceof Brush) {
            ((Brush) this.currentToolState).setBrushShape(brushShape);
            ((Brush) this.currentToolState).setBrushSize(brushSize);
        }
    }

    public void updateCurrentColourState(int[] changes) {
        this.currentColourState.modify(changes);
    }

    public void setCurrentToolState(Tool currentToolState) {
        this.currentToolState = currentToolState;
    }

    public void setCurrentColourState(Colour currentColourState) {
        this.currentColourState = currentColourState;
    }

    public Tool getCurrentToolState() {
        return currentToolState;
    }

    public Colour getCurrentColourState() {
        return currentColourState;
    }
}
