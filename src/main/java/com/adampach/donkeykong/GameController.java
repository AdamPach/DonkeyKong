package com.adampach.donkeykong;

import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.handlers.KeyboardHandler;
import com.adampach.donkeykong.providers.HorizontalDirectionProvider;
import com.adampach.donkeykong.providers.JumpProvider;
import com.adampach.donkeykong.wrappers.MovementProviderWrapper;
import com.adampach.donkeykong.providers.VerticalDirectionProvider;
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

    //Observers and providers
    private final HorizontalDirectionProvider horizontalDirectionProvider;
    private final VerticalDirectionProvider verticalDirectionProvider;
    private final JumpProvider jumpProvider;
    private final MovementProviderWrapper movementProviderWrapper;

    public GameController()
    {
        keyboardHandler = new KeyboardHandler();

        this.horizontalDirectionProvider = new HorizontalDirectionProvider();
        keyboardHandler.registerObserver(this.horizontalDirectionProvider);

        this.verticalDirectionProvider = new VerticalDirectionProvider();
        keyboardHandler.registerObserver(this.verticalDirectionProvider);

        this.jumpProvider = new JumpProvider();
        keyboardHandler.registerObserver(this.jumpProvider);

        this.movementProviderWrapper = new MovementProviderWrapper(
                horizontalDirectionProvider,
                verticalDirectionProvider,
                jumpProvider
        );
    }

    public void startGame()
    {
        scene.setOnKeyPressed(keyboardHandler);
        scene.setOnKeyReleased(keyboardHandler);
        LevelSettings settings = new LevelSettings(
                -12,
                4,
                3,
                2,
                (int)canvas.getWidth(),
                (int)canvas.getHeight(),
                DirectionEnums.HorizontalDirection.Right);

        level = new Level(settings, movementProviderWrapper);
        animationTimer = new DrawingThread(canvas, level);
        animationTimer.start();
    }

    public void setScene(Scene scene)
    {
        this.scene = scene;
    }


}
