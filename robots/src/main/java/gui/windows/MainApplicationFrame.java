package gui.windows;

import gui.MVC.*;
import gui.MVC.Controller;
import gui.MVC.RobotModel;
import log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Locale;


import static gui.windows.ConstantsGUI.*;


public class MainApplicationFrame extends JFrame {
    private final JDesktopPane desktopPane = new JDesktopPane();

    public MainApplicationFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(SCREEN_OFFSET, SCREEN_OFFSET,
                screenSize.width - SCREEN_OFFSET * 2,
                screenSize.height - SCREEN_OFFSET * 2);

        setContentPane(desktopPane);

        JFileChooser fc = new JFileChooser();
        fc.setLocale(new Locale("ru", "RU"));
        fc.updateUI();

        LogWindow logWindow = createLogWindow();

        RobotModel robotModel = new RobotModel();
        SpikeModel spikeModel = new SpikeModel();
        Controller controller = new Controller(robotModel, spikeModel);
        GameWindow gameWindow = new GameWindow(controller);
        gameWindow.setSize(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
        gameWindow.setLocation(screenSize.width / 4, screenSize.height / 10);

        CoordinatesWindow coordinatesWindow = new CoordinatesWindow(controller);
        coordinatesWindow.setSize(COORDINATES_WIDTH, COORDINATES_HEIGHT);
        coordinatesWindow.setLocation(screenSize.width / 2, screenSize.height / 100);

        addWindow(coordinatesWindow);
        addWindow(gameWindow);
        addWindow(logWindow);

        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    protected LogWindow createLogWindow() {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(LOG_WINDOW_LOC_X, LOG_WINDOW_LOC_Y);
        logWindow.setSize(LOG_WINDOW_WIDTH, LOG_WINDOW_HEIGHT);
        setMinimumSize(logWindow.getSize());
        logWindow.pack();
        Logger.debug("Протокол работает");
        return logWindow;
    }

    protected void addWindow(JInternalFrame frame) {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private JMenuBar generateMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu lookAndFeelMenu = new JMenu("Режим отображения");
        lookAndFeelMenu.setMnemonic(KeyEvent.VK_V);
        lookAndFeelMenu.getAccessibleContext().setAccessibleDescription(
                "Управление режимом отображения приложения");

        {
            JMenuItem systemLookAndFeel = new JMenuItem("Системная схема", KeyEvent.VK_S);
            systemLookAndFeel.addActionListener((event) -> {
                setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                this.invalidate();
            });
            lookAndFeelMenu.add(systemLookAndFeel);
        }

        {
            JMenuItem crossplatformLookAndFeel = new JMenuItem("Универсальная схема", KeyEvent.VK_S);
            crossplatformLookAndFeel.addActionListener((event) -> {
                setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                this.invalidate();
            });
            lookAndFeelMenu.add(crossplatformLookAndFeel);
        }

        JMenu testMenu = new JMenu("Тесты");
        testMenu.setMnemonic(KeyEvent.VK_T);
        testMenu.getAccessibleContext().setAccessibleDescription(
                "Тестовые команды");

        {
            JMenuItem addLogMessageItem = new JMenuItem("Сообщение в лог", KeyEvent.VK_S);
            addLogMessageItem.addActionListener((event) -> Logger.debug("Новая строка"));
            testMenu.add(addLogMessageItem);
        }

        JMenuItem exitMenu = new JMenuItem("Выход");
        exitMenu.setMnemonic(KeyEvent.VK_E);
        exitMenu.getAccessibleContext().setAccessibleDescription(
                "Выйти из программы");
        exitMenu.addActionListener((event) -> exit(lookAndFeelMenu));


        menuBar.add(lookAndFeelMenu);
        menuBar.add(testMenu);
        menuBar.add(exitMenu);
        return menuBar;
    }

    /**
     * Метод выхода из программы.
     * Сначала закрывает все фреймы, затем происходит System.exit(0)
     *
     * @param item JMenu
     */
    protected void exit(JMenu item) {
        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(null);
        JLabel exitQuestion = new JLabel("Ты уверен что хочешь выйти?");

        exitQuestion.setVerticalAlignment(SwingConstants.TOP);
        exitQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        exitQuestion.setBounds(EXIT_QUESTION_BOUND_X, EXIT_QUESTION_BOUND_Y, EXIT_QUESTION_WIDTH, EXIT_QUESTION_HEIGHT);
        exitPanel.add(exitQuestion);

        UIManager.put("OptionPane.minimumSize", new Dimension(OPTION_PAIN_MINIMUM_SIZE_WIDTH, OPTION_PAIN_MINIMUM_SIZE_HEIGHT));

        int res = JOptionPane.showConfirmDialog(null, exitPanel, "Are you really want to exit?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (res == 0) {
            Container frame = item.getParent();
            do
                frame = frame.getParent();
            while (!(frame instanceof JFrame));
            ((JFrame) frame).dispose();

            System.exit(0);
        }
    }

    private void setLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);

            SwingUtilities.updateComponentTreeUI(this);

        } catch (ClassNotFoundException | InstantiationException
                 | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e.getMessage());
        }
    }
}
