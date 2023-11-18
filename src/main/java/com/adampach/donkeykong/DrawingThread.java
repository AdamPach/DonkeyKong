package com.adampach.donkeykong;

import com.adampach.donkeykong.gui.Game;
import com.adampach.donkeykong.world.Level;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawingThread extends AnimationTimer {
    private final Game game;

    public DrawingThread(Game game) {
        this.game = game;
    }

    @Override
    public void handle(long now) {
        game.drawGame(now);
    }
}
