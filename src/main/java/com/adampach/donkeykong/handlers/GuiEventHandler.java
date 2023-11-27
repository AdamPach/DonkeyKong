package com.adampach.donkeykong.handlers;

import com.adampach.donkeykong.abstraction.Observer;
import com.adampach.donkeykong.abstraction.Subject;
import com.adampach.donkeykong.enums.GameEventEnums;

import java.util.LinkedList;

public class GuiEventHandler implements Subject<Observer<GameEventEnums.GameEvents>> {
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

    public void handle(GameEventEnums.GameEvents event)
    {
        observers.forEach( o -> o.notifyObserver(event));
    }
}