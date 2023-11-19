package com.adampach.donkeykong.gui;

import com.adampach.donkeykong.abstraction.gui.GuiComponent;
import com.adampach.donkeykong.abstraction.gui.InteractableGuiComponent;
import com.adampach.donkeykong.builders.LevelBuilder;
import com.adampach.donkeykong.data.GameInfo;
import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.enums.GameEventEnums;
import com.adampach.donkeykong.geometry.Rectangle;
import com.adampach.donkeykong.handlers.LevelEventsHandler;
import com.adampach.donkeykong.providers.GameEventProvider;
import com.adampach.donkeykong.providers.LevelEventsObserverProvider;
import com.adampach.donkeykong.world.Level;
import com.adampach.donkeykong.data.LevelSettings;
import com.adampach.donkeykong.wrappers.ButtonEventsSubjectsWrapper;
import com.adampach.donkeykong.wrappers.MovementProviderWrapper;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Game
{
    private final Canvas canvas;

    private final AnchorPane anchorPane;

    private final ArrayList<LevelBuilder> levels;

    private final Dictionary<String, InteractableGuiComponent> guiComponent;

    private GuiComponent currentComponent;

    private long lastTime;

    private final ButtonEventsSubjectsWrapper buttonEventsSubjectsWrapper;

    private final GameInfo gameInfo;

    private final LevelEventsHandler levelEventsHandler;

    private final LevelEventsObserverProvider levelEventsObserverProvider;


    public Game(
            Canvas canvas,
            MovementProviderWrapper movementProviderWrapper,
            AnchorPane anchorPane)
    {
        this.canvas = canvas;
        this.anchorPane = anchorPane;
        this.levels = new ArrayList<>();
        this.lastTime = -1;
        this.guiComponent = new Hashtable<>();
        buttonEventsSubjectsWrapper = new ButtonEventsSubjectsWrapper();
        gameInfo = new GameInfo(1);

        levelEventsHandler = new LevelEventsHandler();
        levelEventsObserverProvider = new LevelEventsObserverProvider();

        levelEventsHandler.registerObserver(levelEventsObserverProvider);

        LevelSettings settings = new LevelSettings(
                -12,
                4,
                3,
                2,
                (int)canvas.getWidth(),
                (int)canvas.getHeight(),
                DirectionEnums.HorizontalDirection.Right,
                new Rectangle(20,20));

        guiComponent.put(MainMenu.class.getName(), new MainMenu(buttonEventsSubjectsWrapper, gameInfo));
        guiComponent.put(EnterName.class.getName(), new EnterName(buttonEventsSubjectsWrapper, gameInfo));

        levels.add(
                LevelBuilder.CreateBuilder(settings)
                        .addConstruction(new Rectangle2D(0, 580, 80, 20))
                        .addConstruction(new Rectangle2D(80, 575, 80, 20))
                        .addConstruction(new Rectangle2D(160, 570, 80, 20))
                        .addConstruction(new Rectangle2D(240, 565, 80, 20))
                        .addConstruction(new Rectangle2D(320, 560, 80, 20))
                        .addConstruction(new Rectangle2D(400, 555, 80, 20))
                        .addConstruction(new Rectangle2D(480, 550, 120, 20))
                        .addConstruction(new Rectangle2D(480, 460, 80, 20))
                        .addConstruction(new Rectangle2D(400, 455, 80, 20))
                        .addConstruction(new Rectangle2D(320, 450, 80, 20))
                        .addConstruction(new Rectangle2D(240, 445, 80, 20))
                        .addConstruction(new Rectangle2D(160, 440, 80, 20))
                        .addConstruction(new Rectangle2D(80, 435, 80, 20))
                        .addConstruction(new Rectangle2D(0, 430, 80, 20))
                        .addLadder(new Rectangle2D(540, 460, 20, 90))
                        .addDestroyBarrelZone(new Rectangle2D(0, 579, 1,1))
                        .addPlayerSpawnPoint(new Point2D(10, 550))
                        .addMovementProviders(movementProviderWrapper)
                        .addLevelEventHandler(levelEventsHandler)
        );

        InteractableGuiComponent component = guiComponent.get(MainMenu.class.getName());

        component.showComponents(anchorPane);

        currentComponent = component;
    }

    public void drawGame(long timeNow)
    {
        checkProvider(buttonEventsSubjectsWrapper.getGameEventProvider());

        if (timeNow - lastTime > 15_000_000) {
            currentComponent.simulate();
            lastTime = timeNow;
        }
        currentComponent.display(canvas);
    }

    private void checkProvider(GameEventProvider gameEventProvider)
    {
        GameEventEnums.GameEvents currentEvent = gameEventProvider.provide();
        GameEventEnums.LevelEvents currentLevelEvent = levelEventsObserverProvider.provide();

        if(currentEvent == GameEventEnums.GameEvents.None
                && currentLevelEvent == GameEventEnums.LevelEvents.None)
            return;

        if(currentComponent instanceof InteractableGuiComponent)
        {
            if(currentEvent == GameEventEnums.GameEvents.PlayGame)
            {
                setNewCurrentComponent(levels.get(0).build());
            }
            else if (currentEvent == GameEventEnums.GameEvents.SetName)
            {
                setNewCurrentComponent(guiComponent.get(EnterName.class.getName()));
            }
            else if( currentEvent == GameEventEnums.GameEvents.HomePage)
            {
                setNewCurrentComponent(guiComponent.get(MainMenu.class.getName()));
            }
        }

        if(currentComponent instanceof Level)
        {
            if(currentLevelEvent == GameEventEnums.LevelEvents.GameOver)
            {
                setNewCurrentComponent(guiComponent.get(MainMenu.class.getName()));
            }
        }
    }

    private void setNewCurrentComponent(GuiComponent newCurrentComponent)
    {
        if(currentComponent instanceof InteractableGuiComponent)
        {
            ((InteractableGuiComponent) currentComponent).clearComponents(anchorPane);
        }

        if(newCurrentComponent instanceof InteractableGuiComponent)
        {
            ((InteractableGuiComponent) newCurrentComponent).showComponents(anchorPane);
        }

        currentComponent = newCurrentComponent;
    }
}
