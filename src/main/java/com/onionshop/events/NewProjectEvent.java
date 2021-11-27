package com.onionshop.events;

/***
 * Gets the filename, directory, width, height, and size of the new project.
 */

public class NewProjectEvent {
    private final String projectName;
    private final String directory;
    private final int width;
    private final int height;

    /***
     * Initializes an instance of NewProjectEvent.
     *
     * @param projectName the name of the project.
     * @param directory the directory of the project.
     * @param width the width of the project.
     * @param height the height of the project.
     */

    public NewProjectEvent(String projectName, String directory, int width, int height) {
        this.projectName = projectName;
        this.directory = directory;
        this.width = width;
        this.height = height;
    }

    /***
     * Returns the name of the project.
     *
     * @return the name of the project.
     */

    public String getProjectName() {
        return projectName;
    }

    /***
     * Returns the directory of the project.
     *
     * @return the directory of the project.
     */

    public String getDirectory() {
        return directory;
    }

    /***
     * Returns the width of the project.
     *
     * @return the width of the project.
     */

    public int getWidth() {
        return width;
    }

    /***
     * Returns the height of the project.
     *
     * @return the height of the project.
     */

    public int getHeight() {
        return height;
    }
}
