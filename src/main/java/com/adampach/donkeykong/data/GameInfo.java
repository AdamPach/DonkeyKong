package com.adampach.donkeykong.data;

public class GameInfo
{
    private String UserName;
    private final int numberOfLevels;
    private int[] levelScores;

    public GameInfo(int numberOfLevels)
    {
        UserName = "";
        this.numberOfLevels = numberOfLevels;
        levelScores = new int[numberOfLevels];
    }

    public String getUserName()
    {
        return UserName;
    }

    public void setUserName(String userName)
    {
        UserName = userName;
    }

    public int getLevelScore(int levelNumber)
    {
        return levelScores[levelNumber];
    }

    public int getNumberOfLevels() {
        return numberOfLevels;
    }
}
