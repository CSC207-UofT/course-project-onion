package com.onionshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/* ++++++++++ O N I O N S H O P ++++++++++
 *               |///  \\\
 *             + ++++++++ +
 *           +++/  /  | \  \ +
 *         |+++/  /   |  \  \ \
 *         |+++|  |   |   |  ||
 *          |++++\ \  |  /   /+
 *            ++++++  | / /+
 *               ````````
 */
public class Onionshop extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProjectExplorer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Onionshop");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}