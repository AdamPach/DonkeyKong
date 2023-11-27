package com.adampach.donkeykong.gui;

import com.adampach.donkeykong.abstraction.gui.InteractableGuiComponent;
import com.adampach.donkeykong.data.GameInfo;
import com.adampach.donkeykong.enums.GameEventEnums;
import com.adampach.donkeykong.wrappers.ButtonEventsSubjectsWrapper;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static com.adampach.donkeykong.assets.FontAssets.Arcade26;
import static com.adampach.donkeykong.assets.FontAssets.Arcade72;

public class EnterName implements InteractableGuiComponent
{
    private final TextField nameFiled;
    private final Button homeButton;
    private final Button saveNameButton;
    private final Label setNameText;

    public EnterName(ButtonEventsSubjectsWrapper subjectsWrapper, GameInfo gameInfo)
    {
        this.nameFiled = new TextField();

        this.nameFiled.setFont(Arcade26);
        this.nameFiled.setLayoutX(250);
        this.nameFiled.setLayoutY(200);
        this.nameFiled.setPrefWidth(200);
        this.nameFiled.setPrefHeight(30);

        this.saveNameButton = new Button("Save name");
        this.saveNameButton.setLayoutX(250);
        this.saveNameButton.setPrefWidth(200);
        this.saveNameButton.setLayoutY(250);
        this.saveNameButton.setFont(Arcade26);
        this.saveNameButton.setOnAction( e -> {
            gameInfo.setUserName(nameFiled.getText());
            subjectsWrapper
                    .getGuiEventHandler()
                    .handle(GameEventEnums.GameEvents.HomePage);
        });

        this.homeButton = new Button("Back home");
        this.homeButton.setLayoutX(250);
        this.homeButton.setPrefWidth(200);
        this.homeButton.setLayoutY(300);
        this.homeButton.setFont(Arcade26);
        this.homeButton.setOnAction(e ->
        {
            subjectsWrapper
                    .getGuiEventHandler()
                    .handle(GameEventEnums.GameEvents.HomePage);
        });

        setNameText = new Label("Set your name");
        setNameText.setPrefWidth(500);
        setNameText.setAlignment(Pos.CENTER);
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

    }

    @Override
    public void showComponents(AnchorPane anchorPane)
    {
        nameFiled.setLayoutX(anchorPane.getWidth() / 2 - nameFiled.getPrefWidth() / 2);
        homeButton.setLayoutX(anchorPane.getWidth() / 2 - homeButton.getPrefWidth() / 2);
        setNameText.setLayoutX(anchorPane.getWidth() / 2 - setNameText.getPrefWidth() / 2);
        saveNameButton.setLayoutX(anchorPane.getWidth() / 2 - saveNameButton.getPrefWidth() / 2);

        anchorPane.getChildren().addAll(nameFiled, homeButton, setNameText, saveNameButton);
    }

    @Override
    public void clearComponents(AnchorPane anchorPane)
    {
        anchorPane.getChildren().removeAll(nameFiled, homeButton, setNameText, saveNameButton);
    }
}
