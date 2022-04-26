/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game;

//import bomberman_game.Exceptions.LoadLevelEXception;
import bomberman_game.Gui.Frame;
import bomberman_game.Gui.Start;
import bomberman_game.Sound.Sound;

import java.awt.event.MouseEvent;

/**
 *
 * @author Huong
 */
public class Bomberman_Game {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Sound.playBackGround();
        Frame f = new Frame();
        //Start start = new Start();
        //new MenuGame().setVisible(true);

    }
    
}
