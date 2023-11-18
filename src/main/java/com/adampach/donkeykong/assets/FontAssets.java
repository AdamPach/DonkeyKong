package com.adampach.donkeykong.assets;

import javafx.scene.text.Font;

public class FontAssets
{
    private FontAssets(){}

    public static final Font Arcade24;
    public static final Font Arcade72;

    static
    {
        Arcade24 = Font.loadFont(FontAssets.class.getResourceAsStream("arcade.ttf"), 24);
        Arcade72 = Font.loadFont(FontAssets.class.getResourceAsStream("arcade.ttf"), 72);
    }
}