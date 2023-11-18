package com.adampach.donkeykong.abstraction.game;

import com.adampach.donkeykong.abstraction.Observer;
import com.adampach.donkeykong.abstraction.Subject;
import javafx.util.Pair;

public abstract class EnemyGenerator extends TextureObject
        implements Subject<Observer<Pair<Boolean, Enemy>>> {
    public EnemyGenerator(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
    }

    public abstract void generate();
}
