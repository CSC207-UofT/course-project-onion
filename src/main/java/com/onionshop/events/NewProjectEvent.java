package com.onionshop.events;

public class NewProjectEvent {
    private String projectName;
    private String directory;
    private int width;
    private int height;

    public NewProjectEvent(String projectName, String directory, int width, int height) {
        this.projectName = projectName;
        this.directory = directory;
        this.width = width;
        this.height = height;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDirectory() {
        return directory;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
