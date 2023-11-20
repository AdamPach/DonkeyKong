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

import java.time.Instant;
import java.util.LinkedList;
import java.util.Random;

import static com.adampach.donkeykong.assets.ImageAssets.DONKEY_KONG;
public class BarrelGenerator extends EnemyGenerator
{
    private final LinkedList<Observer<Pair<Boolean, Enemy>>> observers;
    private final Provider<Boolean> generatorProvider;
    private final DirectionEnums.HorizontalDirection initBarrelDirection;
    private final LevelSettings levelSettings;

    private final Random rnd;

    public BarrelGenerator(
            int positionX,
            int positionY,
            int width,
            int height,
            DirectionEnums.HorizontalDirection initBarrelDirection,
            Provider<Boolean> barrelGenerationIntervalProvider,
            LevelSettings levelSettings
    )
    {
        super(positionX, positionY, width, height);
        this.levelSettings = levelSettings;
        this.observers = new LinkedList<>();
        generatorProvider = barrelGenerationIntervalProvider;
        this.initBarrelDirection = initBarrelDirection;
        rnd = new Random(Instant.now().toEpochMilli());
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
                                (getPositionX() + (getMaxPositionX() - getPositionX()) / 2),
                                (int)(getMaxPositionY() - levelSettings.getDefaultBarrelSize().height()),
                                initBarrelDirection == DirectionEnums.HorizontalDirection.Both ?
                                    rnd.nextBoolean()
                                            ? DirectionEnums.HorizontalDirection.Right
                                            : DirectionEnums.HorizontalDirection.Left
                                    : initBarrelDirection,
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
