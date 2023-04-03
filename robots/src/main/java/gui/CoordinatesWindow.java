package gui;

import javax.swing.*;
import java.awt.*;

public class CoordinatesWindow extends JInternalFrame{
    private Model model;
    public CoordinatesWindow(Model model)
    {
        super("Координаты", true, true, true, true);
        this.model = model;
        CoordinatesPanel coordinatesPanel = new CoordinatesPanel(model);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(coordinatesPanel, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}
