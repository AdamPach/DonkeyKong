package com.example.donkeykong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class DonkeyKongApplication extends Application {
    @Override
    public void start(Stage stage) {

        try
        {
            Group root = new Group();
            Canvas canvas = new Canvas(800, 600);
            root.getChildren().add(canvas);
            Scene scene = new Scene(root);

            stage.setTitle("DonkeyKong🙈");
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(this::OnCloseRequest);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    private void OnCloseRequest(WindowEvent event)
    {
        System.exit(0);
    }
}