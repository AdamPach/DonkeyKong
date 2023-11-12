package com.adampach.donkeykong.objects;

import com.adampach.donkeykong.abstraction.*;
import com.adampach.donkeykong.enums.MovingObjectsEnum;
import com.adampach.donkeykong.providers.MovementProviderWrapper;
import com.adampach.donkeykong.world.LevelSettings;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.ArrayList;


public class Player extends MovingObject
{
    private final LevelSettings levelSettings;
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
                if(getLevelBorderStatusStream().noneMatch( e -> e == MovingObjectsEnum.LevelBorderStatus.Left))
                    this.setPositionX(this.getPositionX() - levelSettings.getDefaultMovementSpeed());
            }
            case Right -> {
                if(getLevelBorderStatusStream().noneMatch( e -> e == MovingObjectsEnum.LevelBorderStatus.Right))
                    this.setPositionX(this.getPositionX() + levelSettings.getDefaultMovementSpeed());
            }
        }

        switch (movementProviderWrapper.verticalPositionProvider.provide())
        {
            case Up -> {
                if(getLadderStatus() == MovingObjectsEnum.LadderStatus.In || getLadderStatus() == MovingObjectsEnum.LadderStatus.Bottom)
                    this.setPositionY( this.getPositionY() - levelSettings.getDefaultClimbingSpeed());
            }
            case Down -> {
                if(getLadderStatus() == MovingObjectsEnum.LadderStatus.On || getLadderStatus() == MovingObjectsEnum.LadderStatus.In)
                    this.setPositionY( this.getPositionY() + levelSettings.getDefaultClimbingSpeed());
            }
        }
    }

    private void handleJump()
    {
        if(movementProviderWrapper.jumpProvider.provide() && getConstructionStatus() == MovingObjectsEnum.ConstructionStatus.On)
            gravityIndex = -11;
    }

    private void simulateGravity()
    {
        // Increase the gravity index for this cycle
        if(gravityIndex < maxGravityIndex)
            gravityIndex++;

        //When player in Step status calculate new position and use it
        if(getConstructionStatus() == MovingObjectsEnum.ConstructionStatus.Step
                && getLadderStatus() != MovingObjectsEnum.LadderStatus.In)
        {
            setPositionY(countStepPosition());
            setConstructionStatus(MovingObjectsEnum.ConstructionStatus.On);
        }

        //When player in on construction and isn't jumping, set turn of gravity for this cycle
        if(getConstructionStatus() == MovingObjectsEnum.ConstructionStatus.On && gravityIndex >= 0)
            gravityIndex = 0;

        // When user is using ladder turn of gravity
        if(getLadderStatus() == MovingObjectsEnum.LadderStatus.In
                || getLadderStatus() == MovingObjectsEnum.LadderStatus.Bottom)
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
        setConstructionStatus(MovingObjectsEnum.ConstructionStatus.None);
        setLadderStatus(MovingObjectsEnum.LadderStatus.None);
        clearLevelBorderStatus();
        maxGravityIndex = levelSettings.getDefaultMaxGravityIndex();
        CollidedObjects.clear();
    }

    private void handleConstructionCollision(Construction construction)
    {
        if(construction.getPositionY() < this.getMaxPositionY()
            && construction.getPositionY() + construction.getHeight() > getMaxPositionY())
            setConstructionStatus(MovingObjectsEnum.ConstructionStatus.Step);
        else if(getConstructionStatus() != MovingObjectsEnum.ConstructionStatus.Step)
        {
            if(construction.getPositionY() == this.getMaxPositionY())
                setConstructionStatus(MovingObjectsEnum.ConstructionStatus.On);
            else if (getConstructionStatus() != MovingObjectsEnum.ConstructionStatus.On)
                setConstructionStatus(MovingObjectsEnum.ConstructionStatus.In);
        }
        CollidedObjects.add(construction);
    }
    private void handleLadderCollision(Ladder ladder)
    {
        if(ladder.getMaxPositionY() == this.getMaxPositionY())
            setLadderStatus(MovingObjectsEnum.LadderStatus.Bottom);
        else if(ladder.getMaxPositionY() > this.getMaxPositionY() &&
                this.getMaxPositionY() > ladder.getPositionY())
            setLadderStatus(MovingObjectsEnum.LadderStatus.In);
        else setLadderStatus(MovingObjectsEnum.LadderStatus.On);
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
            addLevelBorderStatus(MovingObjectsEnum.LevelBorderStatus.Right);
        else if(getPositionX() <= 0)
            addLevelBorderStatus(MovingObjectsEnum.LevelBorderStatus.Left);
    }
}
