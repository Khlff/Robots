package gui.MVC;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

import static gui.MVC.ModelsConstants.*;

public class RobotView extends JPanel {
    Controller controller;


    private static Timer initTimer() {
        return new Timer("events generator", true);
    }

    public RobotView(Controller controller) {
        this.controller = controller;
        Timer m_timer = initTimer();
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onRedrawEvent();
            }
        }, 0, 50);
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onModelUpdateEvent();
            }
        }, 0, 10);
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    protected void onModelUpdateEvent() {
        double distance = controller.distance();

        if (distance < 0.5) {
            return;
        }

        double angleToTarget = controller.angleTo();
        double angularVelocity = 0;

        if (angleToTarget > controller.getRobotModel().getRobotDirection()) {
            angularVelocity = DEFAULT_ROBOT_ANGULAR_VELOCITY;
        }
        if (angleToTarget < controller.getRobotModel().getRobotDirection()) {
            angularVelocity = -DEFAULT_ROBOT_ANGULAR_VELOCITY;
        }

        controller.getRobotModel().moveRobot(DEFAULT_ROBOT_VELOCITY, angularVelocity, 10);
    }

    private static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private void drawRobot(Graphics2D g, double direction) {
        int robotCenterX = (int) controller.getRobotModel().getXCoordinate();
        int robotCenterY = (int) controller.getRobotModel().getYCoordinate();
        AffineTransform t = AffineTransform.getRotateInstance(direction, robotCenterX, robotCenterY);
        g.setTransform(t);
        g.setColor(Color.MAGENTA);
        fillOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.WHITE);
        fillOval(g, robotCenterX + 10, robotCenterY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX + 10, robotCenterY, 5, 5);
    }

    private void drawTarget(Graphics2D g, int x, int y) {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, x, y, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, x, y, 5, 5);
    }

    protected static int round(double value) {
        return (int) (value + 0.5);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        drawRobot(g2d, controller.getRobotModel().getRobotDirection());
        drawTarget(g2d, round(controller.getTargetModel().getXCoordinate()),
                round(controller.getTargetModel().getYCoordinate()));
    }
}
