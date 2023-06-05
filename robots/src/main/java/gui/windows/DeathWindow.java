package gui.windows;

import gui.Game;

import javax.swing.*;
import java.awt.*;

import static gui.windows.ConstantsGUI.*;

public class DeathWindow {
    public DeathWindow(int record) {
        JPanel window = new JPanel();
        int score = Game.getInstance().getGameScore();
        JLabel title = new JLabel("Попробуйте ещё раз, твой рекорд - " + record);
        title.setVerticalAlignment(SwingConstants.TOP);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(EXIT_QUESTION_BOUND_X, EXIT_QUESTION_BOUND_Y, EXIT_QUESTION_WIDTH, EXIT_QUESTION_HEIGHT);
        window.add(title);
        UIManager.put("OptionPane.minimumSize", new Dimension(OPTION_PAIN_MINIMUM_SIZE_WIDTH, OPTION_PAIN_MINIMUM_SIZE_HEIGHT));
        JOptionPane.showConfirmDialog(null, window, "Вы проиграли, вас счёт - " + score,
                JOptionPane.DEFAULT_OPTION);
        System.exit(0);
    }
}
