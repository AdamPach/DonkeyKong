package com.adampach.donkeykong.objects.textures;

import com.adampach.donkeykong.abstraction.game.TextureObject;
import com.adampach.donkeykong.data.LevelSettings;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LevelBorders extends TextureObject
{

    public LevelBorders(LevelSettings settings) {
        super(0, 0, settings.getLevelWidth(), settings.getLevelHeight());
    }

    @Override
    public boolean intersect(Rectangle2D rectangle) {
        return rectangle.getMaxX() >= getWidth()
                || rectangle.getMinX() <= 0;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(getPositionX(), getPositionY(), getWidth(), getHeight());
    }
}
