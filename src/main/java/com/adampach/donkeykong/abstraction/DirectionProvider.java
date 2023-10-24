package com.adampach.donkeykong.abstraction;

import com.adampach.donkeykong.enums.DirectionEnums;

public interface DirectionProvider
{
    public DirectionEnums.HorizontalDirection provideHorizontalPosition();
    public DirectionEnums.VerticalPosition provideVerticalPosition();

}
