package com.adampach.donkeykong.wrappers;

import com.adampach.donkeykong.handlers.*;
import com.adampach.donkeykong.providers.GameEventProvider;

public class ButtonEventsSubjectsWrapper
{
    private final PlayGameHandler playGameHandler;

    private final HomePageHandler homePageHandler;

    private final SetNameHandler setNameHandler;

    private final GameEventProvider gameEventProvider;

    private final StartLevelHandler startLevelHandler;

    private final HallOfFameHandler hallOfFameHandler;

    public ButtonEventsSubjectsWrapper()
    {
        gameEventProvider = new GameEventProvider();

        playGameHandler = new PlayGameHandler();
        playGameHandler.registerObserver(gameEventProvider);

        homePageHandler = new HomePageHandler();
        homePageHandler.registerObserver(gameEventProvider);

        setNameHandler = new SetNameHandler();
        setNameHandler.registerObserver(gameEventProvider);

        startLevelHandler = new StartLevelHandler();
        startLevelHandler.registerObserver(gameEventProvider);

        hallOfFameHandler = new HallOfFameHandler();
        hallOfFameHandler.registerObserver(gameEventProvider);
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

    public StartLevelHandler getStartLevelHandler() {
        return startLevelHandler;
    }

    public HallOfFameHandler getHallOfFameHandler() {
        return hallOfFameHandler;
    }
}
