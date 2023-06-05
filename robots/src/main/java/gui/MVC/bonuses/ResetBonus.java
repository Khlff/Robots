package gui.MVC.bonuses;

import gui.Game;

import static gui.MVC.ModelsConstants.DEFAULT_BONUS_SIZE;

public class ResetBonus extends GameBonus {
    public void changeProperties() {
        Game.getInstance().setGameScore(10000);
        Game.getInstance().setNeedReset(true);
    }

    public ResetBonus() {
        setSize(DEFAULT_BONUS_SIZE + 30);
        setTexturePath(".\\robots\\src\\main\\resources\\objectTextures\\reset.png");
        generateNewCoordinates();
    }
}
