package gui.MVC;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    private final RobotModel robotModel;
    private final ArrayList<TargetModel> targets = new ArrayList<TargetModel>();

    protected static Timer initTimer() {
        return new Timer("events generator", true);
    }

    public Controller(RobotModel robotModel) {
        this.robotModel = robotModel;
        for (int i = 0; i < ModelsConstants.NUMBER_OF_POINTS; i++) {
            TargetModel target = new TargetModel();
            targets.add(target);
        }
        Timer viewTimer = initTimer();
        viewTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                robotModel.move();
            }
        }, 0, 20);
    }

    public RobotModel getRobotModel() {
        return robotModel;
    }

    public ArrayList<TargetModel> getTargets() {
        return targets;
    }

    public void addKeyListener(JPanel panel) {
        KeyEventListener keyEventListener = new KeyEventListener(robotModel);
        panel.addKeyListener(keyEventListener);
    }

    protected ArrayList<Double> calculateDistance() {
        ArrayList<Double> distances = new ArrayList<Double>();
        for (TargetModel target : targets) {
            double diffX = target.getXCoordinate() - robotModel.getXCoordinate();
            double diffY = target.getYCoordinate() - robotModel.getYCoordinate();
            distances.add(Math.sqrt(diffX * diffX + diffY * diffY));
        }
        return distances;
    }

    /**
     * Генерирует новые координаты таргету
     */
    protected void generateNewTargetCoordinates(int index) {
        this.targets.get(index).generateNewCoordinates();
    }


    protected double getRobotSpeed() {
        return this.robotModel.getRobotSpeed();
    }
}

