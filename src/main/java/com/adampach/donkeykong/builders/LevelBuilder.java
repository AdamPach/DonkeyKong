package com.adampach.donkeykong.builders;

import com.adampach.donkeykong.abstraction.Builder;
import com.adampach.donkeykong.abstraction.game.EnemyGenerator;
import com.adampach.donkeykong.abstraction.game.TextureObject;
import com.adampach.donkeykong.abstraction.game.Zone;
import com.adampach.donkeykong.data.LevelSettings;
import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.handlers.LevelEventsHandler;
import com.adampach.donkeykong.objects.generators.BarrelGenerator;
import com.adampach.donkeykong.objects.textures.Construction;
import com.adampach.donkeykong.objects.textures.Ladder;
import com.adampach.donkeykong.objects.textures.LevelBorders;
import com.adampach.donkeykong.objects.zones.DestroyBarrelZone;
import com.adampach.donkeykong.objects.zones.HorizontalMovementZone;
import com.adampach.donkeykong.objects.zones.VerticalMovementZone;
import com.adampach.donkeykong.world.Level;
import com.adampach.donkeykong.wrappers.MovementProviderWrapper;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import java.security.InvalidParameterException;
import java.util.LinkedList;

public class LevelBuilder implements Builder<Level> {
    private final LevelSettings levelSettings;
    private final LinkedList<TextureObject> textures;
    private final LinkedList<EnemyGenerator> enemyGenerators;
    private final LinkedList<Zone> zones;
    private MovementProviderWrapper movementProviderWrapper;
    private LevelEventsHandler levelEventsHandler;
    private Point2D playerSpawnPoint;

    public static LevelBuilder CreateBuilder(LevelSettings levelSettings)
    {
        if(levelSettings == null)
            throw new InvalidParameterException("Level setting are null");
        return new LevelBuilder(levelSettings);
    }

    public LevelBuilder addConstruction(Rectangle2D constructionSize)
    {
        textures.forEach( e ->
        {
            if(e instanceof Construction && constructionSize.intersects(e.getRectangle()))
                throw new RuntimeException("Two construction the on same position");
        });

        textures.add(new Construction(
                (int)constructionSize.getMinX(),
                (int)constructionSize.getMinY(),
                (int)constructionSize.getWidth(),
                (int)constructionSize.getHeight()));

        return this;
    }

    public LevelBuilder addLadder(Rectangle2D ladderSize)
    {
        textures.forEach( e ->
        {
            if(e instanceof Ladder && ladderSize.intersects(e.getRectangle()))
                throw new RuntimeException("Two ladders on the same position");
        });

        textures.add(new Ladder(
                (int)ladderSize.getMinX(),
                (int)ladderSize.getMinY(),
                (int)ladderSize.getWidth(),
                (int)ladderSize.getHeight()));

        return this;
    }

    public LevelBuilder addBarrelGenerator(Rectangle2D generatorPosition, DirectionEnums.HorizontalDirection initBarrelDirection)
    {
        textures.forEach( e ->
        {
            if( !(e instanceof LevelBorders) && generatorPosition.intersects(e.getRectangle()))
                throw new RuntimeException("Generator is in the texture");
        });

        enemyGenerators.forEach( e ->
        {
            if(generatorPosition.intersects(e.getRectangle()))
                throw new RuntimeException("Two generators on the same position");
        });

        enemyGenerators.add(new BarrelGenerator(
                (int)generatorPosition.getMinX(),
                (int)generatorPosition.getMinY(),
                (int)generatorPosition.getWidth(),
                (int)generatorPosition.getHeight(),
                initBarrelDirection,
                levelSettings));

        return this;
    }

    public LevelBuilder addHorizontalMovementZone(Rectangle2D zonePosition, DirectionEnums.HorizontalDirection direction)
    {
        zones.forEach( e ->
        {
            if(e instanceof HorizontalMovementZone && zonePosition.intersects(e.getRectangle()))
                throw new RuntimeException("Two horizontal zones on the same position");
        });

        zones.add(new HorizontalMovementZone(
                (int)zonePosition.getMinX(),
                (int)zonePosition.getMinY(),
                (int)zonePosition.getWidth(),
                (int)zonePosition.getHeight(),
                direction));

        return this;
    }

    public LevelBuilder addVerticalMovementZone(Rectangle2D zonePosition, DirectionEnums.VerticalDirection direction)
    {
        zones.forEach( e ->
        {
            if(e instanceof VerticalMovementZone && zonePosition.intersects(e.getRectangle()))
                throw new RuntimeException("Two horizontal zones on the same position");
        });

        zones.add(new VerticalMovementZone(
                (int)zonePosition.getMinX(),
                (int)zonePosition.getMinY(),
                (int)zonePosition.getWidth(),
                (int)zonePosition.getHeight(),
                direction));

        return this;
    }

    public LevelBuilder addDestroyBarrelZone(Rectangle2D zonePosition)
    {
        zones.forEach( e ->
        {
            if(e instanceof VerticalMovementZone && zonePosition.intersects(e.getRectangle()))
                throw new RuntimeException("Two horizontal zones on the same position");
        });

        zones.add(new DestroyBarrelZone(
                (int)zonePosition.getMinX(),
                (int)zonePosition.getMinY(),
                (int)zonePosition.getWidth(),
                (int)zonePosition.getHeight()));

        return this;
    }

    public LevelBuilder addMovementProviders(MovementProviderWrapper movementProviderWrapper)
    {
        this.movementProviderWrapper = movementProviderWrapper;

        return this;
    }

    public LevelBuilder addLevelEventHandler(LevelEventsHandler LevelEventHandler)
    {
        this.levelEventsHandler = LevelEventHandler;

        return this;
    }

    public LevelBuilder addPlayerSpawnPoint(Point2D playerSpawnPoint)
    {
        this.playerSpawnPoint = playerSpawnPoint;

        return this;
    }

    private LevelBuilder(LevelSettings levelSettings)
    {
        this.levelSettings = levelSettings;
        textures = new LinkedList<>();
        enemyGenerators = new LinkedList<>();
        zones = new LinkedList<>();
        movementProviderWrapper = null;
        levelEventsHandler = null;
        playerSpawnPoint = null;
        textures.add(new LevelBorders(levelSettings));
    }

    @Override
    public Level build()
    {
        if(movementProviderWrapper == null)
            throw new RuntimeException("You haven't set movement providers yet");

        if(levelEventsHandler == null)
            throw new RuntimeException("You haven't set level event handlers yet");

        if(playerSpawnPoint == null)
            throw new RuntimeException("You haven't set player spawn point yet");

        return new Level(
                textures,
                zones,
                enemyGenerators,
                levelSettings,
                playerSpawnPoint,
                movementProviderWrapper,
                levelEventsHandler
        );
    }
}
