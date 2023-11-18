package com.adampach.donkeykong.objects.zones;

import com.adampach.donkeykong.abstraction.game.Zone;
import com.adampach.donkeykong.enums.DirectionEnums;

public class VerticalMovementZone extends Zone<DirectionEnums.VerticalDirection>
{
    private final DirectionEnums.VerticalDirection newDirection;

    public VerticalMovementZone(int positionX, int positionY, int width, int height, DirectionEnums.VerticalDirection newDirection)
    {
        super(positionX, positionY, width, height);
        this.newDirection = newDirection;
    }

    @Override
    public DirectionEnums.VerticalDirection provide() {
        return newDirection;
    }
}
