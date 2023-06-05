package gui.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.windows.ConstantsGUI.START_MENU_HEIGHT;
import static gui.windows.ConstantsGUI.START_MENU_WIDTH;

public class StartMenu extends JFrame implements ActionListener {

    private final JTextField textField;

    public StartMenu() {
        super("AGAROSPACE");
        setSize(START_MENU_WIDTH, START_MENU_HEIGHT);
        setResizable(false);

        ImageIcon icon = new ImageIcon(".\\robots\\src\\main\\resources\\windowsTextures\\space.jpg");
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(START_MENU_WIDTH, START_MENU_HEIGHT));
        label.setBounds(0, 0, START_MENU_WIDTH, START_MENU_HEIGHT);
        add(label);

        JLabel descriptionLabel = new JLabel("<html><center><p>Ты чёрная дыра!</p><p>Кушай планеты и не попадай на инопланетян!</p></center></html>");
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        descriptionLabel.setBounds(START_MENU_WIDTH / 8, 0, START_MENU_WIDTH, START_MENU_HEIGHT / 4);
        label.add(descriptionLabel);

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setBounds(START_MENU_WIDTH / 4, START_MENU_HEIGHT / 2, START_MENU_WIDTH / 2, START_MENU_HEIGHT);
        textField = new JTextField(20);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel textLabel = new JLabel("Введите свое имя:");
        textLabel.setForeground(Color.WHITE);

        inputPanel.add(textLabel);
        inputPanel.add(textField);
        label.add(inputPanel);

        JButton startButton = new JButton("Начать игру!");
        startButton.setFont(new Font("Arial", Font.PLAIN, 16));
        startButton.setFocusPainted(false);
        startButton.setBounds(START_MENU_WIDTH / 4, (int) (START_MENU_HEIGHT / 1.2), 300, 60);
        startButton.addActionListener(this);
        label.add(startButton);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Запускаем окно игры при нажатии на кнопку startButton.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String playerName = textField.getText(); // используем для записи рекордов
        dispose();
        EventQueue.invokeLater(() -> {
            MainApplicationFrame frame = new MainApplicationFrame(playerName);
            frame.pack();
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        });
    }
}