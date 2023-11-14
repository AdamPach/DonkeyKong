package com.adampach.donkeykong.handlers;

import com.adampach.donkeykong.abstraction.KeyBoardSubject;
import com.adampach.donkeykong.abstraction.Observer;
import com.adampach.donkeykong.wrappers.KeyboardDataWrapper;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class KeyboardHandler implements EventHandler<KeyEvent>, KeyBoardSubject {
    private ArrayList<Observer<KeyboardDataWrapper>> Observers;
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
    public void registerObserver(Observer<KeyboardDataWrapper> o)
    {
        Observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer<KeyboardDataWrapper> o)
    {
        Observers.remove(o);
    }

    public void notifyObservers(boolean pressed, KeyCode keyCode)
    {
        for(Observer<KeyboardDataWrapper> observer: Observers)
        {
            observer.notifyObserver(new KeyboardDataWrapper(pressed, keyCode));
        }
    }
}
