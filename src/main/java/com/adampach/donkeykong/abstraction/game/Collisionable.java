package com.adampach.donkeykong.abstraction.game;

import javafx.geometry.Rectangle2D;

public interface Collisionable
{
    Rectangle2D getRectangle();
    boolean intersect(Rectangle2D rectangle);
}