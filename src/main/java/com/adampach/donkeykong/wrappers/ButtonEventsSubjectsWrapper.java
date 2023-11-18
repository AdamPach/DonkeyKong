package com.adampach.donkeykong.wrappers;

import com.adampach.donkeykong.handlers.PlayGameHandler;
import com.adampach.donkeykong.providers.PlayGameProvider;

public class ButtonEventsSubjectsWrapper
{
    private final PlayGameHandler playGameHandler;

    private final ButtonEventsProviderWrapper buttonEventsProviderWrapper;

    public ButtonEventsSubjectsWrapper()
    {
        playGameHandler = new PlayGameHandler();
        PlayGameProvider playGameProvider = new PlayGameProvider();
        playGameHandler.registerObserver(playGameProvider);

        buttonEventsProviderWrapper = new ButtonEventsProviderWrapper(playGameProvider);
    }

    public PlayGameHandler getPlayGameHandler() {
        return playGameHandler;
    }

    public ButtonEventsProviderWrapper getEventsProviders()
    {
        return buttonEventsProviderWrapper;
    }
}
