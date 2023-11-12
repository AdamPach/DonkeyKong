package com.adampach.donkeykong.objects.moving;

import com.adampach.donkeykong.abstraction.Collisionable;
import com.adampach.donkeykong.abstraction.MovingObject;
import com.adampach.donkeykong.abstraction.Zone;
import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.enums.MovingObjectsEnum;
import com.adampach.donkeykong.objects.zones.HorizontalMovementZone;
import com.adampach.donkeykong.objects.zones.VerticalMovementZone;
import com.adampach.donkeykong.world.LevelSettings;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Barrel extends MovingObject
{
    private final LevelSettings levelSettings;
    private DirectionEnums.HorizontalDirection horizontalMovement;
    private DirectionEnums.VerticalDirection verticalMovement;
    private int gravityIndex;
    private int maxGravityIndex;

    public Barrel(int positionX, int positionY, int width, int height, LevelSettings levelSettings)
    {
        super(positionX, positionY, width, height);
        this.levelSettings = levelSettings;
        horizontalMovement = this.levelSettings.getFirstBarrelDirection();
        verticalMovement = DirectionEnums.VerticalDirection.None;
        gravityIndex = 0;
        resetSimulationCycle();
    }

    @Override
    public void draw(GraphicsContext gc)
    {
        Paint paint = gc.getFill();
        gc.setFill(Color.LIGHTSKYBLUE);
        gc.fillRect(positionX, positionY, width, height);
        gc.setFill(paint);
    }

    @Override
    public void simulate()
    {
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

        super.handleCollision(collisionable);
    }

    private void handleMovement()
    {
        if(verticalMovement != DirectionEnums.VerticalDirection.None)
            switch (verticalMovement)
            {
                case Down ->
                {
                    this.setPositionY( this.getPositionY() + levelSettings.getDefaultClimbingSpeed());
                    break;
                }
                case Up ->
                {
                    this.setPositionY( this.getPositionY() - levelSettings.getDefaultClimbingSpeed());
                    break;
                }
            }
        else
            switch (horizontalMovement)
            {
                case Left ->
                {
                    this.setPositionX(this.getPositionX() + levelSettings.getDefaultMovementSpeed());
                    break;
                }
                case Right ->
                {
                    this.setPositionX(this.getPositionX() - levelSettings.getDefaultMovementSpeed());
                    break;
                }
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
                && getLadderStatus() != MovingObjectsEnum.LadderStatus.In)
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
