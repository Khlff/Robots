package gui.windows;
import gui.Game;
import gui.RobotsProgram;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static gui.windows.ConstantsGUI.*;
import static gui.windows.ConstantsGUI.EXIT_QUESTION_HEIGHT;

public class DeathWindow{
    public DeathWindow(int score, int record){
        JPanel window = new JPanel();
        score = Game.getInstance().getScoreOfGame();
        JLabel title = new JLabel("Попробуйте ещё раз, твой рекорд - " + String.valueOf(record));
        title.setVerticalAlignment(SwingConstants.TOP);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(EXIT_QUESTION_BOUND_X, EXIT_QUESTION_BOUND_Y, EXIT_QUESTION_WIDTH, EXIT_QUESTION_HEIGHT);
        window.add(title);
        UIManager.put("OptionPane.minimumSize", new Dimension(OPTION_PAIN_MINIMUM_SIZE_WIDTH, OPTION_PAIN_MINIMUM_SIZE_HEIGHT));
        int result = JOptionPane.showConfirmDialog(null, window, "Вы проиграли, вас счёт - " + String.valueOf(score),
                JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }
}
