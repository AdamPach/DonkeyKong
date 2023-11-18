package com.adampach.donkeykong.providers;

import com.adampach.donkeykong.abstraction.Observer;
import com.adampach.donkeykong.abstraction.Provider;
import com.adampach.donkeykong.enums.GameEventEnums;

public class GameEventProvider implements Provider<GameEventEnums.GameEvents>, Observer<GameEventEnums.GameEvents>
{
    private GameEventEnums.GameEvents lastNotifiedData = GameEventEnums.GameEvents.None;

    @Override
    public void notifyObserver(GameEventEnums.GameEvents data)
    {
        lastNotifiedData = data;
    }

    @Override
    public GameEventEnums.GameEvents provide() {
        GameEventEnums.GameEvents response = GameEventEnums.GameEvents.None;

        if(lastNotifiedData != GameEventEnums.GameEvents.None)
        {
            response = lastNotifiedData;
            lastNotifiedData = GameEventEnums.GameEvents.None;
        }

        return response;
    }
}
