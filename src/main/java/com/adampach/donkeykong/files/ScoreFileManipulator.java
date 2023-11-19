package com.adampach.donkeykong.files;

import com.adampach.donkeykong.data.PlayerInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScoreFileManipulator
{
    private final String path;

    public ScoreFileManipulator(String path) {
        this.path = path;
    }

    public void writePlayerInfo(Map<String, PlayerInfo> players)
    {
        if(players.size() <= 0)
            return;

        try {
            File file = new File(path);
            file.createNewFile();

            FileWriter fw = new FileWriter(file);

            for(PlayerInfo player : players.values())
            {
                fw.write(player.getUsername()+";");
                for(Integer score : player.getTopLevelScores())
                {
                    fw.write(score+";");
                }
                fw.write("\n");
                fw.flush();
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("We cannot write results to the file");
            return;
        }
    }

    public List<PlayerInfo> readPlayerInfo()
    {
        File f = new File(path);
        if(!f.exists())
            return new ArrayList<>();

        ArrayList<PlayerInfo> readData = new ArrayList<>();

        try(BufferedReader buf = new BufferedReader(new FileReader(f.getAbsoluteFile())))
        {
            String line = buf.readLine();

            while (line != null)
            {
                String[] parameters = line.split(";");
                PlayerInfo tmp = new PlayerInfo();

                tmp.setUsername(parameters[0]);

                for(int i = 1; i < parameters.length; i++)
                {
                    tmp.getTopLevelScores().add(Integer.parseInt(parameters[i]));
                }
                readData.add(tmp);
                line = buf.readLine();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return readData;
    }
}
