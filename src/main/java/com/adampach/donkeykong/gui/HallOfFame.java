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
import javafx.scene.text.TextAlignment;
import javafx.util.Pair;

import static com.adampach.donkeykong.assets.FontAssets.Arcade26;
import static com.adampach.donkeykong.assets.FontAssets.Arcade72;

public class HallOfFame implements InteractableGuiComponent
{
    private final Label MainText;
    private final Button Home;
    private final Label Text;
    private final GameInfo gameInfo;

    public HallOfFame(ButtonEventsSubjectsWrapper subjectsWrapper, GameInfo gameInfo)
    {
        this.gameInfo = gameInfo;

        MainText = new Label("Hall of fame");
        MainText.setPrefWidth(500);
        MainText.setAlignment(Pos.CENTER);
        MainText.setLayoutX(150);
        MainText.setLayoutY(100);
        MainText.setFont(Arcade72);

        Home = new Button("HOME");
        Home.setLayoutX(500);
        Home.setPrefWidth(200);
        Home.setLayoutY(500);
        Home.setFont(Arcade26);
        Home.setOnAction(e ->
        {
            subjectsWrapper
                    .getGuiEventHandler()
                    .handle(GameEventEnums.GameEvents.HomePage);
        });

        Text = new Label("");
        Text.setPrefWidth(500);
        Text.setAlignment(Pos.CENTER);
        Text.setTextAlignment(TextAlignment.CENTER);
        Text.setLayoutX(200);
        Text.setLayoutY(200);
        Text.setFont(Arcade26);
    }

    @Override
    public void display(Canvas canvas)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Paint paint = gc.getFill();
        gc.setFill(Color.ORANGE);

        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(paint);
    }

    @Override
    public void simulate() {}

    @Override
    public void showComponents(AnchorPane anchorPane)
    {
        MainText.setLayoutX(anchorPane.getWidth() / 2 - MainText.getPrefWidth() / 2);
        Home.setLayoutX(anchorPane.getWidth() / 2 - Home.getPrefWidth() / 2);
        Text.setLayoutX(anchorPane.getWidth() / 2 - Text.getPrefWidth() / 2);

        StringBuilder sb = new StringBuilder();
        int index = 1;
        for(Pair<String, Integer> info : gameInfo.getTopPlayers(5))
        {
            sb.append(index++)
                    .append(" ")
                    .append(info.getKey())
                    .append(" ")
                    .append(info.getValue())
                    .append('\n');
        }

        Text.setText(sb.toString());

        anchorPane.getChildren().addAll(MainText, Home, Text);
    }

    @Override
    public void clearComponents(AnchorPane anchorPane)
    {
        anchorPane.getChildren().removeAll(MainText, Home, Text);
    }
}
