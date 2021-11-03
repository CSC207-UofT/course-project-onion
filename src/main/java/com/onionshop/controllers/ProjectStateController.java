package com.onionshop.controllers;

import com.onionshop.entities.Colour;
import com.onionshop.entities.Pen;
import com.onionshop.entities.Pixel;
import com.onionshop.entities.Project;
import com.onionshop.events.CanvasEvents;
import com.onionshop.managers.DrawingManager;
import com.onionshop.managers.ProjectManager;
import com.onionshop.managers.ToolStateManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * records the action -> trigger methods -> send data to backend
 * Actions include: selecting a tool on the toolbar(ToolbarEvents - to be implemented), updating brush/pen sizes(Pen),
 * drawing on canvas and selecting a colour(to be implemented).
 */
public class ProjectStateController implements Initializable {
    @FXML
    private Button brushPen;
    @FXML
    private ColorPicker projectColorPicker;
    @FXML
    private Canvas projectDrawing;
    @FXML
    private Slider toolSizeSlider;

    private final DrawingManager projectDrawingManager = new DrawingManager();
    private final CanvasEvents canvasInputProcessor = new CanvasEvents(projectDrawingManager);
    private final ProjectManager projectManager = ProjectManager.getInstance();



    /**
     * This function is called when this scene is first initialized
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCanvas();
    }

    /**
     * Initializes the canvas with current project's saved pixels
     * Sets the height and width of the canvas
     */
    private void initCanvas() {
        Project currentProject = projectManager.getCurrentProject();

        int canvasHeight = currentProject.getHeight();
        int canvasWidth = currentProject.getWidth();

        // set width and height of the canvas UI
        projectDrawing.setHeight(canvasHeight);
        projectDrawing.setWidth(canvasWidth);

        PixelWriter pixelWriter = projectDrawing.getGraphicsContext2D().getPixelWriter();

        // set canvas pixels to match the pixels of the current project
        for (int x = 0; x < canvasWidth; x++) {
            for (int y = 0; y < canvasHeight; y++) {
                int[] rgb = currentProject.getPixelByCoord(x, y).getRGB();
                Color color = Color.rgb(rgb[0], rgb[1], rgb[2], 1);
                pixelWriter.setColor(x, y, color);
            }
        }

    }

    @FXML
    protected void onBrushPenClick() {
        brushPen.setText("Pen: Selected");
    }

    /**
     * Updates the color of the pen used by the graphics context when the color picker is used
     */
    @FXML
    protected void setDrawingColor() {
        //GraphicsContext drawingGraphicsContext = projectDrawing.getGraphicsContext2D();
        //drawingGraphicsContext.setFill(projectColorPicker.getValue());
    }

    /** A function that draws ovals as the user clicks and drags their mouse across the canvas
     *
     * @param canvasMouseLocation - A mouseEvent passed in containing the x and y coordinates of the mouse
     */
    @FXML
    protected void onCanvasMouseDragged(MouseEvent canvasMouseLocation) {
        int[][] updatedPixels = canvasInputProcessor.processControllerDataForDrawingManager(canvasMouseLocation);

        Color currentCanvasColour = Color.BLACK;

        PixelWriter canvasPixelWriter = projectDrawing.getGraphicsContext2D().getPixelWriter();
        for (int i = 0; i < updatedPixels.length; i++) {
            canvasPixelWriter.setColor(updatedPixels[i][0], updatedPixels[i][1], currentCanvasColour);
        }
    }

    /**
     * Changes the brushes size when the user drags the slider
     */
    @FXML
    protected void onToolSizeSliderMove() {
        //toolSize = toolSizeSlider.getValue();
    }

    /**
     * Takes in a ToolbarEvent, update the tool accordingly
     * To be implemented after phase0
     */
    public void toolUpdate(){

    }

    /**
     * Takes in an event that changes the size of brush, update the brush size accordingly
     *
     */
    public void brushSizeUpdate(){
    }


    /**
     * Takes in an event that picks a colour and updates the colour
     * @param colour: the colour object
     * @param newColour : integer array of the RGB value of the new colour
     */
    public void colourUpdate(Colour colour, int[] newColour){
        colour.setRGB(newColour);
    }
}
