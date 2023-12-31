package com.adampach.donkeykong.objects.textures;

import com.adampach.donkeykong.abstraction.game.TextureObject;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static com.adampach.donkeykong.assets.ImageAssets.PLATFORM;

public class Construction extends TextureObject {
    public Construction(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
    }

    @Override
    public void draw(GraphicsContext gc)
    {
//        float width = getHeight() * 2;
//        for(int i = 0; i < Math.ceil(getWidth() / width); i++)
//        {
//            gc.drawImage(PLATFORM, getPositionX() + width * i, getPositionY(), width, getHeight());
//        }
        gc.drawImage(PLATFORM, getPositionX(), getPositionY(), getWidth(), getHeight());
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
