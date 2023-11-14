package com.adampach.donkeykong.providers;

import com.adampach.donkeykong.abstraction.Observer;
import com.adampach.donkeykong.abstraction.Provider;
import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.wrappers.KeyboardDataWrapper;

import java.util.ArrayList;

import static com.adampach.donkeykong.statics.KeyboardMapping.horizontalMapping;
import static com.adampach.donkeykong.statics.KeyboardMapping.movementTypeMapping;

public class HorizontalDirectionProvider implements Observer<KeyboardDataWrapper>, Provider<DirectionEnums.HorizontalDirection> {
    private final ArrayList<DirectionEnums.HorizontalDirection> pressedDirections;

    public HorizontalDirectionProvider()
    {
        pressedDirections = new ArrayList<>();
    }

    @Override
    public void notifyObserver(KeyboardDataWrapper data)
    {
        DirectionEnums.KeyEventType movementType = movementTypeMapping.get(data.keyCode().getChar());
        if(movementType != DirectionEnums.KeyEventType.HorizontalMovement)
            return;

        DirectionEnums.HorizontalDirection mappedType = horizontalMapping.get(data.keyCode());
        if(data.keyPressed())
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
