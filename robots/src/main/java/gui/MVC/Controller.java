package gui.MVC;

import gui.BonusesFabric;
import gui.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private final RobotModel robotModel;
    private final BonusesFabric fabric = new BonusesFabric();
    private final ArrayList<GameEntity> gameEntities = new ArrayList<>();


    protected static Timer initTimer() {
        return new Timer("events generator", true);
    }

    public Controller(RobotModel robotModel) {
        this.robotModel = robotModel;
        Timer movementTimer = initTimer();
        movementTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                robotModel.move();
            }
        }, 0, 20);
        for (int i = 0; i < Game.getInstance().getSpikesNumber(); i++) {
            SpikeModel spike = new SpikeModel();
            gameEntities.add(spike);
        }
        for (int i = 0; i < Game.getInstance().getTargetsNumber(); i++) {
            TargetModel target = new TargetModel();
            gameEntities.add(target);
        }
        GameEntity bonus = fabric.createBonus(Game.getInstance().getRandomBonus());
        gameEntities.add(bonus);
    }

    public void appendNewBonus() {
        GameEntity bonus = fabric.createBonus(Game.getInstance().getRandomBonus());
        gameEntities.remove(gameEntities.size() - 1);
        gameEntities.add(bonus);

    }

    public void reset() {
        for (GameEntity entity : gameEntities) {
            entity.generateNewCoordinates();
        }
    }

    public RobotModel getRobotModel() {
        return robotModel;
    }

    public ArrayList<GameEntity> getGameEntities() {
        return gameEntities;
    }

    public GameEntity getGameEntityByIndex(int index) {
        return gameEntities.get(index);
    }

    public void addKeyListener(JPanel panel) {
        KeyEventListener keyEventListener = new KeyEventListener(robotModel);
        panel.addKeyListener(keyEventListener);
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

