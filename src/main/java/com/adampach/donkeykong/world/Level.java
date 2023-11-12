package com.adampach.donkeykong.world;

import com.adampach.donkeykong.abstraction.*;

import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.objects.moving.Barrel;
import com.adampach.donkeykong.objects.moving.Player;
import com.adampach.donkeykong.objects.textures.Construction;
import com.adampach.donkeykong.objects.textures.Ladder;
import com.adampach.donkeykong.objects.textures.LevelBorders;
import com.adampach.donkeykong.objects.zones.HorizontalMovementZone;
import com.adampach.donkeykong.objects.zones.VerticalMovementZone;
import com.adampach.donkeykong.providers.MovementProviderWrapper;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Level implements Drawable, Simulable{
    private final Player player;
    private final ArrayList<TextureObject> textures;
    private final ArrayList<MovingObject> enemies;
    private final ArrayList<Zone> zones;
    private final LevelSettings levelSettings;

    public Level(LevelSettings levelSettings, MovementProviderWrapper movementProviderWrapper)
    {
        this.levelSettings = levelSettings;
        zones = new ArrayList<>();
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

        enemies.add(new Barrel(50, 115 - 25, 25, 25, levelSettings));

        zones.add(new HorizontalMovementZone(500, 190, 100, 25, DirectionEnums.HorizontalDirection.Right));
        zones.add(new HorizontalMovementZone(0, 365-25, 100, 25, DirectionEnums.HorizontalDirection.Left));
        zones.add(new HorizontalMovementZone(500, 500, 100, 25, DirectionEnums.HorizontalDirection.Right));

        zones.add(new VerticalMovementZone(576, 110, 1,1, DirectionEnums.VerticalDirection.Down));
        zones.add(new VerticalMovementZone(550, 215, 25,1, DirectionEnums.VerticalDirection.None));

        zones.add(new VerticalMovementZone(24, 260, 1,1, DirectionEnums.VerticalDirection.Down));
        zones.add(new VerticalMovementZone(25, 365, 25,1, DirectionEnums.VerticalDirection.None));

        zones.add(new VerticalMovementZone(576, 420, 1,1, DirectionEnums.VerticalDirection.Down));
        zones.add(new VerticalMovementZone(550, 525, 25,1, DirectionEnums.VerticalDirection.None));
    }


    @Override
    public void draw(GraphicsContext gc) {
        textures.forEach( t -> t.draw(gc));
        enemies.forEach( e -> e.draw(gc));
        player.draw(gc);
    }

    @Override
    public void simulate()
    {
        textures.forEach( t ->
        {
            enemies.forEach( e -> e.handleCollision(t));
            player.handleCollision(t);
        });

        zones.forEach( z -> enemies.forEach( e -> e.handleCollision(z)));

        enemies.forEach( e ->
        {
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
