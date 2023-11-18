package com.adampach.donkeykong.abstraction.game;

import com.adampach.donkeykong.abstraction.Observer;
import com.adampach.donkeykong.abstraction.Subject;
import javafx.util.Pair;

import java.util.LinkedList;

public abstract class Enemy extends MovingObject implements Subject<Observer<Pair<Boolean, Enemy>>> {

    private LinkedList<Observer<Pair<Boolean, Enemy>>> observers;

    public Enemy(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
        observers = new LinkedList<>();
    }

    @Override
    public void registerObserver(Observer<Pair<Boolean, Enemy>> o) {
        observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer<Pair<Boolean, Enemy>> o) {
        observers.add(o);
    }

    public void notifyDestroy()
    {
        observers.forEach( o -> o.notifyObserver(new Pair<>(false, this)));
    }
}
