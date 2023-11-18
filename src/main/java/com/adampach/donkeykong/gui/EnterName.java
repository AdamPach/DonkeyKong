package com.adampach.donkeykong.gui;

import com.adampach.donkeykong.abstraction.gui.InteractableGuiComponent;
import com.adampach.donkeykong.data.GameInfo;
import com.adampach.donkeykong.wrappers.ButtonEventsSubjectsWrapper;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static com.adampach.donkeykong.assets.FontAssets.Arcade24;
import static com.adampach.donkeykong.assets.FontAssets.Arcade72;

public class EnterName implements InteractableGuiComponent
{
    private final TextField nameFiled;
    private final Button homeButton;
    private final Label setNameText;
    private final GameInfo gameInfo;

    public EnterName(ButtonEventsSubjectsWrapper subjectsWrapper, GameInfo gameInfo)
    {
        this.gameInfo = gameInfo;
        this.nameFiled = new TextField();

        this.nameFiled.setFont(Arcade24);
        this.nameFiled.setLayoutX(250);
        this.nameFiled.setLayoutY(200);
        this.nameFiled.setPrefHeight(30);

        this.homeButton = new Button("Back home");
        this.homeButton.setLayoutX(250);
        this.homeButton.setLayoutY(250);
        this.homeButton.setFont(Arcade24);
        this.homeButton.setOnAction(subjectsWrapper.getHomePageHandler());

        setNameText = new Label("Set your name");
        setNameText.setLayoutX(150);
        setNameText.setLayoutY(100);
        setNameText.setFont(Arcade72);
    }

    @Override
    public void display(Canvas canvas)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Paint paint = gc.getFill();
        gc.setFill(Color.VIOLET);

        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(paint);
    }

    @Override
    public void simulate()
    {
        gameInfo.setUserName(nameFiled.getText());
    }

    @Override
    public void showComponents(AnchorPane anchorPane)
    {
        anchorPane.getChildren().addAll(nameFiled, homeButton, setNameText);
    }

    @Override
    public void clearComponents(AnchorPane anchorPane)
    {
        anchorPane.getChildren().removeAll(nameFiled, homeButton, setNameText);
    }
}
