# Scenario Walkthrough

This walkthrough outlines the process of creating a new project and making a pen stroke.

### 1. Starting the program

_User will start the program and see the project explorer window open_
- ProjectExplorerController displays and manages this window 
- Window contents:
  - Recent projects 
  - “New project” button 
  - “Open project” button

### 2. Creating a new project

_User selects “New project” button and is shown “Create Project” window_
- newProjectController displays and manages this window 
- Window contents:
  - Project name input box 
  - Directory input (where the project is saved)
  - Canvas size input boxes for width and height 
  - Background colour selector 
  - And a “CREATE PROJECT” button


### 3. The drawing program

_User fills in project setup and selects “CREATE PROJECT”, they are shown a new window: the actual drawing program_
- When “CREATE PROJECT” is selected the user provided save address is tested for validity and then a Project instance is created using the user provided info 
- Also starts ProjectStateController which contains the user interface for the canvas and drawing tools for the new project 
  - Window contents:
  - Toolbar with pen tool 
  - Brush/pen size options 
  - Drawing canvas 
  - Colour selection tools (default is black)


### 4. Selecting a tool

_User selects pen tool, updating the currently selected tool in ToolStateManager_

### 5. Creating a pen stroke

_The user clicks and drags on the canvas creating a pen stroke_

- CanvasEvents tracks the location of the pointer from the moment you click (getting raw data from JavaFX), converting the (x, y) screen coordinates to (x,y) pixel coordinates on the drawing canvas and sending these coordinates to DrawingManager 
  - Location of the pointer and whether you are clicking or not is managed by JavaFX, CanvasEvents processes this raw data 
- DrawingManager takes in the position updates, updating the canvas (stored in the Project instance) according to the current tool, size, and colour in ToolStateManager by calculating the affected pixels and applying the given changes to them; this info is handed to ProjectManager

- ProjectManager combines these changes with the existing canvas (which contains any other strokes you may have made) stored in the Project instance through a process similar to combining layers 
- During this whole process, ProjectController is updating the changes to the screen, allowing the user to see the stroke as they make it


### 6. Handling Un-do and Re-do operations

_Once the user has completed the stroke a copy of the current drawing canvas is sent to UndoRedoManager_

### 7. CTRL + Z

_The user decides they do not like their pen stroke and presses CTRL + Z to undo their change_
- The keystroke is caught by KeyboardEventManager and triggers the undo event in UndoRedoManager 
- UndoRedoManager fetches the previous state of the drawing canvas and updates the active canvas (the one stored in the Project instance), but not before saving the active canvas state allowing the user to redo their changes 
  - If a change is made after any number of undos, all the redo states are deleted in order to prevent branching
- ProjectController updates the canvas with the new active drawing canvas, allowing the user to see the changes


