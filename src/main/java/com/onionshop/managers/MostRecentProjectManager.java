package com.onionshop.managers;
import com.onionshop.entities.Project;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Stack;

public class MostRecentProjectManager {

    private File mostRecentProjectFile;

    /**
     * Adds the given most recent project to the mostRecentProjectFile (default location is Documents).
     * If length of mostRecentProjectFile exceeds 5, removes the oldest file and pushes the new file to the top of the
     * stack.
     * If the project already exists in the mostRecentProjectFile, then order in the mostRecentProjectFile is
     * reshuffled according the selection.
     *
     * @param mostRecentProject the given most recent project.
     * @throws IOException
     */
    public void addMostRecentProject(Project mostRecentProject) throws IOException {

        Stack<String> stack = new Stack<String>();
        loadMostRecentProjects();
        writeStackFromFile(stack);

        String projectName = mostRecentProject.extractProjectName();

        String projectArray = "[" + projectName + "," + mostRecentProject.getPath() + "]";

        if (stack.contains(projectArray)) {
            stack.remove(projectArray);
            reWriteFile(projectArray, stack);

        } else if (stack.size() < 5){
            stack.push(projectArray);
            writeToFile(projectArray);
        } else {
            stack.remove(0);
            reWriteFile(projectArray, stack);
        }

    }

    /**
     * Helper method for addMostRecentProject. Used to reorder the .txt file.
     *
     * @param projectArray the array in the form of [projectDirectory, projectName] to be added.
     * @param stack the stack representing the order of most recent projects.
     * @throws IOException
     */
    public void reWriteFile(String projectArray, Stack<String> stack) throws IOException {
        stack.push(projectArray);
        this.mostRecentProjectFile.delete();
        loadMostRecentProjects();
        for (String projectArrayStack: stack) {
            writeToFile(projectArrayStack);
        }
    }

    /**
     * Helper method to write to .txt files.
     *
     * @param projectArray The string to the be written in a new line.
     * @throws IOException
     */
    public void writeToFile(String projectArray) throws IOException {
        FileWriter writer = new FileWriter(this.mostRecentProjectFile, true);
        writer.write(System.lineSeparator());
        writer.write(projectArray);
        writer.close();
    }

    /**
     * Helper method to update the stack from the file.
     *
     * @param stack the stack to update.
     * @throws IOException
     */
    public void writeStackFromFile(Stack stack) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.mostRecentProjectFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    stack.push(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the most recent projects in a 2D array of length 5.
     * Columns represent the projectName (index 0) and projectDirectory (index 1).
     * Each row represents an individual project.
     *
     * @return the 2D array of mostRecentProjects.
     * @throws IOException
     */
    public String[][] getMostRecentProjects() throws IOException {
        loadMostRecentProjects();
        Stack<String> stack = new Stack<String>();
        writeStackFromFile(stack);

        String[][] mostRecentProjects = new String[5][2];

        for (int i = 4; i >= 0; i --) {
            String project = stack.get(i);
            String projectName = project.substring(1, project.indexOf(','));
            String projectDirectory = project.substring(project.indexOf(',') + 1, project.length() - 1);
            mostRecentProjects[i][0] = projectName;
            mostRecentProjects[i][1] = projectDirectory;
        }
        return mostRecentProjects;
    }

    /**
     * Loads mostRecentProjects from the file if it exists.
     * If the file does not exist, creates a new file called fresh-onions.txt in the Documents folder by default.
     *
     * @throws IOException
     */
    public void loadMostRecentProjects() throws IOException {

        // getting default path
        String defaultPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();

        this.mostRecentProjectFile = new File(defaultPath + "\\fresh-onions.txt");

        try {
            if (this.mostRecentProjectFile.createNewFile()) {
                System.out.println("New file created!");
            } else {
                System.out.println("File already exists!");
            }
            System.out.println(this.mostRecentProjectFile);
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
    }
}