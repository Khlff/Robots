package gui.MVC;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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


    /**
     * Функция, которая отвечает за отрисовку текстуры модели.
     *
     * @param model       модель, у которой отрисовываем
     * @param texturePath путь, к текстуре
     */
    private void draw(Graphics2D g, GameEntity model, String texturePath) {
        int modelCenterX = (int) model.getXCoordinate();
        int modelCenterY = (int) model.getYCoordinate();

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
