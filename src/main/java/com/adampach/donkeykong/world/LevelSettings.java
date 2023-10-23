package com.adampach.donkeykong.world;

public class LevelSettings
{
    private final int defaultMaxGravityIndex;
    private final int defaultSpeed;

    public LevelSettings(int defaultMaxGravityIndex, int defaultSpeed) {
        this.defaultMaxGravityIndex = defaultMaxGravityIndex;
        this.defaultSpeed = defaultSpeed;
    }

    public int getDefaultMaxGravityIndex() {
        return defaultMaxGravityIndex;
    }

    public int getDefaultSpeed() {
        return defaultSpeed;
    }
}
