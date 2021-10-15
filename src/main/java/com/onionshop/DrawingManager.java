package com.onionshop;

public class DrawingManager {
    private Tool currentlySelectedTool;
    private Colour currentlySelectedColour;
    private Project currentProject;

    public DrawingManager(Tool selectedTool, Colour selectedColour, Project curProject) {
        currentlySelectedTool = selectedTool;
        currentlySelectedColour = selectedColour;
        currentProject = curProject;
    }

    public int[][] updateCanvasAfterStroke(int x, int y) {
        return this.currentlySelectedTool.draw(currentProject, currentlySelectedColour, x, y);
    }

    public void setCurrentlySelectedTool(Tool currentlySelectedTool) {
        this.currentlySelectedTool = currentlySelectedTool;
    }

    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }

    public void setCurrentlySelectedColour(Colour currentlySelectedColour) {
        this.currentlySelectedColour = currentlySelectedColour;
    }

    public Tool getCurrentlySelectedTool() {
        return currentlySelectedTool;
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public Colour getCurrentlySelectedColour() {
        return currentlySelectedColour;
    }
}
