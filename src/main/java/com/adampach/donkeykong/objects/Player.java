package com.adampach.donkeykong.objects;

import com.adampach.donkeykong.abstraction.Drawable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Player implements Drawable {
    //Position
    private Point2D position;
    private int width;
    private int height;

    public Player(Point2D position, int width, int height)
    {
        this.position = position;
        this.width = width;
        this.height = height;
    }
    @Override
    public void draw(GraphicsContext gc)
    {
        Paint paint = gc.getFill();
        gc.setFill(Color.WHITE);
        gc.fillRect(position.getX(), position.getY(), width, height);
        gc.setFill(paint);
    }
}
