package bomberman_game.Gui.Menu;

import bomberman_game.Gui.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Game extends JMenu {
    public Frame frame;

    public Game(Frame frame) {
        super("Game");
        this.frame = frame;

        /**
         * New Game.
         */
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newGame.addActionListener(new MenuActionListener(frame));
        add(newGame);

        /**
         * Pause game.
         */
        JMenuItem pauseGame = new JMenuItem("Pause Game");
        pauseGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        pauseGame.addActionListener(new MenuActionListener(frame));
        add(pauseGame);

        /**
         * Resume game.
         */
        JMenuItem resumeGame = new JMenuItem("Resume Game");
        resumeGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        resumeGame.addActionListener(new MenuActionListener(frame));
        add(resumeGame);
    }

    class MenuActionListener implements ActionListener {
        public Frame _frame;
        public MenuActionListener(Frame frame) {
            _frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equals("New Game")) {
                _frame.newGame();
            } else if (e.getActionCommand().equals("Pause Game")) {
                _frame.pauseGame();
            } else if (e.getActionCommand().equals("Resume Game")) {
                _frame.resumeGame();
            }

        }
    }

}
