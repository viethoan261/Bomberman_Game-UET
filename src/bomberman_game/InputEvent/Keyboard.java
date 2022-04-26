/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.InputEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Huong
 */
public class Keyboard implements KeyListener {
    
    private boolean[] keys = new boolean[120];
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean space;
    public boolean pause;
    public boolean continuee;
    
    public void update() {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
	    down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
	    left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
	    right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
	    space = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_X];
        pause = keys[KeyEvent.VK_P];
        continuee = keys[KeyEvent.VK_R];
    }
            

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    
}
