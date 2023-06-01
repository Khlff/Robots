package gui;

import gui.windows.MainApplicationFrame;

import java.awt.Frame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class RobotsProgram
{
    public static void main(String[] args) {
      try {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
      } catch (Exception e) {
        e.printStackTrace();
      }
      SwingUtilities.invokeLater(() -> {
        MainApplicationFrame frame = null;
        try {
          frame = new MainApplicationFrame();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
      });
    }}
