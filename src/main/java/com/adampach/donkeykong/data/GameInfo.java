package com.adampach.donkeykong.data;

import java.util.Dictionary;
import java.util.Hashtable;

public class GameInfo
{
    private PlayerInfo currentPlayer;
    private final Dictionary<String, PlayerInfo> players;
    private final int numberOfLevels;

    public GameInfo(int numberOfLevels)
    {
        currentPlayer = new PlayerInfo();
        this.numberOfLevels = numberOfLevels;
        players = new Hashtable<>();
    }

    public String getUserName()
    {
        return currentPlayer.getUsername();
    }

    public void setUserName(String userName)
    {
        if(userName == "")
            currentPlayer = new PlayerInfo();

        PlayerInfo tmp = players.get(userName);
        if(tmp == null)
        {
            tmp = new PlayerInfo();
            tmp.setUsername(userName);
            players.put(userName, tmp);
        }
        System.out.println(players.size());
        currentPlayer = tmp;
    }
    public int getNumberOfLevels() {
        return numberOfLevels;
    }
}
