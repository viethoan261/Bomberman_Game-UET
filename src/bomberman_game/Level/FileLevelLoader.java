/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Level;

import bomberman_game.Entities.Character.Bomber;
import bomberman_game.Entities.Character.Enemy.Balloon;
import bomberman_game.Entities.Character.Enemy.Doll;
import bomberman_game.Entities.Character.Enemy.Oneal;
import bomberman_game.Entities.LayeredEntity;
import bomberman_game.Entities.Tile.Destroyable.Brick;
import bomberman_game.Entities.Tile.Grass;
import bomberman_game.Entities.Tile.Item.BombItem;
import bomberman_game.Entities.Tile.Item.HeartItem;
import bomberman_game.Entities.Tile.Item.SpeedItem;
import bomberman_game.Entities.Tile.Portal;
import bomberman_game.Entities.Tile.Wall;
import bomberman_game.Graphics.Screen;
import bomberman_game.Graphics.Sprite;
import bomberman_game.Board;
import bomberman_game.Entities.Tile.Item.FlameItem;
import bomberman_game.Game;
import bomberman_game.Exceptions.LoadLevelException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 * tao map - chua thong tin ban do, moi phan tu luu gia tri 
 * ki tu doc duoc tu ma tran ban do trong tep cau hinh
 * @author Huong
 */
public class FileLevelLoader extends LevelLoader {
    
    private static char[][] _map;
	
    public FileLevelLoader(Board board, int level) throws LoadLevelException {
        super(board, level);
    }
	
    @Override
    public void loadLevel(int level) {

        ArrayList<String> s = new ArrayList<>();
        FileReader fr = null;
        try {
                  
            //fr = new FileReader("C:\\Users\\Huong\\IdeaProjects\\Bomberman_Game\\res\\levels - Copy\\Level" + level + ".txt");
            fr = new FileReader("res/levels - Copy/Level" + level + ".txt");
            BufferedReader br = new BufferedReader(fr);
            System.out.println(level);
            String str = br.readLine();
            int i = 0; 
            while (!str.equals("")) {
                s.add(str);
                str = br.readLine(); 
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("File not found !!!!");
        } catch (IOException ex) {
        }
                
        String[] ar = s.get(0).trim().split(" ");
        _level = Integer.parseInt(ar[0]);
        _height = Integer.parseInt(ar[1]);
        _width = Integer.parseInt(ar[2]);
        _map = new char[_height][_width];
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                _map[i][j] = s.get(i + 1).charAt(j);
            }
        }
    }

    @Override
    public void createEntities() {
        for (int y = 0; y < _height; y++) {
            for (int x = 0; x < _width; x++) {
                int pos = x + y * getWidth();
                char c = _map[y][x]; 
                switch(c) { // TODO: minimize this method
                    case '#': 
                        _board.addEntity(pos, new Wall(x, y, Sprite.wall));                     
                        break;                    
                    case 'b': 
                        LayeredEntity layer = new LayeredEntity(x, y, 
                                                    new Grass(x ,y, Sprite.grass), 
                                                    new Brick(x ,y, Sprite.brick));                    

                        if(_board.isItemUsed(x, y, _level) == false) {
                            layer.addBeforeTop(new BombItem(x, y, _level, Sprite.powerup_bombs));
                        }

                        _board.addEntity(pos, layer);
                        break;
                    case 's':
                        layer = new LayeredEntity(x, y, 
                                    new Grass(x ,y, Sprite.grass), 
                                    new Brick(x ,y, Sprite.brick));

                        if(_board.isItemUsed(x, y, _level) == false) {
                            layer.addBeforeTop(new SpeedItem(x, y, _level, Sprite.powerup_speed));
                        }

                        _board.addEntity(pos, layer);
                        break;
                    case 'f': 
                        layer = new LayeredEntity(x, y, 
                                    new Grass(x ,y, Sprite.grass), 
                                    new Brick(x ,y, Sprite.brick));

                        if(_board.isItemUsed(x, y, _level) == false) {
                            layer.addBeforeTop(new FlameItem(x, y, _level, Sprite.powerup_flames));
                        }

                        _board.addEntity(pos, layer);
                        break;
                    case 'h':
                        layer = new LayeredEntity(x, y, 
                                    new Grass(x ,y, Sprite.grass), 
                                    new Brick(x ,y, Sprite.brick));

                        if(_board.isItemUsed(x, y, _level) == false) {
                            layer.addBeforeTop(new HeartItem(x, y, _level, Sprite.powerup_detonator));
                        }

                        _board.addEntity(pos, layer);
                        break;
                    case '*': 
                        _board.addEntity(pos, new LayeredEntity(x, y, 
                                            new Grass(x ,y, Sprite.grass),
                                            new Brick(x ,y, Sprite.brick)) );
                        break;
                    case 'x':
                        _board.addEntity(pos, new LayeredEntity(x, y, 
                                            new Grass(x ,y, Sprite.grass),
                                            new Portal(x ,y, _board, Sprite.portal), 
                                            new Brick(x ,y, Sprite.brick)) );
                                            
                        break;
                    case ' ': 
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                        break;
                    case 'p':
                        _board.addCharacter( new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board) );
                        Screen.setOffset(0, 0);
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                        break;
                    //Enemies
                    case '1': // Balloon
                        _board.addCharacter( new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                        break;
                    case '2': // Oneal
                        _board.addCharacter( new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                        break;
                    case '3': // Doll
                        _board.addCharacter( new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                        break;
                    default: 
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass) );
                        break;
                    }
		}
	}
    }



   
    
    
}
