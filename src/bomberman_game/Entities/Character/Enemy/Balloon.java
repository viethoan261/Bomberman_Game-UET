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
import bomberman_game.Game;

/**
 *
 * @author Huong
 */
public class Balloon extends Enemy {
    public Balloon(int x, int y, Board board) {
        super(x, y, board, Sprite.balloom_dead, Game.getBomberSpeed() / 2, 100);
        _speed = Game.getBomberSpeed() * board.getLevel().getLevel() / 2;

        _sprite = Sprite.balloom_left1;

        _ai = new AILow();
        _direction = _ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                _sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, _animate, 60);
                break;

            case 2:
            case 3:
                _sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, _animate, 60);
                break;
        }
    }
}
