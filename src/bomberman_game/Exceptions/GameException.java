/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Exceptions;

/**
 *
 * @author Huong
 */
public class GameException extends Exception {
    public GameException() {
        
    }
    
    public GameException(String str) {
        super(str);
    }
    
    public GameException(String  str, Throwable cause) {
        super(str, cause);
    }
    
    public GameException(Throwable cause) {
        super(cause);
    }
}
