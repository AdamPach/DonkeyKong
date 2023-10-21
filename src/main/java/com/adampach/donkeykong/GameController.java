package com.adampach.donkeykong;

import com.adampach.donkeykong.world.Level;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class GameController
{
    private Level level;

    @FXML
    private Canvas canvas;

    private AnimationTimer animationTimer;

    public GameController(){}

    public void startGame()
    {
        level = new Level();
        animationTimer = new DrawingThread(canvas, level);
        animationTimer.start();
    }
}
