package com.adampach.donkeykong.gui;

import com.adampach.donkeykong.abstraction.gui.InteractableGuiComponent;
import com.adampach.donkeykong.data.GameInfo;
import com.adampach.donkeykong.wrappers.ButtonEventsSubjectsWrapper;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

import static com.adampach.donkeykong.assets.FontAssets.*;

public class PickLevel implements InteractableGuiComponent {

    public final ArrayList<Button> levelButtons;

    public final Label selectText;

    private final GameInfo gameInfo;

    private final Button backButton;

    private final Label scoreBoard;

    public PickLevel(ButtonEventsSubjectsWrapper subjectsWrapper, GameInfo gameInfo)
    {
        this.gameInfo = gameInfo;
        levelButtons = new ArrayList<>();

        for(int i = 0; i < gameInfo.getNumberOfLevels(); i++)
        {
            Button newBtn = new Button(i + 1 + "");

            newBtn.setLayoutY(200 + 50 * i);
            newBtn.setPrefWidth(200);
            newBtn.setFont(Arcade26);
            int finalI = i;
            newBtn.setOnAction(e ->
            {
                gameInfo.setCurrentLevel(finalI + 1);
                subjectsWrapper.getStartLevelHandler().handle(e);
            });
            levelButtons.add(newBtn);
        }

        selectText = new Label("Select Level");
        selectText.setPrefWidth(500);
        selectText.setAlignment(Pos.CENTER);
        selectText.setLayoutX(150);
        selectText.setLayoutY(100);
        selectText.setFont(Arcade72);

        backButton = new Button("Menu");
        backButton.setLayoutY(200 + 50 * levelButtons.size());
        backButton.setPrefWidth(200);
        backButton.setFont(Arcade26);
        backButton.setOnAction(subjectsWrapper.getHomePageHandler());

        scoreBoard = new Label();
        scoreBoard.setLayoutX(100);
        scoreBoard.setLayoutY(50);
        scoreBoard.setPrefWidth(400);
        scoreBoard.setAlignment(Pos.CENTER);
        scoreBoard.setFont(Arcade36);
    }

    @Override
    public void display(Canvas canvas)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Paint paint = gc.getFill();
        gc.setFill(Color.LIMEGREEN);

        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(paint);
    }

    @Override
    public void simulate() {

    }

    @Override
    public void showComponents(AnchorPane anchorPane)
    {
        selectText.setLayoutX(anchorPane.getWidth() / 2 - selectText.getPrefWidth() / 2);
        backButton.setLayoutX(anchorPane.getWidth() / 2 - backButton.getPrefWidth() / 2);
        scoreBoard.setLayoutX(anchorPane.getWidth() / 2 - scoreBoard.getPrefWidth() / 2);

        scoreBoard.setText(gameInfo.getCurrentPlayerScoreSum() +"");

        anchorPane.getChildren().addAll(selectText, backButton, scoreBoard);

        for(int i = 0; i < levelButtons.size(); i++)
        {
            Button b = levelButtons.get(i);
            b.setDisable(i > gameInfo.getCurrentPlayerLevels());
            b.setLayoutX(anchorPane.getWidth() / 2 - b.getPrefWidth() / 2);
            anchorPane.getChildren().add(b);
        }
    }

    @Override
    public void clearComponents(AnchorPane anchorPane)
    {
        anchorPane.getChildren().removeAll(selectText, backButton, scoreBoard);
        levelButtons.forEach( e ->
                anchorPane.getChildren().remove(e)
        );
    }
}
