package com.adampach.donkeykong.assets;

import javafx.scene.image.Image;

public class ImageAssets {
    private ImageAssets(){}

    public static final Image MARIO_WALK;
    public static final Image MARIO_JUMP;
    public static final Image MARIO_WALK_FLIP;
    public static final Image MARIO_JUMP_FLIP;
    public static final Image PEACH_RIGHT;
    public static final Image PEACH_LEFT;
    public static final Image DONKEY_KONG;
    public static final Image PLATFORM;
    public static final Image LADDER;
    public static final Image BARREL;
    static {
        MARIO_WALK = new Image(ImageAssets.class.getResourceAsStream("mario_walk.png"));
        MARIO_JUMP = new Image(ImageAssets.class.getResourceAsStream("mario_jump.png"));
        MARIO_WALK_FLIP = new Image(ImageAssets.class.getResourceAsStream("mario_walk_flip.png"));
        MARIO_JUMP_FLIP = new Image(ImageAssets.class.getResourceAsStream("mario_jump_flip.png"));

        PEACH_RIGHT = new Image(ImageAssets.class.getResourceAsStream("peach.png"));
        PEACH_LEFT = new Image(ImageAssets.class.getResourceAsStream("peach_rotated.png"));

        DONKEY_KONG = new Image(ImageAssets.class.getResourceAsStream("donkey_kong_1.png"));

        PLATFORM = new Image(ImageAssets.class.getResourceAsStream("platform_texture.png"));

        LADDER = new Image(ImageAssets.class.getResourceAsStream("ladder.png"));

        BARREL = new Image(ImageAssets.class.getResourceAsStream("barrel.gif"));
    }
}
