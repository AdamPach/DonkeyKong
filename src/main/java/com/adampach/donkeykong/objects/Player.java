package com.adampach.donkeykong.objects;

import com.adampach.donkeykong.abstraction.DrawableSimulable;
import com.adampach.donkeykong.abstraction.KeyboardObserver;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Player implements DrawableSimulable, KeyboardObserver {
    //Position
    private int positionX;
    private int positionY;
    private int width;
    private int height;

    public Player(Point2D position, int width, int height)
    {
        this.positionX = (int)position.getX();
        this.positionY = (int)position.getY();
        this.width = width;
        this.height = height;
    }
    @Override
    public void draw(GraphicsContext gc)
    {
        Paint paint = gc.getFill();
        gc.setFill(Color.WHITE);
        gc.fillRect(positionX, positionY, width, height);
        gc.setFill(paint);
    }

    @Override
    public void simulate()
    {

    }

    @Override
    public void notified(KeyCode keyCode)
    {
        switch (keyCode)
        {
            case W -> {
                positionY--;
                break;
            }
        }
    }
}
