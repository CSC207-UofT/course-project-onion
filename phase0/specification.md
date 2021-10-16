# Specification

## Onionshop
Onionshop is a drawing program that has a canvas which takes mouse or tablet input and outputs line strokes (drawing). Before users start drawing, the ProjectExplorerController will give them the option to open one of their most recent projects, browse their file system for the project they want to open, or start a new project. Opening a file allows the user to draw on it, while creating a new canvas leads users to the newProjectController and requires them to enter a filename and choose a directory to the folder they want to save the file, then the program will automatically generate a blank canvas and file with given canvas size and name. 

Users can choose between different drawing tools from the toolbar, including pen, eraser, and shape tool. When the user selects the pen tool, they can press and hold down the left mouse button , this should result in a line appearing on the canvas tracing the path of the mouse movements on the canvas.  The eraser tool works similar to the pen tool, but erases the content at a specified position on the canvas. The shape tool can draw specific shapes, such as circles, triangles, rectangles, and so on, at user specified positions. After selecting the shape, users start creating the shape by clicking the left mouse button on the canvas and adjusting the size and orientation of the shape by holding and dragging the mouse. For every kind of tool, users can select the color and brush size, then the ToolStateManager will be updated to keep the information about the current tool.

The colour palette can be used to switch between different RGB colours. Users can choose colours from a colour wheel, and add and remove frequently used colours to the colour palette.

Users can undo and redo their strokes upto 5 times by clicking the undo and redo button.

Drawings can be saved to files with the extension .onion, and reopened later to continue editing. When the program is initially started users will have the option to open one of their most recent projects.