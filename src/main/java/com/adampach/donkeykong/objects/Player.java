package com.adampach.donkeykong.objects;

import com.adampach.donkeykong.abstraction.*;
import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.enums.PlayerEnum;
import com.adampach.donkeykong.providers.HorizontalDirectionProvider;
import com.adampach.donkeykong.providers.JumpProvider;
import com.adampach.donkeykong.providers.VerticalDirectionProvider;
import com.adampach.donkeykong.world.LevelSettings;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Player extends MovingObject implements Registrable<KeyboardObserver> {
    private final LevelSettings levelSettings;
    private final Provider<DirectionEnums.HorizontalDirection> horizontalProvider;
    private final Provider<DirectionEnums.VerticalPosition> verticalPositionProvider;
    private final Provider<Boolean> jumpProvider;
    private PlayerEnum.ConstructionStatus constructionStatus;
    private PlayerEnum.LadderStatus ladderStatus;
    private ArrayList<PlayerEnum.LevelBorderStatus> levelBorderStatus;
    private int gravityIndex;
    private int maxGravityIndex;
    private ArrayList<GameObject> CollidedObjects;

    public Player(int positionX, int positionY, int width, int height, LevelSettings levelSettings) {
        super(positionX, positionY, width, height);
        this.levelSettings = levelSettings;
        gravityIndex = 0;
        CollidedObjects = new ArrayList<>();
        levelBorderStatus = new ArrayList<>();
        resetSimulationCycle();

        this.horizontalProvider = new HorizontalDirectionProvider();
        this.verticalPositionProvider = new VerticalDirectionProvider();
        this.jumpProvider = new JumpProvider();
    }

    @Override
    public void draw(GraphicsContext gc)
    {
        Paint paint = gc.getFill();
        gc.setFill(Color.WHITE);
        gc.fillRect(positionX, positionY, width, height);
        gc.setFill(paint);
    }

    @Override
    public void simulate()
    {
        handleMovement();
        handleJump();
        simulateGravity();
        resetSimulationCycle();
    }

    @Override
    public void handleCollision(Collisionable collisionable)
    {
        if(!collisionable.intersect(this.getRectangle()))
            return;
        if(collisionable instanceof Construction)
            handleConstructionCollision((Construction) collisionable);
        else if(collisionable instanceof Ladder)
            handleLadderCollision((Ladder) collisionable);
        else if(collisionable instanceof LevelBorders)
            handleLevelCollision((LevelBorders)collisionable);
    }
    @Override
    public void Register(Consumer<KeyboardObserver> registerCallback)
    {
        registerCallback.accept((KeyboardObserver) horizontalProvider);
        registerCallback.accept((KeyboardObserver) verticalPositionProvider);
        registerCallback.accept((KeyboardObserver) jumpProvider);
    }

    @Override
    public void Unregister(Consumer<KeyboardObserver> unregisterCallback)
    {
        unregisterCallback.accept((KeyboardObserver) horizontalProvider);
        unregisterCallback.accept((KeyboardObserver) verticalPositionProvider);
        unregisterCallback.accept((KeyboardObserver) jumpProvider);
    }

    private void handleMovement()
    {

        switch (horizontalProvider.provide())
        {
            case Left -> {
                if(levelBorderStatus.stream().noneMatch( e -> e == PlayerEnum.LevelBorderStatus.Left))
                    this.setPositionX(this.getPositionX() - levelSettings.getDefaultSpeed());
            }
            case Right -> {
                if(levelBorderStatus.stream().noneMatch( e -> e == PlayerEnum.LevelBorderStatus.Right))
                    this.setPositionX(this.getPositionX() + levelSettings.getDefaultSpeed());
            }
        }

        switch (verticalPositionProvider.provide())
        {
            case Up -> {
                if(ladderStatus == PlayerEnum.LadderStatus.In || ladderStatus == PlayerEnum.LadderStatus.Bottom)
                    this.setPositionY( this.getPositionY() - levelSettings.getDefaultSpeed());
            }
            case Down -> {
                if(ladderStatus == PlayerEnum.LadderStatus.On || ladderStatus == PlayerEnum.LadderStatus.In)
                    this.setPositionY( this.getPositionY() + levelSettings.getDefaultSpeed());
            }
        }
    }

    private void handleJump()
    {
        if(jumpProvider.provide() && constructionStatus == PlayerEnum.ConstructionStatus.On)
            gravityIndex = -11;
    }

    private void simulateGravity()
    {
        // Increase the gravity index for this cycle
        if(gravityIndex < maxGravityIndex)
            gravityIndex++;

        //When player in Step status calculate new position and use it
        if(constructionStatus == PlayerEnum.ConstructionStatus.Step
                && ladderStatus != PlayerEnum.LadderStatus.In)
        {
            setPositionY(countStepPosition());
            constructionStatus = PlayerEnum.ConstructionStatus.On;
        }

        //When player in on construction and isn't jumping, set turn of gravity for this cycle
        if(constructionStatus == PlayerEnum.ConstructionStatus.On && gravityIndex >= 0)
            gravityIndex = 0;

        // When user is using ladder turn of gravity
        if(ladderStatus == PlayerEnum.LadderStatus.In
                || ladderStatus == PlayerEnum.LadderStatus.Bottom)
        {
            gravityIndex = 0;
            maxGravityIndex = 0;
        }

        // Gravity handling
        if(gravityIndex != 0)
            this.setPositionY(this.getPositionY() + gravityIndex);
    }

    private void resetSimulationCycle()
    {
        constructionStatus = PlayerEnum.ConstructionStatus.None;
        ladderStatus = PlayerEnum.LadderStatus.None;
        levelBorderStatus.clear();
        maxGravityIndex = levelSettings.getDefaultMaxGravityIndex();
        CollidedObjects.clear();
    }

    private void handleConstructionCollision(Construction construction)
    {
        if(construction.getPositionY() < this.getMaxPositionY()
            && construction.getPositionY() + construction.getHeight() > getMaxPositionY())
            constructionStatus = PlayerEnum.ConstructionStatus.Step;
        else if(constructionStatus != PlayerEnum.ConstructionStatus.Step)
        {
            if(construction.getPositionY() == this.getMaxPositionY())
                constructionStatus = PlayerEnum.ConstructionStatus.On;
            else if (constructionStatus != PlayerEnum.ConstructionStatus.On)
                constructionStatus = PlayerEnum.ConstructionStatus.In;
        }
        CollidedObjects.add(construction);
    }
    private void handleLadderCollision(Ladder ladder)
    {
        if(ladder.getMaxPositionY() == this.getMaxPositionY())
            ladderStatus = PlayerEnum.LadderStatus.Bottom;
        else if(ladder.getMaxPositionY() > this.getMaxPositionY() &&
                this.getMaxPositionY() > ladder.getPositionY())
            ladderStatus = PlayerEnum.LadderStatus.In;
        else ladderStatus = PlayerEnum.LadderStatus.On;
    }

    private int countStepPosition()
    {
        return switch (CollidedObjects.size())
            {
                case 1 ->
                    CollidedObjects.get(0).getPositionY() - getHeight();
                case 2 -> Math.min(CollidedObjects.get(0).getPositionY(),
                        CollidedObjects.get(1).getPositionY()) - getHeight();
                default -> getPositionY();
            };
    }


    private void handleLevelCollision(LevelBorders levelBorders)
    {
        if(getMaxPositionX() >= levelBorders.getWidth())
            levelBorderStatus.add(PlayerEnum.LevelBorderStatus.Right);
        else if(getPositionX() <= 0)
            levelBorderStatus.add(PlayerEnum.LevelBorderStatus.Left);
    }
}
