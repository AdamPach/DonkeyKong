package com.adampach.donkeykong.world;

import com.adampach.donkeykong.abstraction.MovingObject;
import com.adampach.donkeykong.abstraction.Observer;
import javafx.util.Pair;

import java.util.Iterator;
import java.util.List;

/**
 * Holds all enemies and handle their correct deletion
 * Observer receive pair, key determines if the enemy is new and value is object which will be treated
 * @param <TList> Define underlying type of the list
 */
public class EnemiesContainer <TList extends List<MovingObject>>
        implements Observer<Pair<Boolean, MovingObject>>, Iterable<MovingObject> {

    private final TList barrels;

    public EnemiesContainer(TList list) {
        this.barrels = list;
    }

    @Override
    public void notifyObserver(Pair<Boolean, MovingObject> data) {
        if(data.getKey())
            barrels.add(data.getValue());
        else
            barrels.remove(data.getValue());
    }

    @Override
    public Iterator<MovingObject> iterator()
    {
        return barrels.iterator();
    }

}
