package com.adampach.donkeykong.data;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;

public class GameInfo
{
    private PlayerInfo currentPlayer;
    private final Dictionary<String, PlayerInfo> players;
    private int currentLevel;
    private final int numberOfLevels;

    public GameInfo(int numberOfLevels)
    {
        this.numberOfLevels = numberOfLevels;
        currentPlayer = new PlayerInfo();
        players = new Hashtable<>();
        currentLevel = 0;
    }

    public String getUserName()
    {
        return currentPlayer.getUsername();
    }

    public void setUserName(String userName)
    {
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

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getNumberOfLevels() {
        return numberOfLevels;
    }

    public void setScoreForCurrentLevel(int score)
    {
        if(currentLevel >= currentPlayer.getTopLevelScores().size())
            currentPlayer.getTopLevelScores().add(score);
        else
        {
            int tmpScore = currentPlayer.getTopLevelScores().get(currentLevel - 1);
            currentPlayer.getTopLevelScores().set(currentLevel - 1, Math.max(score, tmpScore));
        }
    }

    public int getCurrentPlayerLevels()
    {
        return currentPlayer.getTopLevelScores().size();
    }

    public int getCurrentPlayerScoreSum()
    {
        AtomicInteger tmp = new AtomicInteger();

        currentPlayer.getTopLevelScores().forEach(tmp::addAndGet);

        return tmp.get();
    }
}
