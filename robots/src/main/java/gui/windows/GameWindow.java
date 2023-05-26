package gui.windows;

import gui.MVC.Controller;
import gui.MVC.View;

import java.awt.BorderLayout;

import javax.swing.*;

public class GameWindow extends JInternalFrame {
    Controller controller;

    public GameWindow(Controller controller) {
        super("Игровое поле", false, true, false, false);
        this.controller = controller;
        View view = new View(this.controller);
        ImagePanel panel = new ImagePanel(".\\robots\\src\\main\\resources\\objectTextures\\space.jpg");
        controller.addMouseListener(panel);
        setLayout(new BorderLayout());
        panel.add(view, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }
}
