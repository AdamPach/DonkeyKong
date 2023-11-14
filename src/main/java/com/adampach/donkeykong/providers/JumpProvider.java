package com.adampach.donkeykong.providers;

import com.adampach.donkeykong.abstraction.Observer;
import com.adampach.donkeykong.abstraction.Provider;
import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.wrappers.KeyboardDataWrapper;
import javafx.scene.input.KeyCode;

import static com.adampach.donkeykong.statics.KeyboardMapping.movementTypeMapping;

public class JumpProvider implements Observer<KeyboardDataWrapper>, Provider<Boolean>
{
    private boolean requestedJump = false;
    private boolean doneJump = false;

    @Override
    public Boolean provide()
    {
        boolean result = requestedJump && !doneJump;
        if(requestedJump)
            doneJump = true;
        return result;
    }

    @Override
    public void notifyObserver(KeyboardDataWrapper data) {
        DirectionEnums.KeyEventType movementType = movementTypeMapping.get(data.keyCode().getChar());
        if(movementType != DirectionEnums.KeyEventType.Jump)
            return;

        if(data.keyPressed())
            requestedJump = true;
        else
        {
            requestedJump = false;
            doneJump = false;
        }
    }
}
