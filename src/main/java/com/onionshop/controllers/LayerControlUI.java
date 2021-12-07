package com.onionshop.controllers;

import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;

public class LayerControlUI extends HBox {

    @FXML private Label layerName;
    @FXML private CheckBox layerCheckBox;
    @FXML private final Canvas layer;
    @FXML private Button removeLayerButton;
    private int layerIndex;

    /**
     * Constructor for initializing MostRecentProjectUI with a name, path, and click event handler
     */
    public LayerControlUI(int index, EventHandler<MouseEvent> mouseClickHandler,
                         EventHandler<MouseEvent> mouseClickRemoveLayer, Canvas canvas) {
        initializeControl();
        setName(index);
        layerIndex = index;
        this.setOnMouseClicked(mouseClickHandler);
        this.layer = canvas;
        layerCheckBox.setOnMouseClicked(event -> layer.visibleProperty().set(!layer.isVisible()));
        removeLayerButton.setOnMouseClicked(mouseClickRemoveLayer);

        //Remove the remove button for the background layer
        if (index == 0) {
            this.getChildren().remove(removeLayerButton);
        }
    }

    /**
     * Initializes this control with the corresponding FXML file
     */
    public void initializeControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/com/onionshop/layer-control.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setAsActive() {
        this.setStyle("-fx-background-color: #bbb");
    }

    public void setAsInactive() {
        this.setStyle("-fx-background-color: #ddd");
    }

    /**
     * Returns the Canvas instance of this layer
     * @return the Canvas instance of this layer
     */
    public Canvas getLayer() {
        return layer;
    }

    /**
     * Returns the index of this layer
     * @return the index of this layer
     */
    public int getIndex() {
        return layerIndex;
    }

    /**
     * Returns the name of this layer
     * @return the name of this layer
     */
    public String getName() {
        return name().get();
    }

    /**
     * Sets the name of this layer with the given index
     * @param index of this layer
     */
    public void setName(int index) {
        name().set("Layer " + index);
    }

    public void setLayerIndex(int index) {layerIndex = index; }

    /**
     * Returns the text property of the layerText label
     * @return the text property of the layerText label
     */
    public StringProperty name() {
        return layerName.textProperty();
    }
}
