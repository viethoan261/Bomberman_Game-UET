/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities.Character.Enemy.AI;

import bomberman_game.Game;

/**
 *
 * @author Huong
 */
public class AILow extends AI {

    private int currentDirect = random.nextInt(4);
        
	@Override
	public int calculateDirection() {
            // TODO: cài đặt thuật toán tìm đường đi
            if (!canMove) {
				currentDirect = random.nextInt(4);
                canMove = true;
            }
            return currentDirect;
	}
    
    
}
