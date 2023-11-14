package com.adampach.donkeykong.wrappers;

import com.adampach.donkeykong.abstraction.Provider;
import com.adampach.donkeykong.enums.DirectionEnums;

public class MovementProviderWrapper
{
    public final Provider<DirectionEnums.HorizontalDirection> horizontalProvider;
    public final Provider<DirectionEnums.VerticalDirection> verticalPositionProvider;
    public final Provider<Boolean> jumpProvider;

    public MovementProviderWrapper(
            Provider<DirectionEnums.HorizontalDirection> horizontalProvider,
            Provider<DirectionEnums.VerticalDirection> verticalPositionProvider,
            Provider<Boolean> jumpProvider)
    {
        this.horizontalProvider = horizontalProvider;
        this.verticalPositionProvider = verticalPositionProvider;
        this.jumpProvider = jumpProvider;
    }

}
