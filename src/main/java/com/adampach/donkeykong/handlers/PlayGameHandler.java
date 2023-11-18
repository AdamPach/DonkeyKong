package com.adampach.donkeykong.handlers;

import com.adampach.donkeykong.abstraction.Observer;
import com.adampach.donkeykong.abstraction.Subject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.LinkedList;

public class PlayGameHandler implements EventHandler<ActionEvent>, Subject<Observer<Boolean>> {
    private final LinkedList<Observer<Boolean>> observers = new LinkedList<>();

    @Override
    public void registerObserver(Observer<Boolean> o)
    {
        observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer<Boolean> o)
    {
        observers.remove(o);
    }

    @Override
    public void handle(ActionEvent actionEvent)
    {
        observers.forEach( o -> o.notifyObserver(true));
    }
}
