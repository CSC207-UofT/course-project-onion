package com.onionshop;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

/**
 * This is an example basic drawing controller. We don't need to keep this, I just wanted to make sure everything was
 * working in the fxml. Currently does not interact with the backend at all.
 *
 * Button brushPen - Button that just says pen and does nothing
 * ColorPicker projectColorPicker - Color picker that can be used to select brush color
 * Canvas projectDrawing - Canvas that the user can draw on
 * Slider toolSizeSlider - Slider that modifies the size of the brush/tool
 */
public class DrawingController {


    @FXML
    private Button brushPen;
    @FXML
    private ColorPicker projectColorPicker;
    @FXML
    private Canvas projectDrawing;
    @FXML
    private Slider toolSizeSlider;

    private double toolSize = 5;


    @FXML
    protected void onBrushPenClick() {
        brushPen.setText("Pen: Selected");
    }

    /**
     * Updates the color of the pen used by the graphics context when the color picker is used
     */
    @FXML
    protected void setDrawingColor() {
        GraphicsContext drawingGraphicsContext = projectDrawing.getGraphicsContext2D();
        drawingGraphicsContext.setFill(projectColorPicker.getValue());
    }

    /** A function that draws ovals as the user clicks and drags their mouse across the canvas
     *
     * @param canvasMouseClicked - A mouseEvent passed in containing the x and y coordinates of the mouse
     */
    @FXML
    protected void onCanvasMouseDragged(MouseEvent canvasMouseClicked) {
        GraphicsContext drawingGraphicsContext = projectDrawing.getGraphicsContext2D();
        drawingGraphicsContext.fillOval(canvasMouseClicked.getX(), canvasMouseClicked.getY(), toolSize, toolSize);
    }

    /**
     * Changes the brushes size when the user drags the slider
     */
    @FXML
    protected void onToolSizeSliderMove() {
        toolSize = toolSizeSlider.getValue();
    }
}