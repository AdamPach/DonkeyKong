package com.example.donkeykong;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class GameController
{
    @FXML
    private Canvas canvas;

    private AnimationTimer animationTimer;

    public GameController(){}

    public void startGame()
    {
        animationTimer = new DrawingThread(canvas);
        animationTimer.start();
    }
}
