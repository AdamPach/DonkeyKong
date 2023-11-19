package com.adampach.donkeykong.objects.generators;

import com.adampach.donkeykong.abstraction.Provider;
import com.adampach.donkeykong.abstraction.game.Enemy;
import com.adampach.donkeykong.abstraction.game.EnemyGenerator;
import com.adampach.donkeykong.abstraction.Observer;
import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.objects.moving.Barrel;
import com.adampach.donkeykong.data.LevelSettings;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;

import java.util.LinkedList;

import static com.adampach.donkeykong.assets.ImageAssets.DONKEY_KONG;
public class BarrelGenerator extends EnemyGenerator
{
    private final LinkedList<Observer<Pair<Boolean, Enemy>>> observers;
    private final Provider<Boolean> generatorProvider;
    private final DirectionEnums.HorizontalDirection initBarrelDirection;
    private final LevelSettings levelSettings;

    public BarrelGenerator(
            int positionX,
            int positionY,
            int width,
            int height,
            DirectionEnums.HorizontalDirection initBarrelDirection,
            LevelSettings levelSettings
    )
    {
        super(positionX, positionY, width, height);
        this.levelSettings = levelSettings;
        this.observers = new LinkedList<>();
        generatorProvider = levelSettings.getBarrelGenerationProvider();
        this.initBarrelDirection = initBarrelDirection;
    }

    @Override
    public void draw(GraphicsContext gc) {
       gc.drawImage(DONKEY_KONG, getPositionX(), getPositionY(), getWidth(), getHeight());
    }

    @Override
    public void generate()
    {
        if(generatorProvider.provide())
            observers.forEach( e ->
                e.notifyObserver(new Pair<>(
                        true,
                        new Barrel(
                                (int)(getMaxPositionX() / 2 - levelSettings.getDefaultBarrelSize().width()),
                                (int)(getMaxPositionY() - levelSettings.getDefaultBarrelSize().height()),
                                initBarrelDirection,
                                levelSettings)))
            );
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
