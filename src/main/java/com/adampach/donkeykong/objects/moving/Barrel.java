package com.adampach.donkeykong.objects.moving;

import com.adampach.donkeykong.abstraction.game.Collisionable;
import com.adampach.donkeykong.abstraction.game.Enemy;
import com.adampach.donkeykong.abstraction.game.Zone;
import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.enums.MovingObjectsEnum;
import com.adampach.donkeykong.objects.zones.DestroyBarrelZone;
import com.adampach.donkeykong.objects.zones.HorizontalMovementZone;
import com.adampach.donkeykong.objects.zones.VerticalMovementZone;
import com.adampach.donkeykong.data.LevelSettings;
import javafx.scene.canvas.GraphicsContext;

import static com.adampach.donkeykong.assets.ImageAssets.BARREL;

public class Barrel extends Enemy
{
    private final LevelSettings levelSettings;
    private DirectionEnums.HorizontalDirection horizontalMovement;
    private DirectionEnums.VerticalDirection verticalMovement;
    private int gravityIndex;
    private int maxGravityIndex;

    public Barrel(
            int positionX,
            int positionY,
            DirectionEnums.HorizontalDirection initDirection,
            LevelSettings levelSettings)
    {
        super(positionX, positionY, (int)levelSettings.getDefaultBarrelSize().width(),  (int)levelSettings.getDefaultBarrelSize().height());
        this.levelSettings = levelSettings;
        horizontalMovement = initDirection;
        verticalMovement = DirectionEnums.VerticalDirection.None;
        gravityIndex = 0;
        resetSimulationCycle();
    }

    @Override
    public void draw(GraphicsContext gc)
    {
        gc.drawImage(BARREL, getPositionX(), getPositionY(), getWidth(), getHeight());
    }

    @Override
    public void simulate()
    {
        handleLevelBorders();
        handleMovement();
        simulateGravity();
    }

    @Override
    public void resetSimulationCycle()
    {
        maxGravityIndex = levelSettings.getDefaultMaxGravityIndex();
        super.resetSimulationCycle();
    }

    @Override
    public void handleCollision(Collisionable collisionable)
    {
        if(!collisionable.intersect(this.getRectangle()))
            return;

        if(collisionable instanceof HorizontalMovementZone)
            handleDirectionZone((HorizontalMovementZone)collisionable);
        if(collisionable instanceof VerticalMovementZone)
            handleVerticalZone((VerticalMovementZone)collisionable);
        if(collisionable instanceof DestroyBarrelZone)
            notifyDestroy();

        super.handleCollision(collisionable);
    }

    private void handleLevelBorders()
    {
        if(getLevelBorderStatusStream()
                .anyMatch( e -> e == MovingObjectsEnum.LevelBorderStatus.Right))
        {
            horizontalMovement = DirectionEnums.HorizontalDirection.Left;
        }
        else if (getLevelBorderStatusStream()
                .anyMatch( e -> e == MovingObjectsEnum.LevelBorderStatus.Left))
        {
            horizontalMovement = DirectionEnums.HorizontalDirection.Right;
        }
    }

    private void handleMovement()
    {
        if(verticalMovement != DirectionEnums.VerticalDirection.None)
            switch (verticalMovement)
            {
                case Down -> this.setPositionY( this.getPositionY() + levelSettings.getDefaultClimbingSpeed());
                case Up -> this.setPositionY( this.getPositionY() - levelSettings.getDefaultClimbingSpeed());
            }
        else
            switch (horizontalMovement)
            {
                case Left -> this.setPositionX(this.getPositionX() - levelSettings.getDefaultMovementSpeed());
                case Right -> this.setPositionX(this.getPositionX() + levelSettings.getDefaultMovementSpeed());
            }
    }

    private void handleDirectionZone(Zone<DirectionEnums.HorizontalDirection> zone)
    {
        horizontalMovement = zone.provide();
    }

    private void handleVerticalZone(Zone<DirectionEnums.VerticalDirection> zone)
    {
        verticalMovement = zone.provide();
        if(verticalMovement == DirectionEnums.VerticalDirection.Down)
            if(this.getMaxPositionX() - zone.getPositionX() >
                    zone.getPositionX() - this.getPositionX() )
                this.setPositionX(zone.getPositionX() + 1);
            else
                this.setPositionX(zone.getPositionX() - this.getWidth() - 1);
    }

    private void simulateGravity()
    {
        if(gravityIndex < maxGravityIndex)
            gravityIndex++;

        if(getConstructionStatus() == MovingObjectsEnum.ConstructionStatus.Step
                && verticalMovement != DirectionEnums.VerticalDirection.Down)
        {
            setPositionY(countStepPosition());
            setConstructionStatus(MovingObjectsEnum.ConstructionStatus.On);
        }

        if(getConstructionStatus() == MovingObjectsEnum.ConstructionStatus.On
            || verticalMovement == DirectionEnums.VerticalDirection.Down)
            gravityIndex = 0;

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
}
