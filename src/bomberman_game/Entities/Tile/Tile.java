/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities.Tile;

import bomberman_game.Entities.Entity;
import bomberman_game.Graphics.Screen;
import bomberman_game.Graphics.Sprite;
import bomberman_game.Level.Coordinates;

/**
 *
 * @author Huong
 */
public class Tile extends Entity {

    public Tile(int x, int y, Sprite sprite) {
	_x = x;
	_y = y;
        _sprite = sprite;
    }

    /**
     * Mặc định không cho bất cứ một đối tượng nào đi qua
     * @param e
     * @return 
     */
    @Override
    public boolean collide(Entity e) {
        return false;
    }
	
    @Override
    public void render(Screen screen) {
        screen.renderEntity(Coordinates.tileToPixel(_x), Coordinates.tileToPixel(_y), this);
    }
	
    @Override
    public void update() {}
}
