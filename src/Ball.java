import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

/*
*Name: Sean Jackson
*Date: May 11, 2019
*Program Name: Ball.java
*Description:
*/

public class Ball
{
    //Instance fields
    private ImageIcon io;
    private Image i;
    private float x,y;
    private int WIDTH = 12, ballSpeed = 3, hits, lives = 4;
    private double velX,velY;
    private int boundLeft = 0, boundRight, boundTop = 0, boundBottem, tileHits;
    private boolean resetTiles = false;
    private Rectangle2D hitBox;
    private Rectangle2D paddle;
    private Tile[][] tile;
    //Constructor
    public Ball(float x, float y, int boundRight, int boundBottem) {
        this.x = x;
        this.y = y;
        hitBox = new Rectangle2D.Double(x,y,WIDTH, WIDTH);
        io = new ImageIcon(this.getClass().getResource("BALL.png"));
        i = io.getImage();
        this.boundRight = boundRight;
        this.boundBottem = boundBottem;
        velX = velY = ballSpeed;
    }
    //Move method - Checks for collisions, moves ball, increases speed of ball
    public void move() {
        if(lives == 0) lives = 4;
        if(y<boundTop) {
            velY = ballSpeed;
        }
        if(y+WIDTH>boundBottem) {
            x = boundRight/2-WIDTH/2;
            y = boundBottem-boundBottem/2+WIDTH*2;
            ballSpeed = 3;
            velX = velY = ballSpeed;
            lives--;
        }
        if(x<boundLeft) {
            velX = ballSpeed;
        }
        if(x+WIDTH>boundRight) {
            velX = -ballSpeed;
        }
        if(hitBox.intersects(paddle)) {
            velY = -3;
            if(x<(paddle.getX()+(paddle.getWidth()/2))-paddle.getWidth()/8) {
                velX = -ballSpeed;
            }
            else if(x>(paddle.getX()+(paddle.getWidth()/2))+paddle.getWidth()/8) {
                velX = ballSpeed;
            }
            else if(x<paddle.getX()+paddle.getWidth()/2+paddle.getWidth()/3&&x>paddle.getX()+paddle.getWidth()/2-paddle.getWidth()/3) {
                velX = velX*0.75;
            }
            hits++;
        }
        //Checks if the ball hits a tile
        for(int r = 0; r<tile.length; r++) {
            for(int c = 0; c<tile[r].length; c++) {
                if(hitBox.intersects(tile[r][c].getHitBox())) {
                    velY = -velY;
                    hits++;
                    tileHits++;
                }
            }
        }
        if(hits>32) {
            hits = 0;
            ballSpeed++;
        }
        x+=velX;
        y+=velY;
    }
    //Updates Ball logic
    public void tick(Rectangle2D paddle, Tile[][] tile) {
        this.paddle = paddle;
        this.tile = tile;
        hitBox.setRect(x,y,WIDTH, WIDTH);
        move();
    }
    //Renders ball
    public void render(Graphics2D g2) {
        g2.drawImage(i, (int)x, (int)y, WIDTH, WIDTH, null);
    }
    //Returns image
    public Image getI() {
        return i;
    }
    //Sets image
    public void setI(Image i) {
        this.i = i;
    }
    //Returns x
    public float getX() {
        return x;
    }
    //Sets x
    public void setX(float x) {
        this.x = x;
    }
    //Returns y
    public float getY() {
        return y;
    }
    //sets y
    public void setY(float y) {
        this.y = y;
    }
    //returns width
    public int getWIDTH() {
        return WIDTH;
    }
    //Returns number of times ball hits a tile
    public int getTileHits() {
        return tileHits;
    }
    //Resets the number of times the ball hits a tile
    public void resetTileHits() {
        tileHits = 0;
    }
    //Sets width
    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }
    //Returns ball x velocity
    public double getVelX() {
        return velX;
    }
    //Sets ball x velocity
    public void setVelX(int velX) {
        this.velX = velX;
    }
    //Returns ball y velocity
    public double getVelY() {
        return velY;
    }
    //Sets ball y velocity
    public void setVelY(int velY) {
        this.velY = velY;
    }
    //Returns hitbox
    public Rectangle2D getHitBox() {
        return hitBox;
    }
    //Resets ball
    public void reset() {
        tileHits = 0;
        x = boundRight/2-WIDTH/2;
        y = boundBottem-boundBottem/2+WIDTH*2;
        ballSpeed = 3;
        velX = velY = ballSpeed;
    }
    public int getLives() {
        return lives;
    }
    public void setLives(int lives) {
        this.lives = lives;
    }
    public boolean isResetTiles() {
        return resetTiles;
    }
}
