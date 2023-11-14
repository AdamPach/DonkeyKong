package com.adampach.donkeykong.abstraction;

import com.adampach.donkeykong.wrappers.KeyboardDataWrapper;

public interface KeyBoardSubject
{
    void registerObserver(Observer<KeyboardDataWrapper> o);
    void unregisterObserver(Observer<KeyboardDataWrapper> o);
}
