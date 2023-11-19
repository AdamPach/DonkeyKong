package com.adampach.donkeykong.handlers;

import com.adampach.donkeykong.abstraction.Observer;
import com.adampach.donkeykong.abstraction.Subject;
import com.adampach.donkeykong.enums.GameEventEnums;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.LinkedList;

public class StartLevelHandler implements EventHandler<ActionEvent>, Subject<Observer<GameEventEnums.GameEvents>> {
    private final LinkedList<Observer<GameEventEnums.GameEvents>> observers = new LinkedList<>();

    @Override
    public void registerObserver(Observer<GameEventEnums.GameEvents> o)
    {
        observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer<GameEventEnums.GameEvents> o)
    {
        observers.remove(o);
    }

    @Override
    public void handle(ActionEvent actionEvent)
    {
        observers.forEach( o -> o.notifyObserver(GameEventEnums.GameEvents.StartLevel));
    }
}