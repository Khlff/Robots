package gui.windows;

import java.awt.*;
import javax.swing.*;

import static gui.windows.ConstantsGUI.GAME_WINDOW_HEIGHT;
import static gui.windows.ConstantsGUI.GAME_WINDOW_WIDTH;

/**
 * Класс создания панели с фоновой картинкой
 */
public class ImagePanel extends JPanel {
    private final Image backgroundImage;

    public ImagePanel(String fileName) {
        this.backgroundImage = new ImageIcon(fileName).getImage();
        setSize(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
        setLayout(new BorderLayout());
        add(new JLabel(new ImageIcon(backgroundImage)), BorderLayout.CENTER);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }
}
