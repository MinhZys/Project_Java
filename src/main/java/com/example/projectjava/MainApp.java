package com.example.projectjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/projectjava/Login-view.fxml"));
        StackPane root = new StackPane();
        fxmlLoader.setRoot(root);
        fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("Fast Food Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}