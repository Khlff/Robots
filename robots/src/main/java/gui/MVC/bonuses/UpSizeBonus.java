package gui.MVC.bonuses;

import gui.Game;
import gui.MVC.RobotModel;

import static gui.MVC.ModelsConstants.DEFAULT_BONUS_SIZE;

public class UpSizeBonus extends RobotBonus {
    public void changeProperties(RobotModel robot) {
        int size = robot.getSize() * 5 / 4;
        robot.setSize(size);
        Game.getInstance().setGameScore(size);
    }

    public UpSizeBonus() {
        generateNewCoordinates();
        setSize(DEFAULT_BONUS_SIZE);
        setTexturePath(".\\robots\\src\\main\\resources\\objectTextures\\upgrade.png");
    }
}
