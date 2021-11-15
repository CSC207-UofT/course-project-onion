package com.onionshop.controllers;

import com.onionshop.entities.Pixel;
import com.onionshop.events.CanvasEvents;
import com.onionshop.managers.DrawingManager;
import com.onionshop.managers.ProjectManager;
import com.onionshop.managers.ToolStateManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * records the action -> trigger methods -> send data to backend
 * Actions include: selecting a tool on the toolbar(ToolbarEvents - to be implemented), updating brush/pen sizes(Pen),
 * drawing on canvas and selecting a colour(to be implemented).
 */
public class ProjectStateController implements Initializable {
    public Button eraser;
    public Button rectangle;
    public Button circle;
    public Button line;

    @FXML
    private Button brushPen;
    @FXML
    private ColorPicker projectColourPicker;
    @FXML
    private Canvas projectDrawing;
    @FXML
    private Slider toolSizeSlider;
    @FXML
    private Button addToColourPalette;
    @FXML
    private FlowPane colourPalette;
    @FXML
    private Label colourPaletteLabel;

    private final DrawingManager projectDrawingManager = new DrawingManager();
    private final ToolStateManager toolStateManager = ToolStateManager.getInstance();
    private final CanvasEvents canvasInputProcessor = new CanvasEvents(projectDrawingManager);
    private final ProjectManager projectManager = ProjectManager.getInstance();
    private Color currentCanvasColour = Color.BLACK;

    private int brushSize;


    /**
     * This function is called when this scene is first initialized
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCanvas();
        initColourPalette();
    }

    /**
     * Initializes the canvas with current project's saved pixels
     * Sets the height and width of the canvas
     */

    public void initCanvas() {
        int canvasHeight = projectManager.getCurrentProject().getHeight();
        int canvasWidth = projectManager.getCurrentProject().getWidth();

        // set width and height of the canvas UI
        projectDrawing.setHeight(canvasHeight);
        projectDrawing.setWidth(canvasWidth);

        PixelWriter pixelWriter = projectDrawing.getGraphicsContext2D().getPixelWriter();

        // set canvas pixels to match the pixels of the current project
        for (int x = 0; x < canvasWidth; x++) {
            for (int y = 0; y < canvasHeight; y++) {
                int[] rgb = projectManager.getCurrentProject().getPixelByCoord(x, y).getRGB();
                Color color = Color.rgb(rgb[0], rgb[1], rgb[2], 1);
                pixelWriter.setColor(x, y, color);
            }
        }
    }

    /**
     * Initializes the colour palette
     */
    private void initColourPalette() {
        //The dot function calls are needed so that this file (a controller) doesn't become dependent on an entity
        //Iterates through the saved colours and adds them to the palette
        for (int i = 0; i < projectManager.getCurrentProject().getColourPalette().size(); i++) {
            String colourHex = projectManager.getCurrentProject().getColourPalette().getColour(i).getName();
            addColourButtonToPalette(colourHex);
        }
    }

    /**
     * Updates the color of the pen used by the graphics context when the color picker is used
     */
    @FXML
    protected void setDrawingColour() {
        if (!toolStateManager.getColourLocked()) {
            currentCanvasColour = canvasInputProcessor.processSelectedColour(projectColourPicker.getValue());
            projectDrawing.getGraphicsContext2D().setFill(currentCanvasColour);
        }
    }

    /**
     * This controller adds a colour to the palette when the user clicks the "Add Colour to Palette Button". This
     * involves creating a button that will act as the colour swatch. The user can click on this swatch to select a
     * colour and right click on it to remove the colour from the palette.
     */
    @FXML
    protected void onAddToColourPalette() {
        //This adds the colour to the backend palette and returns the string hex value of the colour which is used for
        //the button id.
        String selectedColourHex = canvasInputProcessor.processColourToAddToPalette(projectColourPicker.getValue());
        addColourButtonToPalette(selectedColourHex);
    }

    /**
     * This method adds a new colour button to the colour palette using the given hex string as the colour
     * @param selectedColourHex The hex string representation of the colour to add to the palette
     */
    protected void addColourButtonToPalette(String selectedColourHex) {
        Button colourSwatch = new Button("");
        colourSwatch.setStyle("-fx-background-color: " + selectedColourHex);
        colourSwatch.setId(selectedColourHex);

        //This is the event handler for the swatch, which selects or removes the colour
        EventHandler<MouseEvent> colourSelectHandler =
                colourButton -> {
                    //Left click selects the colour
                    if (colourButton.getButton() == MouseButton.PRIMARY){
                        if (!(toolStateManager.getColourLocked())) {
                            currentCanvasColour =
                                    canvasInputProcessor.selectColourFromPalette((Button)colourButton.getSource());
                            projectColourPicker.setValue(currentCanvasColour);
                        }
                    }
                    //Right click removes the colour
                    else if (colourButton.getButton() == MouseButton.SECONDARY) {
                        canvasInputProcessor.removeColourFromPalette((Button)colourButton.getSource());
                        colourPalette.getChildren().remove(colourSwatch);
                    }
                };
        colourSwatch.setOnMouseClicked(colourSelectHandler);

        //Adds the colour swatch/button to the palette
        colourPalette.getChildren().add(colourSwatch);
    }


    /** A function that draws ovals as the user clicks and drags their mouse across the canvas
     *
     * @param canvasMouseLocation - A mouseEvent passed in containing the x and y coordinates of the mouse
     */
    @FXML
    protected void onCanvasMouseDragged(MouseEvent canvasMouseLocation) {
        int[][] updatedPixels = canvasInputProcessor.processControllerDataForDrawingManager(canvasMouseLocation);

        PixelWriter canvasPixelWriter = projectDrawing.getGraphicsContext2D().getPixelWriter();
        for (int[] updatedPixel : updatedPixels) {
            canvasPixelWriter.setColor(updatedPixel[0], updatedPixel[1], currentCanvasColour);
        }
    }


    /**
     * Changes the brushes size when the user drags the slider
     */
    @FXML
    protected void onToolSizeSliderMove() {
        brushSize = (int) Math.round(toolSizeSlider.getValue());
        toolStateManager.updateCurrentToolState(brushSize);
    }

    /**
     * Sets the current tool being used to brush
     */
    @FXML
    protected void onBrushPenClick() {
        canvasInputProcessor.setTool(Tools.PEN, brushSize);
        setDrawingColour();
        onToolSizeSliderMove();
    }

    /**
     * Sets the current tool being used to eraser
     */
    @FXML
    public void onEraserClick() {
        canvasInputProcessor.setTool(Tools.ERASER, brushSize);
        currentCanvasColour = Color.WHITE;
        onToolSizeSliderMove();
    }


    /**
     * Sets the current tool being used to line
     */
    public void onLineToolClick() {
        canvasInputProcessor.setTool(Tools.LINE, brushSize);
        setDrawingColour();
    }


    /**
     * Sets the current tool being used to circle
     */
    public void onCircleToolClick() {
        canvasInputProcessor.setTool(Tools.CIRCLE, brushSize);
        setDrawingColour();
    }

    /**
     * Sets the current tool being used to rectangle
     */
    public void onRectangleToolClick() {
        canvasInputProcessor.setTool(Tools.RECTANGLE, brushSize);
        setDrawingColour();
    }

    public void onCanvasMouseReleased(MouseEvent mouseDragEvent) {
        PixelReader canvasPixelReader = projectDrawing.snapshot(null,null).getPixelReader();
        Pixel[][] pixelArray = new Pixel[projectManager.getCurrentProject().getWidth()]
                [projectManager.getCurrentProject().getHeight()];
        for (int x = 0; x < projectManager.getCurrentProject().getWidth(); x++) {
            for (int y = 0; y < projectManager.getCurrentProject().getHeight(); y++) {
                Color color = canvasPixelReader.getColor(x, y);
                int[] rgbValues = new int[]{
                        (int)Math.round(color.getRed() * 255),
                        (int)Math.round(color.getGreen() * 255), (int)Math.round(color.getBlue() * 255)
                };
                Pixel pixel = new Pixel(rgbValues);
                pixelArray[x][y] = pixel;
            }
        }
        projectManager.updateDrawingCanvas(pixelArray);
    }
}
