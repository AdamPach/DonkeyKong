package com.adampach.donkeykong.objects.textures;

import com.adampach.donkeykong.abstraction.game.TextureObject;
import javafx.geometry.Rectangle2D;
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
        gc.fillRect(getPositionX(), getPositionY(), getWidth(), getHeight());
        gc.setFill(paint);
    }

    @Override
    public boolean intersect(Rectangle2D rectangle) {
        Rectangle2D thisRectangle = this.getRectangle();
        return rectangle.getMaxX() > thisRectangle.getMinX()
                && rectangle.getMinX() < thisRectangle.getMaxX()
                && rectangle.getMaxY() >= thisRectangle.getMinY()
                && rectangle.getMinY() < thisRectangle.getMaxY();
    }
}
