package com.adampach.donkeykong.statics;

import com.adampach.donkeykong.enums.DirectionEnums;
import javafx.scene.input.KeyCode;

import java.util.HashMap;

public class KeyboardMapping
{
    public static final HashMap<String, DirectionEnums.KeyEventType> movementTypeMapping = new HashMap<>();
    public static final HashMap<KeyCode, DirectionEnums.HorizontalDirection> horizontalMapping = new HashMap<>();
    public static final HashMap<KeyCode, DirectionEnums.VerticalDirection> verticalMapping = new HashMap<>();

    static
    {
        movementTypeMapping.put(KeyCode.A.getChar(), DirectionEnums.KeyEventType.HorizontalMovement);
        movementTypeMapping.put(KeyCode.D.getChar(), DirectionEnums.KeyEventType.HorizontalMovement);
        movementTypeMapping.put(KeyCode.S.getChar(), DirectionEnums.KeyEventType.VerticalMovement);
        movementTypeMapping.put(KeyCode.W.getChar(), DirectionEnums.KeyEventType.VerticalMovement);
        movementTypeMapping.put(KeyCode.SPACE.getChar(), DirectionEnums.KeyEventType.Jump);

        horizontalMapping.put(KeyCode.A, DirectionEnums.HorizontalDirection.Left);
        horizontalMapping.put(KeyCode.D, DirectionEnums.HorizontalDirection.Right);

        verticalMapping.put(KeyCode.S, DirectionEnums.VerticalDirection.Down);
        verticalMapping.put(KeyCode.W, DirectionEnums.VerticalDirection.Up);

    }
}
