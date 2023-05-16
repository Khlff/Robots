package gui.MVC;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller {

    private final RobotModel robotModel;
    private final TargetModel targetModel;
    private final MouseModel mouseModel;

    public Controller(RobotModel robotModel, TargetModel targetModel, MouseModel mouseModel) {
        this.targetModel = targetModel;
        this.robotModel = robotModel;
        this.mouseModel = mouseModel;
    }

    public RobotModel getRobotModel() {
        return robotModel;
    }

    public TargetModel getTargetModel() {
        return targetModel;
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

    protected double distance() {
        double diffX = this.targetModel.getXCoordinate() - this.robotModel.getXCoordinate();
        double diffY = this.targetModel.getYCoordinate() - this.robotModel.getYCoordinate();
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    protected double angleTo() {
        double diffX = mouseModel.getXCoordinate() - robotModel.getXCoordinate();
        double diffY = mouseModel.getYCoordinate() - robotModel.getYCoordinate();
        return RobotModel.asNormalizedRadians(Math.atan2(diffY, diffX));
    }

    /**
     * Генерирует новые координаты таргету
     */
    protected void generateNewTargetCoordinates() {
        this.targetModel.generateNewCoordinates();
    }

    protected void setRobotSpeed(double newSpeed) {
        this.robotModel.setRobotSpeed(newSpeed);
    }

    protected double getRobotSpeed() {
        return this.robotModel.getRobotSpeed();
    }
}

