package com.adampach.donkeykong.abstraction.gui;


import javafx.scene.layout.AnchorPane;

public interface InteractableGuiComponent extends GuiComponent
{
    void showComponents(AnchorPane anchorPane);
    void clearComponents(AnchorPane anchorPane);
}
