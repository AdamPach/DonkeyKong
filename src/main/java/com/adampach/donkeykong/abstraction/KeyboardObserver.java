package com.adampach.donkeykong.abstraction;


import javafx.scene.input.KeyCode;

public interface KeyboardObserver
{
    void notifyObserver(boolean keyPressed, KeyCode keyCode);
}
