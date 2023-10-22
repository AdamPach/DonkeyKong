package com.adampach.donkeykong.abstraction;

import javafx.geometry.Rectangle2D;

public interface Collisionable
{
    Rectangle2D getRectangle();
    boolean intersect(Rectangle2D rectangle);
}
