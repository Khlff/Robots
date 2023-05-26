package gui.MVC;

import gui.Game;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;


public class RobotView extends JPanel implements Observer {
    Controller controller;
    JLabel score = new JLabel();

    private static Timer initTimer() {
        return new Timer("events generator", true);
    }

    public RobotView(Controller controller) {
        add(score);
        controller.addObserver(this);
        score.setText(String.valueOf(Game.getInstance().getScoreOfGame()));
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
                controller.onModelUpdateEvent();
            }
        }, 0, 10);
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }


    private static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private void drawRobot(Graphics2D g, double direction, int robotSize) {
        int robotCenterX = (int) controller.getRobotModel().getXCoordinate();
        int robotCenterY = (int) controller.getRobotModel().getYCoordinate();
        AffineTransform t = AffineTransform.getRotateInstance(direction, robotCenterX, robotCenterY);
        g.setTransform(t);
        g.setColor(Color.MAGENTA);
        fillOval(g, robotCenterX, robotCenterY, robotSize, robotSize);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, robotSize, robotSize);
    }

    private void drawTarget(Graphics2D g, int x, int y, int targetSize) {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
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
        drawRobot(g2d, controller.getRobotModel().getRobotDirection(), controller.getRobotModel().getSize());
        for (TargetModel target : controller.getTargets()) {
            drawTarget(g2d, round(target.getXCoordinate()),
                    round(target.getYCoordinate()), target.getSize());
        }
    }

    @Override
    public void update() {
        score.setText(String.valueOf(Game.getInstance().getScoreOfGame()));
    }
}
