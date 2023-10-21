package com.example.donkeykong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class DonkeyKongApplication extends Application {
    private GameController controller;

    @Override
    public void start(Stage stage) {

        try
        {
            //Load a default scene
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("GameView.fxml"));
            Scene scene = new Scene(loader.load());

            //Show a default scene and setting up the stage
            stage.setTitle("DonkeyKong🙈");
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(this::OnCloseRequest);

            //Starting a game with controller
            controller = loader.getController();
            controller.startGame();
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