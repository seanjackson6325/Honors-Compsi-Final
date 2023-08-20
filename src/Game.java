/*
*Name: Sean Jackson
*Date: May 8, 2019
*Program Name: Super Breakout
*Description: The game class for Super Breakout
*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Game extends JComponent {
    
    //Instance Fields
    private final int WIDTH = 640, HEIGHT = 480; //For window
    private int fps, gameTime, seconds, level = 2, startSize = 1; //For structure of game and levels
    
    private final String worldPath = "src/Level"; //Path for levels
    
    private boolean left, right, pause = false, startScreen = true; //For moving paddle and pausing game
    
    //ImageIcon and image for pause screen
    private ImageIcon io;
    private Image i;
    
    //For background and startScreen
    private Background background;
    private StartScreen sScreen;
    
    //Dimension for window
    private Dimension d;
    
    //Random object
    private Random random;
    
    //Other object for game
    private Paddle paddle;
    private Ball ball;
    private World world;
    
    //Constructor
    public Game() throws IOException {
        super();
        paddle = new Paddle(WIDTH/2-40,HEIGHT-HEIGHT/16-2,WIDTH);
        ball = new Ball(WIDTH/2, HEIGHT-HEIGHT/2+24, WIDTH, HEIGHT);
        world = new World(worldPath+level, ball);
        background = new Background(WIDTH,HEIGHT,world.getBackground(),world.isMoving());
        random = new Random();
        sScreen = new StartScreen(WIDTH,HEIGHT);
        d = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(d);
    }
    //____________________Updating Game____________________
    
    //Game Loop
    public void start() throws IOException {
        fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        
        while(true) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            if(delta >= 1) {
                tick();
                repaint();
                ticks++;
                delta--;
            }
            
            if(timer >= 1000000000) {
                System.out.println("UPS and FPS: " + ticks + " | Time: " + seconds);
                ticks = 0;
                timer = 0;
            }
        }
    }
    
    //Updates game logic
    public void tick() throws IOException {
        if(pause) {
            paddle.tick();
            ball.tick(paddle.getHitBox(), world.getWorld());
            world.tick();
            background.tick((int)paddle.getX());
            nextWorld();
        }
        sScreen.tick(pause, startScreen, gameTime, fps, startSize);
        gameTime();
    }
    
    //Counts the amount of ticks in a second, keeps track of game clock
    public void gameTime() {
        gameTime+=1;
        if(gameTime==fps) {
            gameTime = 0;
            seconds++;
            startSize = -startSize;
        }
    }
    //_____________________________________________________
    
    //____________________Graphics____________________
    
    //Paints
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        render(g2);
        if(ball.getLives()!=0) setImage("Score_"+ball.getLives()+".png");
        else setImage("Score_"+1+".png");
    }
    //Handles rendering objects
    public void render(Graphics2D g2) {
        background.render(g2);
        if(pause) {
            paddle.render(g2);
            ball.render(g2);
            world.render(g2);
            g2.drawImage(i, WIDTH-50, HEIGHT-50, 50, 50, null);
        }
        if(!pause) sScreen.render(g2);
        setImage("Screen darkness.png");
        g2.drawImage(i, 0, 0, WIDTH, HEIGHT, null);
    }
    //Sets Image
    public void setImage(String path) {
        io = new ImageIcon(this.getClass().getResource(path));
        i = io.getImage();
    }
    //________________________________________________
    //Returns Game Width
    public int getWIDTH() {
        return WIDTH;
    }
    //Returns Game Height
    public int getHEIGHT() {
        return HEIGHT;
    }
    //Returns World
    public World getWorld() {
        return world;
    }
    //Switches to next world
    public void nextWorld() throws IOException {
        if(ball.getLives() == 0) {
            ball.reset();
            world = new World(worldPath+level, ball);
            background = new Background(WIDTH,HEIGHT,world.getBackground(),world.isMoving());
        }
        if(ball.getTileHits() == world.getTileSum()) {
            final int l = level;
            ball.setLives(4);
            do level = random.nextInt(9)+1;
            while(l==level);
            level++;
            ball.reset();
            world = new World(worldPath+level, ball);
            background = new Background(WIDTH,HEIGHT,world.getBackground(),world.isMoving());
        }
    }
    //____________________Input Methods____________________
    public void setSpacePressed(boolean b) {
        this.pause = b;
    }
    public void setLeftPressed(boolean b) {
        paddle.setLeft(b);
    }
    public void setRightPressed(boolean b) {
        paddle.setRight(b);
    }
    //_____________________________________________________
    
    //Makes the startscreen render or not render
    public void setStartScreen(boolean b) {
        this.startScreen = b;
    }
}
