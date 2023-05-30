package gui.MVC;

import gui.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static gui.MVC.ModelsConstants.DEFAULT_ROBOT_ANGULAR_VELOCITY;
import static gui.MVC.ModelsConstants.DEFAULT_SPIKE_SIZE;

public class Controller {
    private final ArrayList<Observer> observers;
    private final RobotModel robotModel;
    private final SpikeModel spikeModel;
    private final ArrayList<TargetModel> targets = new ArrayList<TargetModel>();

    public Controller(RobotModel robotModel, SpikeModel spikeModel) {
        observers = new ArrayList<>();
    protected static Timer initTimer() {
        return new Timer("events generator", true);
    }

    public Controller(RobotModel robotModel) {
        this.robotModel = robotModel;
        for (int i = 0; i < ModelsConstants.NUMBER_OF_POINTS; i++) {
        this.mouseModel = mouseModel;
        this.spikeModel = spikeModel;
        for (int i = 0; i < Game.getInstance().getNumberOfTargets(); i++){
            TargetModel target = new TargetModel();
            targets.add(target);
        }
    }
    protected void onModelUpdateEvent() {
        ArrayList<Double> distances = calculateDistance();
        double distanceToSpike = distanceToSpike();
        for (Double distance : distances) {
            if (distance < (double) getRobotModel().getSize() / 2 && distance >
                    (double) getRobotModel().getSize() / 2 - 1) {
                Game.getInstance().setScoreOfGame();
                notifyObservers();
                if (distances.size() < 3) {

                    getRobotModel().setSize(getRobotModel().getSize() + 10);
                    generateNewTargets();
                    setRobotSpeed(getRobotSpeed() - 0.5);
                }
                else {;
                    getRobotModel().setSize(getRobotModel().getSize() + 10);
                    deleteTarget(distances.indexOf(distance));
                    setRobotSpeed(getRobotSpeed() - 0.5);
                }
            }
            if (distanceToSpike < DEFAULT_SPIKE_SIZE / 2){
                System.exit(0);
            }
            if (distance < 0.5) {
                return;
            }
        }


        double angleToTarget = angleTo();
        double angularVelocity = 0;

        if (RobotModel.asNormalizedRadians(getRobotModel().getRobotDirection() - angleToTarget) > Math.PI) {
            angularVelocity = DEFAULT_ROBOT_ANGULAR_VELOCITY;
        } else if (getRobotModel().getRobotDirection() != angleToTarget) {
            angularVelocity = -DEFAULT_ROBOT_ANGULAR_VELOCITY;
        }

        getRobotModel().moveRobot(angularVelocity);
    }

    public void generateNewTargets(){
        Game.getInstance().resetTargets();
        for (int i = 0; i < Game.getInstance().getNumberOfTargets(); i++){
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

    public void addKeyListener(JPanel panel){
            KeyEventListener keyEventListener = new KeyEventListener(robotModel);
            panel.addKeyListener(keyEventListener);
        }
    public SpikeModel getSpikeModel(){
        return spikeModel;
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
    public void deleteTarget(int index){
        targets.remove(index);
    }

    protected double distanceToSpike(){
        double diffX = this.spikeModel.getXCoordinate() - this.robotModel.getXCoordinate();
        double diffY = this.spikeModel.getYCoordinate() - this.robotModel.getYCoordinate();
        return (Math.sqrt(diffX * diffX + diffY * diffY));
    }

    /**
     * Генерирует новые координаты таргету
     *
     * @param index индекс таргета в массиве таргетов
     */
    protected void generateNewTargetCoordinates(int index) {
        this.targets.get(index).generateNewCoordinates();
    }

    /**
     * Генерирует путь к новой текстуре таргету
     *
     * @param index индекс таргета в массиве таргетов
     */
    protected void generateNewTargetTexture(int index) {
        this.targets.get(index).generateNewTexturePath();
    }

    protected double getRobotSpeed() {
        return this.robotModel.getRobotSpeed();
    }
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

