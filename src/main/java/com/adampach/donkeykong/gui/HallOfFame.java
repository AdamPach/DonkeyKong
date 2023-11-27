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

import static com.adampach.donkeykong.assets.FontAssets.Arcade26;
import static com.adampach.donkeykong.assets.FontAssets.Arcade72;

public class HallOfFame implements InteractableGuiComponent
{
    private final Label MainText;
    private final Button Home;

    public HallOfFame(ButtonEventsSubjectsWrapper subjectsWrapper, GameInfo gameInfo)
    {
        MainText = new Label("Hall of fame");
        MainText.setPrefWidth(500);
        MainText.setAlignment(Pos.CENTER);
        MainText.setLayoutX(150);
        MainText.setLayoutY(100);
        MainText.setFont(Arcade72);

        Home = new Button("HOME");
        Home.setLayoutX(250);
        Home.setPrefWidth(200);
        Home.setLayoutY(250);
        Home.setFont(Arcade26);
        Home.setOnAction(subjectsWrapper.getHomePageHandler());
    }

    @Override
    public void display(Canvas canvas)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Paint paint = gc.getFill();
        gc.setFill(Color.DARKGREEN);

        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(paint);
    }

    @Override
    public void simulate() {

    }

    @Override
    public void showComponents(AnchorPane anchorPane)
    {
        MainText.setLayoutX(anchorPane.getWidth() / 2 - MainText.getPrefWidth() / 2);
        Home.setLayoutX(anchorPane.getWidth() / 2 - Home.getPrefWidth() / 2);

        anchorPane.getChildren().addAll(MainText, Home);
    }

    @Override
    public void clearComponents(AnchorPane anchorPane)
    {
        anchorPane.getChildren().removeAll(MainText, Home);
    }
}
