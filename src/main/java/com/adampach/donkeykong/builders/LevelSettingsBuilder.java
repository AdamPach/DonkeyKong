package com.adampach.donkeykong.builders;

import com.adampach.donkeykong.abstraction.Builder;
import com.adampach.donkeykong.abstraction.Provider;
import com.adampach.donkeykong.data.LevelSettings;
import com.adampach.donkeykong.geometry.Rectangle;
import com.adampach.donkeykong.providers.RandomIntervalGeneratorProvider;

public class LevelSettingsBuilder implements Builder<LevelSettings>
{
    private Provider<Boolean> barrelGenerationProvider;
    private int maxAvailableScore;
    private int cyclesToDecreaseScore;
    private int decreaseAtOnce;
    private int playerAttempts;
    private Rectangle defaultPlayerSize;
    private Rectangle defaultBarrelSize;
    private int defaultJumpGravity;
    private int defaultMaxGravityIndex;
    private int defaultMovementSpeed;
    private int defaultClimbingSpeed;
    private final int levelWidth;
    private final int levelHeight;

    public LevelSettingsBuilder addGenerationProvider(Provider<Boolean> provider)
    {
        barrelGenerationProvider = provider;

        return this;
    }

    public LevelSettingsBuilder addMaxAvailableScore(int score)
    {
        maxAvailableScore = score;

        return this;
    }

    public LevelSettingsBuilder addCyclesToDecrease(int cycles)
    {
        cyclesToDecreaseScore = cycles;

        return this;
    }

    public LevelSettingsBuilder addDecreaseAtOnce(int decrease)
    {
        decreaseAtOnce = decrease;

        return this;
    }

    public LevelSettingsBuilder addPlayerLives(int lives)
    {
        playerAttempts = lives;

        return this;
    }

    public LevelSettingsBuilder addDefaultBarrelSize(Rectangle defaultBarrelSize)
    {
        this.defaultBarrelSize = defaultBarrelSize;

        return this;
    }

    public LevelSettingsBuilder addDefaultPlayerSize(Rectangle defaultPlayerSize)
    {
        this.defaultPlayerSize = defaultPlayerSize;

        return this;
    }
    public LevelSettingsBuilder addJumpGravity(int gravity)
    {
        this.defaultJumpGravity = gravity;

        return this;
    }

    public LevelSettingsBuilder addClimbingSpeed(int climbingSpeed)
    {
        this.defaultClimbingSpeed = climbingSpeed;

        return this;
    }

    public LevelSettingsBuilder addMaxGravityIndex(int maxGravityIndex)
    {
        this.defaultMaxGravityIndex = maxGravityIndex;

        return this;
    }

    public LevelSettingsBuilder addMovementSpeed(int movementSpeed)
    {
        this.defaultMovementSpeed = movementSpeed;

        return this;
    }

    public static LevelSettingsBuilder CreateBuilder(int levelWidth, int levelHeight)
    {
        return new LevelSettingsBuilder(levelWidth, levelHeight);
    }

    private LevelSettingsBuilder(int levelWidth, int levelHeight)
    {
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        barrelGenerationProvider = new RandomIntervalGeneratorProvider(1,25);
        maxAvailableScore = 1;
        cyclesToDecreaseScore = 1;
        decreaseAtOnce = 1;
        playerAttempts = 1;

        defaultJumpGravity = 0;
        defaultClimbingSpeed = 0;
        defaultMaxGravityIndex = 0;
        defaultMovementSpeed = 0;

        defaultPlayerSize = null;
        defaultBarrelSize = null;
    }

    @Override
    public LevelSettings build() {

        if(defaultPlayerSize == null)
            throw new RuntimeException("You haven't defined player size");

        if(defaultBarrelSize == null)
            throw new RuntimeException("You haven't defined barrel size");

        return new LevelSettings(
                defaultJumpGravity,
                defaultMaxGravityIndex,
                defaultMovementSpeed,
                defaultClimbingSpeed,
                levelWidth,
                levelHeight,
                barrelGenerationProvider,
                maxAvailableScore,
                cyclesToDecreaseScore,
                decreaseAtOnce,
                playerAttempts,
                defaultPlayerSize,
                defaultBarrelSize
        );
    }
}
