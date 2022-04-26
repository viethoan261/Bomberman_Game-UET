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
public class LoadLevelException extends GameException {
    
    public LoadLevelException() {
        
    }
    
    public LoadLevelException(String s) {
        super(s);
    }
    
    public LoadLevelException(String s, Throwable cause) {
        super(s, cause);
    }
    
    public LoadLevelException(Throwable cause) {
        super(cause);
    }
    
}
