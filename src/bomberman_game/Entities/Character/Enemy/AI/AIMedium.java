/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities.Character.Enemy.AI;

import bomberman_game.Entities.Character.Bomber;
import bomberman_game.Entities.Character.Enemy.Enemy;

/**
 *
 * @author Huong
 */
public class AIMedium extends AI {
    
    Bomber bomber;
    Enemy enemy;

    public AIMedium(Bomber bomber, Enemy enemy) {
        this.bomber = bomber;
        this.enemy = enemy;
    }
    
    protected int calculateColDirection() {
        if (this.bomber.getYTile() < this.enemy.getYTile()) {
            return 3;
        } else if (this.bomber.getXTile() > this.enemy.getXTile()) {
            return 1;
        }
           	
	    return -1;
    }
    
    protected int calculateRowDirection() {
        if (this.bomber.getXTile() < this.enemy.getXTile()) {
            return 0;
        } else if (this.bomber.getXTile() > this.enemy.getXTile()) {
            return 2;
        }
           	
	    return -1;
    }

    @Override
    public int calculateDirection() {
        int direct = random.nextInt(4);
        
        if (bomber == null || direct >= 3) {
            return random.nextInt(4);
        }
        
        if (direct == 1) {
            int vertical = calculateRowDirection();
            
            if (vertical != -1) {
                return vertical;
            } else {
                return calculateColDirection();
            }
        }
        
        if (direct == 2) {
            int horizontal = calculateColDirection();
            
            if (horizontal != -1) {
                return horizontal;
            } else {
                return calculateRowDirection();
            }
        }
        
        return 4;
    }
    
}
