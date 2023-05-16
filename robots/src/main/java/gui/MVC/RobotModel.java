package gui.MVC;

import java.util.ArrayList;

import static gui.MVC.ModelsConstants.*;

public class RobotModel extends Entity {
    private final ArrayList<Observer> observers;
    private double robotDirection = 0;
    private double robotVelocity = DEFAULT_ROBOT_VELOCITY;
    private double robotSpeed = DEFAULT_ROBOT_SPEED;

    public RobotModel() {
        observers = new ArrayList<>();
        setXCoordinate(DEFAULT_X_ROBOT_POSITION);
        setYCoordinate(DEFAULT_Y_ROBOT_POSITION);
        setRobotDirection(DEFAULT_ROBOT_ANGULAR_VELOCITY);
        setSize(DEFAULT_ROBOT_SIZE);
    }

    public double getRobotSpeed() {
        return robotSpeed;
    }

    public void setRobotSpeed(double robotSpeed) {
        if (!(this.robotSpeed == 0.5)) {
            this.robotSpeed = robotSpeed;
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void setRobotDirection(double robotDirection) {
        this.robotDirection = robotDirection;
    }

    public double getRobotDirection() {
        return robotDirection;
    }

    protected static double applyLimits(double value, double min, double max) {
        if (value < min)
            return min;
        return Math.min(value, max);
    }

    protected static double asNormalizedRadians(double angle) {
        while (angle < 0) {
            angle += 2 * Math.PI;
        }
        while (angle >= 2 * Math.PI) {
            angle -= 2 * Math.PI;
        }
        return angle;
    }

    protected void moveRobot(double angularVelocity) {
        this.robotVelocity = applyLimits(this.robotVelocity, 0, DEFAULT_ROBOT_VELOCITY);
        angularVelocity = applyLimits(angularVelocity, -DEFAULT_ROBOT_ANGULAR_VELOCITY, DEFAULT_ROBOT_ANGULAR_VELOCITY);
        double newX = xCoordinate + this.robotVelocity / angularVelocity *
                (Math.sin(robotDirection + angularVelocity * robotSpeed) -
                        Math.sin(robotDirection));
        if (!Double.isFinite(newX)) {
            newX = xCoordinate + this.robotVelocity * robotSpeed * Math.cos(robotDirection);
        }
        double newY = yCoordinate - this.robotVelocity / angularVelocity *
                (Math.cos(robotDirection + angularVelocity * robotSpeed) -
                        Math.cos(robotDirection));
        if (!Double.isFinite(newY)) {
            newY = yCoordinate + this.robotVelocity * robotSpeed * Math.sin(robotDirection);
        }
        robotDirection = asNormalizedRadians(robotDirection + angularVelocity * robotSpeed);
        xCoordinate = newX;
        yCoordinate = newY;
        robotDirection = asNormalizedRadians(robotDirection + angularVelocity * robotSpeed);

        notifyObservers();
    }


}
