package com.onionshop.controllers;

import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class LayerControlUI extends HBox {

    @FXML private Label layerName;
    @FXML private CheckBox layerCheckBox;
    @FXML private final Canvas layer;
    private final int layerIndex;

    /**
     * Constructor for initializing MostRecentProjectUI with a name, path, and click event handler
     */
    public LayerControlUI(int index, EventHandler<MouseEvent> mouseClickHandler,
                          Canvas canvas) {
        initializeControl();
        setName(index);
        layerIndex = index;
        this.setOnMouseClicked(mouseClickHandler);
        this.layer = canvas;
        layerCheckBox.setOnMouseClicked(event -> layer.visibleProperty().set(!layer.isVisible()));
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

    /**
     * Returns the text property of the layerText label
     * @return the text property of the layerText label
     */
    public StringProperty name() {
        return layerName.textProperty();
    }
}