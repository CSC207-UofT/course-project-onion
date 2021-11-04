package com.onionshop.events;

import com.onionshop.entities.Colour;
import com.onionshop.managers.DrawingManager;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CanvasEvents {
    DrawingManager currentDrawingManager;

    public CanvasEvents(DrawingManager projectDrawingManager){
        this.currentDrawingManager = projectDrawingManager;
    }

    public int[][] processControllerDataForDrawingManager(MouseEvent inputMouseData) {
        int x = (int)inputMouseData.getX();
        int y = (int)inputMouseData.getY();
        int[][] pixelsToReturn = currentDrawingManager.updateCanvasAfterStroke(x, y);
        return pixelsToReturn;
    }

    public Color processSelectedColour(Color userSelectedColour) {
        int[] colourArray = new int[]{(int)userSelectedColour.getRed()*256,
                (int)userSelectedColour.getGreen()*256, (int)userSelectedColour.getBlue()*256};
        Colour selectedColour = new Colour(" ", colourArray);
        currentDrawingManager.updateSelectedColour(selectedColour);
        Color finalSelectedColour = new Color(colourArray[0]/256.0, colourArray[1]/256.0,
                colourArray[2]/256.0, 1);
        return finalSelectedColour;
    }

    public void processColourToAddToPalette(Color userSelectedColour) {
        int[] colourArray = new int[]{(int)userSelectedColour.getRed()*256,
                (int)userSelectedColour.getGreen()*256, (int)userSelectedColour.getBlue()*256};
        Colour selectedColour = new Colour(" ", colourArray);
        currentDrawingManager.addColourToPalette(selectedColour);
    }



}
