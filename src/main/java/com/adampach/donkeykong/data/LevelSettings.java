package com.adampach.donkeykong.data;

import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.geometry.Rectangle;

public class LevelSettings
{
    private final int defaultJumpGravity;
    private final int defaultMaxGravityIndex;
    private final int defaultMovementSpeed;
    private final int defaultClimbingSpeed;
    private final int levelWidth;
    private final int levelHeight;
    private final DirectionEnums.HorizontalDirection firstBarrelDirection;
    private final Rectangle defaultBarrelSize;
    public LevelSettings(
            int defaultJumpGravity,
            int defaultMaxGravityIndex,
            int defaultMovementSpeed,
            int defaultClimbingSpeed,
            int levelWidth,
            int levelHeight,
            DirectionEnums.HorizontalDirection firstBarrelDirection,
            Rectangle defaultBarrelSize) {
        this.defaultJumpGravity = defaultJumpGravity;
        this.defaultMaxGravityIndex = defaultMaxGravityIndex;
        this.defaultMovementSpeed = defaultMovementSpeed;
        this.defaultClimbingSpeed = defaultClimbingSpeed;

        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        this.firstBarrelDirection = firstBarrelDirection;
        this.defaultBarrelSize = defaultBarrelSize;
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

    public int getDefaultJumpGravity() {
        return defaultJumpGravity;
    }

    public Rectangle getDefaultBarrelSize() {
        return defaultBarrelSize;
    }
}
