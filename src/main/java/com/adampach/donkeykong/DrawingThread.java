package com.adampach.donkeykong;

import com.adampach.donkeykong.world.Level;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawingThread extends AnimationTimer {
    private final Level level;
    private final Canvas canvas;
    private final GraphicsContext graphicsContext;
    private long lastTime;

    public DrawingThread(Canvas canvas, Level level) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.level = level;
        lastTime = -1;
    }


    @Override
    public void handle(long now) {
        if (now - lastTime > 15_000_000) {
            level.simulate();
            level.resetSimulationCycle();
            lastTime = now;
        }
        level.draw(graphicsContext);
    }
}
