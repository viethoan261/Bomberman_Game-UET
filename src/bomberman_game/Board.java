/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman_game;

import bomberman_game.Entities.Bomb.Bomb;
import bomberman_game.Entities.Bomb.FlameSegment;
import bomberman_game.Entities.Character.Bomber;
import bomberman_game.Entities.Entity;
import bomberman_game.Graphics.IRender;
import bomberman_game.Graphics.Screen;
import bomberman_game.InputEvent.Keyboard;
import bomberman_game.Level.LevelLoader;
import bomberman_game.Entities.Character.Character;
import bomberman_game.Entities.Message;
import bomberman_game.Entities.Tile.Item.Item;
import bomberman_game.Exceptions.LoadLevelException;
import bomberman_game.Level.FileLevelLoader;
import bomberman_game.Sound.Sound;

import javax.swing.*;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * quan li thao tac dieu khien, load level, render cac man hinh cua game
 * @author Huong
 */
public class Board implements IRender {
    protected LevelLoader _levelLoader;
    protected Game _game;
    protected Keyboard _input;
    protected Screen _screen;

    public Entity[] _entities;
    public List<Character> _characters = new ArrayList<>();
    protected List<Bomb> _bombs = new ArrayList<>();
    private List<Message> _messages = new ArrayList<>();

    private int _screenToShow = -1; //1:endgame, 2:changelevel, 3:paused, 4: Win game.
    private int _time = Game.TIME;
    private int _points = Game.POINTS;
    public static boolean checkWinGame = false;
    public int _amountEnemy = 0;

    //public Graphics g;

    public Board(Game game, Keyboard input, Screen screen) {
        _game = game;
        _input = input;
        _screen = screen;
        loadLevel(1); //start in level 1
    }


    @Override
    public void update() {
        if (_game.isPaused()) {
            return;
        }
        updateEntities();
        updateCharacters();
        updateBombs();
        updateMessages();
        detectEndGame();
        detectWinGame();

        for (int i = 0; i < _characters.size(); i++) {
            Character a = _characters.get(i);
            if (a.isRemoved()) {
                _characters.remove(i);
            }
        }

    }

    @Override
    public void render(Screen screen) {
        if (_game.isPaused()) {
            return;
        }

        //only render the visible part of screen
        int x0 = Screen.xOffset >> 4; //tile precision, -> left X
        int x1 = (Screen.xOffset + screen.getWidth() + Game.TILES_SIZE) / Game.TILES_SIZE; // -> right X
        int y0 = Screen.yOffset >> 4;
        int y1 = (Screen.yOffset + screen.getHeight()) / Game.TILES_SIZE; //render one tile plus to fix black margins

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                _entities[x + y * _levelLoader.getWidth()].render(screen);
            }
        }

        renderBombs(screen);
        renderCharacter(screen);

    }

    public void resetGame(){
        if (_screenToShow == 1 || _screenToShow == 4) {
            _points = Game.POINTS;
            Bomber.clearItem();
            Game.addBomberHeartnew(3);
            Game.bomberSpeed = 1.0;
            Game.bombRadius = 1;
            Game.bombRate = 1;
            loadLevel(1);
        }
    }

    public void restartLevel() {

        updateBombRate();
        loadLevel(_levelLoader.getLevel());
    }

    public void newGame() {
        resetGame();
        _screenToShow = 2;
        loadLevel(1);
    }

    public void gamePause() {
        _game.resetScreenDelay();
        if(_screenToShow <= 0) {
            _screenToShow = 3;
        }
        _game.pause();
    }

    public void gameResume() {
        _game.resetScreenDelay();
        _screenToShow = -1;
        _game.run();
    }

    public void nextLevel() {
        updateBombRate();
        Sound.playStartStage();
        if (_levelLoader.getLevel() < 5){
            loadLevel(_levelLoader.getLevel() + 1);
        }
    }

    public void loadLevel(int level) {
        _time = Game.TIME;
        _screenToShow = 2;
        _game.resetScreenDelay();
        _characters.clear();
        _bombs.clear();
        _messages.clear();

        try {
            _levelLoader = new FileLevelLoader(this, level);
            _entities = new Entity[_levelLoader.getHeight() * _levelLoader.getWidth()];
            _levelLoader.createEntities();

        } catch (LoadLevelException e) {
            endGame();
        }
    }

    protected void detectEndGame() {
        if (_time <= 0 || _game.getBomberHeart() == 0)
            endGame();
    }

    public void endGame() {
        updateBombRate();
        _game.setBomberHeart(3);
        _screenToShow = 1;
        _game.resetScreenDelay();
        _game.setGameOver(true);
        Sound.playLose();
    }

    // add function WinGame.
    protected void detectWinGame() {
        if (checkWinGame)
            winGame();
    }

    public void winGame() {
        updateBombRate();
        _screenToShow = 4;
        _game.resetScreenDelay();
        Sound.playWin();
    }

    public boolean detectNoEnemies() {
        int total = 0;
        for (int i = 0; i < _characters.size(); i++) {
            if (_characters.get(i) instanceof Bomber == false)
                ++total;
        }

        return total == 0;
    }

    public void drawScreen(Graphics g) {
        switch (_screenToShow) {
            case 1:
                _screen.drawEndGame(g, _points);
                break;
            case 2:
                _screen.drawChangeLevel(g, _levelLoader.getLevel());
                break;
            case 3:
                _screen.drawPaused(g);
                break;
            case 4:
                _screen.drawWinGame(g, _points);
                break;
        }
    }

    public Entity getEntity(double x, double y, Character m) {

        Entity res = null;

        res = getFlameSegmentAt((int)x, (int)y);
        if (res != null) {
            return res;
        }

        res = getBombAt(x, y);
        if (res != null) {
            return res;
        }

        res = getCharacterAtExcluding((int)x, (int)y, m);
        if (res != null) {
            return res;
        }

        res = getEntityAt((int)x, (int)y);

        return res;
    }

    public List<Bomb> getBombs() {
        return _bombs;
    }

    public Bomb getBombAt(double x, double y) {
        Iterator<Bomb> bs = _bombs.iterator();
        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();
            if (b.getX() == (int)x && b.getY() == (int)y)
                return b;
        }

        return null;
    }

    public Bomber getBomber() {
        Iterator<Character> itr = _characters.iterator();

        Character cur;
        while (itr.hasNext()) {
            cur = itr.next();

            if (cur instanceof Bomber) {
                return (Bomber) cur;
            }
        }

        return null;
    }

    public Character getCharacterAtExcluding(int x, int y, Character a) {
        Iterator<Character> itr = _characters.iterator();

        Character cur;
        while (itr.hasNext()) {
            cur = itr.next();
            if (cur == a) {
                continue;
            }

            if (cur.getXTile() == x && cur.getYTile() == y) {
                return cur;
            }

        }

        return null;
    }

    public FlameSegment getFlameSegmentAt(int x, int y) {
        Iterator<Bomb> bs = _bombs.iterator();
        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();

            FlameSegment e = b.flameAt(x, y);
            if (e != null) {
                return e;
            }
        }

        return null;
    }

    public Entity getEntityAt(double x, double y) {
        return _entities[(int)x + (int)y * _levelLoader.getWidth()];
    }

    public void addEntity(int pos, Entity e) {
        _entities[pos] = e;
    }

    public void addCharacter(Character e) {
        _characters.add(e);
    }

    public void addBomb(Bomb e) {
        _bombs.add(e);
    }

    public void addMessage(Message e) {
        _messages.add(e);
    }

    protected void renderCharacter(Screen screen) {
        Iterator<Character> itr = _characters.iterator();

        while (itr.hasNext()) {
            itr.next().render(screen);
        }
    }

    protected void renderBombs(Screen screen) {
        Iterator<Bomb> itr = _bombs.iterator();

        while (itr.hasNext()) {
            itr.next().render(screen);
        }
    }

    public void renderMessages(Graphics g) {
        Message m;
        for (int i = 0; i < _messages.size(); i++) {
            m = _messages.get(i);

            g.setFont(new Font("TimeRomans", Font.PLAIN, m.getSize()));
            g.setColor(m.getColor());
            g.drawString(m.getMessage(), (int)m.getX() - Screen.xOffset  * Game.SCALE, (int)m.getY());
        }
    }

    protected void updateEntities() {
        if (_game.isPaused()) {
            return;
        }
        for (int i = 0; i < _entities.length; i++) {
            _entities[i].update();
        }
    }

    protected void updateCharacters() {
        if (_game.isPaused()) {
            return;
        }

        for (int i = 0; i < _characters.size(); i++) {
            _characters.get(i).update();
        }
        // gán thêm số enemy.
        _amountEnemy = _characters.size() - 1;
    }

    protected void updateBombs() {
        if (_game.isPaused()) {
            return;
        }

        for (int i = 0; i < _bombs.size(); i++) {
            _bombs.get(i).update();
        }
    }

    protected void updateBombRate(){
        Iterator<Bomb> bs = _bombs.iterator();
        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();
            if (b.getTimeToExplode() > 0) {
                Game.addBombRate(1);
            }
            b.remove();
        }
    }

    protected void updateMessages() {
        if( _game.isPaused() ) return;
        Message m;
        int left;
        for (int i = 0; i < _messages.size(); i++) {
            m = _messages.get(i);
            left = m.getDuration();

            if (left > 0) {
                m.setDuration(--left);
            } else {
                _messages.remove(i);
            }
        }
    }

    public int subtractTime() {
        if (_game.isPaused()) {
            return this._time;
        } else {
            return this._time--;
        }
    }

    // add function Item of Bomber.
    public boolean isItemUsed(int x, int y, int level) {
        Item p;
        for (int i = 0; i < Bomber._listItem.size(); i++) {
            p = Bomber._listItem.get(i);
            if (p.getX() == x && p.getY() == y && level == p.getLevel()) {
                return true;
            }
        }

        return false;
    }

    public int getHeart(){
        return _game.getBomberHeart();
    }

    public void setHeart(int i){
        _game.addBomberHeart(i);
    }

    public Keyboard getInput() {
        return _input;
    }

    public LevelLoader getLevel() {
        return _levelLoader;
    }

    public Game getGame() {
        return _game;
    }

    public int getShow() {
        return _screenToShow;
    }

    public void setShow(int i) {
        _screenToShow = i;
    }

    public int getTime() {
        return _time;
    }

    public int getPoints() {
        return _points;
    }

    public void addPoints(int points) {
        this._points += points;
        _amountEnemy--;
    }

    public int getWidth() {
        return _levelLoader.getWidth();
    }

    public int getHeight() {
        return _levelLoader.getHeight();
    }

    public int getAmountEnemy(){
        return _amountEnemy;
    }

    public int getNumLevel(){
        return _levelLoader.getLevel();
    }

    public void clearEntity(){
        for (int i = 0; i < _entities.length; i++) {
            _entities[i].remove();
        }

    }
}
