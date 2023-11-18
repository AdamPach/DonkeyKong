package com.adampach.donkeykong.providers;

import com.adampach.donkeykong.abstraction.Observer;
import com.adampach.donkeykong.abstraction.Provider;
import com.adampach.donkeykong.enums.GameEventEnums;

public class LevelEventsObserverProvider implements Provider<GameEventEnums.LevelEvents>, Observer<GameEventEnums.LevelEvents> {
    private GameEventEnums.LevelEvents lastLevelEvent = GameEventEnums.LevelEvents.None;

    @Override
    public void notifyObserver(GameEventEnums.LevelEvents data)
    {
        lastLevelEvent = data;
    }

    @Override
    public GameEventEnums.LevelEvents provide() {
        GameEventEnums.LevelEvents response = GameEventEnums.LevelEvents.None;

        if(lastLevelEvent != GameEventEnums.LevelEvents.None)
        {
            response = lastLevelEvent;
            lastLevelEvent = GameEventEnums.LevelEvents.None;
        }

        return response;
    }
}
