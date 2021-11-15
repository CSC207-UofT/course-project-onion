### Revised Specification

Onionshop is a drawing program that has a canvas which takes mouse or tablet input and outputs line strokes (drawing). Drawings can be saved to files with the extension .onion, and reopened later to continue editing. The .onion files could also be opened in the notepad, and users could see the serialized documentation, including the height and width of the canvas, the project saved colour, and RGB colour code in the pixel array.

The first window the user will encounter is the Project Explorer which gives them the options to open a .onion file from their file explorer, open a recently edited file, or create a new .onion project. After either having opened a previous project or creating a new project, the Main Canvas window will open.

Users can choose between different drawing tools from the toolbar, including pen, eraser, and shape tool. When the user selects the Pen, they can press and hold down the left mouse button, this should result in a line appearing on the canvas tracing the path of the mouse movements on the canvas. The Eraser tool works similar to the pen tool, but erases the content at a specified position on the canvas. The Shape tool can draw specific shapes (line and rectangle) at user specified positions. After selecting the shape, users start creating the shape by clicking the left mouse button on the canvas and adjusting the size and orientation of the shape by holding and dragging the mouse. For every kind of tool, users can select the color and brush size, then the ToolStateManager will be updated to keep the information about the current tool.

The colour palette can be used to switch between different RGB colours. Users can choose colours from a colour wheel, and add and remove frequently used colours to the colour palette.

User can also interact with the program through their keyboard:
- Ctrl Z to undo a stroke, up to 5 times.  (WIP)
- Ctrl Y to redo a stroke that was just undid by accident, up to 5 times. (also WIP)
- Ctrl S to save the project. Users can save their project, which will overwrite the .onion file associated with the project with the new changes.

The summary of  additional functionality that we have implemented between phase 0 and the end of phase 1.
- Save project to file
- Open project from file
- Most Recent projects
- Shape tool - line, rectangle
- Eraser tool
- Brush size
- Colour palette
- Undo / redo (Work In Progress)
- Keyboard events

What we are considering adding in Phase 2 (if possible):
- Layers: the user can add a layer in their drawing such that each layer is essentially a separate drawing that does not affect other layers. But while each layer is visible, they stack together and create the whole drawing.
- Circle tool
