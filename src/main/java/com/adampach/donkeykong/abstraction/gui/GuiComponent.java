package com.adampach.donkeykong.abstraction.gui;

import javafx.scene.canvas.Canvas;

public interface GuiComponent
{
     public abstract void display(Canvas canvas);
     public abstract void simulate();
}
