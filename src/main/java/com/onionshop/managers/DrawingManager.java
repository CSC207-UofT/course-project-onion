package com.onionshop.managers;


import com.onionshop.managers.ProjectManager;

public class DrawingManager {
    /**
     * This class holds the currently selected Colour, tool and project for the backend of Onionshop. It uses this
     * information to calculate and update the pixels that are effected as the user draws on the canvas
     */

    /**
     * Instantiates the drawing manager with the current project, the default Tool, and the default colour.
     *
     */
    public DrawingManager() {
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
        int[][] pixelsToReturn = ToolStateManager.getInstance().getCurrentToolState().draw(
                ProjectManager.getInstance().getCurrentProject(),
                ToolStateManager.getInstance().getCurrentColourState(), x, y);
        return pixelsToReturn;
    }

}
