package com.adampach.donkeykong.world;

import com.adampach.donkeykong.abstraction.*;
import com.adampach.donkeykong.objects.*;

import com.adampach.donkeykong.providers.MovementProviderWrapper;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Level implements Drawable, Simulable{
    private final Player player;
    private final ArrayList<TextureObject> textures;
    private final ArrayList<MovingObject> enemies;
    private final LevelSettings levelSettings;

    public Level(LevelSettings levelSettings, MovementProviderWrapper movementProviderWrapper)
    {
        this.levelSettings = levelSettings;
        player = new Player(25, 400, 25, 50, levelSettings, movementProviderWrapper);
        textures = new ArrayList<>();
        enemies = new ArrayList<>();
        textures.add(new LevelBorders(levelSettings));
        textures.add(new Ladder(550, 425, 25, 100));
        textures.add(new Ladder(25, 265, 25, 100));
        textures.add(new Ladder(550, 115, 25, 100));
        textures.add(new Construction(0, 575, 100, 25));
        textures.add(new Construction(100, 565, 100, 25));
        textures.add(new Construction(200, 555, 100, 25));
        textures.add(new Construction(300, 545, 100, 25));
        textures.add(new Construction(400, 535, 100, 25));
        textures.add(new Construction(500, 525, 100, 25));
        textures.add(new Construction(500, 425, 100, 25));
        textures.add(new Construction(400, 415, 100, 25));
        textures.add(new Construction(300, 395, 100, 25));
        textures.add(new Construction(200, 385, 100, 25));
        textures.add(new Construction(100, 375, 100, 25));
        textures.add(new Construction(0, 365, 100, 25));
        textures.add(new Construction(0, 265, 100, 25));
        textures.add(new Construction(100, 255, 100, 25));
        textures.add(new Construction(200, 245, 100, 25));
        textures.add(new Construction(300, 235, 100, 25));
        textures.add(new Construction(400, 225, 100, 25));
        textures.add(new Construction(500, 215, 100, 25));
        textures.add(new Construction(500, 115, 100, 25));
        textures.add(new Construction(400, 115, 100, 25));
        textures.add(new Construction(300, 115, 100, 25));
        textures.add(new Construction(200, 115, 100, 25));
        textures.add(new Construction(100, 115, 100, 25));
        textures.add(new Construction(0, 115, 100, 25));

        enemies.add(new Barrel(50, 115 - 25, 25, 25));
    }


    @Override
    public void draw(GraphicsContext gc) {
        for(Drawable construction : textures)
        {
            construction.draw(gc);
        }
        for(Drawable enemy : enemies)
        {
            enemy.draw(gc);
        }
        player.draw(gc);
    }

    @Override
    public void simulate()
    {
        textures.forEach(player::handleCollision);
        enemies.forEach( e -> {
            e.simulate();
            player.handleCollision(e);
        });
        player.simulate();
    }

    @Override
    public void resetSimulationCycle() {
        player.resetSimulationCycle();
        enemies.forEach(MovingObject::resetSimulationCycle);
    }

}
