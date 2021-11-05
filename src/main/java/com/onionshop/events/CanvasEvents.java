package com.onionshop.events;

import com.onionshop.entities.Colour;
import com.onionshop.managers.DrawingManager;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CanvasEvents {
    DrawingManager currentDrawingManager;
    //This is used to convert between our colours (0-255) and javaFx's colors (0.0-1.0)
    private final float conversionValue = 255;

    public CanvasEvents(DrawingManager projectDrawingManager){
        this.currentDrawingManager = projectDrawingManager;
    }

    /**
     * Processes the mouse data for the drawing manager and passes the returned updated pixels
     * back from the drawing manager after drawing is finished on the backend
     *
     * @param inputMouseData the current x y coordinate of the mouse
     * @return
     */
    public int[][] processControllerDataForDrawingManager(MouseEvent inputMouseData) {
        int x = (int)inputMouseData.getX();
        int y = (int)inputMouseData.getY();
        int[][] pixelsToReturn = currentDrawingManager.updateCanvasAfterStroke(x, y);
        return pixelsToReturn;
    }

    /**
     * Creates our backend's version of a colour based on the JavaFx color passed in. This Colour is then sent
     * to the drawing manager to update drawing on the backend. Returns the
     *
     * @param userSelectedColour The selected color from the frontend
     * @return The color to select on the frontend
     */
    public Color processSelectedColour(Color userSelectedColour) {
        int[] colourArray = new int[]{(int)(userSelectedColour.getRed()*conversionValue ),
                (int)(userSelectedColour.getGreen()*conversionValue ), (int)(userSelectedColour.getBlue()*conversionValue )};
        Colour selectedColour = new Colour(" ", colourArray);
        currentDrawingManager.updateSelectedColour(selectedColour);

        // We might want to remove this later since the color can be set just on the frontend, I was just including
        // this conversion to catch issues with the Conversion value (Since our colours are from 0-255 and JavaFx is
        // from 0.0-1.0)
        return new Color(colourArray[0]/conversionValue , colourArray[1]/conversionValue ,
                colourArray[2]/conversionValue , 1);
    }

    /**
     * Creates our backend's version of a colour based on the JavaFx color passed in. This Colour is then sent
     * to the drawing manager to be added to the backend Colour Palette. The hex string is used as the name for the,
     * and is returned so that it can be used for the colour swatch on the front end.
     *
     * @param userSelectedColour The selected color from the frontend
     * @return The hex string of the colour
     */
    public String processColourToAddToPalette(Color userSelectedColour) {
        int[] colourArray = new int[]{(int)(userSelectedColour.getRed()*conversionValue ),
                (int)(userSelectedColour.getGreen()*conversionValue ),
                (int)(userSelectedColour.getBlue()*conversionValue )};
        String hexColour = String.format("#%02x%02x%02x", colourArray[0], colourArray[1], colourArray[2]);
        Colour selectedColour = new Colour(hexColour, colourArray);
        currentDrawingManager.addColourToPalette(selectedColour);
        return hexColour;
    }

    /**
     * Takes the hex value of the colour from the front end from the button that was selected and
     * sends it to the drawing manager in order to select the colour from the colour palette. Returns the
     * colour once found.
     *
     * @param colourButton The button/"swatch" that was selected on the frontend
     * @return The selected color from the palette
     */
    public Color selectColourFromPalette(Button colourButton){
        String colourId = colourButton.getId();
        int[] rgbColour = currentDrawingManager.selectColourFromPalette(colourId);
        return new Color(rgbColour[0]/conversionValue,
                rgbColour[1]/conversionValue,
                rgbColour[2]/conversionValue, 1);
    }

    /**
     * Takes the hex value of the colour from the front end from the button that was selected and
     *      sends it to the drawing manager in order to remove the selected colour.
     *
     * @param colourButton The button/"swatch" that was selected on the frontend
     */
    public void removeColourFromPalette(Button colourButton) {
        String colourId = colourButton.getId();
        currentDrawingManager.removeColourFromPalette(colourId);
    }


}
