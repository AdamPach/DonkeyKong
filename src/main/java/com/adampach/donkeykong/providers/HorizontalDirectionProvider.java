package com.adampach.donkeykong.providers;

import com.adampach.donkeykong.abstraction.KeyboardObserver;
import com.adampach.donkeykong.abstraction.Provider;
import com.adampach.donkeykong.enums.DirectionEnums;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

import static com.adampach.donkeykong.statics.KeyboardMapping.horizontalMapping;
import static com.adampach.donkeykong.statics.KeyboardMapping.movementTypeMapping;

public class HorizontalDirectionProvider implements KeyboardObserver, Provider<DirectionEnums.HorizontalDirection> {
    private ArrayList<DirectionEnums.HorizontalDirection> pressedDirections;

    public HorizontalDirectionProvider()
    {
        pressedDirections = new ArrayList<>();
    }

    @Override
    public void notifyObserver(boolean keyPressed, KeyCode keyCode)
    {
        DirectionEnums.KeyEventType movementType = movementTypeMapping.get(keyCode.getChar());
        if(movementType != DirectionEnums.KeyEventType.HorizontalMovement)
            return;

        DirectionEnums.HorizontalDirection mappedType = horizontalMapping.get(keyCode);
        if(keyPressed)
        {
            if(pressedDirections.stream().noneMatch( e -> e == mappedType))
                pressedDirections.add(mappedType);
        }
        else
            pressedDirections.remove(mappedType);
    }

    @Override
    public DirectionEnums.HorizontalDirection provide() {
        if(pressedDirections.isEmpty())
            return DirectionEnums.HorizontalDirection.None;
        return pressedDirections.get(pressedDirections.size() - 1);
    }
}
