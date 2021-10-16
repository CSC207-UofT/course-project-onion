package com.onionshop.controllers;
import com.onionshop.Project;

public class NewProjectController {
    /**
     * A controller that create a new project.
     * Users need to text their project name, the width and height of canvas they want to create,
     * and select the folder they want to save the project.
     */

    private int width;
    private int height;
    private String path;

    /***
     * Instantiates a new instance of the NewProjectController
     *
     * @param width the required width of the project.
     * @param height the required height of the project.
     * @param path the required path of the project.
     */

    public NewProjectController(int width, int height, String path) {
        this.width = width;
        this.height = height;
        this.path = path;
    }

    /***
     * Creates a new instance of a project using the controller's instance variables.
     *
     * @return returns an instance of a project.
     */

    public Project createNewProject() {
        return new Project(this.path, this.width, this.height);
    }

}
