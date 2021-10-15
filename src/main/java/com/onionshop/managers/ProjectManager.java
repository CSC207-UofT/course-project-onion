package com.onionshop.managers;

import com.onionshop.Project;
import com.onionshop.events.NewProjectEvent;
import com.onionshop.OnionFileLoader;

public class ProjectManager {
    private Project currentProject;

    ProjectManager() {}

    /**
     * Creates a new project based on newProjectEvent
     * @param newProjectEvent an event that describes the base properties of the new project.
     */
    public void newProject(NewProjectEvent newProjectEvent) {
        /* TODO: make currentProject have the same properties as described in the new
            project event. Use OnionFileLoader to save project */
    }
}
