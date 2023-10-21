package com.adampach.donkeykong.world;

import com.adampach.donkeykong.abstraction.Drawable;
import com.adampach.donkeykong.objects.Player;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Level implements Drawable {
    private final Player player;

    public Level()
    {
        player = new Player(new Point2D(300, 500), 50, 100);
    }

    @Override
    public void draw(GraphicsContext gc) {
        player.draw(gc);
    }
}
