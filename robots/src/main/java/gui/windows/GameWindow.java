package gui.windows;

import gui.MVC.Controller;
import gui.MVC.View;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GameWindow extends JInternalFrame
{
    Controller controller;
    public GameWindow(Controller controller)
{
    super("Игровое поле", false, false, false, false);
    this.controller = controller;
    View view = new View(this.controller);
    JPanel panel = new JPanel(new BorderLayout());
    panel.setFocusable(true);
    panel.requestFocusInWindow();
    controller.addKeyListener(panel);
    panel.add(view, BorderLayout.CENTER);
    getContentPane().add(panel);
    pack();
}
}
