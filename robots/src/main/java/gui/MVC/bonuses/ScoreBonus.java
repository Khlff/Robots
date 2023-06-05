package gui.MVC.bonuses;

import gui.Game;

import static gui.MVC.ModelsConstants.DEFAULT_BONUS_SIZE;

public class ScoreBonus extends GameBonus {
    public void changeProperties() {
        Game.getInstance().setGameScore(100);

    }

    public ScoreBonus() {
        setSize(DEFAULT_BONUS_SIZE);
        setTexturePath(".\\robots\\src\\main\\resources\\objectTextures\\100.png");
        generateNewCoordinates();
    }
}
