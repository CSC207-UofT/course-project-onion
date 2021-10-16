# Specification

## Onionshop (Summarized Specification):

A drawing program that has a canvas which takes mouse or tablet input and outputs line strokes (drawing). When the left mouse button is pressed and held down, this should result in a line to appear on the canvas tracing the path of the mouse movements on the canvas. Users can choose between different drawing tools from the toolbar, including pen, eraser, and shape tool.

The colour palette can be used to switch between different RGB colours. Users can choose colours from a colour wheel, and add and remove frequently used colours to the colour palette.

Users can undo and redo their strokes upto 5 times.

Drawings can be saved to files with the extension .onion, and reopened later to continue editing. When the program is initially started users will have the option to open one of their most recent projects, browse their file system for the project they want to open, or start a new project.


## Onionshop (Full specification)

Onionshop is a drawing program that has a canvas which takes mouse or tablet input and outputs line strokes (drawing). Drawings can be saved to files with the extension .onion, and reopened later to continue editing.

The first window the user will encounter is the Project Explorer which gives them the options to open a .onion file from their file explorer, open a recently edited file, or create a new .onion project.
If a project is opened from the file explorer or from most recent projects, the file will be loaded/read and assigned as the currentProject.
If the user chooses to create a new project, then the New Project window will be opened. In order to create a new project users are required to provide a project name, directory to save the new project, as well as canvas width and height. These properties will be used to create the .onion file in the specified directory with the specified name and basic onion project

.onion Files:
Contain the dimensions of the canvas
The project specific colour palette
And a list of individual pixels

After either having opened a previous project or creating a new project, the Main Canvas/Drawing window will open.

Users can choose between different drawing tools from the toolbar, including pen, eraser, and shape tool.
When the user selects the Pen, they can press and hold down the left mouse button, this should result in a line appearing on the canvas tracing the path of the mouse movements on the canvas. Size of the pen can be selected.
The Eraser tool works similar to the pen tool, but erases the content at a specified position on the canvas. Size of the eraser can be selected.
The Shape tool can draw specific shapes, such as circles, triangles, rectangles, and so on, at user specified positions. After selecting the shape, users start creating the shape by clicking the left mouse button on the canvas and adjusting the size and orientation of the shape by holding and dragging the mouse. For every kind of tool, users can select the color and brush size, then the ToolStateManager will be updated to keep the information about the current tool.

The colour palette can be used to switch between different RGB colours. Users can choose colours from a colour wheel, and add and remove frequently used colours to the colour palette.

The colour as well as the tool together affect what will ultimately be outputted on the canvas.

Users can save their project, which will overwrite the .onion file associated with the project with the new changes.

Users can undo and redo their strokes upto 5 times.

User can also interact with the program through their keyboard:
Ctrl Z to undo a stroke
Ctrl Y to redo a stroke that was just undid by accident
Ctrl S to save the project.