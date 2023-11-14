package com.adampach.donkeykong.abstraction;

public interface Subject <T extends Observer> {
    void registerObserver(T o);
    void unregisterObserver(T o);
}
