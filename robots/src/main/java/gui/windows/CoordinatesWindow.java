package gui.windows;

import gui.MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class CoordinatesWindow extends JInternalFrame{
    private Controller controller;
    public CoordinatesWindow(Controller controller)
    {
        super("Координаты", false, true, false, true);
        this.controller = controller;
        CoordinatesObserver coordinatesPanel = new CoordinatesObserver(controller);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(coordinatesPanel, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}
