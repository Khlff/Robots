package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import log.Logger;


public class MainApplicationFrame extends JFrame {
    private final JDesktopPane desktopPane = new JDesktopPane();

    public MainApplicationFrame() {
        //Make the big window be indented 50 pixels from each edge
        //of the screen.
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width - inset * 2,
                screenSize.height - inset * 2);

        setContentPane(desktopPane);
//        setLocale(new Locale("RUS")); не работает

        LogWindow logWindow = createLogWindow();
        addWindow(logWindow);

        GameWindow gameWindow = new GameWindow();
        gameWindow.setSize(400, 400);
        addWindow(gameWindow);

        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    protected LogWindow createLogWindow() {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(10, 10);
        logWindow.setSize(300, 800);
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

    protected void exit(JMenuItem item) {

        JPanel panel = new JPanel();
        panel.setSize(new Dimension(250, 100));
        panel.setLayout(null);
        JLabel label2 = new JLabel("Ты уверен что хочешь выйти?");
        label2.setVerticalAlignment(SwingConstants.TOP);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setBounds(20, 80, 200, 20);
        panel.add(label2);
        UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));
        int res = JOptionPane.showConfirmDialog(null, panel, "File",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (res == 0) {
            //Закратие всех фреймов
            Container frame = item.getParent();
            do
                frame = frame.getParent();
            while (!(frame instanceof JFrame));
            ((JFrame) frame).dispose();
            // Завершение рантайма
            System.exit(0);
        }
    }

    private void setLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);

            SwingUtilities.updateComponentTreeUI(this);

        } catch (ClassNotFoundException | InstantiationException
                 | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // just ignore
        }
    }
}
