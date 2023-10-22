package com.adampach.donkeykong.abstraction;

import javafx.geometry.Rectangle2D;

public abstract class TextureObject extends GameObject implements Drawable, Collisionable
{
    public TextureObject(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
    }

    @Override
    public Rectangle2D getRectangle() { return new Rectangle2D(positionX, positionY, width, height); }

    @Override
    public boolean intersect(Rectangle2D rectangle) {
        return getRectangle().intersects(rectangle);
    }
}
