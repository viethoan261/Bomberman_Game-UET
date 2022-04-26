/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities.Bomb;

import bomberman_game.Entities.Character.Bomber;
import bomberman_game.Entities.Entity;
import bomberman_game.Graphics.Screen;
import bomberman_game.Board;
import bomberman_game.Entities.Character.Character;
import bomberman_game.Entities.Character.Enemy.Enemy;

/**
 *
 * @author Huong
 */
public class Flame extends Entity {

    protected Board _board;
    protected int _direction;// huong cua flame
    private int _radius;// do dai cuc dai
    protected int xOrigin;// hoanh do bat dau
    protected int yOrigin;// tung do bat dau
    protected FlameSegment[] _flameSegments;

    public Flame(int x, int y, int direction, int radius, Board board) {
        xOrigin = x;
        yOrigin = y;
        _x = x;
        _y = y;
        _direction = direction;
        _radius = radius;
        _board = board;

        _flameSegments = new FlameSegment[calculatePermitedDistance()];
        createFlameSegments();
    }

    private void createFlameSegments() {

        boolean last = false;

        // TODO: tạo các segment.
        int x = (int)_x;
        int y = (int)_y;
        for (int i = 0; i < _flameSegments.length; i++) {

            last = (i == _flameSegments.length - 1) ? true : false;
            switch (_direction) {
                case 0:
                    y--;
                    break;
                case 1:
                    x++;
                    break;
                case 2:
                    y++;
                    break;
                case 3:
                    x--;
                    break;
            }
            _flameSegments[i] = new FlameSegment(x, y, _direction, last, _board);
        }

    }

    // Tinh toan do dai cua flame neu gap vat can la Brick/Wall thi flame bi cat ngan do dai
    private int calculatePermitedDistance() {
        // TODO: thực hiện tính toán độ dài của Flame
        int radius = 0;
        int x = (int)_x;
        int y = (int)_y;
        while (radius < _radius) {
            if (_direction == 0) {
                y--;
            }
            if (_direction == 1) {
                x++;
            }
            if (_direction == 2) {
                y++;
            }
            if (_direction == 3) {
                x--;
            }

            Entity a = _board.getEntity(x, y, null);

            if (a instanceof Character){
                ++radius;
            } //explosion has to be below the mob

            if (a.collide(this) == false) {
                //cannot pass thru
                break;
            }
            ++radius;
        }
        return radius;
    }

    public FlameSegment flameSegmentAt(int x, int y) {
        for (int i = 0; i < _flameSegments.length; i++) {
            if (_flameSegments[i].getX() == x && _flameSegments[i].getY() == y)
                return _flameSegments[i];
        }
        return null;
    }

    @Override
    public void update() {}

    @Override
    public void render(Screen screen) {
        for (int i = 0; i < _flameSegments.length; i++) {
            _flameSegments[i].render(screen);
        }
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber) {
            ((Bomber) e).kill();
        }

        if (e instanceof Enemy) {
            ((Enemy) e).kill();
        }
        return true;
    }
}
