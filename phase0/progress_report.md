# Progress Report

## Project Specification Summary:

A drawing program that has a canvas which takes mouse or tablet input and outputs line strokes (drawing). When the left mouse button is pressed and held down, this should result in a line to appear on the canvas tracing the path of the mouse movements on the canvas. Users can choose between different drawing tools from the toolbar, including pen, eraser, and shape tool. 

The colour palette can be used to switch between different RGB colours. Users can choose colours from a colour wheel, and add and remove frequently used colours to the colour palette. 

Users can undo and redo their strokes upto 5 times.

Drawings can be saved to files with the extension .onion, and reopened later to continue editing. When the program is initially started users will have the option to open one of their most recent projects, browse their file system for the project they want to open, or start a new project. 

## CRC Model Summary:
The Onionshop CRC model is divided into four specifications: User Interface, Controllers, Use Cases, and Entities. From which OnionShop is the only User Interface that starts up the initial scene. Then, there are four Controllers: ProjectExplorerController, NewProjectController, ProjectStateController, and KeyboardEventController, plus one SceneSwitcher in the Controller specification. From them we have currently implemented all except the KeyboardEventController that handles keyboard inputs related to commands such as undo, redo, and save. In the Use Cases, there are four Events: CanvasEvent, ToolbarEvent, ColorSelectionEvents, and NewProjectEvent. To give an example, the NewProjectEvent is responsible for the Filename, Directory, Width, and Height of a new project. After that, there are four Managers, from which the ProjectManager directly collaborates with the NewProjectEvent, OnionFileLoader, UndoRedoManager, and Project from the Entity class to fulfill the creation, storage, update, undo/redo instance, and opening of the project. The ToolStateManager manages and stores the state of the tool and colour on the canvas. Last, there are eight specific entities with the Project entity being one of them that stores current project address, drawing, dimensions of drawing, colour palette, etc. all the while directly working with the previously mentioned ProjectManager. The entities Colour, Tool, and Brush all work with the ToolStateManager as well. There are also a few cards which contain miscellaneous features and the .onion file format.

## Scenario walk-through Summary: 
The scenario walk-through outlines the case in which the user creates a new project and makes a pen stroke. 

1) A given user will start the program and see the project explorer window open. 
2) In this window, the user selects the “New project” button and then is shown the “Create project” window. Here, a user can specify the requirements of the project. 
3) After doing so, the user selects “CREATE PROJECT”, which opens the drawing window. 
4) The user can then select the pen tool, and then click and draw intuitively on the canvas, creating a pen stroke. Once this drawing action is completed, a copy of the current canvas is saved for undo and redo actions. 
5) The user can then press CTRL + Z to undo their change.


## Skeleton program Summary:

### Getting the skeleton program running
You must download and configure the JavaFX 11.0.2 SDK for your local machine. Full instructions are available on the README in the root directory of the project repository: https://github.com/CSC207-UofT/course-project-onion/blob/main/README.md

### Summary of implementation
For the skeleton program, we have implemented 2 flows/scenarios in our project, including creating a new project, and drawing on the canvas with the pen tool. 

In the User Interface, we currently have three screens implemented (roughly) using fxml, the Project Explorer screen, the New Project screen, and the Main Canvas/Drawing screen. 

**New Project Flow:** On the Project Explorer (UI) screen the user can click on the New Project button. This event is handled by the ProjectExplorerController (controller) which then uses the SceneSwitcher (controller layer) to switch to the New Project (UI) screen. Here the user will enter the properties of the project including the project name, canvas width, canvas height, and directory the project will be saved to. The events on this screen are handled by the NewProjectController (controller) which will take the input from the user and create a NewProjectEvent (use case layer) which is then passed to the ProjectManager.newProject method. The ProjectManager (use case) will then convert NewProjectEvent into a Project (entity), and OnionFileLoader (use case) to create the .onion file in the correct directory. 

**Drawing Flow (In Progress):** The events on the Main Canvas/Drawing screen (UI) are handled by the ProjectStateController (controller). The mouse drag events are converted to CanvasEvents (use case layer) which are then handled by the DrawingManager (use case). The pen stroke data is passed on to DrawingManager and Pen (entity) will update the pixels affected on the canvas. The changed pixel data will then be passed back to ProjectStateController to update the current canvas.


## Open question your group is struggling with
- What is the best way to handle the state of the program (the currently selected tool and colour for instance)? 
- How should we organize our events? There doesn't seem to be a lot of overlap between them, but would it be a good idea for them to share some common interface?
- Currently we have ProjectManager as a singleton that holds an instance of itself, is this okay to do, or is it bad practice? We want ProjectManager to be accessible to multiple controllers and this is one solution we found but we are not sure about it.
- Should controllers be unit tested? If so, how? 
- Our current idea for how to implement Most Recent Projects is to have a “application properties” file that stores the 5 most recently edited project’s directories. This solution doesn’t seem very elegant, but since we are not handling databases in our project, this seems like the only way to persist data outside of our application. Thoughts? 
- How to update the tool state when multiple tools are involved? Especially when completely differently functioning tools (Shape) are involved. Adding distinct methods might be bad practice since it can get messy.
- How to implement the Shape tool so that the user draws out the selection and the inner shape is selected? Possibly going to be decently difficult.
- How will we keep track of “Strokes” for the undo redo manager given the way individual (x, y) coordinates are sent when the mouse is dragged across the screen? 

## What has worked well so far with your design
So far, our group has built the general structure of the project according to the CRC cards. For the frontend, we completed a simple UI design that provides sample interfaces for opening files, creating new projects, and canvas. For the backend, we are working to separate the inputs from JavaFX into our own events, creating the interaction between our user interface and the user case. We also created an interface named tool and an abstract class named brush, then built the whole inheritance relationship about the tools we planned to implement. The unit test for ToolStateManager also finished. For the canvas, we used the (x, y) coordinate system to represent the location of pixels. The pixel class helps us to track the color which is currently used and update the color of the pixel. 
Besides, we basically finished the new project workflow. Users could create a new project according to the NewProjectController by entering the filename and the size of canvas, then the NewProjectEvents and ProjectManager will generate an empty project with .onion format, storing in the directory where the user specified. The .onion files are serialized by the Project class, loaded and generated by the OnionLoader. So far they've worked pretty well, and the ProjectManager use case is going well also. 
For other parts that haven't been implemented yet, we've created basic classes that will be completed in the future.


## Brief summary of group member contribution and future plans

**Bhavjot**
- Worked on CRC cards and helped with NewProjectController and ProjectExplorerController
- Documentation, summary of scenario walkthrough, formatting of directories, phase 0 and related requirements on Github
- Documentation of how to actually get the program running
- Future plans: 
  - Implement ColourSelectionEvent
  - Unit testing
  
**Courtney**
- Worked on CRC cards, Scenario Walkthrough, Drawing Manager, ProjectStateController, ToolStateController, Brush, Pen
- Worked on fxml file for Main Canvas View
- Future plans: 
  - UndoRedoManager
  - Finish implementation of Pen calculateEffectedPixels function so that the pen can be resized (Also change the shape from a square to a circle)
  - Assist with implementing color selection and color palettes
  
**Cynthia**
- Worked on CRC cards, Project Specification, Documentation, Progress Report
- Future plans: 
  - UndoRedoManager, and KeyBoardEventController
  
**Faizah**
- Worked on NewProjectController, ProjectManager, CanvasEvents, ProjectExplorerController, SceneSwitcher (New Project Flow)
- Created fxml UI for NewProject and ProjectExplorer
- Helped with OnionFileLoader
- Worked on CRC cards and Project Specification
- Future plans: 
  - Work on Most recent project and open file from file explorer flow
  - Unit testing for Project, ProjectManager classes
  - Clean Up UI
  - Help with shape tool implementation

**Finn**
- Added and implemented: Colour, OnionFileLoader, Pixel, Project
- Worked on CRC cards and Scenario walk through
- Future plans:
  - Continue work on .onion file processing
  - Help with implementing shape tool
  - Colour selection flow

**Runce**
- Worked on ProjectStateController and CanvasEvent
- Worked on CRC cards
- Documentation of skeleton program summary
- Future plans: 
  - ProjectStateController should also handle the tool selection and colour selection after toolbar and colour palette features are implemented
  - CanvasEvent will process other raw data like selecting tools
  - Work on other stuff if needed

**Tina**
- Worked on Project, ToolStateManager, Pen, Brush, Tool, 
- CRC cards, Project Specification
- Unit Testing
- Future plans: 
  - Work on other tools, such as Eraser tool and Shape Tool 
  - Finished the isSelected Method in ToolStatemanager once the toolbar selection is completed, and integrate other tools into updateCurrentToolState method
  - Help out UndoRedo Manager possibly since that’s a lot of work
  - More Unit Testings (Pen, etc.)
