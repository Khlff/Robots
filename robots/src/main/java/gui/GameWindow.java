package gui;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GameWindow extends JInternalFrame
{
    Model model;
    public GameWindow(Model model)
    {
        super("Игровое поле", true, true, true, true);
        this.model = model;
        ViewRobot viewRobot = new ViewRobot(this.model);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(viewRobot, BorderLayout.CENTER);
        this.model.addMouseListener(panel);
        getContentPane().add(panel);
        pack();
    }
}
