/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game.Entities;

import bomberman_game.Graphics.IRender;
import bomberman_game.Graphics.Screen;
import bomberman_game.Graphics.Sprite;
import bomberman_game.Level.Coordinates;

/**
 *
 * @author Huong
 */
public abstract class Entity implements IRender {
    
    protected double _x, _y;
    protected boolean _removed = false;
    protected Sprite _sprite;

    /**
     * Phương thức này được gọi liên tục trong vòng lặp game,
     * mục đích để xử lý sự kiện và cập nhật trạng thái Entity
     */
    @Override
    public abstract void update();

    /**
     * Phương thức này được gọi liên tục trong vòng lặp game,
     * mục đích để cập nhật hình ảnh của entity theo trạng thái
     */
    @Override
    public abstract void render(Screen screen);
	
    public void remove() {
        _removed = true;

    }
	
    public boolean isRemoved() {
        return _removed;

    }
	
    public Sprite getSprite() {
        return _sprite;

    }

    /**
     * Phương thức này được gọi để xử lý khi hai entity va chạm vào nhau
     * @param e
     * @return
     */
    public abstract boolean collide(Entity e);
	
    public double getX() {
        return _x;
    }
	
    public double getY() {
        return _y;
    }
	
    public int getXTile() {
        return Coordinates.pixelToTile(_x + _sprite.SIZE / 2);
    }
	
    public int getYTile() {
        return Coordinates.pixelToTile(_y - _sprite.SIZE / 2);
    }

}
