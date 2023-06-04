package gui.MVC;

import gui.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private final RobotModel robotModel;
    private final SpikeModel spikeModel;
    private final ArrayList<GameEntity> gameEntities = new ArrayList<>();


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
        for (int i = 0; i < Game.getInstance().getNumberOfSpikes(); i++) {
            SpikeModel spike = new SpikeModel();
            gameEntities.add(spike);
        }
        for (int i = 0; i < Game.getInstance().getNumberOfTargets(); i++) {
            TargetModel target = new TargetModel();
            gameEntities.add(target);
        }
    }

    public RobotModel getRobotModel() {
        return robotModel;
    }

    public ArrayList<GameEntity> getGameEntities() {
        return gameEntities;
    }

    public GameEntity getGameEntityByIndex(int index){
        return gameEntities.get(index);
    }
    public void addKeyListener(JPanel panel) {
        KeyEventListener keyEventListener = new KeyEventListener(robotModel);
        panel.addKeyListener(keyEventListener);
    }

    public SpikeModel getSpikeModel() {
        return spikeModel;
    }

    protected ArrayList<Double> calculateDistanceToEntities() {
        ArrayList<Double> distances = new ArrayList<>();
        for (GameEntity entity : gameEntities) {
            double diffX = entity.getXCoordinate() - robotModel.getXCoordinate();
            double diffY = entity.getYCoordinate() - robotModel.getYCoordinate();
            distances.add(Math.sqrt(diffX * diffX + diffY * diffY));
        }
        return distances;
    }

    /**
     * Генерирует новые координаты таргету
     *
     * @param index индекс таргета в массиве таргетов
     */
    protected void generateNewTargetCoordinates(int index) {
        gameEntities.get(index).generateNewCoordinates();
    }

    /**
     * Генерирует путь к новой текстуре таргету
     *
     * @param index индекс таргета в массиве таргетов
     */
    protected void generateNewTargetTexture(int index) {
        gameEntities.get(index).generateNewTexturePath();
    }
}

