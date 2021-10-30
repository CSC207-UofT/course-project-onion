module com.onionshop {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens com.onionshop to javafx.fxml;
    opens com.onionshop.controllers to javafx.fxml;

    exports com.onionshop;
    exports com.onionshop.managers;
    opens com.onionshop.managers to javafx.fxml;
    exports com.onionshop.entities;
    opens com.onionshop.entities to javafx.fxml;
}