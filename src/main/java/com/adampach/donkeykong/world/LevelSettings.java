package com.adampach.donkeykong.world;

import com.adampach.donkeykong.enums.DirectionEnums;

public class LevelSettings
{
    private final int defaultMaxGravityIndex;
    private final int defaultMovementSpeed;
    private final int defaultClimbingSpeed;
    private final int levelWidth;
    private final int levelHeight;
    private final DirectionEnums.HorizontalDirection firstBarrelDirection;

    public LevelSettings(int defaultMaxGravityIndex, int defaultMovementSpeed, int defaultClimbingSpeed, int levelWidth, int levelHeight, DirectionEnums.HorizontalDirection firstBarrelDirection) {
        this.defaultMaxGravityIndex = defaultMaxGravityIndex;
        this.defaultMovementSpeed = defaultMovementSpeed;
        this.defaultClimbingSpeed = defaultClimbingSpeed;

        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        this.firstBarrelDirection = firstBarrelDirection;
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

    public DirectionEnums.HorizontalDirection getFirstBarrelDirection() {
        return firstBarrelDirection;
    }
}
