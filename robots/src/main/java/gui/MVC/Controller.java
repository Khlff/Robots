package gui.MVC;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Controller {

    private final RobotModel robotModel;
    private final ArrayList<TargetModel> targets = new ArrayList<TargetModel>();
    private final MouseModel mouseModel;

    public Controller(RobotModel robotModel, MouseModel mouseModel) {
        this.robotModel = robotModel;
        this.mouseModel = mouseModel;
        for (int i = 0; i < ModelsConstants.NUMBER_OF_POINTS;i++){
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

    public void addMouseListener(JPanel panel) {
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseModel.setXCoordinate(e.getX());
                mouseModel.setYCoordinate(e.getY());
            }
        });

    }

    protected ArrayList<Double> distance() {
        ArrayList<Double> distances = new ArrayList<Double>();
        for (TargetModel target: targets){
            double diffX = target.getXCoordinate() - this.robotModel.getXCoordinate();
            double diffY = target.getYCoordinate() - this.robotModel.getYCoordinate();
            distances.add(Math.sqrt(diffX * diffX + diffY * diffY));
        }
        return distances;
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
}

