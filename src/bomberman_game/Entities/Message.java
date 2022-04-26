/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities;

import bomberman_game.Graphics.Screen;
import java.awt.Color;

/**
 * hien thi thong diep
 * @author Huong
 */
public class Message extends Entity {
    
    
    protected String _message;
    protected int _duration;
    protected Color _color;
    protected int _size;

    /**
     * Hiển thị message khi tiêu diệt được Enemy ví dụ (+100)
     * @param message
     * @param x
     * @param y
     * @param duration
     * @param color
     * @param size 
     */
    public Message(String message, double x, double y, int duration, Color color, int size) {
        _x = x;
	    _y = y;
	    _message = message;
        _duration = duration * 60; //seconds
	    _color = color;
        _size = size;
    }

    public int getDuration() {
        return _duration;
    }

    public void setDuration(int _duration) {
        this._duration = _duration;
    }

    public String getMessage() {
        return _message;

    }

    public Color getColor() {
        return _color;

    }

    public int getSize() {
        return _size;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Screen screen) {
    }

    @Override
    public boolean collide(Entity e) {
        return true;

    }
	
    
}
