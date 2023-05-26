package gui;

import java.util.Random;

public class Game {
    private static Game instance;
    private int numberOfTargets;
    private final int numberOfSpikes;
    private int scoreOfGame = 0;

    private Game() {
        resetTargets();
        this.numberOfSpikes = (int) (Math.random() * 3) + 1;
    }

    public int getScoreOfGame() {
        return scoreOfGame;
    }

    public void setScoreOfGame() {
        scoreOfGame += 1;
    }

    public int getNumberOfTargets() {
        return numberOfTargets;
    }

    public int getNumberOfSpikes() {
        return numberOfSpikes;
    }

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