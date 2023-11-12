package com.adampach.donkeykong.abstraction;

public abstract class Zone<T> extends GameObject implements Provider<T>{
    public Zone(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
    }
}
