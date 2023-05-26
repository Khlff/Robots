package gui.MVC;

import gui.Game;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static gui.MVC.ModelsConstants.DEFAULT_ROBOT_ANGULAR_VELOCITY;

public class Controller {
    private final ArrayList<Observer> observers;
    private final RobotModel robotModel;
    private final SpikeModel spikeModel;
    private final ArrayList<TargetModel> targets = new ArrayList<TargetModel>();
    private final MouseModel mouseModel;

    public Controller(RobotModel robotModel, MouseModel mouseModel, SpikeModel spikeModel) {
        observers = new ArrayList<>();
        this.robotModel = robotModel;
        this.mouseModel = mouseModel;
        this.spikeModel = spikeModel;
        for (int i = 0; i < Game.getInstance().getNumberOfTargets(); i++){
            TargetModel target = new TargetModel();
            targets.add(target);
        }
    }
    protected void onModelUpdateEvent() {
        ArrayList<Double> distances = calculateDistance();
        for (Double distance : distances) {
            if (distance < (double) getRobotModel().getSize() / 2 && distance >
                    (double) getRobotModel().getSize() / 2 - 1) {
                Game.getInstance().setScoreOfGame();
                notifyObservers();
                if (distances.size() < 2) {

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
    }
    public RobotModel getRobotModel() {
        return robotModel;
    }

    public ArrayList<TargetModel> getTargets() {
        return targets;
    }

    public SpikeModel getSpikeModel(){
        return spikeModel;
    }

    public void addMouseListener(JPanel panel) {
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseModel.setXCoordinate(e.getX());
                mouseModel.setYCoordinate(e.getY());
            }
        });

    }

    public ArrayList<Double> calculateDistance() {
        ArrayList<Double> distances = new ArrayList<Double>();
        for (TargetModel target: targets){
            double diffX = target.getXCoordinate() - this.robotModel.getXCoordinate();
            double diffY = target.getYCoordinate() - this.robotModel.getYCoordinate();
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

    protected double angleTo() {
        double diffX = mouseModel.getXCoordinate() - robotModel.getXCoordinate();
        double diffY = mouseModel.getYCoordinate() - robotModel.getYCoordinate();
        return RobotModel.asNormalizedRadians(Math.atan2(diffY, diffX));
    }

    /**
     * Генерирует новые координаты таргету
     */
    protected void generateNewTargetCoordinates(int index) {
        this.targets.get(index).generateNewCoordinates();
    }

    protected void setRobotSpeed(double newSpeed) {
        this.robotModel.setRobotSpeed(newSpeed);
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

