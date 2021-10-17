# The Onionshop

This is a directory for the project. Setup instructions are below the links section.

## Links

- [Phase 0](/phase0)

## How do I get started?

1) Clone the repository.

2) Visit https://gluonhq.com/products/javafx/ to download the appropriate version of the JavaFX SDK for your computer.

3) Click include older versions.

![Image of Download Page](phase0/instruction_images/com.onion....png)

4) Download version 11.0.2.

5) Unzip the zipped file, and place the folder named `javafx-sdk-11.0.2` into your desired directory.

6) Open the project in IntelliJ.

7) Click the Run/Debug Configuration dropdown.

![Image of Run/debug config](phase0/instruction_images/Edit-1.png)

8) Click Edit Configurations.

![Edit-2](phase0/instruction_images/Edit-2.png)

9) Click Modify Options, and select Add VM options

![Modify-Options](phase0/instruction_images/modify.png)

![modify-2](phase0/instruction_images/edit%20vm.png)

10) In VM options, enter:


    `--module-path "<INSERT-JAVAFX-PATH-HERE>" --add-modules=javafx.controls,javafx.fxml`

Ensure you remove the `<` and `>`. Point the path into the lib folder inside `javafx-sdk-11.0.2`.

![module-path](phase0/instruction_images/module_path.png)

11) Ensure “com.onionshop.Onionshop” is entered in the “Main class” selector.

![module-path-2](phase0/instruction_images/module_path-2.png)

12) Leave program arguments blank.

13) Click apply, 

14) Navigate to Onionshop.java under “src\main\java\com\onionshop”.

15) Run “Onionshop.java”. 
