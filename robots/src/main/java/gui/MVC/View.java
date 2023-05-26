package gui.MVC;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyEvent;

import static gui.MVC.Controller.initTimer;
import static gui.windows.ConstantsGUI.GAME_WINDOW_HEIGHT;
import static gui.windows.ConstantsGUI.GAME_WINDOW_WIDTH;

public class View extends JPanel {
    Controller controller;

    public View(Controller controller) {
        this.controller = controller;
        Timer viewTimer = initTimer();
        viewTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                onRedrawEvent();
            }
        }, 0, 5);
        viewTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                onModelUpdateEvent();
            }
        }, 0, 5);
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    protected void onModelUpdateEvent() {
        ArrayList<Double> distances = controller.calculateDistance();
        for (Double distance : distances) {
            if (distance <= (double) controller.getRobotModel().getSize() / 2) {
                controller.getRobotModel().setSize(controller.getRobotModel().getSize() + 10);
                controller.generateNewTargetCoordinates(distances.indexOf(distance));
            }
            if (distance < 0.5) {
                return;
            }
        }
    }

    private static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private void drawRobot(Graphics2D g, int robotSize) {
        int robotCenterX = (int) controller.getRobotModel().getXCoordinate();
        int robotCenterY = (int) controller.getRobotModel().getYCoordinate();
        g.setColor(Color.MAGENTA);
        fillOval(g, robotCenterX, robotCenterY, robotSize, robotSize);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, robotSize, robotSize);
    }

    private void drawTarget(Graphics2D g, int x, int y, int targetSize) {
        g.setColor(Color.GREEN);
        fillOval(g, x, y, targetSize, targetSize);
        g.setColor(Color.BLACK);
        drawOval(g, x, y, targetSize, targetSize);
    }

    protected static int round(double value) {
        return (int) (value + 0.5);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        drawRobot(g2d, controller.getRobotModel().getSize());
        for (TargetModel target : controller.getTargets()) {
            drawTarget(g2d, round(target.getXCoordinate()),
                    round(target.getYCoordinate()), target.getSize());
        }
    }
}
