package bomberman_game.Gui.Menu;

import bomberman_game.Gui.Frame;
import bomberman_game.Gui.InfoDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Help extends JMenu {
    //super("Help");

    public Help(Frame frame) {
        super("Help");

        /**
         * How to play.
         */
        JMenuItem tutorial = new JMenuItem("Tutorial");
        tutorial.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        tutorial.addActionListener(new MenuActionListener(frame));
        add(tutorial);

    }

    class MenuActionListener implements ActionListener {
        public Frame frame;

        public MenuActionListener(Frame frame) {
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Tutorial")) {
                new InfoDialog(frame, "How to Play", "Movement: UP, DOWN, RIGHT, LEFT or W, S, A, D" + "\n"
                        + "Put Bombs: SPACE or X" + "\n"
                        + "Pause Game: P" + "\n"
                        + "Resume Game: R", JOptionPane.QUESTION_MESSAGE);
            }
        }
    }
}
