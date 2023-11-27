package com.adampach.donkeykong.wrappers;

import com.adampach.donkeykong.handlers.*;
import com.adampach.donkeykong.providers.GameEventProvider;

public class ButtonEventsSubjectsWrapper
{
    private final GameEventProvider gameEventProvider;

     private final GuiEventHandler guiEventHandler;

    public ButtonEventsSubjectsWrapper()
    {
        gameEventProvider = new GameEventProvider();

        guiEventHandler = new GuiEventHandler();
        guiEventHandler.registerObserver(gameEventProvider);
    }
    public GameEventProvider getGameEventProvider() {
        return gameEventProvider;
    }

    public GuiEventHandler getGuiEventHandler(){
        return guiEventHandler;
    }
}
