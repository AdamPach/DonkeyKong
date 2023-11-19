package com.adampach.donkeykong.data;

import java.util.ArrayList;

public class PlayerInfo
{
    private String username;
    private ArrayList<Integer> topLevelScores;

    public PlayerInfo()
    {
        username = "";
        topLevelScores = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Integer> getTopLevelScores() {
        return topLevelScores;
    }

}
