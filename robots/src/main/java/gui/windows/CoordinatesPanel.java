package gui.windows;

import gui.MVC.Controller;
import gui.MVC.Panel;

import javax.swing.*;
import java.awt.*;

public class CoordinatesPanel extends JPanel implements Panel {
    private Controller controller;
    JPanel coordinatesPanel = new JPanel(new BorderLayout());
    JLabel xLabel = new JLabel();
    JLabel yLabel = new JLabel();

    public CoordinatesPanel(Controller controller) {
        this.controller = controller;
        this.controller.getRobotModel().addObserver(this);
        add(yLabel);
        add(xLabel);
        xLabel.setText(String.valueOf(round(controller.getRobotModel().getXCoordinate())));
        yLabel.setText(String.valueOf(round(controller.getRobotModel().getXCoordinate())));
    }

    private int round(double value) {
        return (int) (value + 0.5);
    }

    @Override
    public void update() {
        xLabel.setText(String.valueOf(round((controller.getRobotModel().getXCoordinate()))));
        yLabel.setText(String.valueOf(round((controller.getRobotModel().getYCoordinate()))));
    }
}
