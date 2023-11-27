package com.adampach.donkeykong.gui;

import com.adampach.donkeykong.abstraction.gui.GuiComponent;
import com.adampach.donkeykong.abstraction.gui.InteractableGuiComponent;
import com.adampach.donkeykong.builders.LevelBuilder;
import com.adampach.donkeykong.builders.LevelSettingsBuilder;
import com.adampach.donkeykong.data.GameInfo;
import com.adampach.donkeykong.data.LevelSettings;
import com.adampach.donkeykong.enums.GameEventEnums;
import com.adampach.donkeykong.files.ScoreFileManipulator;
import com.adampach.donkeykong.handlers.LevelEventsHandler;
import com.adampach.donkeykong.providers.GameEventProvider;
import com.adampach.donkeykong.providers.LevelEventsObserverProvider;
import com.adampach.donkeykong.statics.LevelDefinitions;
import com.adampach.donkeykong.world.Level;
import com.adampach.donkeykong.wrappers.ButtonEventsSubjectsWrapper;
import com.adampach.donkeykong.wrappers.MovementProviderWrapper;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.Function;

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

    private final ScoreFileManipulator scoreFileManipulator;


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
        scoreFileManipulator = new ScoreFileManipulator("score.csv");

        levelEventsHandler = new LevelEventsHandler();
        levelEventsObserverProvider = new LevelEventsObserverProvider();

        levelEventsHandler.registerObserver(levelEventsObserverProvider);

        LevelSettingsBuilder settingsBuilder = LevelDefinitions.getDefaultSetting((int) canvas.getWidth(), (int) canvas.getHeight());

        tryAddLevel(LevelDefinitions::getLevelOneBuilder, settingsBuilder, movementProviderWrapper);
        tryAddLevel(LevelDefinitions::getLevelTwoBuilder, settingsBuilder, movementProviderWrapper);

        gameInfo = new GameInfo(levels.size());
        gameInfo.readDataFromFile(scoreFileManipulator);

        guiComponent.put(MainMenu.class.getName(), new MainMenu(buttonEventsSubjectsWrapper, gameInfo));
        guiComponent.put(EnterName.class.getName(), new EnterName(buttonEventsSubjectsWrapper, gameInfo));
        guiComponent.put(PickLevel.class.getName(), new PickLevel(buttonEventsSubjectsWrapper, gameInfo));
        guiComponent.put(LevelPassed.class.getName(), new LevelPassed(buttonEventsSubjectsWrapper));

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
                setNewCurrentComponent(guiComponent.get(PickLevel.class.getName()));
            }
            else if (currentEvent == GameEventEnums.GameEvents.SetName)
            {
                setNewCurrentComponent(guiComponent.get(EnterName.class.getName()));
            }
            else if( currentEvent == GameEventEnums.GameEvents.HomePage)
            {
                setNewCurrentComponent(guiComponent.get(MainMenu.class.getName()));
            }
            else if( currentEvent == GameEventEnums.GameEvents.StartLevel)
            {
                setNewCurrentComponent(levels.get(gameInfo.getCurrentLevel() - 1).build());
            }
            else if( currentEvent == GameEventEnums.GameEvents.HallOfFame)
            {
                setNewCurrentComponent(guiComponent.get(HallOfFame.class.getName()));
            }
        }

        if(currentComponent instanceof Level)
        {
            if(currentLevelEvent == GameEventEnums.LevelEvents.GameOver)
            {
                setNewCurrentComponent(guiComponent.get(MainMenu.class.getName()));
            }
            else if (currentLevelEvent == GameEventEnums.LevelEvents.ClearEnemies)
            {
                ((Level)currentComponent).clearEnemies();
            }
            else if (currentLevelEvent == GameEventEnums.LevelEvents.Peach)
            {
                gameInfo.setScoreForCurrentLevel(((Level)currentComponent).getCurrentScore());
                gameInfo.writeCurrentData(scoreFileManipulator);
                setNewCurrentComponent(guiComponent.get(LevelPassed.class.getName()));
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

    private void tryAddLevel(
            Function<LevelSettings, LevelBuilder> builderSupplier,
            LevelSettingsBuilder settingsBuilder,
            MovementProviderWrapper movementProviderWrapper)
    {
        LevelBuilder builder;
        try
        {
            builder = builderSupplier
                    .apply(settingsBuilder.build())
                    .addMovementProviders(movementProviderWrapper)
                    .addLevelEventHandler(levelEventsHandler);
            builder.build();
        }
        catch (RuntimeException e)
        {
            return;
        }

        levels.add(builder);
    }
}
