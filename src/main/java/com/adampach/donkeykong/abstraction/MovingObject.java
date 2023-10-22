package com.adampach.donkeykong.abstraction;

public abstract class MovingObject extends TextureObject implements Simulable
{

    public MovingObject(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
    }

    public abstract void handleCollision(Collisionable collisionable);
}
