package com.adampach.donkeykong.assets;

import javafx.scene.text.Font;

public class FontAssets
{
    private FontAssets(){}

    public static final Font Arcade26;
    public static final Font Arcade36;
    public static final Font Arcade72;

    static
    {
        Arcade26 = Font.loadFont(FontAssets.class.getResourceAsStream("arcade.ttf"), 26);
        Arcade36 = Font.loadFont(FontAssets.class.getResourceAsStream("arcade.ttf"), 36);
        Arcade72 = Font.loadFont(FontAssets.class.getResourceAsStream("arcade.ttf"), 72);
    }
}