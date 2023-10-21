package com.adampach.donkeykong.world;

import com.adampach.donkeykong.abstraction.DrawableSimulable;
import com.adampach.donkeykong.handlers.KeyboardHandler;
import com.adampach.donkeykong.objects.Player;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Level implements DrawableSimulable {
    private final Player player;

    public Level()
    {
        player = new Player(new Point2D(300, 550), 25, 50);
    }

    public void registerPlayerToHandler(KeyboardHandler keyboardHandler)
    {
        keyboardHandler.registerObserver(player);
    }

    @Override
    public void draw(GraphicsContext gc) {
        player.draw(gc);
    }

    @Override
    public void simulate()
    {
        player.simulate();
    }
}
