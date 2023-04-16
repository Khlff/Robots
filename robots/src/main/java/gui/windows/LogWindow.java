package gui.windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import log.LogChangeListener;
import log.LogEntry;
import log.LogWindowSource;

import static gui.windows.ConstantsGUI.*;

public class LogWindow extends JInternalFrame implements LogChangeListener
{
    private final LogWindowSource logWindow;
    private final TextArea logText;

    public LogWindow(LogWindowSource logSource) 
    {
        super("Протокол работы", true, true, true, true);
        logWindow = logSource;
        logWindow.registerListener(this);
        logText = new TextArea("");
        logText.setSize(LOG_TEXT_WIDTH, LOG_TEXT_HEIGHT);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(logText, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
        updateLogContent();
    }

    private void updateLogContent()
    {
        StringBuilder content = new StringBuilder();
        for (LogEntry entry : logWindow.all())
        {
            content.append(entry.getMessage()).append("\n");
        }
        logText.setText(content.toString());
        logText.invalidate();
    }
    
    @Override
    public void onLogChanged()
    {
        EventQueue.invokeLater(this::updateLogContent);
    }
}
