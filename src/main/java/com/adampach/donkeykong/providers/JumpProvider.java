package com.adampach.donkeykong.providers;

import com.adampach.donkeykong.abstraction.KeyboardObserver;
import com.adampach.donkeykong.abstraction.Provider;
import com.adampach.donkeykong.enums.DirectionEnums;
import javafx.scene.input.KeyCode;

import static com.adampach.donkeykong.statics.KeyboardMapping.movementTypeMapping;

public class JumpProvider implements KeyboardObserver, Provider<Boolean>
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
    public void notifyObserver(boolean keyPressed, KeyCode keyCode)
    {
        DirectionEnums.KeyEventType movementType = movementTypeMapping.get(keyCode.getChar());
        if(movementType != DirectionEnums.KeyEventType.Jump)
            return;

        if(keyPressed)
            requestedJump = true;
        else
        {
            requestedJump = false;
            doneJump = false;
        }
    }
}
