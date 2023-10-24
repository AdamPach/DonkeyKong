package com.adampach.donkeykong.providers;

import com.adampach.donkeykong.abstraction.DirectionProvider;
import com.adampach.donkeykong.abstraction.KeyboardObserver;
import com.adampach.donkeykong.enums.DirectionEnums;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

import static com.adampach.donkeykong.statics.KeyboardMapping.directionMapping;


public class KeyboardDirectionProvider
        implements KeyboardObserver, DirectionProvider
{
    private ArrayList<String> pressedHorizontalKeys;
    private ArrayList<String> pressedVerticalKeys;

    public KeyboardDirectionProvider()
    {
        pressedHorizontalKeys = new ArrayList<>();
        pressedVerticalKeys = new ArrayList<>();
    }

    @Override
    public void notifyObserver(boolean keyPressed, KeyCode keyCode)
    {
        if(keyPressed)
            handleKeyPressed(keyCode);
        else handleKeyReleased(keyCode);
    }

    @Override
    public DirectionEnums.HorizontalDirection provideHorizontalPosition() {
        return DirectionEnums.HorizontalDirection.None;
    }

    @Override
    public DirectionEnums.VerticalPosition provideVerticalPosition() {
        return DirectionEnums.VerticalPosition.None;
    }

    private void handleKeyPressed(KeyCode keyCode)
    {
        DirectionEnums.Direction direction = directionMapping.get(keyCode.getChar());
        if(direction == DirectionEnums.Direction.Horizontal)
        {
            if(pressedHorizontalKeys.stream().noneMatch(e -> e.equals(keyCode.getChar())))
            {
                System.out.println("Key pressed " + keyCode.getChar());
                pressedHorizontalKeys.add(keyCode.getChar());
            }
        }
        else if(direction == DirectionEnums.Direction.Vertical)
        {

        }
    }

    private void handleKeyReleased(KeyCode keyCode)
    {
        DirectionEnums.Direction direction = directionMapping.get(keyCode.getChar());
        if(direction == DirectionEnums.Direction.Horizontal)
        {
            if(pressedHorizontalKeys.stream().anyMatch(e -> e.equals(keyCode.getChar())))
            {
                System.out.println("Key released " + keyCode.getChar());
                pressedHorizontalKeys.remove(keyCode.getChar());
            }
        }
    }
}
