package com.adampach.donkeykong;

import com.adampach.donkeykong.abstraction.KeyboardObserver;
import com.adampach.donkeykong.handlers.KeyboardHandler;
import com.adampach.donkeykong.world.Level;
import com.adampach.donkeykong.world.LevelSettings;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

public class GameController
{
    private Level level;

    @FXML
    private Canvas canvas;
    private Scene scene;
    private final KeyboardHandler keyboardHandler;
    private AnimationTimer animationTimer;

    public GameController()
    {
        keyboardHandler = new KeyboardHandler();
    }

    public void startGame()
    {
        scene.setOnKeyPressed(keyboardHandler);
        scene.setOnKeyReleased(keyboardHandler);
        level = new Level(new LevelSettings(5, 3), this::registerObserver);
        animationTimer = new DrawingThread(canvas, level);
        animationTimer.start();
    }

    public void setScene(Scene scene)
    {
        this.scene = scene;
    }

    private void registerObserver(KeyboardObserver observer)
    {
        keyboardHandler.registerObserver(observer);
    }
}
