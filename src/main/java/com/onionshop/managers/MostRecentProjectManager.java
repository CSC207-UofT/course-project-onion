package com.onionshop.managers;

import java.io.File;

public class MostRecentProjectManager {

    private File mostRecentProjectFile;

    /* TODO: create a function for appending a mostRecentProject to the mostRecentProjectFile
    */
    public void addMostRecentProject() {

    }

    /* TODO: create a function for creating / loading mostRecentProjects
        Each most recent project is an array with [projectName, directory]
        For our purposes, I think a 2D String array is fine, but if you think
        creating a MostRecentProject class makes more sense, go for it, idk
     */
    public String[][] getMostRecentProjects() {
        loadMostRecentProjects();
        return null;
    }

    // TODO: create a function for creating / loading mostRecentProjects
    public File loadMostRecentProjects() {
        // check if fresh-onions file exists
        // if it does, set mostRecentProjectFile to that file
        // if it does not, create the file and then set mostRecentProjectFile to that file
        // (create it either in like make it in Documents, or in this project idk)
        return null;
    }


}
