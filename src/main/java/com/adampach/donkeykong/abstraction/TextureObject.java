package com.adampach.donkeykong.abstraction;

public abstract class TextureObject extends GameObject implements Drawable
{
    public TextureObject(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
    }

}
