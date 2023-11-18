package com.adampach.donkeykong.objects.zones;

import com.adampach.donkeykong.abstraction.game.Zone;

public class DestroyBarrelZone extends Zone<Boolean> {
    public DestroyBarrelZone(int positionX, int positionY, int width, int height) {
        super(positionX, positionY, width, height);
    }

    @Override
    public Boolean provide() {
        return true;
    }
}
