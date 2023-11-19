package com.adampach.donkeykong.data;

import com.adampach.donkeykong.abstraction.Provider;
import com.adampach.donkeykong.geometry.Rectangle;

public class LevelSettings
{
    private final int defaultJumpGravity;
    private final int defaultMaxGravityIndex;
    private final int defaultMovementSpeed;
    private final int defaultClimbingSpeed;
    private final int levelWidth;
    private final int levelHeight;
    private final Provider<Boolean> barrelGenerationProvider;
    private final int maxAvailableScore;
    private final int cyclesToDecreaseScore;
    private final int decreaseAtOnce;
    private final int playerAttempts;
    private final Rectangle defaultPlayerSize;
    private final Rectangle defaultBarrelSize;
    public LevelSettings(
            int defaultJumpGravity,
            int defaultMaxGravityIndex,
            int defaultMovementSpeed,
            int defaultClimbingSpeed,
            int levelWidth,
            int levelHeight,
            Provider<Boolean> barrelGenerationProvider,
            int maxAvailableScore,
            int cyclesToDecreaseScore,
            int decreaseAtOnce,
            int playerAttempts,
            Rectangle defaultPlayerSize,
            Rectangle defaultBarrelSize)
    {
        this.defaultJumpGravity = defaultJumpGravity;
        this.defaultMaxGravityIndex = defaultMaxGravityIndex;
        this.defaultMovementSpeed = defaultMovementSpeed;
        this.defaultClimbingSpeed = defaultClimbingSpeed;

        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        this.barrelGenerationProvider = barrelGenerationProvider;
        this.maxAvailableScore = maxAvailableScore;
        this.cyclesToDecreaseScore = cyclesToDecreaseScore;
        this.decreaseAtOnce = decreaseAtOnce;
        this.playerAttempts = playerAttempts;
        this.defaultPlayerSize = defaultPlayerSize;
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

    public Provider<Boolean> getBarrelGenerationProvider() {
        return barrelGenerationProvider;
    }

    public int getMaxAvailableScore() {
        return maxAvailableScore;
    }

    public int getCyclesToDecreaseScore() {
        return cyclesToDecreaseScore;
    }

    public int getDecreaseAtOnce() {
        return decreaseAtOnce;
    }

    public int getPlayerAttempts() {
        return playerAttempts;
    }

    public Rectangle getDefaultPlayerSize() {
        return defaultPlayerSize;
    }
}
