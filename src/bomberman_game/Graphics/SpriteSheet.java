/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Huong
 */
public class SpriteSheet {

    private String _path;
    public final int SIZE;
    public int[] _pixels;
	
    //public static SpriteSheet tiles = new SpriteSheet("C:\\Users\\Huong\\IdeaProjects\\Bomberman_Game\\res\\textures\\classic.png", 256);
	public static SpriteSheet tiles = new SpriteSheet("res/textures/classic.png", 256);
    public SpriteSheet(String path, int size) {
        _path = path;
	    SIZE = size;
        _pixels = new int[SIZE * SIZE];
	    load();
    }
	
    private void load() {
        try {
//			URL a = SpriteSheet.class.getResource(_path);
//			BufferedImage image = ImageIO.read(a);
            BufferedImage image = ImageIO.read(new File(_path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, _pixels, 0, w);
        } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
        }
    }

}
