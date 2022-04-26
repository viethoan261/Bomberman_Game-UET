/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Gui;

import bomberman_game.Exceptions.LoadLevelException;

import bomberman_game.Game;
import java.awt.*;
import javax.swing.*;


/**
 *
 * @author Huong
 */
public class GamePanel extends JPanel {
    private Game _game;
	
    public GamePanel(Frame frame) {
        setLayout(new BorderLayout());
	    setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));

        _game = new Game(frame);

        add(_game);

        _game.setVisible(true);

	    setVisible(true);
        setFocusable(true);
		
    }

    public Game getGame() {
        return _game;
    }
}
