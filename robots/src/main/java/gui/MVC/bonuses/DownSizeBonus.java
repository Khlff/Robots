package gui.MVC.bonuses;

import gui.MVC.RobotModel;

import static gui.MVC.ModelsConstants.DEFAULT_BONUS_SIZE;

public class DownSizeBonus extends RobotBonus {
    public void changeProperties(RobotModel robot) {
        robot.setSize(robot.getSize() * 3 / 4);
    }

    public DownSizeBonus() {
        generateNewCoordinates();
        setSize(DEFAULT_BONUS_SIZE);
        setTexturePath(".\\robots\\src\\main\\resources\\objectTextures\\downgrade.png");
    }

}
