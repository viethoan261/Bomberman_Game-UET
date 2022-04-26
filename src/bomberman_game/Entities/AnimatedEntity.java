/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities;

import bomberman_game.Entities.Entity;

/**
 *
 * @author Huong
 */
public abstract class AnimatedEntity extends Entity {
    
    protected int _animate = 0;
    
    protected final int MAX_ANIMATE = 7500;
    
    protected void animate() {
        if (this._animate < MAX_ANIMATE) {
            _animate++;
        } else {
            _animate = 0;
        }
    }
    
}
