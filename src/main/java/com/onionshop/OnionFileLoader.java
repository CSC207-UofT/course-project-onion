/*
Implements static save and load functionality for .onion files and Project serialization

@author Finn Williams
 */
package com.onionshop;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

public class OnionFileLoader {

    /*
        Saves Project instance serialization to .onion file type
        @param currentProject: the Project instance of which a serialization will be saved
        @return true if save was successful, false if the save failed
     */
    public static boolean saveProject(Project project) throws IOException {
        try {
            FileWriter writer = new FileWriter(project.getPath());
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (String line : project.Serialize()) {
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

    /*
    Converts a .onion file to a Project instance

    @param path: the location of the .onion file
    @return: returns a Project instance if there are no files errors, returns null if there are errors
     */
    public static Project loadProject(String path) {
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
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");
            return null;
        } catch (IOException ex) {
            System.out.println("Error reading file '" + path + "'");
            return null;
        }

        //CREATING PROJECT INSTANCE

        //getting width and height
        int lineNumber = 1;
        int width = Integer.parseInt(lines.get(lineNumber).substring(lines.indexOf(":")));
        lineNumber++;
        int height = Integer.parseInt(lines.get(lineNumber).substring(lines.indexOf(":")));
        lineNumber++;

        Project loadedProject = new Project(path, width, height);
        loadedProject.updatePath(path);

        //getting saved colours
        ArrayList<Colour> colourPalette = new ArrayList<>();
        while (!Objects.equals(line = lines.get(lineNumber), "[pixels]")) {
            String name = line.substring(0, line.indexOf(":"));
            String stringRGB = line.substring(line.indexOf(":") + 1);
            int[] RGB = new int[]{
                    Integer.parseInt(stringRGB.substring(0, stringRGB.indexOf(","))),
                    Integer.parseInt(stringRGB.substring(stringRGB.indexOf(",") + 1, stringRGB.lastIndexOf(","))),
                    Integer.parseInt(stringRGB.substring(stringRGB.lastIndexOf(",") + 1))
            };
            colourPalette.add(new Colour(name, RGB));
            lineNumber++;
        }
        lineNumber++;
        //getting saved drawing canvas

        Pixel[][] drawingCanvas = new Pixel[width][height];
        //NOTE: may want to replace this with ArrayList mapping algorithm - Finn
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                line = lines.get(lineNumber);
                int[] RGB = new int[]{
                        Integer.parseInt(line.substring(0, line.indexOf(","))),
                        Integer.parseInt(line.substring(line.indexOf(",") + 1, line.lastIndexOf(","))),
                        Integer.parseInt(line.substring(line.lastIndexOf(",") + 1))
                };
                drawingCanvas[x][y] = new Pixel(RGB);
                lineNumber++;
            }
        }
        assert (Objects.equals(lines.get(lineNumber), "[end]"));
        loadedProject.colourPalette = colourPalette.toArray(loadedProject.colourPalette);
        loadedProject.drawingCanvas = drawingCanvas;

        return loadedProject;
    }
}
