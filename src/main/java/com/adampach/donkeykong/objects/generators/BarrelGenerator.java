package com.adampach.donkeykong.objects.generators;

import com.adampach.donkeykong.abstraction.game.Enemy;
import com.adampach.donkeykong.abstraction.game.EnemyGenerator;
import com.adampach.donkeykong.abstraction.Observer;
import com.adampach.donkeykong.objects.moving.Barrel;
import com.adampach.donkeykong.providers.RandomIntervalGeneratorProvider;
import com.adampach.donkeykong.data.LevelSettings;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Pair;

import java.util.LinkedList;

public class BarrelGenerator extends EnemyGenerator
{
    private final LinkedList<Observer<Pair<Boolean, Enemy>>> observers;
    private final RandomIntervalGeneratorProvider generatorProvider;
    private final LevelSettings levelSettings;

    public BarrelGenerator(
            int positionX,
            int positionY,
            int width,
            int height,
            LevelSettings levelSettings)
    {
        super(positionX, positionY, width, height);
        this.levelSettings = levelSettings;
        this.observers = new LinkedList<>();
        generatorProvider = new RandomIntervalGeneratorProvider(1, 20000);
    }

    @Override
    public void draw(GraphicsContext gc) {
        Paint paint = gc.getFill();
        gc.setFill(Color.BLUEVIOLET);
        gc.fillRect(getPositionX(), getPositionY(), getWidth(), getHeight());
        gc.setFill(paint);
    }

    @Override
    public void generate()
    {
        if(generatorProvider.provide())
            observers.forEach( e ->
            {
                e.notifyObserver(new Pair<>(
                        true,
                        new Barrel(
                                getPositionX(),
                                getPositionY(),
                                getWidth(),
                                getHeight(),
                                levelSettings)));
            });
    }

    @Override
    public void registerObserver(Observer<Pair<Boolean, Enemy>> o)
    {
        observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer<Pair<Boolean, Enemy>> o)
    {
        observers.remove(o);
    }
}
