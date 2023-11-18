package com.adampach.donkeykong.gui;

import com.adampach.donkeykong.abstraction.gui.InteractableGuiComponent;
import com.adampach.donkeykong.wrappers.ButtonEventsSubjectsWrapper;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static com.adampach.donkeykong.assets.FontAssets.*;

public class MainMenu implements InteractableGuiComponent {

    private final Button playGameButton;
    private final Button setNameButton;
    private final Label gameName;

    public MainMenu(ButtonEventsSubjectsWrapper subjectsWrapper) {
        playGameButton = new Button("Play Game");

        playGameButton.setLayoutX(250);
        playGameButton.setLayoutY(250);
        playGameButton.setFont(Arcade24);

        playGameButton.setOnAction(subjectsWrapper.getPlayGameHandler());

        setNameButton = new Button("Set your name");

        setNameButton.setLayoutX(250);
        setNameButton.setLayoutY(300);
        setNameButton.setFont(Arcade24);
        setNameButton.setOnAction(subjectsWrapper.getSetNameHandler());

        gameName = new Label("Donkey Kong");
        gameName.setLayoutX(150);
        gameName.setLayoutY(100);
        gameName.setFont(Arcade72);
    }

    @Override
    public void display(Canvas canvas)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Paint paint = gc.getFill();
        gc.setFill(Color.LIGHTSKYBLUE);

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
        anchorPane.getChildren().add(playGameButton);
        anchorPane.getChildren().add(setNameButton);
        anchorPane.getChildren().add(gameName);
    }

    @Override
    public void clearComponents(AnchorPane anchorPane)
    {
        anchorPane.getChildren().remove(playGameButton);
        anchorPane.getChildren().remove(setNameButton);
        anchorPane.getChildren().remove(gameName);
    }
}