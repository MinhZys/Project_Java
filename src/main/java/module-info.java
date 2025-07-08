module com.example.projectjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;

    opens com.example.projectjava to javafx.fxml;
    exports com.example.projectjava;
    exports com.example.projectjava.controller;
    opens com.example.projectjava.controller to javafx.fxml;
}