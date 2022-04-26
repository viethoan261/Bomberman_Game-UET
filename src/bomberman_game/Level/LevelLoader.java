/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Level;

import bomberman_game.Exceptions.LoadLevelException;
import bomberman_game.Board;

/**
 *
 * @author Huong
 */
public abstract class LevelLoader {
    protected int _width = 20;
    protected int _height = 20; // default values just for testing
    protected int _level;
    protected Board _board;

    public LevelLoader(Board board, int level) throws LoadLevelException {
        _board = board;
	    loadLevel(level);
    }

    public abstract void loadLevel(int level) throws LoadLevelException;

    public abstract void createEntities();

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public int getLevel() {
        return _level;
    }

    public Board getBoard() {
        return _board;
    }

    
}
