package gui.MVC;

import gui.MVC.RobotModel;
import gui.MVC.TargetModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller {

    private RobotModel robotModel;
    private TargetModel targetModel;

    public Controller(RobotModel robotModel, TargetModel targetModel) {
        this.targetModel = targetModel;
        this.robotModel = robotModel;

    }

    public RobotModel getRobotModel() {
        return robotModel;
    }

    public TargetModel getTargetModel() {
        return targetModel;
    }

    public void addMouseListener(JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                targetModel.setTargetPosition(e.getPoint());

            }
        });
    }

    protected double distance() {
        double diffX = this.targetModel.getXCoordinate() - this.robotModel.getXCoordinate();
        double diffY = this.targetModel.getYCoordinate() - this.robotModel.getYCoordinate();
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    protected double angleTo() {
        double diffX = targetModel.getXCoordinate() - robotModel.getXCoordinate();
        double diffY = targetModel.getXCoordinate() - robotModel.getYCoordinate();
        return RobotModel.asNormalizedRadians(Math.atan2(diffY, diffX));
    }
}

