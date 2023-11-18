package com.adampach.donkeykong.wrappers;

import com.adampach.donkeykong.handlers.HomePageHandler;
import com.adampach.donkeykong.handlers.PlayGameHandler;
import com.adampach.donkeykong.handlers.SetNameHandler;
import com.adampach.donkeykong.providers.GameEventProvider;

public class ButtonEventsSubjectsWrapper
{
    private final PlayGameHandler playGameHandler;

    private final HomePageHandler homePageHandler;

    private final SetNameHandler setNameHandler;

    private final GameEventProvider gameEventProvider;

    public ButtonEventsSubjectsWrapper()
    {
        gameEventProvider = new GameEventProvider();

        playGameHandler = new PlayGameHandler();
        playGameHandler.registerObserver(gameEventProvider);

        homePageHandler = new HomePageHandler();
        homePageHandler.registerObserver(gameEventProvider);

        setNameHandler = new SetNameHandler();
        setNameHandler.registerObserver(gameEventProvider);
    }

    public PlayGameHandler getPlayGameHandler() {
        return playGameHandler;
    }

    public HomePageHandler getHomePageHandler() {
        return homePageHandler;
    }

    public SetNameHandler getSetNameHandler() {
        return setNameHandler;
    }

    public GameEventProvider getGameEventProvider() {
        return gameEventProvider;
    }
}
