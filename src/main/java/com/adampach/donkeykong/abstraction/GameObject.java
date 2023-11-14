package com.adampach.donkeykong.abstraction;


import javafx.geometry.Rectangle2D;

public abstract class GameObject implements Collisionable {

    private int positionX;
    private int positionY;
    private int width;
    private int height;

    public GameObject(int positionX, int positionY, int width, int height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxPositionY() { return getPositionY() + getHeight();}

    public int getMaxPositionX() { return getPositionX() + getWidth();}

    @Override
    public Rectangle2D getRectangle() { return new Rectangle2D(positionX, positionY, width, height); }

    @Override
    public boolean intersect(Rectangle2D rectangle) {
        return getRectangle().intersects(rectangle);
    }

}
