/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities.Character;

import bomberman_game.Board;
import bomberman_game.Entities.AnimatedEntity;
import bomberman_game.Graphics.Screen;
import bomberman_game.Game;

/**
 *
 * @author Huong
 */
public abstract class Character extends AnimatedEntity {
    protected Board _board;
    protected int _direction = -1;
    protected boolean _alive = true;
    protected boolean _moving = false;
    public int _timeAfter = 40;

    public Character(int x, int y, Board board) {
        _x = x;
        _y = y;
        _board = board;
    }

    @Override
    public abstract void update();

    @Override
    public abstract void render(Screen screen);

    /**
     * Tính toán hướng đi
     */
    protected abstract void calculateMove();

    protected abstract void move(double xa, double ya);

    /**
     * Được gọi khi đối tượng bị tiêu diệt
     */
    public abstract void kill();

    /**
     * Xử lý hiệu ứng bị tiêu diệt
     */
    protected abstract void afterKill();

    /**
     * Kiểm tra xem đối tượng có di chuyển tới vị trí đã tính toán hay không
     * @param x
     * @param y
     * @return
     */
    protected abstract boolean canMove(double x, double y);

    protected double getXMessage() {
        return (_x * Game.SCALE) + (_sprite.SIZE / 2 * Game.SCALE);
    }

    protected double getYMessage() {
        return (_y* Game.SCALE) - (_sprite.SIZE / 2 * Game.SCALE);
    }
}
