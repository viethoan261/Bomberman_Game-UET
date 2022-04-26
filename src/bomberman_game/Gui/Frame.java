/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Gui;

import bomberman_game.Exceptions.LoadLevelException;
import bomberman_game.Game;
import bomberman_game.Gui.Menu.Menu;

import java.awt.*;
import javax.swing.*;


/**
 *
 * @author Huong
 */
public class Frame extends JFrame {
    
    //public MenuGame _menugame;
    public GamePanel _gamepane;
    private JPanel _containerpane;
    private InfoPanel _infopanel;
    //private Start start = new Start();
	
    private Game _game;

    public Frame() {
        setJMenuBar(new Menu(this));
        _containerpane = new JPanel(new BorderLayout());
	    _gamepane = new GamePanel(this);
        _infopanel = new InfoPanel(_gamepane.getGame());
		
	    _containerpane.add(_infopanel, BorderLayout.PAGE_START);
        _containerpane.add(_gamepane, BorderLayout.PAGE_END);
		
	    _game = _gamepane.getGame();
		
        add(_containerpane);
		
	    setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
	    setVisible(true);

	    //start = new Start();
        _game.start();                
    }

    public void newGame() {
        _game.getBoard().newGame();
    }

    public void pauseGame() {
        _game.getBoard().gamePause();
    }

	public void resumeGame() {
        _game.getBoard().gameResume();
    }

    public void setTime(int time) {
        _infopanel.setTime(time);
    }
	
    public void setPoints(int points) {
        _infopanel.setPoints(points);
    }
	
    public void setHeartBomber(int hearts){
        _infopanel.setHearts(hearts);
    }
        
    public void setAmountEnemy(int num){
        _infopanel.setAmountEnemy(num);
    }
    
}
