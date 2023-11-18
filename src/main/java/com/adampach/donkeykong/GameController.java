package com.adampach.donkeykong;

import com.adampach.donkeykong.gui.Game;
import com.adampach.donkeykong.handlers.KeyboardHandler;
import com.adampach.donkeykong.providers.HorizontalDirectionProvider;
import com.adampach.donkeykong.providers.JumpProvider;
import com.adampach.donkeykong.wrappers.MovementProviderWrapper;
import com.adampach.donkeykong.providers.VerticalDirectionProvider;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

public class GameController
{
    @FXML
    private Canvas canvas;

    @FXML
    private AnchorPane anchorPane;

    private Scene scene;

    private final KeyboardHandler keyboardHandler;

    private AnimationTimer animationTimer;

    private Game game;

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

        game = new Game(canvas, this.movementProviderWrapper, anchorPane);

        animationTimer = new DrawingThread(game);
        animationTimer.start();
    }

    public void setScene(Scene scene)
    {
        this.scene = scene;
    }
}
