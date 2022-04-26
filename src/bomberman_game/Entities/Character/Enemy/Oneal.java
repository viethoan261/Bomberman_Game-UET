/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities.Character.Enemy;

import bomberman_game.Entities.Character.Enemy.AI.AILow;
import bomberman_game.Graphics.Screen;
import bomberman_game.Graphics.Sprite;
import bomberman_game.Board;
import bomberman_game.Entities.Character.Enemy.AI.AIMedium;
import bomberman_game.Game;

/**
 *
 * @author Huong
 */
public class Oneal extends Enemy {

    public Oneal(int x, int y, Board board) {
        super(x, y, board, Sprite.oneal_dead, Game.getBomberSpeed(), 200);
        _speed = Game.getBomberSpeed()*board.getLevel().getLevel()/2;
                
        _sprite = Sprite.oneal_left1;
		
	    _ai = new AIMedium(_board.getBomber(), this);
        _direction  = _ai.calculateDirection();
    }
	
    @Override
    protected void chooseSprite() {
        switch(_direction) {

            case 0:
            case 1:
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, _animate, 60);
                } else {
                    _sprite = Sprite.oneal_left1;
                }
                break;
            case 2:
            case 3:
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, _animate, 60);
                } else {
                    _sprite = Sprite.oneal_left1;
                }
                break;
	}
    }
    
}
