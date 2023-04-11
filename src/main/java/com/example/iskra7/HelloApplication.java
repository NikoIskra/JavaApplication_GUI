package com.example.iskra7;

import hr.java.production.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HelloApplication extends Application {

    private static Stage Stage;

    @Override
    public void start(Stage stage) throws IOException {
        Stage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Production application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }

    public static javafx.stage.Stage getStage() {
        return Stage;
    }

    public static void setStage(javafx.stage.Stage stage) {
        Stage = stage;
    }
}