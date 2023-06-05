package gui.MVC;
import java.io.*;
import java.util.HashMap;

import gui.Game;
import org.json.JSONObject;

public class ScoreRecord {
    File storeFile;
    public ScoreRecord(File file){
        this.storeFile = file;
    }

    public void save(int score){
        try{
            storeFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try{
            FileWriter writer = new FileWriter(storeFile);
            writer.write(score);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public int getRecord(){
        int score = 0;
        try{
            FileReader fileReader = new FileReader(storeFile);
            score = fileReader.read();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return score;
    }
}
