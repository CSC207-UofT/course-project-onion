/*
Implements static save and load functionality for .onion files and Project serialization

@author Finn Williams
 */
package com.onionshop.managers;

import com.onionshop.entities.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OnionFileLoader {

    /**
     * Saves Project instance serialization to .onion file type
     *
     * @param project the Project instance of which a serialization will be saved
     * @return true if save was successful, false if the save failed
     */
    public static boolean saveProject(Project project) throws IOException {
        try {
            FileWriter writer = new FileWriter(project.getPath());
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (String line : project.serialize()) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + project.getPath() + "'");
            return false;
        }
    }


    /**
     * Returns whether the path is a valid directory (meaning it exists, is readable and
     * writable)
     *
     * @param pathString the path to be checked
     * @return whether the path is a valid directory
     */
    public static boolean isDirectoryValid(String pathString) {
        File file = new File(pathString);
        return file.exists() && file.canRead() && file.canWrite();
    }


    /**
     * Return whether the file already exists
     * Saves Project instance serialization to .onion file type
     *
     * @param pathString the path to be checked
     * @return whether the file already exists in the specified pathString
     */
    public static boolean doesFileAlreadyExist(String pathString) {
        File file = new File(pathString);
        return file.exists();
    }

    /**
     * Converts a .onion file to a Project instance
     *
     * @param path the location of the project .onion file
     * @return returns a Project instance if there are no files errors, returns null if there are errors
     */
    public static Project loadProject(String path) throws Exception {
        ProjectManager projectManager = ProjectManager.getInstance();

        String[] lines = getFileLines(path);
        Project loadedProject = generateProjectInstance(lines, path);
        loadedProject.setColourPalette(generateColourPalette(lines));

        List<Layer> layers = generateLayers(loadedProject.getWidth(), loadedProject.getHeight(), lines);
        for (Layer l : layers) {
            projectManager.addLayer(l);
        }


        return loadedProject;
    }

    /**
     * Reads .onion files and coverts them to a programmatic format
     *
     * @param path the path of the file to be read
     * @return an array of strings where arr[i] is the ith line of the file
     */
    private static String[] getFileLines(String path) {
        String line;
        ArrayList<String> lines = new ArrayList<>();

        //reading and storing lines of file at path
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
            String[] linesArray = new String[lines.size()];
            return lines.toArray(linesArray);
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");
            return null;
        } catch (IOException ex) {
            System.out.println("Error reading file '" + path + "'");
            return null;
        }
    }

    /**
     * Helper method to loadProject
     * Creates a project instance from the lines of a file in onion format
     *
     * @param lines array of Strings where array[i] is the i-th line of a file
     * @return Project instane with width and height read from lines and empty path
     */
    private static Project generateProjectInstance(String[] lines, String path) {
        int width = Integer.parseInt(lines[1].substring(lines[1].indexOf(':') + 1));
        int height = Integer.parseInt(lines[2].substring(lines[2].indexOf(':') + 1));
        return new Project(path, width, height);
    }

    /**
     * Helper method to loadProject
     * Creates a Colour array from the saved colours read from lines
     *
     * @param lines array of Strings where array[i] is the i-th line of a file
     * @return arrayList of saved colours read from lines
     */
    private static ColourPalette generateColourPalette(String[] lines) throws Exception {
        ArrayList<Colour> colours = new ArrayList<>();
        int lineNumber = getIndexOfString("[saved colours]", lines) + 1;
        String line;
        while (!Objects.equals(line = lines[lineNumber], "[pixels]")) {
            String name = line.substring(0, line.indexOf(':'));
            String stringRGB = line.substring(line.indexOf(':') + 1);
            colours.add(new Colour(name, extractRGB(stringRGB)));
            lineNumber++;
        }

        return new ColourPalette(colours);
    }

    /**
     * Helper method to loadProject;
     * Extracts the RBG value from a String in the format: "255,255,255,255"
     *
     * @param str the string from which the RGB value will be extracted
     * @return returns the RGB value contained in the String
     */
    public static int[] extractRGB(String str) {
        int[] RGB = new int[4];
        for (int i = 0; i < 3; i++) {
            RGB[i] = Integer.parseInt(str.substring(0, str.indexOf(',')));
            str = str.substring(str.indexOf(',') + 1);
        }
        RGB[RGB.length - 1] = Integer.parseInt(str);

        return RGB;
    }

    /**
     * Helper method to loadProject;
     * Reads a 1D array of strings coverts them to ints and then maps them to a 2D array
     * representing the
     *
     * @param width  width of canvas in pixels
     * @param height height of canvas in pixels
     * @param lines  array of Strings where array[i] is the i-th line of a file
     * @return returns Layer of Pixels where Layer.canvas[x][y] is a pixel on the screen at (x, y)
     */
    private static List<Layer> generateLayers(int width, int height, String[] lines) throws Exception {
        List<Layer> layers = new ArrayList<>();
        int lineNumber = getIndexOfString("[layer:0]", lines) + 1;
        int layerNumber = 0;
        String line = lines[lineNumber];

        while (!Objects.equals(line, "[end]")) {
            line = lines[lineNumber];
            lineNumber++;

            assert Integer.parseInt(line.substring(line.indexOf(":") + 1, line.indexOf("]"))) == layerNumber;
            layerNumber++;

            Layer layer = new Layer(width, height, new int[]{0,0,0,0});
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    line = lines[lineNumber];
                    layer.layerCanvas[x][y] = new Pixel(extractRGB(line));
                    lineNumber++;
                }
            }
        }
        return layers;
    }

    /**
     * finds and returns the index of the first appearance of a String in an array
     *
     * @param str   the string of which you are finding the index of the first appearance of
     * @param lines array of Strings where array[i] is the i-th line of a file
     * @return index of str appearance
     * @throws Exception throws exception str does not appear in the lines array
     */
    public static int getIndexOfString(String str, String[] lines) throws Exception {
        int lineNumber = -1;
        for (int i = 0; i < lines.length; i++) {
            if (Objects.equals(lines[i], str)) {
                lineNumber = i;
                break;
            }
        }
        if (lineNumber == -1) {
            throw new Exception("Array does not contain element: " + str);
        } //may want more specific error
        return lineNumber;
    }
}
