/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities.Tile.Item;

import bomberman_game.Entities.Character.Bomber;
import bomberman_game.Entities.Entity;
import bomberman_game.Entities.Tile.Tile;
import bomberman_game.Graphics.Sprite;

/**
 *
 * @author Huong
 */
public abstract class Item extends Tile {
   
    protected int _duration = -1; 
    protected boolean _active = false;
    protected int _level;
    
    public Item(int x, int y, int level, Sprite sprite) {
        super(x, y, sprite);
        _level = level; 
    }
         
    public abstract void setValues();
	
    public boolean collide(Entity e){
        if (e instanceof Bomber) {
            ((Bomber) e).addPowerup(this);
            remove();
            return true;
	    }
        return false;
    }
        
    public void removeLive() {
        if  (_duration > 0) {
            _duration--;
        }
			
        if (_duration == 0) {
            _active = false;
        }
			
    }
	
    public int getDuration() {
        return _duration;
    }
	
    public int getLevel() {
        return _level;
    }

    public void setDuration(int duration) {
        this._duration = duration;
    }

    public boolean isActive() {
        return _active;
    }

    public void setActive(boolean active) {
        this._active = active;
    }
    
    
    
}
