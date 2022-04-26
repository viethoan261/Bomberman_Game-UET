/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Level;

import bomberman_game.Game;

/**
 * Chuyen doi toa do 2 he
 * @author Huong
 */
public class Coordinates {
    public static int pixelToTile(double i) {
        return (int) (i / Game.TILES_SIZE);
    }
    
    public static int tileToPixel(int i) {
        return i * Game.TILES_SIZE;
    }
    
    public static int tileToPixel(double i) {
        return (int) (i * Game.TILES_SIZE);
    }
}
