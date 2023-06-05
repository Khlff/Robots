package gui.MVC.bonuses;

import gui.Game;
import gui.MVC.RobotModel;

import static gui.MVC.ModelsConstants.DEFAULT_BONUS_SIZE;

public class TeleportBonus extends RobotBonus {
    public void changeProperties(RobotModel robot) {
        robot.generateNewCoordinates();
        Game.getInstance().setGameScore(1000);
    }

    public TeleportBonus() {
        generateNewCoordinates();
        setSize(DEFAULT_BONUS_SIZE + 10);
        setTexturePath(".\\robots\\src\\main\\resources\\objectTextures\\teleport.png");
    }
}
