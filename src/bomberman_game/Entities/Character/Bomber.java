/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities.Character;

import bomberman_game.Entities.Bomb.Bomb;
import bomberman_game.Entities.Bomb.Flame;
import bomberman_game.Entities.Character.Enemy.Enemy;
import bomberman_game.Board;
import bomberman_game.Entities.Entity;
import bomberman_game.Entities.Message;
import bomberman_game.Entities.Tile.Item.Item;
import bomberman_game.Entities.Tile.Portal;
import bomberman_game.Exceptions.LoadLevelException;
import bomberman_game.Graphics.Screen;
import bomberman_game.Graphics.Sprite;
import bomberman_game.InputEvent.Keyboard;
import bomberman_game.Level.Coordinates;
import bomberman_game.Game;
import bomberman_game.Sound.Sound;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huong
 */
public class Bomber extends Character {

    private List<Bomb> _bombs;
    protected Keyboard _input;

    protected int _timeBetweenPutBombs = 0;
    //list Item of bomber.
    public static List<Item> _listItem = new ArrayList<Item>();
    // bien su dung o ham afterKill.

    public Bomber(int x, int y, Board board) {
        super(x, y, board);
        _bombs = _board.getBombs();
        _input = _board.getInput();
        _sprite = Sprite.player_right;
    }

    @Override
    public void update() {
        clearBombs();
        if (!_alive) {
            afterKill();
            return;
        }

        if (_timeBetweenPutBombs < -7500) {
            _timeBetweenPutBombs = 0;
        } else {
            _timeBetweenPutBombs--;
        }

        animate();

        calculateMove();

        detectPlaceBomb();
    }

    @Override
    public void render(Screen screen) {
        calculateXOffset();

        if (_alive) {
            chooseSprite();
        } else {
            _sprite = Sprite.player_dead1;
        }

        screen.renderEntity((int) _x, (int) _y - _sprite.SIZE, this);
    }

    public void calculateXOffset() {
        int xScroll = Screen.calculateXOffset(_board, this);
        Screen.setOffset(xScroll, 0);
    }


    private void detectPlaceBomb() {

        if (_input.space && Game.getBombRate() > 0 && _timeBetweenPutBombs < 0) {

            int xt = Coordinates.pixelToTile(_x + _sprite.getSize() / 2);
            int yt = Coordinates.pixelToTile((_y + _sprite.getSize() / 2) - _sprite.getSize()); //subtract half player height and minus 1 y position

            if (bombTrue(xt, yt)) {
                placeBomb(xt, yt);
                Game.addBombRate(-1);
                _timeBetweenPutBombs = 30;

                //soundPutBomb.playAgain();
            }
        }
    }

    private boolean bombTrue(int xt, int yt) {
        Bomb b = new Bomb(xt, yt, _board);
        for (Bomb bom : _board.getBombs()) {
            if (bom.equal(b)) {
                return false;
            }
        }
        return true;
    }

    protected void placeBomb(int x, int y) {
        Bomb b = new Bomb(x, y, _board);
        _board.addBomb(b);
        Sound.playPlaceNewBomb();
    }

    private void clearBombs() {
        Iterator<Bomb> bs = _bombs.iterator();

        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();
            if (b.isRemoved()) {
                bs.remove();
                Game.addBombRate(1);
            }
        }

    }

    public static void clearItem(){
        Bomber._listItem.clear();
    }

    public boolean isDead() {
        return !_alive;
    }

    @Override
    public void kill() {
        if (!_alive) {
            return;
        }
        _alive = false;
        // Am thanh
        Sound.playBomberDie();
    }

    @Override
    protected void afterKill() {
        if (_timeAfter > 0) {
            --_timeAfter;
        } else {
            _board.setHeart(-1);
            _board.restartLevel();
        }
    }

    @Override
    protected void calculateMove() {

        int xa = 0;
        int ya = 0;
        if (_input.up) {
            ya--;
        }
        if (_input.down) {
            ya++;
        }
        if (_input.left) {
            xa--;
        }
        if (_input.right) {
            xa++;
        }

        if (_input.up || _input.down) {
            if (_x % 16 <= 9 && _x % 16 > 4) {
                _x = (int) (_x / 16) * 16 + 16 - Sprite.player_down.getRealWidth();
            } else if ((_x - (int) (_x / 16) * 16) > 12) {
                _x = (int) (_x / 16) * 16 + 16;
            }
        }

        if (_input.left || _input.right) {
            if (_y % 16 <= 5) {
                _y = (int) (_y / 16) * 16;
            } else if ((_y - (int) (_y / 16) * 16) > 12) {
                _y = (int) (_y / 16) * 16 + 16;
            }
        }

        if (xa != 0 || ya != 0) {
            move(xa * Game.getBomberSpeed(), ya * Game.getBomberSpeed());
            _moving = true;
        } else {
            _moving = false;
        }

    }

    @Override
    public boolean canMove(double x, double y) {
        for (int c = 0; c < 4; c++) { //colision detection for each corner of the player
            double xt = ((_x + x) + c % 2 * 11) / Game.TILES_SIZE; //divide with tiles size to pass to tile coordinate
            double yt = ((_y + y) + c / 2 * 12 - 13) / Game.TILES_SIZE; //these values are the best from multiple tests

            Entity a = _board.getEntity(xt, yt, this);

            if (!a.collide(this)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void move(double xa, double ya) {

        if (xa > 0) {
            _direction = 1;
        }
        if (xa < 0) {
            _direction = 3;
        }
        if (ya > 0) {
            _direction = 2;
        }
        if (ya < 0) {
            _direction = 0;
        }

        if (canMove(0, ya)) { //separate the moves for the player can slide when is colliding
            _y += ya;
        }

        if (canMove(xa, 0)) {
            _x += xa;
        }
    }

    @Override
    public boolean collide(Entity e) {

        if (e instanceof Flame) {
            kill();
            return false;
        }

        if (e instanceof Enemy) {
            double x1 = this.getX();
            double x2 = this.getY();
            double e1 = e.getX();
            double e2 = e.getY();
            double z1 = this.getSprite().getRealWidth();
            double z2 = this.getSprite().getRealHeight();
            double m1 = e.getSprite().getRealWidth();
            double m2 = e.getSprite().getRealHeight();
            if (Math.abs(x1 - e1) < (z1 + m1) / 2) {
                if (Math.abs(x2 - e2) < (z2 + m2) / 2) {
                    kill();
                    return false;
                }
            }
        }
        return true;
    }

    private void chooseSprite() {
        switch (_direction) {
            case 0:
                _sprite = Sprite.player_up;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                }
                break;
            case 1:
                _sprite = Sprite.player_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
            case 2:
                _sprite = Sprite.player_down;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
                }
                break;
            case 3:
                _sprite = Sprite.player_left;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                }
                break;
            default:
                _sprite = Sprite.player_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
        }
    }

    // add hadling eating Item.
    public void addPowerup(Item p) {
        if (p.isRemoved()) {
            return;
        }

        _listItem.add(p);

        p.setValues();
        // Am thanh
        Sound.playGetNewItem();
    }

    public void clearUsedPowerups() {
        Item p;
        for (int i = 0; i < _listItem.size(); i++) {
            p = _listItem.get(i);
            if (p.isActive() == false) {
                _listItem.remove(i);
            }
        }
    }

    public void removePowerups() {
        for (int i = 0; i < _listItem.size(); i++) {
            _listItem.remove(i);
        }

    }
}
