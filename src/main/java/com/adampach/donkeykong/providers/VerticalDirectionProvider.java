package com.adampach.donkeykong.providers;

import com.adampach.donkeykong.abstraction.Observer;
import com.adampach.donkeykong.abstraction.Provider;
import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.wrappers.KeyboardDataWrapper;

import java.util.ArrayList;

import static com.adampach.donkeykong.statics.KeyboardMapping.*;

public class VerticalDirectionProvider implements Observer<KeyboardDataWrapper>, Provider<DirectionEnums.VerticalDirection> {

    private final ArrayList<DirectionEnums.VerticalDirection> pressedDirections;

    public VerticalDirectionProvider()
    {
        pressedDirections = new ArrayList<>();
    }

    @Override
    public void notifyObserver(KeyboardDataWrapper data) {
        DirectionEnums.KeyEventType movementType = movementTypeMapping.get(data.keyCode().getChar());
        if(movementType != DirectionEnums.KeyEventType.VerticalMovement)
            return;

        DirectionEnums.VerticalDirection mappedType = verticalMapping.get(data.keyCode());
        if(data.keyPressed())
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
