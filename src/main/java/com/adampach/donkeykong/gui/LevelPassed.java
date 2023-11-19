package com.adampach.donkeykong.gui;

import com.adampach.donkeykong.abstraction.gui.InteractableGuiComponent;
import com.adampach.donkeykong.wrappers.ButtonEventsSubjectsWrapper;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static com.adampach.donkeykong.assets.FontAssets.Arcade24;
import static com.adampach.donkeykong.assets.FontAssets.Arcade72;

public class LevelPassed implements InteractableGuiComponent {

    private final Label youPassedLevelText;

    private final Button menuButton;

    public LevelPassed(ButtonEventsSubjectsWrapper subjectsWrapper)
    {
        youPassedLevelText = new Label("Level Passed");
        youPassedLevelText.setPrefWidth(500);
        youPassedLevelText.setAlignment(Pos.CENTER);
        youPassedLevelText.setLayoutX(150);
        youPassedLevelText.setLayoutY(100);
        youPassedLevelText.setFont(Arcade72);

        menuButton = new Button("Back to the menu");
        menuButton.setLayoutY(200);
        menuButton.setPrefWidth(250);
        menuButton.setFont(Arcade24);
        menuButton.setOnAction(subjectsWrapper.getHomePageHandler());
    }

    @Override
    public void display(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Paint paint = gc.getFill();
        gc.setFill(Color.YELLOW);

        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(paint);
    }

    @Override
    public void simulate() {

    }

    @Override
    public void showComponents(AnchorPane anchorPane) {
        menuButton.setLayoutX(anchorPane.getWidth() / 2 - menuButton.getPrefWidth() / 2);
        youPassedLevelText.setLayoutX(anchorPane.getWidth() / 2 - youPassedLevelText.getPrefWidth() / 2);

        anchorPane.getChildren().addAll(menuButton, youPassedLevelText);
    }

    @Override
    public void clearComponents(AnchorPane anchorPane) {
        anchorPane.getChildren().removeAll(menuButton, youPassedLevelText);
    }
}
