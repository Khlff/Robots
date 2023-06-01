package gui.MVC;

import gui.Game;
import multiplayer.Client;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private final RobotModel localRobotModel;
    private final RobotModel enemyRobotModel;
    private final SpikeModel spikeModel;
    private final ArrayList<TargetModel> targets = new ArrayList<>();


    protected static Timer initTimer() {
        return new Timer("events generator", true);
    }

    public Controller(RobotModel localRobotModel, RobotModel enemyRobotModel, SpikeModel spikeModel, Client multiplayerClient) {
        this.localRobotModel = localRobotModel;
        this.enemyRobotModel = enemyRobotModel;
        this.spikeModel = spikeModel;
        Timer movementTimer = initTimer();
        movementTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                localRobotModel.move();
            }
        }, 0, 20);

        for (int i = 0; i < Game.getInstance().getNumberOfTargets(); i++) {
            TargetModel target = new TargetModel();
            targets.add(target);
        }
    }

    public RobotModel getLocalRobotModel() {
        return localRobotModel;
    }

    public RobotModel getEnemyRobotModel() {
        return enemyRobotModel;
    }

    public ArrayList<TargetModel> getTargets() {
        return targets;
    }


    public SpikeModel getSpikeModel() {
        return spikeModel;
    }

    public void addKeyListener(JPanel panel) {
        KeyEventListener keyEventListener = new KeyEventListener(localRobotModel);
        panel.addKeyListener(keyEventListener);
    }

    protected ArrayList<Double> calculateDistance() {
        ArrayList<Double> distances = new ArrayList<>();
        for (TargetModel target : targets) {
            double diffX = target.getXCoordinate() - localRobotModel.getXCoordinate();
            double diffY = target.getYCoordinate() - localRobotModel.getYCoordinate();
            distances.add(Math.sqrt(diffX * diffX + diffY * diffY));
        }
        return distances;
    }

    protected void generateNewSpikeCoordinates() {
        spikeModel.generateCoordinates();
    }

    protected double distanceToSpike() {
        double diffX = spikeModel.getXCoordinate() - localRobotModel.getXCoordinate();
        double diffY = spikeModel.getYCoordinate() - localRobotModel.getYCoordinate();
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

