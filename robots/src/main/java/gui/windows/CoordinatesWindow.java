package gui.windows;

import gui.MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class CoordinatesWindow extends JInternalFrame{
    public CoordinatesWindow(Controller controller)
    {
//        super("Координаты", false, true, false, true);
        CoordinatesObserver coordinatesPanel = new CoordinatesObserver(controller);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(coordinatesPanel, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}
