package com.adampach.donkeykong.data;

import com.adampach.donkeykong.files.ScoreFileManipulator;
import javafx.util.Pair;

import java.util.*;
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
        currentPlayer.setUsername("UNKNOWN");
        players = new Hashtable<>();
        currentLevel = 0;
    }

    public String getUserName()
    {
        return currentPlayer.getUsername();
    }

    public void setUserName(String userName)
    {
        if(userName == null || userName.isEmpty())
        {
            currentPlayer = new PlayerInfo();
            currentPlayer.setUsername("UNKNOWN");
            return;
        }

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
        if(currentLevel > currentPlayer.getTopLevelScores().size())
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

    public List<Pair<String, Integer>> getTopPlayers(int numberOfTopPlayers)
    {
        ArrayList<Pair<String, Integer>> top = new ArrayList<>();

        for(PlayerInfo info : players.values())
        {
            AtomicInteger tmp = new AtomicInteger();

            info.getTopLevelScores().forEach(tmp::addAndGet);

            top.add(new Pair<>(info.getUsername(), tmp.get()));
        }

        top.sort(Comparator.comparing(Pair::getValue));
        Collections.reverse(top);

        return top.stream()
                .limit(numberOfTopPlayers)
                .toList();
    }
}
