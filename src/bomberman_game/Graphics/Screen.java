/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Graphics;

import bomberman_game.Entities.Character.Bomber;
import bomberman_game.Entities.Entity;
import bomberman_game.Board;
import bomberman_game.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author Huong
 */
public class Screen {
    protected int _width;
    protected int _height;
    public int[] _pixels;
    private int _transparentColor = 0xffff00ff;

    public static int xOffset = 0;
    public static int yOffset = 0;


    public Screen(int width, int height) {
        _width = width;
        _height = height;

        _pixels = new int[width * height];

    }

    public void clear() {
        for (int i = 0; i < _pixels.length; i++) {
            _pixels[i] = 0;
        }
    }

    public void renderEntity(int xp, int yp, Entity entity) { //save entity pixels
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < entity.getSprite().getSize(); y++) {
            int ya = y + yp; //add offset
            for (int x = 0; x < entity.getSprite().getSize(); x++) {
                int xa = x + xp; //add offset
                if (xa < -entity.getSprite().getSize() || xa >= _width || ya < 0 || ya >= _height) {
                    break; //fix black margins
                }
                if (xa < 0) {
                    xa = 0; //start at 0 from left
                }
                int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
                if (color != _transparentColor) {
                    _pixels[xa + ya * _width] = color;
                }
            }
        }
    }

    public void renderEntityWithBelowSprite(int xp, int yp, Entity entity, Sprite below) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < entity.getSprite().getSize(); y++) {
            int ya = y + yp;
            for (int x = 0; x < entity.getSprite().getSize(); x++) {
                int xa = x + xp;
                if (xa < -entity.getSprite().getSize() || xa >= _width || ya < 0 || ya >= _height) {
                    break; //fix black margins
                }
                if (xa < 0) xa = 0;
                int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
                if (color != _transparentColor) {
                    _pixels[xa + ya * _width] = color;
                } else {
                    _pixels[xa + ya * _width] = below.getPixel(x + y * below.getSize());
                }
            }
        }
    }

    public static void setOffset(int xO, int yO) {
        xOffset = xO;
        yOffset = yO;
    }

    public static int calculateXOffset(Board board, Bomber bomber) {
        if(bomber == null) return 0;
        int temp = xOffset;

        double BomberX = bomber.getX() / 16;
        double complement = 0.5;
        int firstBreakpoint = board.getWidth() / 4;
        int lastBreakpoint = board.getWidth() - firstBreakpoint;

        if (BomberX > firstBreakpoint + complement && BomberX < lastBreakpoint - complement) {
            temp = (int)bomber.getX() - (Game.WIDTH / 2);
        }

        return temp;
    }

    public void drawEndGame(Graphics g, int points) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, 900, 900);
        g.setFont(new Font("TimesRoman", Font.PLAIN,40));
        g.setColor(Color.blue);
        g.drawString("Game Over",10,30);

        g.setColor(Color.black);
        g.fillRect(0, 0, getRealWidth(), getRealHeight());

        Font font = new Font("TimesRoman", Font.PLAIN, 20 * Game.SCALE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("GAME OVER", getRealWidth(), getRealHeight(), g);

        font = new Font("TimesRoman", Font.PLAIN, 10 * Game.SCALE);
        g.setFont(font);
        g.setColor(Color.yellow);
        drawCenteredString("POINTS: " + points, getRealWidth(), getRealHeight() + (Game.TILES_SIZE * 2) * Game.SCALE, g);

        String namePro = "Team HDH";
        Font font2 = new Font("TimesRoman", 20, 40);
        g.setFont(font2);
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.GRAY);
        int x = (getRealWidth() - fm.stringWidth(namePro)) / 2;
        g.drawString(namePro, x, getRealHeight() - 10);
    }

    // draw Win game.
    public void drawWinGame(Graphics g, int points) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getRealWidth(), getRealHeight());

        Font font = new Font("TimesRoman", Font.PLAIN, 20 * Game.SCALE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("YOU WIN", getRealWidth(), getRealHeight(), g);

        font = new Font("TimesRoman", Font.PLAIN, 10 * Game.SCALE);
        g.setFont(font);
        g.setColor(Color.yellow);
        drawCenteredString("POINTS: " + points, getRealWidth(), getRealHeight() + (Game.TILES_SIZE * 2) * Game.SCALE, g);

        String namePro = "Team HDH";
        Font font2 = new Font("TimesRoman", 20, 40);
        g.setFont(font2);
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.GRAY);
        int x = (getRealWidth() - fm.stringWidth(namePro)) / 2;
        g.drawString(namePro, x, getRealHeight() - 10);

    }

    public void drawChangeLevel(Graphics g, int level) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getRealWidth(), getRealHeight());

        Font font = new Font("TimesRoman", Font.PLAIN, 20 * Game.SCALE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("LEVEL " + level, getRealWidth(), getRealHeight(), g);

        // draw ten.
        String namePro = "Team HDH";
        Font font2 = new Font("TimesRoman", 20, 40);
        g.setFont(font2);
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.GRAY);
        int x = (getRealWidth() - fm.stringWidth(namePro)) / 2;
        g.drawString(namePro, x, getRealHeight() - 10);

    }

    public void drawPaused(Graphics g) {
        Font font = new Font("TimesRoman", Font.PLAIN, 20 * Game.SCALE);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("PAUSED", getRealWidth(), getRealHeight(), g);

    }

    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);

        g.drawString(s, x, y);

    }


    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public int getRealWidth() {
        return _width * Game.SCALE;
    }

    public int getRealHeight() {
        return _height * Game.SCALE;
    }
}

