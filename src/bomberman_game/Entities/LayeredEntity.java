/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities;

import bomberman_game.Entities.Tile.Destroyable.DestroyableTile;
import bomberman_game.Graphics.Screen;

import java.util.LinkedList;

/**
 *
 * @author Huong
 */
public class LayeredEntity extends Entity {
    
    protected LinkedList<Entity> _entities = new LinkedList<>();
	
    public LayeredEntity(int x, int y, Entity ... entities) {
        _x = x;
	    _y = y;
		
        for (int i = 0; i < entities.length; i++) {
            _entities.add(entities[i]); 
            if (i > 1) {
		        if (entities[i] instanceof DestroyableTile) {
				    ((DestroyableTile)entities[i]).addBelowSprite(entities[i-1].getSprite());
                }
            }
	    }
    }
	
    @Override
    public void update() {
        clearRemoved();
	    getTopEntity().update();
    }
	
    @Override
    public void render(Screen screen) {
        getTopEntity().render(screen);
    }
	
    public Entity getTopEntity() {
        return _entities.getLast();
    }
	
    private void clearRemoved() {
        Entity top  = getTopEntity();
		
        if (top.isRemoved())  {
                _entities.removeLast();
        }
    }
	
    public void addBeforeTop(Entity e) {
        _entities.add(_entities.size() - 1, e);

    }
	
    @Override
    public boolean collide(Entity e) {
	// TODO: lấy entity trên cùng ra để xử lý va chạm
                  
	    return getTopEntity().collide(e);
    }

}
