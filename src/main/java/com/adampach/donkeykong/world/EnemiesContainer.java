package com.adampach.donkeykong.world;

import com.adampach.donkeykong.abstraction.game.Enemy;
import com.adampach.donkeykong.abstraction.Observer;
import javafx.util.Pair;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Holds all enemies and handle their correct deletion
 * Observer receive pair, key determines if the enemy is new and value is object which will be treated
 * @param <TList> Define underlying type of the list
 */
public class EnemiesContainer <TList extends List<Enemy>>
        implements Observer<Pair<Boolean, Enemy>>, Iterable<Enemy> {

    private final TList enemies;
    private final LinkedList<Enemy> toRemove;

    public EnemiesContainer(TList list) {
        this.enemies = list;
        toRemove = new LinkedList<>();
    }

    @Override
    public void notifyObserver(Pair<Boolean, Enemy> data) {
        Enemy enemy = data.getValue();
        if(data.getKey())
        {
            enemy.registerObserver(this);
            enemies.add(enemy);
        }
        else
        {
            toRemove.add(enemy);
        }
    }

    @Override
    public Iterator<Enemy> iterator()
    {
        return enemies.iterator();
    }

    public void clean()
    {
        System.out.println(enemies.size());
        if(!toRemove.isEmpty())
        {
            toRemove.forEach( e ->
            {
                e.unregisterObserver(this);
                enemies.remove(e);
            });
            toRemove.clear();
        }
    }

    public void clear()
    {
        enemies.clear();
    }
}
