package gui.MVC;

import java.util.ArrayList;

import static gui.MVC.ModelsConstants.*;
import static gui.windows.ConstantsGUI.GAME_WINDOW_HEIGHT;
import static gui.windows.ConstantsGUI.GAME_WINDOW_WIDTH;
import static java.lang.Math.sqrt;

public class RobotModel extends GameEntity {
    private final ArrayList<Observer> observers;
    private final int robotSpeed = DEFAULT_ROBOT_SPEED;
    public final String texturePath = "./robots/src/main/resources/objectTextures/black-hole.png";

    private enum Direction {
        UP_LEFT, UP, UP_RIGHT, LEFT, RIGHT, DOWN_LEFT, DOWN, DOWN_RIGHT
    }

    private Direction currentDirection = Direction.RIGHT;

    public RobotModel() {
        observers = new ArrayList<>();
        setXCoordinate(DEFAULT_X_ROBOT_POSITION);
        setYCoordinate(DEFAULT_Y_ROBOT_POSITION);
        setSize(DEFAULT_ROBOT_SIZE);
    }

    public double getRobotSpeed() {
        return robotSpeed;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * Установить направление движения вверх.
     */
    protected void setMovementUp() {
        if (getYCoordinate() - robotSpeed >= 0) {
            currentDirection = Direction.UP;
        }
    }

    /**
     * Установить направление движения вниз.
     */
    protected void setMovementDown() {
        if (getYCoordinate() + robotSpeed <= GAME_WINDOW_HEIGHT) {
            currentDirection = Direction.DOWN;
        }
    }

    /**
     * Установить направление движения влево.
     */
    protected void setMovementLeft() {
        if (getXCoordinate() - robotSpeed >= 0) {
            currentDirection = Direction.LEFT;
        }
    }

    /**
     * Установить направление движения вправо.
     */
    protected void setMovementRight() {
        if (getXCoordinate() + robotSpeed <= GAME_WINDOW_WIDTH) {
            currentDirection = Direction.RIGHT;
        }
    }

    /**
     * Установить направление движения по диагонали вверх влево.
     */
    protected void setMovementUpLeft() {
        if (getXCoordinate() - robotSpeed >= 0 && getYCoordinate() - robotSpeed >= 0) {
            currentDirection = Direction.UP_LEFT;
        }
    }

    /**
     * Установить направление движения по диагонали вверх вправо.
     */
    protected void setMovementUpRight() {
        if (getXCoordinate() + robotSpeed <= GAME_WINDOW_WIDTH && getYCoordinate() - robotSpeed >= 0) {
            currentDirection = Direction.UP_RIGHT;
        }
    }

    /**
     * Установить направление движения по диагонали вниз влево.
     */
    protected void setMovementDownLeft() {
        if (getYCoordinate() + robotSpeed <= GAME_WINDOW_HEIGHT && getXCoordinate() - robotSpeed >= 0) {
            currentDirection = Direction.DOWN_LEFT;
        }
    }

    /**
     * Установить направление движения по диагонали вниз вправо.
     */
    protected void setMovementDownRight() {
        if (getYCoordinate() + robotSpeed <= GAME_WINDOW_HEIGHT && getXCoordinate() + robotSpeed <= GAME_WINDOW_WIDTH) {
            currentDirection = Direction.DOWN_RIGHT;
        }
    }

    /**
     * Двигает робота по установленному направлению.
     */
    protected void move() {
        switch (currentDirection) {
            case UP -> {
                if (getYCoordinate() - robotSpeed >= 0) {
                    setYCoordinate(getYCoordinate() - robotSpeed);
                }
            }
            case DOWN -> {
                if (getYCoordinate() + robotSpeed <= GAME_WINDOW_HEIGHT - robotSpeed / Math.sqrt(2)) {
                    setYCoordinate(getYCoordinate() + robotSpeed);
                }
            }
            case LEFT -> {
                if (getXCoordinate() - robotSpeed >= 0) {
                    setXCoordinate(getXCoordinate() - robotSpeed);
                }
            }
            case RIGHT -> {
                if (getXCoordinate() + robotSpeed <= GAME_WINDOW_WIDTH) {
                    setXCoordinate(getXCoordinate() + robotSpeed);
                }
            }
            case UP_LEFT -> {
                if (getXCoordinate() - robotSpeed >= 0 && getYCoordinate() - robotSpeed >= 0) {
                    setXCoordinate(getXCoordinate() - robotSpeed / Math.sqrt(2));
                    setYCoordinate(getYCoordinate() - robotSpeed / Math.sqrt(2));
                }
            }
            case UP_RIGHT -> {
                if (getXCoordinate() + robotSpeed <= GAME_WINDOW_WIDTH && getYCoordinate() - robotSpeed >= 0) {
                    setXCoordinate(getXCoordinate() + robotSpeed / Math.sqrt(2));
                    setYCoordinate(getYCoordinate() - robotSpeed / Math.sqrt(2));
                }
            }
            case DOWN_LEFT -> {
                if (getYCoordinate() + robotSpeed <= GAME_WINDOW_HEIGHT - sqrt(size) && getXCoordinate() - robotSpeed >= 0) {
                    setXCoordinate(getXCoordinate() - robotSpeed / Math.sqrt(2));
                    setYCoordinate(getYCoordinate() + robotSpeed / Math.sqrt(2));
                }
            }
            case DOWN_RIGHT -> {
                if (getYCoordinate() + robotSpeed <= GAME_WINDOW_HEIGHT - sqrt(size) && getXCoordinate() + robotSpeed <= GAME_WINDOW_WIDTH) {
                    setXCoordinate(getXCoordinate() + robotSpeed / Math.sqrt(2));
                    setYCoordinate(getYCoordinate() + robotSpeed / Math.sqrt(2));
                }
            }
        }
        notifyObservers();
    }
}
