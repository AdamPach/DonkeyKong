package com.adampach.donkeykong.world;

import com.adampach.donkeykong.abstraction.Collisionable;
import com.adampach.donkeykong.abstraction.Drawable;
import com.adampach.donkeykong.abstraction.GameObject;
import com.adampach.donkeykong.abstraction.Simulable;
import com.adampach.donkeykong.handlers.KeyboardHandler;
import com.adampach.donkeykong.objects.Construction;
import com.adampach.donkeykong.objects.Player;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Level implements Drawable, Simulable {
    private final Player player;
    private final ArrayList<GameObject> constructions;

    public Level()
    {
        player = new Player(25, 400, 25, 50);
        constructions = new ArrayList<>();
        constructions.add(new Construction(0, 575, 100, 25));
    }

    public void registerPlayerToHandler(KeyboardHandler keyboardHandler)
    {
        keyboardHandler.registerObserver(player);
    }

    @Override
    public void draw(GraphicsContext gc) {
        player.draw(gc);
        for(Drawable construction : constructions)
        {
            construction.draw(gc);
        }
    }

    @Override
    public void simulate()
    {
        player.simulate();
        for(Collisionable collisionable : constructions)
        {
            player.handleCollision(collisionable);
        }
    }
}
