package com.adampach.donkeykong.world;

public class LevelSettings
{
    private final int defaultMaxGravityIndex;
    private final int defaultMovementSpeed;
    private final int defaultClimbingSpeed;
    private final int levelWidth;
    private final int levelHeight;

    public LevelSettings(int defaultMaxGravityIndex, int defaultMovementSpeed, int defaultClimbingSpeed, int levelWidth, int levelHeight) {
        this.defaultMaxGravityIndex = defaultMaxGravityIndex;
        this.defaultMovementSpeed = defaultMovementSpeed;
        this.defaultClimbingSpeed = defaultClimbingSpeed;

        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
    }

    public int getDefaultMaxGravityIndex() {
        return defaultMaxGravityIndex;
    }

    public int getLevelWidth() {
        return levelWidth;
    }

    public int getLevelHeight() {
        return levelHeight;
    }

    public int getDefaultClimbingSpeed() {
        return defaultClimbingSpeed;
    }

    public int getDefaultMovementSpeed() {
        return defaultMovementSpeed;
    }
}
