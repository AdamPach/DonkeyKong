package com.adampach.donkeykong.objects;

import com.adampach.donkeykong.abstraction.Collisionable;
import com.adampach.donkeykong.abstraction.DirectionProvider;
import com.adampach.donkeykong.abstraction.GameObject;
import com.adampach.donkeykong.abstraction.MovingObject;
import com.adampach.donkeykong.world.LevelSettings;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.ArrayList;

public class Player extends MovingObject {
    private final LevelSettings levelSettings;
    private final DirectionProvider directionProvider;
    private boolean jumpRequested;
    private ConstructionStatus constructionStatus;
    private LadderStatus ladderStatus;
    private int gravityIndex;
    private int maxGravityIndex;
    private ArrayList<GameObject> CollidedObjects;

    public Player(int positionX, int positionY, int width, int height, LevelSettings levelSettings, DirectionProvider directionProvider) {
        super(positionX, positionY, width, height);
        this.levelSettings = levelSettings;
        this.directionProvider = directionProvider;
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
    }

    private void handleMovement()
    {
        switch (directionProvider.provideHorizontalPosition())
        {
            case Left -> this.setPositionX( this.getPositionX() - levelSettings.getDefaultSpeed());
            case Right -> this.setPositionX( this.getPositionX() + levelSettings.getDefaultSpeed());
        }
    }

    private void handleJump()
    {
        if(jumpRequested && constructionStatus == ConstructionStatus.On)
            gravityIndex = -11;
    }

    private void simulateGravity()
    {
        // Increase the gravity index for this cycle
        if(gravityIndex < maxGravityIndex)
            gravityIndex++;

        //When player in Step status calculate new position and use it
        if(constructionStatus == ConstructionStatus.Step
                && ladderStatus != LadderStatus.In)
        {
            setPositionY(countStepPosition());
            constructionStatus = ConstructionStatus.On;
        }

        //When player in on construction and isn't jumping, set turn of gravity for this cycle
        if(constructionStatus == ConstructionStatus.On && !jumpRequested)
            gravityIndex = 0;

        // When user is using ladder turn of gravity
        if(ladderStatus == LadderStatus.In
                || ladderStatus == LadderStatus.Bottom)
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
        constructionStatus = ConstructionStatus.None;
        ladderStatus = LadderStatus.None;
        jumpRequested = false;
        maxGravityIndex = levelSettings.getDefaultMaxGravityIndex();
        CollidedObjects.clear();
    }

    private void handleConstructionCollision(Construction construction)
    {
        if(construction.getPositionY() < this.getMaxPositionY()
            && construction.getPositionY() + construction.getHeight() > getMaxPositionY())
            constructionStatus = ConstructionStatus.Step;
        else if(constructionStatus != ConstructionStatus.Step)
        {
            if(construction.getPositionY() == this.getMaxPositionY())
                constructionStatus = ConstructionStatus.On;
            else if (constructionStatus != ConstructionStatus.On)
                constructionStatus = ConstructionStatus.In;
        }
        CollidedObjects.add(construction);
    }
    private void handleLadderCollision(Ladder ladder)
    {
        if(ladder.getMaxPositionY() == this.getMaxPositionY())
            ladderStatus = LadderStatus.Bottom;
        else if(ladder.getMaxPositionY() > this.getMaxPositionY() &&
                this.getMaxPositionY() > ladder.getPositionY())
            ladderStatus = LadderStatus.In;
        else ladderStatus = LadderStatus.On;
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

    private enum LadderStatus { On, In, Bottom, None };
    private enum ConstructionStatus { On, In, Step, None };
}
