# Specification

Onionshop is a drawing program that has a canvas which takes mouse or tablet input and outputs line strokes (drawing). Drawings can be saved to files with the extension .onion, and reopened later to continue editing. The .onion files could also be opened in the notepad, and users could see the serialized documentation, including the height and width of the canvas, the project saved colour, and RGB colour code in the pixel array.

The first window the user will encounter is the Project Explorer which gives them the three options to open a drawing project. First, choosing a file in their computer using the file explorer. Second, the program will store and display up to 5 recently edited files, and users could choose to open one of them directly. Third, creating a new project by entering the canvas height, width, name, and the directory. After either having opened a previous project or creating a new project, the Main Canvas window will open.

Users can choose between different drawing tools from the toolbar, including pen, eraser, and shape tools. The shape tools include line, rectangle, and circle. When the user selects the Pen, they can press and hold down the left mouse button, this should result in a line appearing on the canvas tracing the path of the mouse movements on the canvas. The Eraser tool works similar to the pen tool, but erases the content at a specified position on the canvas. The Shape tool can draw specific shapes at user specified positions. After selecting the shape, users start creating the shape by clicking the left mouse button on the canvas and adjusting the size and orientation of the shape by holding and dragging the mouse. For every kind of tool, users can select the color and brush size.

The colour palette can be used to switch between different RGB colours. Users can choose colours from the given colour palette, or customize their own colour from the colour wheel. Once they select the colour, users could add and remove frequently used colours to the colour palette.

The canvas could have multiple layers, each layer is essentially a separate drawing that does not affect other layers. But when every layer is visible, they stack together and create the whole drawing. Users can choose between different existing layers, or add new layers. Each layer can be shown or hidden.

User can also interact with the program through their keyboard:
- Ctrl Z to undo a stroke, up to 5 times. 
- Ctrl Y to redo a stroke that was just undid by accident, up to 5 times. But after drawing a new stroke, the previous undo will not be able to be redo anymore. 
- Ctrl S to save the project. Users can save their project, which will overwrite the .onion file associated with the project with the new changes.
