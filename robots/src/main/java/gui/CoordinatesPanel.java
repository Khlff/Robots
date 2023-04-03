package gui;

import javax.swing.*;
import java.awt.*;

public class CoordinatesPanel extends JInternalFrame implements Panel {
    Model model;
    JPanel coordinatesPanel = new JPanel(new BorderLayout());
    private JLabel xLabel;
    private JLabel yLabel;
    CoordinatesPanel(Model model) {
        super("Координаты");
        xLabel = new JLabel();
        yLabel = new JLabel();
        coordinatesPanel.add(xLabel);
        coordinatesPanel.add(yLabel);
        xLabel.setText(String.valueOf(model.getM_robotPositionX()));
        yLabel.setText(String.valueOf(model.getM_robotPositionY()));
        this.model = model;
        model.addObserver(this);

        getContentPane().add(coordinatesPanel);
        pack();
    }

    @Override
    public void update() {

    }
}
