package com.onionshop.controllers;

import com.onionshop.entities.Shape;
import com.onionshop.events.CanvasEvents;
import com.onionshop.events.LayerEvents;
import com.onionshop.managers.DrawingManager;
import com.onionshop.managers.LayerManager;
import com.onionshop.managers.ProjectManager;
import com.onionshop.managers.ToolStateManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * records the action -> trigger methods -> send data to backend
 * Actions include: selecting a tool on the toolbar(ToolbarEvents - to be implemented), updating brush/pen sizes(Pen),
 * drawing on canvas and selecting a colour(to be implemented).
 */
public class ProjectStateController implements Initializable {

    @FXML public Button eraser;
    @FXML public Button rectangle;
    @FXML public Button circle;
    @FXML public Button line;
    @FXML public Button brushPen;
    @FXML public AnchorPane container;
    @FXML public VBox layersContainer;
    @FXML public AnchorPane canvasCollection;
    @FXML private ColorPicker projectColourPicker;
    @FXML private Canvas selectedLayer;
    @FXML private Slider toolSizeSlider;
    @FXML private FlowPane colourPalette;

    private LayerControlUI selectedLayerUIControl;
    private Button selectedToolButton;
    private final DrawingManager projectDrawingManager = new DrawingManager();
    private final ToolStateManager toolStateManager = ToolStateManager.getInstance();
    private final CanvasEvents canvasInputProcessor = new CanvasEvents(projectDrawingManager);
    private final LayerEvents layerInputProcessor = new LayerEvents(projectDrawingManager);
    private final ProjectManager projectManager = ProjectManager.getInstance();
    private Color currentCanvasColour = Color.BLACK;

    private LayerManager layerManager = LayerManager.getInstance();


    private int brushSize;


    /**
     * This function is called when this scene is first initialized
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLayers();
        initColourPalette();
        selectedToolButton = brushPen;
        setSelectedToolButton(brushPen);
    }

    /**
     * Initializes scene with each layer
     */
    public void initLayers() {
        int layerNumber = projectManager.getCurrentProject().layers.size();
        for (int currentLayer = 0; currentLayer < layerNumber; currentLayer++) {
            layerInputProcessor.processSelectLayer(currentLayer);
            initCanvas();
        }
        System.out.println("Layer: " + layerNumber);
        selectedLayer = (Canvas) canvasCollection.getChildren().get(layerNumber);
        selectedLayerUIControl = (LayerControlUI) layersContainer.getChildren().get(0);
        selectedLayerUIControl.setAsActive();
    }

    /**
     * Initializes the canvas with current project's saved pixels
     * Sets the height and width of the canvas
     */
    private void initCanvas() {
        double conversionValue = 255.0;
        int canvasHeight = projectManager.getCurrentProject().getHeight();
        int canvasWidth = projectManager.getCurrentProject().getWidth();

        Canvas newLayer = createNewLayer();

        PixelWriter pixelWriter = newLayer.getGraphicsContext2D().getPixelWriter();

        // set canvas pixels to match the pixels of the current project
        for (int x = 0; x < canvasWidth; x++) {
            for (int y = 0; y < canvasHeight; y++) {
                int[] rgb = layerManager.getSelectedLayer().getPixelByCoord(x, y).getRGB();
                Color color = Color.rgb(rgb[0], rgb[1], rgb[2], (double) rgb[3] / conversionValue);
                pixelWriter.setColor(x, y, color);
            }
        }

        canvasCollection.getChildren().add(newLayer);
        layersContainer.getChildren().add(0,
                new LayerControlUI(layersContainer.getChildren().size(), this::onSelectLayer, this::onRemoveLayer,
                        newLayer));

        container.setMinHeight(canvasHeight < 300 ? 475 : canvasHeight + (float)(canvasHeight / 500) * 125);
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
            selectedLayer.getGraphicsContext2D().setFill(currentCanvasColour);
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


    /** A function that draws as the user clicks and drags their mouse across the canvas
     *
     * @param canvasMouseLocation - A mouseEvent passed in containing the x and y coordinates of the mouse
     */
    @FXML
    protected void onCanvasMouseDragged(MouseEvent canvasMouseLocation) {
        int[][] updatedPixels = canvasInputProcessor.processControllerDataForDrawingManager(canvasMouseLocation);

        PixelWriter canvasPixelWriter = selectedLayer.getGraphicsContext2D().getPixelWriter();
        for (int[] updatedPixel : updatedPixels) {
            canvasPixelWriter.setColor(updatedPixel[0], updatedPixel[1], currentCanvasColour);
        }
    }

    /** A function that updates the canvas given a set of pixels. We may want to add this as a helper
     * function to the above onCanvasMouseDragged function.
     *
     * @param updatedPixels the pixels to update on the canvas
     */
    protected void updateCanvas(int[][] updatedPixels) {
        PixelWriter canvasPixelWriter = selectedLayer.getGraphicsContext2D().getPixelWriter();
        for (int[] updatedPixel : updatedPixels) {
            canvasPixelWriter.setColor(updatedPixel[0], updatedPixel[1], currentCanvasColour);
        }
    }

    /**
     * Refreshes JavaFX canvas layers
     */
    public void reInitLayers() {
        removeAllLayers();
        initLayers();
    }

    /**
     * Helper method to reInitLayers
     * Removes all JavaFX canvas layers from the front-end
     */
    private void removeAllLayers() {
        canvasCollection.getChildren().removeAll(canvasCollection.getChildren());
        layersContainer.getChildren().removeAll(layersContainer.getChildren());
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
        setSelectedToolButton(brushPen);
    }

    /**
     * Sets the current tool being used to eraser
     */
    @FXML
    public void onEraserClick() {
        canvasInputProcessor.setTool(Tools.ERASER, brushSize);
        currentCanvasColour = new Color(1, 1, 1, 0);
        onToolSizeSliderMove();
        setSelectedToolButton(eraser);
    }


    /**
     * Sets the current tool being used to line
     */
    public void onLineToolClick(ActionEvent event) {
        canvasInputProcessor.setTool(Tools.LINE, brushSize);
        setDrawingColour();
        setSelectedToolButton(line);
    }


    /**
     * Sets the current tool being used to circle
     */
    public void onCircleToolClick(ActionEvent event) {
        canvasInputProcessor.setTool(Tools.CIRCLE, brushSize);
        setDrawingColour();
        setSelectedToolButton(circle);
    }

    /**
     * Sets the current tool being used to rectangle
     */
    public void onRectangleToolClick(ActionEvent event) {
        canvasInputProcessor.setTool(Tools.RECTANGLE, brushSize);
        setDrawingColour();
        setSelectedToolButton(rectangle);
    }

    /**
     * Make the colour of the background of the selected tool darker
     */
    private void setSelectedToolButton(Button button) {
        selectedToolButton.setStyle("-fx-background-color: #ddd;");
        selectedToolButton = button;
        button.setStyle("-fx-background-color: #fff;");
    }

    /**
     * Update the current canvas state after each mouse drag event.
     * @param mouseDragEvent
     */
    public void onCanvasMouseReleased(MouseEvent mouseDragEvent) {
        updateShapeOnMouseRelease(mouseDragEvent);
        projectManager.updateLayers(projectManager.getCurrentProject().getLayers());
    }

    /**
     * This function checks if the current tool is a shape and if it is it draws a shape on the canvas when the
     * user releases their mouse
     *
     * @param mouseDragEvent The MouseEvent triggered when the user releases their mouse
     */
    public void updateShapeOnMouseRelease(MouseEvent mouseDragEvent) {
        // These two if statement check if the current tool as a shape that needs to be drawn with click and drag
        if (ToolStateManager.getInstance().getCurrentToolState() instanceof Shape) {
            if (((Shape) ToolStateManager.getInstance().getCurrentToolState()).getDrawStage() == 2) {
                // If it is a shape, this draws the shape on the canvas when the user releases their mouse
                int[][] pixelsToUpdate = projectDrawingManager.drawShapeOnRelease((int) mouseDragEvent.getX(),
                        (int) mouseDragEvent.getY());
                //This calls a function to update the user end canvas
                updateCanvas(pixelsToUpdate);
            }
        }
    }

    /**
     * Changes the selected layer to the one that one clicked on
     */
    private void onSelectLayer(MouseEvent mouseEvent) {
        LayerControlUI layerControlUI = (LayerControlUI) mouseEvent.getSource();

        layerInputProcessor.processSelectLayer(layerControlUI.getIndex());

        selectedLayerUIControl.setAsInactive();
        selectedLayer = layerControlUI.getLayer();
        selectedLayerUIControl = layerControlUI;
        layerControlUI.setAsActive();
    }


    /**
     * Returns a new layer with the onMouseDragged and onMouseReleased event handlers
     */
    private Canvas createNewLayer() {
        Canvas newLayer = new Canvas(
                projectManager.getCurrentProject().getWidth(),
                projectManager.getCurrentProject().getHeight()
        );
        newLayer.setOnMouseDragged(this::onCanvasMouseDragged);
        newLayer.setOnMouseReleased(this::onCanvasMouseReleased);

        return newLayer;
    }


    /**
     * Create a new layer
     */
    public void addLayer(ActionEvent actionEvent) {
        layerInputProcessor.processAddLayer();
        Canvas newLayer = createNewLayer();
        canvasCollection.getChildren().add(newLayer);
        layersContainer.getChildren().add(0,
                new LayerControlUI(layersContainer.getChildren().size(), this::onSelectLayer, this::onRemoveLayer,
                        newLayer));
    }

    /**
     * Remove an existing layer and then updates the indices of the other layers to reflect its removal
     *
     * @param removeButtonClick The event triggered when the user clicks the remove button
     */
    public void onRemoveLayer(MouseEvent removeButtonClick) {
        Button selectedRemoveButton = (Button) removeButtonClick.getSource();
        LayerControlUI layerToRemoveControlUI = (LayerControlUI) selectedRemoveButton.getParent();
        int removedLayerIndex = layerToRemoveControlUI.getIndex();

        //Make sure we're not removing the background layer
        if (removedLayerIndex != 0) {
            layerInputProcessor.processRemoveLayer(removedLayerIndex);

            //Remove the layer from the layer container pane and the canvas itself
            Canvas layerToRemove = layerToRemoveControlUI.getLayer();
            canvasCollection.getChildren().remove(layerToRemove);
            layersContainer.getChildren().remove(layerToRemoveControlUI);

            //Update the indices of the other layers after removing one
            for (Node layer : layersContainer.getChildren()) {
                LayerControlUI currentLayer = (LayerControlUI) layer;
                if (currentLayer.getIndex() > removedLayerIndex) {
                    currentLayer.setLayerIndex(currentLayer.getIndex() - 1);
                }
            }
        }
    }
}
