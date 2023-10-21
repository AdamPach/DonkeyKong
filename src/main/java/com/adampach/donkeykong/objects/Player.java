package com.adampach.donkeykong.objects;

import com.adampach.donkeykong.abstraction.Collisionable;
import com.adampach.donkeykong.abstraction.GameObject;
import com.adampach.donkeykong.abstraction.KeyboardObserver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Player extends GameObject implements KeyboardObserver {
    private Direction direction;
    private int gravityIndex;

    private final int maxGravityIndex;

    public Player(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
        direction = Direction.None;
        gravityIndex = 0;
        maxGravityIndex = 5;
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
        simulateGravity();
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
                this.gravityIndex = -10;
            }
        }
    }

    @Override
    public void handleCollision(Collisionable collisionable)
    {
        if(!this.intersect(collisionable.getRectangle()))
            return;
        if(collisionable instanceof Construction)
            handleConstructionCollision((Construction) collisionable);
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

    private void simulateGravity()
    {
        this.setPositionY(this.getPositionY() + gravityIndex);
        if(gravityIndex < maxGravityIndex)
            gravityIndex++;
    }

    private void handleConstructionCollision(Construction construction)
    {
        if(construction.getPositionY() >= this.getPositionY() + this.getWidth())
            gravityIndex = 0;
    }

    private enum Direction { Left, Right, Up, Down, None };
}
