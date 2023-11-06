package com.adampach.donkeykong.objects;

import com.adampach.donkeykong.abstraction.*;
import com.adampach.donkeykong.enums.PlayerEnum;
import com.adampach.donkeykong.providers.MovementProviderWrapper;
import com.adampach.donkeykong.world.LevelSettings;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.ArrayList;


public class Player extends MovingObject
{
    private final LevelSettings levelSettings;
    private PlayerEnum.ConstructionStatus constructionStatus;
    private PlayerEnum.LadderStatus ladderStatus;
    private ArrayList<PlayerEnum.LevelBorderStatus> levelBorderStatus;
    private final MovementProviderWrapper movementProviderWrapper;
    private int gravityIndex;
    private int maxGravityIndex;
    private ArrayList<GameObject> CollidedObjects;

    public Player(int positionX, int positionY, int width, int height, LevelSettings levelSettings, MovementProviderWrapper movementProviderWrapper) {
        super(positionX, positionY, width, height);
        this.levelSettings = levelSettings;
        this.movementProviderWrapper = movementProviderWrapper;
        gravityIndex = 0;
        CollidedObjects = new ArrayList<>();
        levelBorderStatus = new ArrayList<>();
        resetSimulationCycle();
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

    private void handleMovement()
    {

        switch (movementProviderWrapper.horizontalProvider.provide())
        {
            case Left -> {
                if(levelBorderStatus.stream().noneMatch( e -> e == PlayerEnum.LevelBorderStatus.Left))
                    this.setPositionX(this.getPositionX() - levelSettings.getDefaultMovementSpeed());
            }
            case Right -> {
                if(levelBorderStatus.stream().noneMatch( e -> e == PlayerEnum.LevelBorderStatus.Right))
                    this.setPositionX(this.getPositionX() + levelSettings.getDefaultMovementSpeed());
            }
        }

        switch (movementProviderWrapper.verticalPositionProvider.provide())
        {
            case Up -> {
                if(ladderStatus == PlayerEnum.LadderStatus.In || ladderStatus == PlayerEnum.LadderStatus.Bottom)
                    this.setPositionY( this.getPositionY() - levelSettings.getDefaultClimbingSpeed());
            }
            case Down -> {
                if(ladderStatus == PlayerEnum.LadderStatus.On || ladderStatus == PlayerEnum.LadderStatus.In)
                    this.setPositionY( this.getPositionY() + levelSettings.getDefaultClimbingSpeed());
            }
        }
    }

    private void handleJump()
    {
        if(movementProviderWrapper.jumpProvider.provide() && constructionStatus == PlayerEnum.ConstructionStatus.On)
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
