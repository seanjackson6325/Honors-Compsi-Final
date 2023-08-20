import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

/*
*Name: Sean Jackson
*Date: May 23, 2019
*Program Name: StartScreen.java
*Description:
*/

public class StartScreen {
    //Instance fields
    private final int WIDTH, HEIGHT;
    private int startSize = 1, gameTime, fps;
    boolean pause, startScreen;
    private ImageIcon io;
    private final Image PRESS_START, SUPER_BREAKOUT, PAUSE_ICON, PAUSE;
    private final String PRESS_START_IMAGE_PATH, SUPER_BREAKOUT_IMAGE_PATH, PAUSE_ICON_IMAGE_PATH, PAUSE_IMAGE_PATH;
    //Constructor
    public StartScreen(int w, int h) {
        this.WIDTH = w;
        this.HEIGHT = h;
        PRESS_START_IMAGE_PATH = "PRESS_START.png";
        SUPER_BREAKOUT_IMAGE_PATH = "pixil-frame-0.png";
        PAUSE_ICON_IMAGE_PATH = "PauseSign.png";
        PAUSE_IMAGE_PATH = "pause.png";
        io = new ImageIcon(this.getClass().getResource(PRESS_START_IMAGE_PATH));
        PRESS_START = io.getImage();
        io = new ImageIcon(this.getClass().getResource(SUPER_BREAKOUT_IMAGE_PATH));
        SUPER_BREAKOUT = io.getImage();
        io = new ImageIcon(this.getClass().getResource(PAUSE_ICON_IMAGE_PATH));
        PAUSE_ICON = io.getImage();
        io = new ImageIcon(this.getClass().getResource(PAUSE_IMAGE_PATH));
        PAUSE = io.getImage();
    }
    //Inits variables
    public void tick(boolean pause, boolean startScreen, int gameTime, int fps, int startSize) {
        this.pause = pause;
        this.startScreen = startScreen;
        this.gameTime = gameTime;
        this.fps = fps;
        this.startSize = startSize;
    }
    //Renders start screen
    public void render(Graphics2D g2) {
            g2.drawImage(PAUSE,0,0,WIDTH,HEIGHT,null);
            if(startScreen) {
                if(gameTime<fps/2) {
                    g2.drawImage(PRESS_START,WIDTH/2-184,HEIGHT/2-75,400,400,null);
                }
                if(startSize == 1) {
                    int gt = 30+gameTime;
                    g2.drawImage(SUPER_BREAKOUT, WIDTH/2-gt*10/2, HEIGHT/2-gt*10/2-110, gt*10, gt*10, null);
                }
                if(startSize == -1) {
                    int gt = 90-gameTime;
                    g2.drawImage(SUPER_BREAKOUT, WIDTH/2-gt*10/2, HEIGHT/2-gt*10/2-110, gt*10, gt*10, null);
                }
            }
            else
                if(gameTime<fps/2) {
                g2.drawImage(PAUSE_ICON,WIDTH/2-50,HEIGHT/2-50,100,100,null);
            }
    }
}
