package com.onionshop.managers;


import com.onionshop.entities.Colour;
import com.onionshop.entities.ColourPalette;

import java.util.Objects;

public class DrawingManager {
    /**
     * This class holds the currently selected Colour, tool and project for the backend of Onionshop. It uses this
     * information to calculate and update the pixels that are effected as the user draws on the canvas
     */

    /**
     * Instantiates the drawing manager with the current project, the default Tool, and the default colour.
     *
     */
    ToolStateManager toolStateManager = ToolStateManager.getInstance();
    ProjectManager projectManager = ProjectManager.getInstance();

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
        return toolStateManager.getCurrentToolState().draw(
                projectManager.getCurrentProject(),
                toolStateManager.getCurrentColourState(), x, y);
    }

    /**
     * Selects the inputted colour
     *
     * @param selectedColour The colour to select
     */
    public void updateSelectedColour(Colour selectedColour) {
        toolStateManager.setCurrentColourState(selectedColour);
    }

    /**
     * Adds the inputted colour to the palette
     *
     * @param selectedColour The colour to add to the palette
     */
    public void addColourToPalette(Colour selectedColour) {
        projectManager.getCurrentProject().getColourPalette().addColour(selectedColour);
    }

    /**
     * Selects the inputted colour from the colour palette, and returns black if the colour cannot be found
     * @param colourId The colour to find in the colour palette and select
     * @return The selected colour
     */
    public int[] selectColourFromPalette(String colourId) {
        for (Colour currentColour : projectManager.getCurrentProject().getColourPalette().getColours()) {
            if (Objects.equals(currentColour.getName(), colourId)) {
                toolStateManager.setCurrentColourState(currentColour);
                return currentColour.getRGB();
            }
        }

        System.out.println("ERROR :: FAILED TO FIND COLOUR IN BACKEND COLOUR PALETTE :: colorId - " + colourId);

        toolStateManager.setCurrentColourState(new Colour("", new int[] {0, 0, 0}));
        return new int[]{0, 0, 0};
    }

    /**
     * Removes the selected colour from the colour palette
     *
     * @param colourId The hex value of the colour to remove (This is the colour's name in the palette)
     */
    public void removeColourFromPalette(String colourId) {
        int indexToRemove = -1;
        ColourPalette colourPalette = projectManager.getCurrentProject().getColourPalette();

        //Iterates through the palette to select the colour
        for (int i = 0; i < colourPalette.size(); i++) {
            if (Objects.equals(colourPalette.getColour(i).getName(), colourId)) {
                indexToRemove = i;
            }
        }

        if (indexToRemove == -1) {
            System.out.println("ERROR :: FAILED TO FIND COLOUR IN BACKEND COLOUR PALETTE :: colourId - " + colourId);
        }
        else {
            projectManager.getCurrentProject().getColourPalette().removeColour(indexToRemove);
        }
    }
}
