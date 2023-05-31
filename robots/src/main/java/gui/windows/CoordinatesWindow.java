package gui.windows;

import gui.MVC.Controller;

import javax.swing.*;
import java.awt.*;

public class CoordinatesWindow extends JInternalFrame{
    public CoordinatesWindow(Controller controller)
    {
        CoordinatesObserver coordinatesPanel = new CoordinatesObserver(controller);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(coordinatesPanel, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}
