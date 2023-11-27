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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static com.adampach.donkeykong.assets.FontAssets.*;

public class MainMenu implements InteractableGuiComponent {

    private final Button playGameButton;
    private final Button setNameButton;
    private final Button hallOfFameButton;
    private final Label gameName;
    private final Label playerName;
    private final GameInfo gameInfo;

    public MainMenu(ButtonEventsSubjectsWrapper subjectsWrapper, GameInfo gameInfo) {
        this.gameInfo = gameInfo;
        playGameButton = new Button("Play Game");

        playGameButton.setLayoutX(250);
        playGameButton.setLayoutY(250);
        playGameButton.setFont(Arcade26);
        playGameButton.setPrefWidth(200);
        playGameButton.setOnAction(e ->
        {
            subjectsWrapper
                    .getGuiEventHandler()
                    .handle(GameEventEnums.GameEvents.PlayGame);
        });

        setNameButton = new Button("Set your name");
        setNameButton.setLayoutX(250);
        setNameButton.setLayoutY(300);
        setNameButton.setFont(Arcade26);
        setNameButton.setPrefWidth(200);
        setNameButton.setOnAction(e ->
        {
            subjectsWrapper
                    .getGuiEventHandler()
                    .handle(GameEventEnums.GameEvents.SetName);
        });

        hallOfFameButton = new Button("Hall of fame");

        hallOfFameButton.setLayoutX(250);
        hallOfFameButton.setLayoutY(350);
        hallOfFameButton.setFont(Arcade26);
        hallOfFameButton.setPrefWidth(200);
        hallOfFameButton.setOnAction( e ->
        {
            subjectsWrapper
                    .getGuiEventHandler()
                    .handle(GameEventEnums.GameEvents.HallOfFame);
        });

        gameName = new Label("Donkey Kong");
        gameName.setLayoutX(150);
        gameName.setLayoutY(100);
        gameName.setPrefWidth(400);
        gameName.setAlignment(Pos.CENTER);
        gameName.setFont(Arcade72);

        playerName = new Label();
        playerName.setLayoutX(100);
        playerName.setLayoutY(50);
        playerName.setPrefWidth(400);
        playerName.setAlignment(Pos.CENTER);
        playerName.setFont(Arcade26);
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
        if(gameInfo.getUserName() != "")
        {
            playerName.setText("Player " + gameInfo.getUserName());
        }
        else
        {
            playerName.setText("");
        }
    }

    @Override
    public void showComponents(AnchorPane anchorPane)
    {
        setNameButton.setLayoutX(anchorPane.getWidth() / 2 - setNameButton.getPrefWidth() / 2);
        playGameButton.setLayoutX(anchorPane.getWidth() / 2 - playGameButton.getPrefWidth() / 2);
        gameName.setLayoutX(anchorPane.getWidth() / 2 - gameName.getPrefWidth() / 2);
        playerName.setLayoutX(anchorPane.getWidth() / 2 - playerName.getPrefWidth() / 2);
        hallOfFameButton.setLayoutX(anchorPane.getWidth() / 2 - hallOfFameButton.getPrefWidth() / 2);

        anchorPane.getChildren().add(playGameButton);
        anchorPane.getChildren().add(setNameButton);
        anchorPane.getChildren().add(gameName);
        anchorPane.getChildren().add(playerName);
        anchorPane.getChildren().add(hallOfFameButton);

    }

    @Override
    public void clearComponents(AnchorPane anchorPane)
    {
        anchorPane.getChildren().remove(playGameButton);
        anchorPane.getChildren().remove(setNameButton);
        anchorPane.getChildren().remove(gameName);
        anchorPane.getChildren().remove(playerName);
        anchorPane.getChildren().remove(hallOfFameButton);

    }
}