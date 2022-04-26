/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities.Tile.Item;

import bomberman_game.Graphics.Sprite;
import bomberman_game.Game;

/**
 *
 * @author Huong
 */
public class SpeedItem extends Item {

    public SpeedItem(int x, int y, int level, Sprite sprite) {
        super(x, y, level, sprite);
    }

    @Override
    public void setValues() {
        this._active = true;
        Game.addBomberSpeed(1);
    }
    
    
}
