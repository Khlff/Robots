package gui.windows;

import gui.MVC.Controller;
import gui.MVC.RobotView;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GameWindow extends JInternalFrame
{
    Controller controller;
    public GameWindow(Controller controller)
{
    super("Игровое поле", true, true, true, true);
    this.controller = controller;
    RobotView robotView = new RobotView(this.controller);
    JPanel panel = new JPanel(new BorderLayout());
    controller.addMouseListener(panel);
    panel.add(robotView, BorderLayout.CENTER);
    getContentPane().add(panel);
    pack();
}
}
