/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities.Tile.Destroyable;

import bomberman_game.Graphics.Screen;
import bomberman_game.Graphics.Sprite;
import bomberman_game.Level.Coordinates;

/**
 *
 * @author Huong
 */
public class Brick extends DestroyableTile {

    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Screen screen) {
        int x = Coordinates.tileToPixel(_x);
        int y = Coordinates.tileToPixel(_y);

        if(_destroyed) {
            _sprite = movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2);

            screen.renderEntityWithBelowSprite(x, y, this, _belowSprite);
        }
        else
            screen.renderEntity( x, y, this);
    }
}
