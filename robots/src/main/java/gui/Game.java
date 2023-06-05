package gui;


import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static Game instance;
    private final int targetsNumber;
    private final int spikesNumber;
    private int gameScore = 0;
    private boolean needReset = false;
    Random random = new Random();
    private final ArrayList<String> bonuses = new ArrayList<String>() {{
        add("HALF-SIZE");
        add("SCORE");
        add("UPGRADE");
        add("TELEPORT");
        add("RESET");
    }};

    private Game() {
        this.targetsNumber = random.nextInt(6) + 4;
        this.spikesNumber = random.nextInt(3) + 1;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int points) {
        gameScore += points;
    }

    public int getTargetsNumber() {
        return targetsNumber;
    }

    public int getSpikesNumber() {
        return spikesNumber;
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }


    public String getRandomBonus() {
        int randomNumber = random.nextInt(bonuses.size());
        return bonuses.get(randomNumber);
    }

    public void setNeedReset(boolean needReset) {
        this.needReset = needReset;
    }

    public boolean isNeedReset() {
        return needReset;
    }
}