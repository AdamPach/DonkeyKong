package com.adampach.donkeykong.providers;

import com.adampach.donkeykong.abstraction.Observer;
import com.adampach.donkeykong.abstraction.Provider;

public class PlayGameProvider implements Provider<Boolean>, Observer<Boolean> {
    private boolean actualData;

    public PlayGameProvider()
    {
        actualData = false;
    }

    @Override
    public void notifyObserver(Boolean data)
    {
        actualData = data;
    }

    @Override
    public Boolean provide() {
        if(actualData)
        {
            actualData = false;
            return true;
        }
        return false;
    }
}
