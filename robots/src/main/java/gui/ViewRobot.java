package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ViewRobot {
    JPanel gamePanel = new JPanel();
    JPanel coordinates = new JPanel();
    JFrame jFrame;
    ViewRobot(JFrame jFrame) {
        this.jFrame = jFrame;

        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point target = e.getPoint();
                System.out.println(target);
            }
        });
        jFrame.add(gamePanel);
        jFrame.add(coordinates);
        gamePanel.setVisible(true);
        coordinates.setVisible(true);
        jFrame.pack();
        gamePanel.setDoubleBuffered(true);
    }
}
