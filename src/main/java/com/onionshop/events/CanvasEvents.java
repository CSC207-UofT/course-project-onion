package com.onionshop.events;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 *
 */
public class CanvasEvents {
    @FXML
    private Canvas projectDrawing;

    /**
     * Takes in a mouse event and pass the x,y coordinates down
     */
    public double[] DrawingEvent(MouseEvent canvasMouseClicked){
        double[] arr = new double[2];
        arr[0] = canvasMouseClicked.getX();
        arr[1] = canvasMouseClicked.getY();

        return arr;
    }
}
