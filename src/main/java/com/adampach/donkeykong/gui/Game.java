package com.adampach.donkeykong.gui;

import com.adampach.donkeykong.abstraction.gui.ILevel;
import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.world.Level;
import com.adampach.donkeykong.world.LevelSettings;
import com.adampach.donkeykong.wrappers.MovementProviderWrapper;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class Game
{
    private final Canvas canvas;

    private final ArrayList<ILevel> levels;

    private final MovementProviderWrapper movementProviderWrapper;

    private long lastTime;


    public Game(
            Canvas canvas,
            MovementProviderWrapper movementProviderWrapper)
    {
        this.canvas = canvas;
        this.movementProviderWrapper = movementProviderWrapper;
        levels = new ArrayList<>();
        lastTime = -1;

        LevelSettings settings = new LevelSettings(
                -12,
                4,
                3,
                2,
                (int)canvas.getWidth(),
                (int)canvas.getHeight(),
                DirectionEnums.HorizontalDirection.Right);

        levels.add(new Level(settings, movementProviderWrapper));
    }

    public void drawGame(long timeNow)
    {
        if (timeNow - lastTime > 15_000_000) {
            levels.get(0).simulate();
            levels.get(0).resetSimulationCycle();
            lastTime = timeNow;
        }
        levels.get(0).display(canvas);
    }
}
