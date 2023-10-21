package com.adampach.donkeykong.objects;

import com.adampach.donkeykong.abstraction.GameObject;
import com.adampach.donkeykong.abstraction.KeyboardObserver;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Player extends GameObject implements KeyboardObserver  {
    private Direction direction;

    public Player(Point2D position, int width, int height)
    {
        super((int)position.getX(), (int)position.getY(), width, height);
        direction = Direction.None;
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
        HandleMovement();
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
        }
    }

    private void HandleMovement()
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

    private enum Direction { Left, Right, Up, Down, None };
}
