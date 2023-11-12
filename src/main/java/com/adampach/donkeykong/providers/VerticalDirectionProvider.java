package com.adampach.donkeykong.providers;

import com.adampach.donkeykong.abstraction.KeyboardObserver;
import com.adampach.donkeykong.abstraction.Provider;
import com.adampach.donkeykong.enums.DirectionEnums;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

import static com.adampach.donkeykong.statics.KeyboardMapping.*;

public class VerticalDirectionProvider implements KeyboardObserver, Provider<DirectionEnums.VerticalDirection> {

    private ArrayList<DirectionEnums.VerticalDirection> pressedDirections;

    public VerticalDirectionProvider()
    {
        pressedDirections = new ArrayList<>();
    }

    @Override
    public void notifyObserver(boolean keyPressed, KeyCode keyCode)
    {
        DirectionEnums.KeyEventType movementType = movementTypeMapping.get(keyCode.getChar());
        if(movementType != DirectionEnums.KeyEventType.VerticalMovement)
            return;

        DirectionEnums.VerticalDirection mappedType = verticalMapping.get(keyCode);
        if(keyPressed)
        {
            if(pressedDirections.stream().noneMatch( e -> e == mappedType))
                pressedDirections.add(mappedType);
        }
        else
            pressedDirections.remove(mappedType);
    }

    @Override
    public DirectionEnums.VerticalDirection provide() {
        if(pressedDirections.isEmpty())
            return DirectionEnums.VerticalDirection.None;
        return pressedDirections.get(pressedDirections.size() - 1);
    }
}
