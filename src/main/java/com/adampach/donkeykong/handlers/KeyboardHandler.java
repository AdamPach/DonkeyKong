package com.adampach.donkeykong.handlers;

import com.adampach.donkeykong.abstraction.KeyBoardSubject;
import com.adampach.donkeykong.abstraction.KeyboardObserver;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class KeyboardHandler implements EventHandler<KeyEvent>, KeyBoardSubject {
    private ArrayList<KeyboardObserver> Observers;
    private KeyCode lastCode;

    public KeyboardHandler()
    {
        Observers = new ArrayList<>();
    }

    @Override
    public void handle(KeyEvent event)
    {
        lastCode = event.getCode();
        notifyObservers();
    }

    @Override
    public void registerObserver(KeyboardObserver o)
    {
        Observers.add(o);
    }

    @Override
    public void unregisterObserver(KeyboardObserver o)
    {
        Observers.remove(o);
    }

    @Override
    public void notifyObservers()
    {
        for(KeyboardObserver observer: Observers)
        {
            observer.notified(lastCode);
        }
    }
}
