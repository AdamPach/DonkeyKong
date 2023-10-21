package com.adampach.donkeykong.objects;

import com.adampach.donkeykong.abstraction.Collisionable;
import com.adampach.donkeykong.abstraction.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Construction extends GameObject {
    public Construction(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
    }

    @Override
    public void draw(GraphicsContext gc)
    {
        Paint paint = gc.getFill();
        gc.setFill(Color.RED);
        gc.fillRect(positionX, positionY, width, height);
        gc.setFill(paint);
    }

    @Override
    public void simulate()
    {

    }

    @Override
    public void handleCollision(Collisionable collisionable) {

    }
}
