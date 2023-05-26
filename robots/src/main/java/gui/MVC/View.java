package gui.MVC;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static gui.MVC.ModelsConstants.*;

public class View extends JPanel {
    Controller controller;

    private static Timer initTimer() {
        return new Timer("events generator", true);
    }

    public View(Controller controller) {
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
        ArrayList<Double> distances = controller.distance();
        for (Double distance : distances) {
            if (distance < (double) controller.getRobotModel().getSize() / 2 && distance > (double) controller.getRobotModel().getSize() / 2 - 1) {
                controller.getRobotModel().setSize(controller.getRobotModel().getSize() + 10);
                controller.generateNewTargetCoordinates(distances.indexOf(distance));
                controller.setRobotSpeed(controller.getRobotSpeed() - 0.5);
            }
            if (distance < 0.5) {
                return;
            }
        }

        double angleToTarget = controller.angleTo();
        double angularVelocity = 0;

        if (RobotModel.asNormalizedRadians(controller.getRobotModel().getRobotDirection() - angleToTarget) > Math.PI) {
            angularVelocity = DEFAULT_ROBOT_ANGULAR_VELOCITY;
        } else if (controller.getRobotModel().getRobotDirection() != angleToTarget) {
            angularVelocity = -DEFAULT_ROBOT_ANGULAR_VELOCITY;
        }

        controller.getRobotModel().move(angularVelocity);
    }


    /**
     * Функция, которая отвечает за отрисовку текстуры модели.
     *
     * @param model       модель, у которой отрисовываем
     * @param texturePath путь, к текстуре
     */
    private void draw(Graphics2D g, GameEntity model, String texturePath) {
        int modelCenterX = (int) model.getXCoordinate();
        int modelCenterY = (int) model.getYCoordinate();
        AffineTransform t = AffineTransform.getRotateInstance(0, modelCenterX, modelCenterY);
        g.setTransform(t);

        BufferedImage texture;
        try {
            texture = ImageIO.read(new File(texturePath));
            g.drawOval(
                    modelCenterX - model.getSize() / 2,
                    modelCenterY - model.getSize() / 2,
                    model.getSize(), model.getSize()
            );
            g.drawImage(
                    texture,
                    modelCenterX - model.getSize() / 2 - 2,
                    modelCenterY - model.getSize() / 2 - 2,
                    modelCenterX + model.getSize(),
                    modelCenterY + model.getSize(),
                    0, 0, texture.getWidth(),
                    texture.getHeight(),
                    new Color(0, 0, 0, 0),
                    null
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d, controller.getRobotModel(), ".\\robots\\src\\main\\resources\\objectTextures\\black-hole.png");
        for (TargetModel target : controller.getTargets()) {
            draw(g2d, target, ".\\robots\\src\\main\\resources\\objectTextures\\planet.png");
        }
    }
}
