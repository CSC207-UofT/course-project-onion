#Progress Report

##Open questions

1) How should we deal with errors and exceptions? We are currently just outputting the errors to the console. 
2) Any concrete advice to implement Layers? What would be a decent amount of workload for Phase 2? 
3) Although we are not directly using entities in the controllers, I feel like with some of the chain of methods we are calling, we might as well be using entities in our controllers. Is this an issue? 
4) How should we address the SRP issue in CanvasEvents and DrawingManager addressed above? 
5) The undo & redo feature seems to work well in the backend, but works in the front-end only some of the time. Why is it being finicky and how can we make it work consistently?

##What has worked well so far

- Memento pattern worked well in the undo-redo implementation as it made possible the copying and storing of the current canvas plus the roll back of various states, 
- Flexibility of Tool structure allows for multiple branchings of tools such as the brushes and the shapes, or other possible tools in the future. 
- The template method has worked incredibly well with the brush and shape tools as they both involve a certain amount of standardization with the possibility of having multiple child classes. 
- The structure using the drawStage makes it simple to execute the shape drawing and possible future modifications. 

##What each member has worked on

_Bhavjot_
- Worked on:
  - Eraser tool backend 
  - MostRecentProjectManager backend 
  - Unit testing for MostRecentProjectManager
- Planning to work on:
  - Finish MostRecentProjectManager unit testing 
  - Unit testing for Eraser tool 
  - Fix any remaining violations of clean architecture
  
_Faizah_
- Worked on:
  - Connected Save project to the keyboard events controller 
  - Connected Most Recent Projects to the frontend as well as the open project flow 
  - Cleaned up the UI 
  - Helped with the logic of connecting our undo redo system to the frontend. 
  - Unit testing
- Planning to work on:
  - Unit testing for ProjectManager 
  - Changing the .onion file structure for the addition of a layers feature 
  - Refactoring code to adhere more to SOLID principles and Clean architecture
        
_Finn_
- Worked on:
  - Implemented UndoRedoManager 
  - Wrote methods for ColourPalette 
  - Helped connect UndoRedoManager to front end 
  - Bug testing 
  - Broke up OnionFileLoader loader method into components for better testing and maintenance 
  - Added unit tests where they were missing
- Planning to work on:
  - Efficiency changes 
  - Using parallel programming for specific elements 
  - Bug fixes 
  - Assisting in the backend of any new features

_Courtney_
- Worked on:
  - Implemented front end for Colour Palette 
  - Implemented Rectangle Tool for Shape Tool 
  - Implemented Front end for Eraser tool
- Planning to work On:
  - Assisting with frontend implementation and UI of layer feature 
  - Possibly adding a round brush/pen 
  - Assisting with other frontend work as needed.
  
_Tina_
- Worked on:
  - Connected pen, eraser, line, rectangle and circle (to be implemented phase 2) tool to the frontend toolbar 
  - Designed and Implemented the Shape Tool and the Line tool
  - Finished Unit test for Shape, Line, and Rectangle tool 
- Planning to work On:
  - Circle Shape implementation in Shape tool 
  - Possibility of introducing Layers 
  - Shape tools clean up, making the current system and ugly code better. 
  - Any Unit Test possible.
                  
_Runce_
- Worked on:
  - Implemented the frontend for toolbar 
  - Implemented frontend for pen, eraser and shapes tools.
- Planning to work On:
  - Work on anything else in phase 2 if needed

_Cynthia_
- Worked on:
  - Implemented KeyboardController
  - Implemented UndoRedoManager 
  - Connect the UndoRedo function to the ProjectManager and the frontend 
  - Basic idea of UndoRedoManager unit test
- Planning to work On:
  - Fix the error of UndoRedoManager 
  - Revised and finish the UndoRedoManager unit test
