package gui.windows;

import gui.MVC.Controller;
import gui.MVC.View;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JInternalFrame {
    Controller controller;

    public GameWindow(Controller controller, String playerName) {
        super("Игровое поле", false, true, false, false);
        this.controller = controller;
        View view = new View(this.controller, playerName);
        ImagePanel panel = new ImagePanel(".\\robots\\src\\main\\resources\\objectTextures\\space.jpg");
        controller.addKeyListener(panel);
        setLayout(new BorderLayout());
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.add(view, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}
