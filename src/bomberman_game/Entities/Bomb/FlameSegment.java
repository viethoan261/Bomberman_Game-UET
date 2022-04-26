/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities.Bomb;

import bomberman_game.Entities.Entity;
import bomberman_game.Graphics.Screen;
import bomberman_game.Graphics.Sprite;
import bomberman_game.Entities.Character.Character;
import bomberman_game.Board;

/**
 *
 * @author Huong
 */
public class FlameSegment extends Entity {

    protected boolean _last = false;
    protected Board _board;
    /**
     *
     * @param x
     * @param y
     * @param direction
     * @param last cho biết segment này là cuối cùng của Flame hay không,
     *                segment cuối có sprite khác so với các segment còn lại
     * @param board
     */
    public FlameSegment(int x, int y, int direction, boolean last, Board board) {
        _x = x;
        _y = y;
        _last = last;
        _board = board;

        switch (direction) {
            case 0:
                if (last == false) {
                    _sprite = Sprite.explosion_vertical2;
                } else {
                    _sprite = Sprite.explosion_vertical_top_last2;
                }
                break;
            case 1:
                if (last == false) {
                    _sprite = Sprite.explosion_horizontal2;
                } else {
                    _sprite = Sprite.explosion_horizontal_right_last2;
                }
                break;
            case 2:
                if (last == false) {
                    _sprite = Sprite.explosion_vertical2;
                } else {
                    _sprite = Sprite.explosion_vertical_down_last2;
                }
                break;
            case 3:
                if (last == false) {
                    _sprite = Sprite.explosion_horizontal2;
                } else {
                    _sprite = Sprite.explosion_horizontal_left_last2;
                }
                break;
        }
    }

    @Override
    public void render(Screen screen) {
        int xt = (int)_x << 4;
        int yt = (int)_y << 4;

        screen.renderEntity(xt, yt , this);
    }

    @Override
    public void update() {}

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý khi FlameSegment va chạm với Character
        if (e instanceof Character) {
            ((Character)e).kill();
        }
        return true;
    }
}
