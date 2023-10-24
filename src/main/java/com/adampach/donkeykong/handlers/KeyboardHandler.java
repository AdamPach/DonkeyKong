package com.adampach.donkeykong.handlers;

import com.adampach.donkeykong.abstraction.KeyBoardSubject;
import com.adampach.donkeykong.abstraction.KeyboardObserver;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class KeyboardHandler implements EventHandler<KeyEvent>, KeyBoardSubject {
    private ArrayList<KeyboardObserver> Observers;
    public KeyboardHandler()
    {
        Observers = new ArrayList<>();
    }

    @Override
    public void handle(KeyEvent event)
    {
        notifyObservers(event.getEventType().equals(KeyEvent.KEY_PRESSED) , event.getCode());
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

    public void notifyObservers(boolean pressed, KeyCode keyCode)
    {
        for(KeyboardObserver observer: Observers)
        {
            observer.notifyObserver(pressed, keyCode);
        }
    }
}
