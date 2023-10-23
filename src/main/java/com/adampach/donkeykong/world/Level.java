package com.adampach.donkeykong.world;

import com.adampach.donkeykong.abstraction.*;
import com.adampach.donkeykong.handlers.KeyboardHandler;
import com.adampach.donkeykong.objects.Construction;
import com.adampach.donkeykong.objects.Ladder;
import com.adampach.donkeykong.objects.Player;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Level implements Drawable, Simulable {
    private final Player player;
    private final ArrayList<TextureObject> textures;

    public Level()
    {
        player = new Player(25, 400, 25, 50);
        textures = new ArrayList<>();
        textures.add(new Construction(0, 575, 100, 25));
        textures.add(new Construction(100, 565, 100, 25));
        textures.add(new Construction(200, 555, 100, 25));
        textures.add(new Construction(300, 545, 100, 25));
        textures.add(new Construction(400, 535, 100, 25));
        textures.add(new Construction(500, 525, 100, 25));
        textures.add(new Construction(600, 515, 100, 25));
        textures.add(new Construction(700, 505, 100, 25));
        textures.add(new Ladder(750, 405, 25, 100));
    }

    public void registerPlayerToHandler(KeyboardHandler keyboardHandler)
    {
        keyboardHandler.registerObserver(player);
    }

    @Override
    public void draw(GraphicsContext gc) {
        for(Drawable construction : textures)
        {
            construction.draw(gc);
        }
        player.draw(gc);
    }

    @Override
    public void simulate()
    {
        for(Collisionable collisionable : textures)
        {
            player.handleCollision(collisionable);
        }
        player.simulate();
    }
}
