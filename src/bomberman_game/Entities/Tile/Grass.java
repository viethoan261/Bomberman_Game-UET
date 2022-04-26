/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities.Tile;

import bomberman_game.Entities.Entity;
import bomberman_game.Graphics.Sprite;

/**
 *
 * @author Huong
 */
public class Grass extends Tile {

    public Grass(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }
    
    @Override
    public boolean collide(Entity entity) {
        return true; // cho doi tuong khac di qua
    }
    
}
