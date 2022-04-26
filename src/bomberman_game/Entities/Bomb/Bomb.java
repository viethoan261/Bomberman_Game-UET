/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities.Bomb;

import bomberman_game.Entities.AnimatedEntity;
import bomberman_game.Entities.Character.Bomber;
import bomberman_game.Entities.Character.Character;
import bomberman_game.Entities.Entity;
import bomberman_game.Graphics.Screen;
import bomberman_game.Graphics.Sprite;
import bomberman_game.Level.Coordinates;
import bomberman_game.Board;
import bomberman_game.Game;
import bomberman_game.Sound.Sound;

import java.awt.*;

/**
 *
 * @author Huong
 */
public class Bomb extends AnimatedEntity {

    protected double _timeToExplode = 120; //2 seconds
    public int _timeAfter = 20;
    protected Board _board;
    protected Flame[] _flames = null;
    protected boolean _exploded = false;
    protected boolean _allowedToPassThru = true;

    public Bomb(int x, int y, Board board) {
        _x = x;
        _y = y;
        _board = board;
        _sprite = Sprite.bomb;
    }

    @Override
    public void update() {
        if (_timeToExplode > 0) {
            _timeToExplode--;
        }
        else {
            if (!_exploded) {
                explode();
            } else {
                updateFlames();
            }

            if (_timeAfter > 0) {
                _timeAfter--;
            } else {
                remove();
            }
        }
        animate();
    }

    @Override
    public void render(Screen screen) {
        if (_exploded) {
            _sprite =  Sprite.bomb_exploded2;
            renderFlames(screen);
        } else {
            _sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);
        }

        int xt = (int)_x << 4;
        int yt = (int)_y << 4;

        screen.renderEntity(xt, yt , this);
    }

    public void renderFlames(Screen screen) {
        for (int i = 0; i < _flames.length; i++) {
            _flames[i].render(screen);
        }
    }

    public void updateFlames() {
        for (int i = 0; i < _flames.length; i++) {
            _flames[i].update();
        }
    }

    /**
     * Xử lý Bomb nổ
     */
    protected void explode() {
        // TODO: xử lý khi Character đứng tại vị trí Bomb

        // TODO: tạo các Flame
        _timeToExplode = 0;
        _allowedToPassThru = true;
        _exploded = true;
        Character a0 = null;
        Character a = _board.getCharacterAtExcluding((int)_x, (int)_y, a0);
        if (a != null)  {
            a.kill();
        }

        _flames = new Flame[4];

        for (int i = 0; i < _flames.length; i++) {
            _flames[i] = new Flame((int)_x, (int)_y, i, Game.getBombRadius(), _board);
        }

        // Nhac cho bom no
        Sound.playBombExplode();
    }

    public FlameSegment flameAt(int x, int y) {
        if (!_exploded) {
            return null;
        }

        for (int i = 0; i < _flames.length; i++) {
            if (_flames[i] == null) {
                return null;
            }
            FlameSegment e = _flames[i].flameSegmentAt(x, y);
            if (e != null) {
                return e;
            }
        }

        return null;
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý khi Bomber đi ra sau khi vừa đặt bom (_allowedToPassThru)
        if (e instanceof Bomber) {
            double diffX = e.getX() - Coordinates.tileToPixel(getX());
            double diffY = e.getY() - Coordinates.tileToPixel(getY());

            if(!(diffX >= -10 && diffX < 16 && diffY >= 1 && diffY <= 28)) {
                // differences to see if the player has moved out of the bomb, tested values
                _allowedToPassThru = false;
            }

            return _allowedToPassThru;
        }

        // khi va cham voi flame cua bomb khac
        if (e instanceof Flame) {
            _timeToExplode = 0;
            return true;
        }

        return false;
    }

    public boolean equal(Bomb e)
    {
        if (_x == e._x && _y == e._y)  {
            return true;
        }
        return false;
    }

    public double getTimeToExplode() {
        return _timeToExplode;
    }
}
