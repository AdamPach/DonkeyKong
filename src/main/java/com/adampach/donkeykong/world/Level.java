package com.adampach.donkeykong.world;

import com.adampach.donkeykong.abstraction.game.*;
import com.adampach.donkeykong.abstraction.gui.GuiComponent;
import com.adampach.donkeykong.data.LevelSettings;
import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.handlers.LevelEventsHandler;
import com.adampach.donkeykong.objects.generators.BarrelGenerator;
import com.adampach.donkeykong.objects.moving.Player;
import com.adampach.donkeykong.objects.textures.Construction;
import com.adampach.donkeykong.objects.textures.Ladder;
import com.adampach.donkeykong.objects.textures.LevelBorders;
import com.adampach.donkeykong.objects.zones.DestroyBarrelZone;
import com.adampach.donkeykong.objects.zones.HorizontalMovementZone;
import com.adampach.donkeykong.objects.zones.VerticalMovementZone;
import com.adampach.donkeykong.wrappers.MovementProviderWrapper;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

import static com.adampach.donkeykong.assets.FontAssets.Arcade24;

public class Level implements GuiComponent {
    private final Player player;
    private final LinkedList<TextureObject> textures;
    private final EnemiesContainer<LinkedList<Enemy>> enemies;
    private final LinkedList<Zone> zones;
    private final LinkedList<EnemyGenerator> generators;
    private final LevelSettings levelSettings;
    private int currentScore;
    private int currentTick;

    public Level(
            LinkedList<TextureObject> textures,
            LinkedList<Zone> zones,
            LinkedList<EnemyGenerator> generators,
            LevelSettings levelSettings,
            Point2D spawnPoint,
            MovementProviderWrapper movementProviderWrapper,
            LevelEventsHandler levelEventsHandler)
    {

        this.textures = textures;
        this.zones = zones;
        this.generators = generators;
        this.levelSettings = levelSettings;
        this.player = new Player(
                spawnPoint,
                levelSettings,
                movementProviderWrapper,
                levelEventsHandler);

        enemies = new EnemiesContainer<>(new LinkedList<>());

        currentScore = levelSettings.getMaxAvailableScore();
        currentTick = 0;

        generators.forEach( e -> e.registerObserver(enemies));
    }

    @Override
    public void display(Canvas canvas)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        textures.forEach( t -> t.draw(gc));
        enemies.forEach( e -> e.draw(gc));
        generators.forEach( g -> g.draw(gc));
        player.draw(gc);

        gc.setFont(Arcade24);
        gc.setFill(Color.WHITE);
        gc.fillText("Score " + currentScore, 450, 20);
        gc.fillText("Lives " + player.getCurrentLives(), 450, 40);
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

        generators.forEach(EnemyGenerator::generate);
        player.simulate();

        if(++currentTick >= levelSettings.getCyclesToDecreaseScore() &&
            currentScore > 0)
        {
            currentScore -= levelSettings.getDecreaseAtOnce();
            currentTick = 0;
        }

        resetSimulationCycle();
    }

    public void resetSimulationCycle()
    {
        player.resetSimulationCycle();
        enemies.forEach(Enemy::resetSimulationCycle);
        enemies.clean();
    }

    public void clearEnemies()
    {
        enemies.clear();
    }

    public int getCurrentScore() {
        return currentScore;
    }
}
