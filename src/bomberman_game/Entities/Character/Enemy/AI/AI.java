/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities.Character.Enemy.AI;

import java.util.Random;

/**
 *
 * @author Huong
 */
public abstract class AI {
    public boolean canMove = true;
    protected Random random = new Random();
    
    // thuat toan tim duong di
    // xuong/phai/trai/len - 0/1/2/3
    public abstract int calculateDirection();
}
