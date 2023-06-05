package gui;


public class Game {
    private static Game instance;
    private final int numberOfTargets;
    private final int numberOfSpikes;
    private int scoreOfGame = 0;

    private Game() {
        this.numberOfTargets = (int) (Math.random() * 6) + 3;
        this.numberOfSpikes = (int) (Math.random() * 3) + 1;
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

    public int getNumberOfSpikes() {
        return numberOfSpikes;
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

}