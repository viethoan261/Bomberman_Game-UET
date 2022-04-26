/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities.Tile;

import bomberman_game.Entities.Character.Bomber;
import bomberman_game.Entities.Entity;
import bomberman_game.Graphics.Sprite;
import bomberman_game.Board;

/**
 *
 * @author Huong
 */
public class Portal extends Tile {

    protected Board _board;
    
    public Portal(int x, int y, Board _board, Sprite sprite) {
        super(x, y, sprite);
        this._board = _board; 
    }
	
    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý khi Bomber đi vào
                 
        if (e instanceof  Bomber) {
            if (_board.detectNoEnemies() == false) {
                return false;
            }
             
            try {
                if (_board.getNumLevel() < 5) {
                    //soundPassgame.playAgain();
                    _board.nextLevel();
                } else {
                    Board.checkWinGame = true; 
                    System.out.println("Victory");
                }
            } catch (Exception ex) {
                Board.checkWinGame = true; 
                System.out.println("Victory");
            }
			
            return true;
	    }
		
        return false;
    }

}
