package com.onionshop.controllers;

import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MostRecentProjectUI extends VBox {

    @FXML private Label mostRecentName;
    @FXML private Label mostRecentPath;

    /**
     * Constructor for initializing MostRecentProjectUI with no arguments
     */
    public MostRecentProjectUI() {
        initializeControl();
    }

    /**
     * Constructor for initializing MostRecentProjectUI with a name, path, and click event handler
     */
    public MostRecentProjectUI(String name, String path, EventHandler<MouseEvent> mouseClickHandler) {
        initializeControl();
        setName(name);
        setPath(path);
        this.setOnMouseClicked(mouseClickHandler);
    }

    /**
     * Initializes this control with the corresponding FXML file
     */
    public void initializeControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/com/onionshop/most-recent-project.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Returns the name of this most recent project
     * @return the name of this most recent project
     */
    public String getName() {
        return name().get();
    }

    /**
     * Sets the name of this most recent project with the given newName
     * @param newName the new name of this most recent project
     */
    public void setName(String newName) {
        name().set(newName);
    }

    /**
     * Returns the text property of the mostRecentName label
     * @return the text property of the mostRecentName label
     */
    public StringProperty name() {
        return mostRecentName.textProperty();
    }

    /**
     * Returns the path of this most recent project
     * @return the path of this most recent project
     */
    public String getPath() {
        return path().get();
    }

    /**
     * Sets the path of this most recent project with the given newPath
     * @param newPath the new path of this most recent project
     */
    public void setPath(String newPath) {
        path().set(newPath);
    }

    /**
     * Returns the text property of the mostRecentPath label
     * @return the text property of the mostRecentPath label
     */
    public StringProperty path() {
        return mostRecentPath.textProperty();
    }
}
