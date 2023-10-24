package com.adampach.donkeykong.abstraction;

public interface KeyBoardSubject
{
    void registerObserver(KeyboardObserver o);
    void unregisterObserver(KeyboardObserver o);
}
