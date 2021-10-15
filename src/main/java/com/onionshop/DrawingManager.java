package com.onionshop;


public class DrawingManager {
    /**
     * This class holds the currently selected Colour, tool and project for the backend of Onionshop. It uses this
     * information to calculate and update the pixels that are effected as the user draws on the canvas
     */

    private Tool currentlySelectedTool;
    private Colour currentlySelectedColour;
    private final Project currentProject;

    /**
     * Instantiates the drawing manager with the current project, the default Tool, and the default colour.
     *
     * @param selectedTool The currently selected or default tool for the project
     * @param selectedColour The currently selected or default colour for the project
     * @param curProject The current project
     */
    public DrawingManager(Tool selectedTool, Colour selectedColour, Project curProject) {
        currentlySelectedTool = selectedTool;
        currentlySelectedColour = selectedColour;
        currentProject = curProject;
    }

    /**
     * Calls the draw method on the tool and passes the canvas, x coordinate, y coordinate and current color to it. The
     * draw method then updates the backend canvas and returns a list of pixels that were altered. These pixels should
     * then be passed to the CanvasEvents class and then to the ProjectStateController to update the canvas on the user's
     * end.
     *
     * @param x x coordinate of the user's last mouse movement
     * @param y x coordinate of the user's last mouse movement
     * @return A list of pixels to update. This list only contains the pixel location, not the color data.
     */
    public int[][] updateCanvasAfterStroke(int x, int y) {
        return this.currentlySelectedTool.draw(currentProject, currentlySelectedColour, x, y);
    }

    /**
     * Sets the currently selected tool
     * @param newCurrentlySelectedTool The new tool to set currentlySelectedTool as
     */
    public void setCurrentlySelectedTool(Tool newCurrentlySelectedTool) {
        this.currentlySelectedTool = newCurrentlySelectedTool;
    }

    /**
     * Sets the currently selected colour
     * @param newCurrentlySelectedColour The new colour to set currentlySelectedColour as
     */
    public void setCurrentlySelectedColour(Colour newCurrentlySelectedColour) {
        this.currentlySelectedColour = newCurrentlySelectedColour;
    }

    /**
     * Returns the currently selected tool
     * @return The currently selected tool
     */
    public Tool getCurrentlySelectedTool() {
        return currentlySelectedTool;
    }

    /**
     * Returns the currently selected project
     * @return The currently selected project
     */
    public Project getCurrentProject() {
        return currentProject;
    }

    /**
     * Returns the currently selected colour
     * @return The currently selected colour
     */
    public Colour getCurrentlySelectedColour() {
        return currentlySelectedColour;
    }
}
