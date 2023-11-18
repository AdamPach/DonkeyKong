package com.adampach.donkeykong.gui;

import com.adampach.donkeykong.abstraction.gui.GuiComponent;
import com.adampach.donkeykong.abstraction.gui.InteractableGuiComponent;
import com.adampach.donkeykong.data.GameInfo;
import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.enums.GameEventEnums;
import com.adampach.donkeykong.handlers.LevelEventsHandler;
import com.adampach.donkeykong.providers.GameEventProvider;
import com.adampach.donkeykong.providers.LevelEventsObserverProvider;
import com.adampach.donkeykong.world.Level;
import com.adampach.donkeykong.data.LevelSettings;
import com.adampach.donkeykong.wrappers.ButtonEventsSubjectsWrapper;
import com.adampach.donkeykong.wrappers.MovementProviderWrapper;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Game
{
    private final Canvas canvas;

    private final AnchorPane anchorPane;

    private final ArrayList<GuiComponent> levels;

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
                DirectionEnums.HorizontalDirection.Right);

        guiComponent.put(MainMenu.class.getName(), new MainMenu(buttonEventsSubjectsWrapper, gameInfo));
        guiComponent.put(EnterName.class.getName(), new EnterName(buttonEventsSubjectsWrapper, gameInfo));

        levels.add(new Level(settings, movementProviderWrapper, levelEventsHandler));

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
                setNewCurrentComponent(levels.get(0));
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
