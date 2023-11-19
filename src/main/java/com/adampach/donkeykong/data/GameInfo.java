package com.adampach.donkeykong.data;

import com.adampach.donkeykong.files.ScoreFileManipulator;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class GameInfo
{
    private PlayerInfo currentPlayer;
    private final Map<String, PlayerInfo> players;
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

    public void writeCurrentData(ScoreFileManipulator scoreFileManipulator)
    {
        scoreFileManipulator.writePlayerInfo(players);
    }

    public void readDataFromFile(ScoreFileManipulator scoreFileManipulator)
    {
        List<PlayerInfo> playerInfos = scoreFileManipulator.readPlayerInfo();
        playerInfos.forEach( e ->
        {
            this.players.put(e.getUsername(), e);
        });
    }
}
