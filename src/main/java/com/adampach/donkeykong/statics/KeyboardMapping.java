package com.adampach.donkeykong.statics;

import com.adampach.donkeykong.enums.DirectionEnums;
import javafx.scene.input.KeyCode;

import java.util.HashMap;

public class KeyboardMapping
{
    public static final HashMap<String, DirectionEnums.Direction> directionMapping = new HashMap<>();
    public static final HashMap<String, DirectionEnums.HorizontalDirection> horizontalMapping = new HashMap<>();
    public static final HashMap<String, DirectionEnums.VerticalPosition> verticalMapping = new HashMap<>();

    static
    {
        directionMapping.put(KeyCode.A.getChar(), DirectionEnums.Direction.Horizontal);
        directionMapping.put(KeyCode.D.getChar(), DirectionEnums.Direction.Horizontal);
        directionMapping.put(KeyCode.S.getChar(), DirectionEnums.Direction.Vertical);
        directionMapping.put(KeyCode.W.getChar(), DirectionEnums.Direction.Vertical);
        directionMapping.put(KeyCode.SPACE.getChar(), DirectionEnums.Direction.Vertical);

        horizontalMapping.put(KeyCode.A.getChar(), DirectionEnums.HorizontalDirection.Left);
        horizontalMapping.put(KeyCode.D.getChar(), DirectionEnums.HorizontalDirection.Right);

        verticalMapping.put(KeyCode.S.getChar(), DirectionEnums.VerticalPosition.Down);
        verticalMapping.put(KeyCode.W.getChar(), DirectionEnums.VerticalPosition.Up);
        verticalMapping.put(KeyCode.SPACE.getChar(), DirectionEnums.VerticalPosition.Jump);
    }
}
