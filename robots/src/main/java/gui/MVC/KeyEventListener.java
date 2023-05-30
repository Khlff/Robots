package gui.MVC;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyEventListener extends KeyAdapter {
    private final RobotModel robot;
    private boolean isWPressed = false;
    private boolean isAPressed = false;
    private boolean isSPressed = false;
    private boolean isDPressed = false;

    public KeyEventListener(RobotModel robot) {
        this.robot = robot;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keyPressed(e);
    }

    /**
     * Устанавливает направления в зависимости от нажатых кнопок.
     *
     * @param e - нажатая кнопка.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W -> {
                isWPressed = true;
                if (isAPressed) {
                    robot.setMovementUpLeft();
                } else if (isDPressed) {
                    robot.setMovementUpRight();
                } else {
                    robot.setMovementUp();
                }
            }
            case KeyEvent.VK_S -> {
                isSPressed = true;
                if (isAPressed) {
                    robot.setMovementDownLeft();
                } else if (isDPressed) {
                    robot.setMovementDownRight();
                } else {
                    robot.setMovementDown();
                }
            }
            case KeyEvent.VK_A -> {
                isAPressed = true;
                if (isSPressed) {
                    robot.setMovementDownLeft();
                } else if (isWPressed) {
                    robot.setMovementUpLeft();
                } else {
                    robot.setMovementLeft();
                }
            }
            case KeyEvent.VK_D -> {
                isDPressed = true;
                if (isWPressed) {
                    robot.setMovementUpRight();
                } else if (isSPressed) {
                    robot.setMovementDownRight();
                } else {
                    robot.setMovementRight();
                }
            }
        }
    }

    /**
     * Делает состояние отжатой кнопки неактивным.
     *
     * @param e - отжатая кнопка.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> isWPressed = false;
            case KeyEvent.VK_A -> isAPressed = false;
            case KeyEvent.VK_S -> isSPressed = false;
            case KeyEvent.VK_D -> isDPressed = false;
        }
    }
}
