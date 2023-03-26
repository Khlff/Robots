package gui;

import javax.swing.*;
import java.awt.*;

public class CoordinatesPanel extends JPanel implements Panel {
    Model model;
    JPanel coordinatesPanel = new JPanel(new BorderLayout());
    JLabel xLabel = new JLabel("a");
    JLabel yLabel = new JLabel("b");

    CoordinatesPanel(Model model) {
        add(yLabel);
        add(xLabel);
        xLabel.setText(String.valueOf(round(model.getM_robotPositionX())));
        yLabel.setText(String.valueOf(round(model.getM_robotPositionY())));

        this.model = model;
        model.addObserver(this);

    }

    private int round(double value) {
        return (int) (value + 0.5);
    }

    @Override
    public void update() {
        xLabel.setText(String.valueOf(round(model.getM_robotPositionX())));
        yLabel.setText(String.valueOf(round(model.getM_robotPositionY())));
    }
}
