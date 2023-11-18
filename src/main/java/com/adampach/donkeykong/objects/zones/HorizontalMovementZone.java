package com.adampach.donkeykong.objects.zones;

import com.adampach.donkeykong.abstraction.game.Zone;
import com.adampach.donkeykong.enums.DirectionEnums;

public class HorizontalMovementZone extends Zone<DirectionEnums.HorizontalDirection>
{
    private final DirectionEnums.HorizontalDirection newDirection;

    public HorizontalMovementZone(int positionX, int positionY, int width, int height, DirectionEnums.HorizontalDirection newDirection) {
        super(positionX, positionY, width, height);
        this.newDirection = newDirection;
    }

    @Override
    public DirectionEnums.HorizontalDirection provide() {
        return newDirection;
    }
}
