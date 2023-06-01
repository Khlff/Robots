package gui;

import org.json.JSONArray;
import org.json.JSONObject;

import gui.MVC.TargetModel;

import java.util.ArrayList;

public class Game {
    private static Game instance;
    private int numberOfTargets;
    private int scoreOfGame = 0;
    private final ArrayList<TargetModel> targets = new ArrayList<>();
    private Game() {
        resetTargets();
    }

    public int getScoreOfGame() {
        return scoreOfGame;
    }

    public void addScoreOfGame() {
        scoreOfGame += 1;
    }

    public int getNumberOfTargets() {
        return numberOfTargets;
    }
    public ArrayList<TargetModel> getTargets() {return targets;}

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void resetTargets() {
        this.numberOfTargets = (int) (Math.random() * 10) + 1;
    }
}