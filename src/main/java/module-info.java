module com.onionshop {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.onionshop.controllers to javafx.fxml;
    exports com.onionshop;
}