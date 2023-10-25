package com.adampach.donkeykong.world;

import javafx.scene.canvas.Canvas;

public class LevelSettings
{
    private final int defaultMaxGravityIndex;
    private final int defaultSpeed;
    private final int levelWidth;
    private final int levelHeight;

    public LevelSettings(int defaultMaxGravityIndex, int defaultSpeed, int levelWidth, int levelHeight) {
        this.defaultMaxGravityIndex = defaultMaxGravityIndex;
        this.defaultSpeed = defaultSpeed;

        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
    }

    public int getDefaultMaxGravityIndex() {
        return defaultMaxGravityIndex;
    }

    public int getDefaultSpeed() {
        return defaultSpeed;
    }

    public int getLevelWidth() {
        return levelWidth;
    }

    public int getLevelHeight() {
        return levelHeight;
    }
}
