package com.adampach.donkeykong.assets;

import javafx.scene.image.Image;

public class ImageAssets {
    private ImageAssets(){}

    public static final Image MARIO_WALK;
    public static final Image MARIO_JUMP;
    public static final Image MARIO_WALK_FLIP;
    public static final Image MARIO_JUMP_FLIP;
    static {
        MARIO_WALK = new Image(ImageAssets.class.getResourceAsStream("mario_walk.png"));
        MARIO_JUMP = new Image(ImageAssets.class.getResourceAsStream("mario_jump.png"));
        MARIO_WALK_FLIP = new Image(ImageAssets.class.getResourceAsStream("mario_walk_flip.png"));
        MARIO_JUMP_FLIP = new Image(ImageAssets.class.getResourceAsStream("mario_jump_flip.png"));
    }
}
