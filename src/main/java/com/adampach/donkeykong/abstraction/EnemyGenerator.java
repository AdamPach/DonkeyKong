package com.adampach.donkeykong.abstraction;

import javafx.util.Pair;

public abstract class EnemyGenerator extends TextureObject
        implements Subject<Observer<Pair<Boolean, Enemy>>>{
    public EnemyGenerator(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
    }

    public abstract void generate();
}
