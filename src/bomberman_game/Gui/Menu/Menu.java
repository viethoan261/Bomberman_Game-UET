package bomberman_game.Gui.Menu;

import bomberman_game.Gui.Frame;

import javax.swing.*;

public class Menu extends JMenuBar {
    public Menu(Frame frame) {
        add(new Game(frame));
        add(new Help(frame));
    }
}
