package com.adampach.donkeykong.handlers;

import com.adampach.donkeykong.abstraction.Observer;
import com.adampach.donkeykong.abstraction.Subject;
import com.adampach.donkeykong.enums.GameEventEnums;

import java.util.LinkedList;

public class LevelEventsHandler implements Subject<Observer<GameEventEnums.LevelEvents>> {
    private final LinkedList<Observer<GameEventEnums.LevelEvents>> observers = new LinkedList<>();

    @Override
    public void registerObserver(Observer<GameEventEnums.LevelEvents> o)
    {
        observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer<GameEventEnums.LevelEvents> o)
    {
        observers.remove(o);
    }

    public void notifyWithLevelEvent(GameEventEnums.LevelEvents event)
    {
        observers.forEach( e -> e.notifyObserver(event));
    }
}
