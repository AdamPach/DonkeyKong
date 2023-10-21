package com.adampach.donkeykong.abstraction;

import javafx.scene.input.KeyCode;

public interface KeyboardObserver
{
    void notified(KeyCode keyCode);
}
