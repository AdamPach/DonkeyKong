package com.adampach.donkeykong.gui;

import com.adampach.donkeykong.abstraction.gui.GuiComponent;
import com.adampach.donkeykong.abstraction.gui.InteractableGuiComponent;
import com.adampach.donkeykong.enums.DirectionEnums;
import com.adampach.donkeykong.world.Level;
import com.adampach.donkeykong.world.LevelSettings;
import com.adampach.donkeykong.wrappers.ButtonEventsProviderWrapper;
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

        LevelSettings settings = new LevelSettings(
                -12,
                4,
                3,
                2,
                (int)canvas.getWidth(),
                (int)canvas.getHeight(),
                DirectionEnums.HorizontalDirection.Right);

        guiComponent.put(MainMenu.class.getName(), new MainMenu(buttonEventsSubjectsWrapper));

        levels.add(new Level(settings, movementProviderWrapper));

        InteractableGuiComponent component = guiComponent.get(MainMenu.class.getName());

        component.showComponents(anchorPane);

        currentComponent = component;
    }

    public void drawGame(long timeNow)
    {
        checkProviders(buttonEventsSubjectsWrapper.getEventsProviders());

        if (timeNow - lastTime > 15_000_000) {
            currentComponent.simulate();
            lastTime = timeNow;
        }
        currentComponent.display(canvas);
    }

    private void checkProviders(ButtonEventsProviderWrapper buttonEventsProviderWrapper)
    {
        if(currentComponent instanceof InteractableGuiComponent)
        {
            if(buttonEventsProviderWrapper.playGameProvider().provide())
            {
                setNewCurrentComponent(levels.get(0));
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
