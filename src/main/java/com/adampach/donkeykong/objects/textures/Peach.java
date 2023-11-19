package com.adampach.donkeykong.objects.textures;

import com.adampach.donkeykong.abstraction.game.TextureObject;
import javafx.scene.canvas.GraphicsContext;

import static com.adampach.donkeykong.assets.ImageAssets.*;

public class Peach extends TextureObject
{
    private final boolean OnLeft;

    public Peach(int positionX, int positionY, int width, int height, boolean onLeft)
    {
        super(positionX, positionY, width, height);
        OnLeft = onLeft;
    }

    @Override
    public void draw(GraphicsContext gc)
    {
        if(OnLeft)
            gc.drawImage(PEACH_LEFT, getPositionX(), getPositionY(), getWidth(), getHeight());
        else
            gc.drawImage(PEACH_RIGHT, getPositionX(), getPositionY(), getWidth(), getHeight());
    }
}
