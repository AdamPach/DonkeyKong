package com.adampach.donkeykong.objects;

import com.adampach.donkeykong.abstraction.TextureObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Ladder extends TextureObject
{
    public Ladder(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
    }

    @Override
    public void draw(GraphicsContext gc) {
        Paint paint = gc.getFill();
        gc.setFill(Color.BLUE);
        gc.fillRect(positionX, positionY, width, height);
        gc.setFill(paint);
    }
}
