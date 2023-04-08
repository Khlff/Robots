package gui;

import static gui.ConstantsGUI.*;

public class RobotModel extends Entity {
    private double robotDirection = DEFAULT_ROBOT_DIRECTION;
    private double maxVelocity = DEFAULT_ROBOT_VELOCITY;
    private double maxAngularVelocity = DEFAULT_ROBOT_ANGULAR_VELOCITY;

    public RobotModel() {
        setXCoordinate(DEFAULT_X_ROBOT_POSITION);
        setYCoordinate(DEFAULT_Y_ROBOT_POSITION);
    }

}
