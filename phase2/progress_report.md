# Progress Report

## What has worked well so far with our design:
- Memento pattern worked well in the undo-redo implementation as it made possible the copying and storing of the current canvas plus the roll back of various states, 
- Flexibility of Tool structure allows for multiple branchings of tools such as the brushes and the shapes, or other possible tools in the future. 
- The template method has worked incredibly well with the brush and shape tools as they both involve a certain amount of standardization with the possibility of having multiple child classes. 
- The structure using the drawStage makes it simple to execute the shape drawing and possible future modifications. 
- The new drag feature implemented for shape tool instead of the point-click feature adhered better to program accessibility as it is significantly less confusing to draw a shape now

A summary of what each group member has been working on: 

_Bhavjot_
- Phase 2:
  - Layers backend 
  - Layer entity, LayerManager, refactoring backend to implement layers 
  - Bug fixes, specifically regarding MostRecentProjectManager and errors while refactoring
- In the past:
  - Backend for recent projects (MostRecentProjectManager)
  - Eraser tool backend, refactoring related to Brush 
  - Helped with NewProjectController and ProjectExplorerController 
  - Documentation on various classes 
- Significant pull requests:
  - https://github.com/CSC207-UofT/course-project-onion/pull/69/
    - Implemented the backend for MostRecentProjectManager. Note fixes and revamps were also done at later points in other pull requests
  - https://github.com/CSC207-UofT/course-project-onion/pull/118
    - Refactoring for layers, bug fixes, clean architecture
          
_Faizah_
- Phase 2:
  - Frontend logic for create new layer, add layer, layer UI control, select layers 
  - Cleaned up most recent projects to adhere to OCP 
  - Cleaned up and refactored testing for changes related to layers
- In the past:
  - Connected Save project to the keyboard events controller 
  - Connected Most Recent Projects to the frontend as well as the open project flow 
  - Cleaned up the UI 
  - Helped with the logic of connecting our undo redo system to the frontend. 
  - Unit testing
- Significant pull requests:
  - https://github.com/CSC207-UofT/course-project-onion/pull/54
    - Implemented save project flow on the frontend
  - https://github.com/CSC207-UofT/course-project-onion/pull/98
    - Added create new layer, add layer, layer UI control, select layers features on the frontend

_Finn_
- Phase 2:
  - Alfa value backend (Pixel/Colour update, saving and loading)
  - Layers backend logic (not pulled yet)
  - Layers format saving and loading (not pulled yet - refactoring)
  - Helped Cynthia fix UndoRedoManager 
  - Helped refactor
- In the past:
  - Added Entity classes: Pixel, Colour, and Project 
  - Implemented project serialization + testing 
  - Implemented saving (custom format) + testing 
  - Implemented loading (custom format) + testing 
  - Added missing backend logic 
  - Implemented ColourPallete methods 
- Significant pull requests:
  - https://github.com/CSC207-UofT/course-project-onion/pull/9/files
    - Implemented serialization, loading, and saving
  - https://github.com/CSC207-UofT/course-project-onion/pull/118 (part of major pull)
    - Implemented Layer class, LayerManager methods, and updated serialization, saving, and loading to accommodate for layers
          
_Courtney_
- Phase 2:
  - Refactored Shape tool to use click and drag instead of 2 clicks 
  - Added remove layers feature to Front end 
  - Fixed serialization problems with layers that prevented saving and loading 
  - Connect front end layers to backend layers
- In the past:
  - Implemented front end for Colour Palette 
  - Implement Drawing Manager and Project State Controller + tests 
  - Implemented Rectangle Tool for Shape Tool 
  - Implemented Front end for Eraser tool 
- Significant pull requests:
  - https://github.com/CSC207-UofT/course-project-onion/pull/118
    - Pull request involves other’s work on the same branch. This request connected the frontend layers to the backend and fixed project saving and loading with layers.
  - https://github.com/CSC207-UofT/course-project-onion/pull/61
    - Implemented Colour palette frontend and connected to backend.

_Tina_
- Phase 2:
  - Finished Circle tool implementation and all shape, brush related test fixing and clean up 
  - Did Unit test for layer and layerManager 
  - Contributed to making Eraser transparent
- In the past:
  - ToolStateManager, Brush abstract class and Tool interface, backend of pen and eraser 
  - Connected pen, eraser, line, rectangle and circle tool to the frontend toolbar 
  - Frontend tool transitions 
  - Designed and Implemented the Shape abstract class and line 
  - Finished Unit test for Brush, Pen, Eraser, Shape, Line, Rectangle tool
    Various error fixing and debugging
- Significant pull requests:
  - https://github.com/CSC207-UofT/course-project-onion/pull/62
    - This is the pull request where I connected and implemented front end tool transitions, worked out and coded the Shape abstract class and line class, and fixed the toolStateManger.
        
_Runce_
- Phase 2:
  - Major refactoring in ProjectManager, Project, Shape and LayerManager
  - Accessibility Report 
- In the past:
    - Implemented the frontend for toolbar
    - Implemented frontend for pen, eraser and shapes tools.
- Significant pull requests:
  - https://github.com/CSC207-UofT/course-project-onion/pull/105
    - Refactoring the project to get it running with the layers
  - https://github.com/CSC207-UofT/course-project-onion/pull/58
    - Implemented the toolbar frontend with images
    

_Cynthia_
- Phase 2:
  - Fix the error of UndoRedoManager 
  - Finish the unit tests for UndoRedoManager
- In the past 
  - Implemented KeyboardEventController, process keyboard input 
  - Implemented UndoRedoManager (with some errors)
  - Using memento design pattern on undo redo function, create the DrawingState class 
  - Connect the undo redo backend to frontend
- Significant pull requests:
  - https://github.com/CSC207-UofT/course-project-onion/pull/67
    - Implement the memento design pattern for undo-redo function
  - https://github.com/CSC207-UofT/course-project-onion/pull/78
    - Fix the undo-redo function to make it basically work, also connect it to the frontend.

