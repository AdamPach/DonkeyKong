package com.adampach.donkeykong.objects.moving;

import com.adampach.donkeykong.abstraction.game.Collisionable;
import com.adampach.donkeykong.abstraction.game.MovingObject;
import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.enums.GameEventEnums;
import com.adampach.donkeykong.enums.MovingObjectsEnum;
import com.adampach.donkeykong.handlers.LevelEventsHandler;
import com.adampach.donkeykong.objects.textures.Peach;
import com.adampach.donkeykong.wrappers.MovementProviderWrapper;
import com.adampach.donkeykong.data.LevelSettings;
import javafx.scene.canvas.GraphicsContext;

import static com.adampach.donkeykong.assets.ImageAssets.*;


public class Player extends MovingObject
{
    private final LevelSettings levelSettings;
    private final MovementProviderWrapper movementProviderWrapper;
    private final LevelEventsHandler levelEventsHandler;
    private DirectionEnums.HorizontalDirection lastHorizontalDirection;
    private boolean isJumping;
    private int gravityIndex;
    private int maxGravityIndex;


    public Player(int positionX, int positionY, int width, int height, LevelSettings levelSettings, MovementProviderWrapper movementProviderWrapper, LevelEventsHandler levelEventsHandler) {
        super(positionX, positionY, width, height);
        this.levelSettings = levelSettings;
        this.movementProviderWrapper = movementProviderWrapper;
        this.levelEventsHandler = levelEventsHandler;
        gravityIndex = 0;
        lastHorizontalDirection = DirectionEnums.HorizontalDirection.None;
        isJumping = false;
        resetSimulationCycle();
    }

    @Override
    public void draw(GraphicsContext gc)
    {
        handleLastDirection();
        if(isJumping)
            if(lastHorizontalDirection == DirectionEnums.HorizontalDirection.Left)
                gc.drawImage(MARIO_JUMP_FLIP, getPositionX(), getPositionY(), getWidth(), getHeight());
            else
                gc.drawImage(MARIO_JUMP, getPositionX(), getPositionY(), getWidth(), getHeight());
        else
            if(lastHorizontalDirection == DirectionEnums.HorizontalDirection.Left)
                gc.drawImage(MARIO_WALK_FLIP, getPositionX(), getPositionY(), getWidth(), getHeight());
            else
                gc.drawImage(MARIO_WALK, getPositionX(), getPositionY(), getWidth(), getHeight());
    }

    @Override
    public void handleCollision(Collisionable collisionable)
    {
        if(!collisionable.intersect(this.getRectangle()))
            return;

        if(collisionable instanceof Barrel)
            levelEventsHandler.notifyWithLevelEvent(GameEventEnums.LevelEvents.GameOver);

        if(collisionable instanceof Peach)
            levelEventsHandler.notifyWithLevelEvent(GameEventEnums.LevelEvents.Peach);

        super.handleCollision(collisionable);
    }

    @Override
    public void simulate()
    {
        handleMovement();
        handleJump();
        simulateGravity();
    }

    @Override
    public void resetSimulationCycle()
    {
        clearLevelBorderStatus();
        maxGravityIndex = levelSettings.getDefaultMaxGravityIndex();
        super.resetSimulationCycle();
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
        if(movementProviderWrapper.jumpProvider.provide() && !isJumping) {
            gravityIndex = levelSettings.getDefaultJumpGravity();
            isJumping = true;
        }
        else if(isJumping && (getConstructionStatus() ==
                MovingObjectsEnum.ConstructionStatus.On
                || getLadderStatus() != MovingObjectsEnum.LadderStatus.None))
            isJumping = false;
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

    private int countStepPosition()
    {
        return switch (getCollisionObjectsSize())
            {
                case 1 ->
                    getCollidedObjectAt(0).getPositionY() - getHeight();
                case 2 -> Math.min(getCollidedObjectAt(0).getPositionY(),
                        getCollidedObjectAt(1).getPositionY()) - getHeight();
                default -> getPositionY();
            };
    }

    private void handleLastDirection()
    {
        DirectionEnums.HorizontalDirection tmpDirection =
                movementProviderWrapper.horizontalProvider.provide();
        if(tmpDirection != DirectionEnums.HorizontalDirection.None)
            lastHorizontalDirection = tmpDirection;
    }
}
