package gui.MVC;

import gui.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private final RobotModel robotModel;
    private final SpikeModel spikeModel;
    private final ArrayList<TargetModel> targets = new ArrayList<>();


    protected static Timer initTimer() {
        return new Timer("events generator", true);
    }

    public Controller(RobotModel robotModel, SpikeModel spikeModel) {
        this.robotModel = robotModel;
        this.spikeModel = spikeModel;
        Timer movementTimer = initTimer();
        movementTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                robotModel.move();
            }
        }, 0, 20);

        for (int i = 0; i < Game.getInstance().getNumberOfTargets(); i++) {
            TargetModel target = new TargetModel();
            targets.add(target);
        }
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

    public SpikeModel getSpikeModel() {
        return spikeModel;
    }

    protected ArrayList<Double> calculateDistance() {
        ArrayList<Double> distances = new ArrayList<>();
        for (TargetModel target : targets) {
            double diffX = target.getXCoordinate() - robotModel.getXCoordinate();
            double diffY = target.getYCoordinate() - robotModel.getYCoordinate();
            distances.add(Math.sqrt(diffX * diffX + diffY * diffY));
        }
        return distances;
    }

    public void deleteTarget(int index) {
        targets.remove(index);
    }

    protected double distanceToSpike() {
        double diffX = spikeModel.getXCoordinate() - robotModel.getXCoordinate();
        double diffY = spikeModel.getYCoordinate() - robotModel.getYCoordinate();
        return (Math.sqrt(diffX * diffX + diffY * diffY));
    }

    /**
     * Генерирует новые координаты таргету
     *
     * @param index индекс таргета в массиве таргетов
     */
    protected void generateNewTargetCoordinates(int index) {
        targets.get(index).generateNewCoordinates();
    }

    /**
     * Генерирует путь к новой текстуре таргету
     *
     * @param index индекс таргета в массиве таргетов
     */
    protected void generateNewTargetTexture(int index) {
        targets.get(index).generateNewTexturePath();
    }
}

