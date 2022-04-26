package bomberman_game.Sound;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;

/**
 * Sound.
 */
public class Sound {
    public static String bombExplose = "BOM_11_M";
    public static String startStage = "startstage";
    public static String bomberDie = "bomber_die";
    public static String item = "item";
    public static String lose = "lose";
    public static String menu = "menu";
    public static String mosterDie = "monster_die";
    public static String newbomb = "newbomb";
    public static String win = "win";
    public static String backgroundGame = "soundtrack";
    public static String playGame = "playgame";
    public static String destroy = "destroy";

    public Sound() {
    }

    public static void play(String filePath) {
        InputStream music;
        try {
            //music = new FileInputStream(new File("C:\\Users\\Huong\\IdeaProjects\\Bomberman_Game\\res\\sound\\" + filePath + ".wav"));
            music = new FileInputStream(new File("res/sound/" + filePath + ".wav"));
            AudioStream audio = new AudioStream(music);
            AudioPlayer.player.start(audio);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * các hàm gọi trong các trường hợp trong game
     *
     *
     *
     */

    public static void playLose() {
        InputStream music;
        try {
            music = new FileInputStream(new File("C:\\Users\\Huong\\IdeaProjects\\Bomberman_Game\\res\\sound\\lose.mid"));
            AudioStream audio = new AudioStream(music);
            AudioPlayer.player.start(audio);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void playDestroy(){
        Sound.play(destroy);
    }

    public static  void playBombExplode(){
        Sound.play(bombExplose);
    }
    public static void playStartStage(){
        Sound.play(startStage);
    }

    public static void playBomberDie(){
        Sound.play(bomberDie);
    }
    public static void playGetNewItem(){
        Sound.play(item);
    }
//    //public static void playLose(){
//        Sound.play(lose);
//    }
    public static void playMenu(){
        Sound.play(menu);
    }
    public static void playMosterDie(){
        Sound.play(mosterDie);
    }
    public static void playPlaceNewBomb(){
        //for ( int i = 0 ; i < 10000 ; i++)
        Sound.play(newbomb);
        // Sound.playBombExplose();
    }
    public static void playWin(){
        Sound.play(win);
    }
    public static void playBackGround(){
        Sound.play(backgroundGame);
    }

}
