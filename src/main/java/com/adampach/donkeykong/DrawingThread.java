package com.adampach.donkeykong;

import com.adampach.donkeykong.world.Level;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
        if (lastTime > 0) {
            level.simulate();
        }
        lastTime = now;
        level.draw(graphicsContext);

    }
}
