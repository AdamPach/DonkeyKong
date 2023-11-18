package com.adampach.donkeykong.gui;

import com.adampach.donkeykong.abstraction.gui.InteractableGuiComponent;
import com.adampach.donkeykong.wrappers.ButtonEventsSubjectsWrapper;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static com.adampach.donkeykong.assets.FontAssets.Arcade24;

public class MainMenu implements InteractableGuiComponent {

    private final Button playGameButton;

    public MainMenu(ButtonEventsSubjectsWrapper subjectsWrapper) {
        playGameButton = new Button("Play Game");

        playGameButton.setLayoutX(300);
        playGameButton.setLayoutY(200);
        playGameButton.setFont(Arcade24);

        playGameButton.setOnAction(subjectsWrapper.getPlayGameHandler());
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
    }

    @Override
    public void clearComponents(AnchorPane anchorPane)
    {
        anchorPane.getChildren().remove(playGameButton);
    }
}