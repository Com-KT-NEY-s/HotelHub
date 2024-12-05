package helpers;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

public class KeyboardHelper {
    private JRootPane rootPane;

    public KeyboardHelper(JRootPane rootPane) {
        this.rootPane = rootPane;
    }

    /**
     * set a button to be automatically pressed when the enter key is typed
     * @param button button to be pressed
     */
    public void setConfirmButton(JButton button) {
        addKeyBinding(button, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
    }

    /**
     * set a button to be automatically pressed when the escape key is typed
     * @param button button to be pressed
     */
    public void setCancelButton(JButton button) {
        addKeyBinding(button, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
    }

    /**
     * set a button to be automatically pressed when a keyboard shortcut is executed
     * @param button button to be pressed
     * @param shortcut keyboard shortcut (Ctrl+N = KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK)
     */
    public void setShortcutButton(JButton button, KeyStroke shortcut) {
        addKeyBinding(button, shortcut);
    }

    /**
     * automatically close a frame when the escape key is typed
     * @param frame jframe to be closed
     */
    public void setCloseOnEscape(JFrame frame) {
        addEscapeKeyBinding(frame);
    }

    /**
     * automatically close a dialog when the escape key is typed
     * @param dialog jdialog to be closed
     */
    public void setCloseOnEscape(JDialog dialog) {
        addEscapeKeyBinding(dialog);
    }

    private void addKeyBinding(JButton button, KeyStroke keyStroke) {
        String actionKey = "action_" + keyStroke.toString();

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, actionKey);
        rootPane.getActionMap().put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.doClick();
            }
        });
    }

    private void addEscapeKeyBinding(JFrame frame) {
        KeyStroke escapeKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        String actionKey = "action_escape_" + escapeKey.toString();

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKey, actionKey);
        rootPane.getActionMap().put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    private void addEscapeKeyBinding(JDialog dialog) {
        KeyStroke escapeKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        String actionKey = "action_escape_" + escapeKey.toString();

        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKey, actionKey);
        rootPane.getActionMap().put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
    }
}
