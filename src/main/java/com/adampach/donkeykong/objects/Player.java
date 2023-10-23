package com.adampach.donkeykong.objects;

import com.adampach.donkeykong.abstraction.Collisionable;
import com.adampach.donkeykong.abstraction.KeyboardObserver;
import com.adampach.donkeykong.abstraction.MovingObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Player extends MovingObject implements KeyboardObserver {
    private Direction direction;
    private boolean jumpRequested;
    private boolean isOnConstruction;
    private boolean isOnLadder;
    private int gravityIndex;

    private int maxGravityIndex;

    public Player(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
        direction = Direction.None;
        gravityIndex = 0;
        setDefaultSimulationValues();
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
        setDefaultSimulationValues();
    }

    @Override
    public void notified(KeyCode keyCode)
    {
        switch (keyCode)
        {
            case A ->
            {
                this.direction = Direction.Left;
                break;
            }
            case D ->
            {
                this.direction = Direction.Right;
                break;
            }
            case W ->
            {
                this.direction = Direction.Up;
                break;
            }
            case S ->
            {
                this.direction = Direction.Down;
                break;
            }
            case SPACE ->
            {
                jumpRequested = true;
                break;
            }
        }
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
        if(direction != Direction.None)
        {
            if(direction == Direction.Left)
                this.setPositionX( this.getPositionX() - 5 );
            else if(direction == Direction.Right)
                this.setPositionX( this.getPositionX() + 5 );
            else if(direction == Direction.Up)
                this.setPositionY( this.getPositionY() - 5 );
            else if(direction == Direction.Down)
                this.setPositionY( this.getPositionY() + 5 );
            direction = Direction.None;
        }
    }

    private void handleJump()
    {
        if(jumpRequested && isOnConstruction)
            gravityIndex = -10;
    }

    private void simulateGravity()
    {
        this.setPositionY(this.getPositionY() + gravityIndex);
        if(gravityIndex < maxGravityIndex)
            gravityIndex++;
    }

    private void setDefaultSimulationValues()
    {
        isOnConstruction = false;
        isOnLadder = false;
        jumpRequested = false;
        maxGravityIndex = 5;
    }

    private void handleConstructionCollision(Construction construction)
    {
        if(construction.getPositionY() <= this.getPositionY() + this.getHeight() && !isOnLadder)
        {
            this.setPositionY(construction.getPositionY() - this.getHeight());
            isOnConstruction = true;
            gravityIndex = 0;
        }
    }

    private void handleLadderCollision(Ladder ladder)
    {
        isOnLadder = true;
        maxGravityIndex = 0;
        gravityIndex = 0;
    }

    private enum Direction { Left, Right, Up, Down, None };
}
