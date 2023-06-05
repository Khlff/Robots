package gui.MVC;

import gui.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import static gui.MVC.Controller.initTimer;
import static gui.MVC.ModelsConstants.DEFAULT_SPIKE_SIZE;

public class View extends JPanel {
    Controller controller;
    private final String playerName;
    private final HashMap<String, BufferedImage> textureCache = new HashMap<>();
    JLabel score = new JLabel();

    public View(Controller controller, String playerName) {
        this.controller = controller;
        this.playerName = playerName;

        add(score);
        score.setText(String.valueOf(Game.getInstance().getScoreOfGame()));

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
                controller.generateNewTargetTexture(distances.indexOf(distance));
                Game.getInstance().addScoreOfGame();
                score.setText(String.valueOf(Game.getInstance().getScoreOfGame()));
            }

            if (controller.distanceToSpike() <= (double) DEFAULT_SPIKE_SIZE / 2) {
                System.out.println(playerName + " набрал " + score.getText() + " очков.");
                System.exit(0);
            }

            if (distance < 0.5) {
                return;
            }
        }
    }

    /**
     * Функция возвращает текстуру из HashMap, если она там есть, если её там нет - хэширует её.
     *
     * @param texturePath путь к текстуре.
     * @return объект текстуры
     */
    private BufferedImage getTexture(String texturePath) {
        BufferedImage texture = textureCache.get(texturePath);
        if (texture == null) {
            try {
                texture = ImageIO.read(new File(texturePath));
                textureCache.put(texturePath, texture);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return texture;
    }

    /**
     * Функция, которая отвечает за отрисовку модели.
     *
     * @param model       модель, у которой отрисовываем
     * @param texturePath путь, к текстуре
     */
    private void draw(Graphics2D g, GameEntity model, String texturePath) {
        int modelCenterX = (int) model.getXCoordinate();
        int modelCenterY = (int) model.getYCoordinate();

        BufferedImage texture = getTexture(texturePath);
        if (texture != null) {
            g.drawImage(
                    texture,
                    modelCenterX - model.getSize() / 2,
                    modelCenterY - model.getSize() / 2,
                    model.getSize(), model.getSize(),
                    null
            );
        }

    }

    /**
     * Функция отрисовки всех объектов
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d, controller.getRobotModel(), controller.getRobotModel().texturePath);
        draw(g2d, controller.getSpikeModel(), controller.getSpikeModel().texturePath);
        for (TargetModel target : controller.getTargets()) {
            draw(g2d, target, target.texturePath);
        }
    }
}
