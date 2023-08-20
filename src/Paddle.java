/*
*Name: Sean Jackson
*Date: May 8, 2019
*Program Name: Paddle.java
*Description:
*/

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Paddle {
    //Instance fields
    private ImageIcon io;
    private Image i;
    private float x;
    private final int y, PADDLE_WIDTH = 80, PADDLE_HEIGHT = 16;
    private int boundLeft = 0, boundRight;
    private boolean right, left;
    private double vel;
    private Rectangle2D hitBox;
    //Constructor
    public Paddle(int x, int y, int boundRight) {
        this.x = x;
        this.y = y;
        this.boundRight = boundRight;
        hitBox = new Rectangle2D.Double(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        init();
    }
    //Initialized some variables of paddle
    private void init() {
        io = new ImageIcon(this.getClass().getResource("PADDLE.png"));
        i = io.getImage();
        vel = 0;
    }
    //Moves paddle
    public void move() {
            if(right) {
                vel+=1;
            }
            if(left) {
                vel-=1;
            }
            if(x+PADDLE_WIDTH>boundRight) {
                //vel=-vel;
                vel=0;
                x = boundRight-PADDLE_WIDTH;
            }
            if(x<boundLeft) {
                //vel=-vel;
                vel=0;
                x = 0;
            }
            vel*=0.9;
            x+=vel;
    }
    //Updates paddle logic
    public void tick() {
        move();
        hitBox.setRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }
    //Renders paddle
    public void render(Graphics2D g2) {
        g2.drawImage(i, (int)x, (int)y, PADDLE_WIDTH, PADDLE_HEIGHT, null); //Draws paddle
    }
    //Returns velocity
    public double getVel() {
        return vel;
    }
    //Returns hitbox
    public Rectangle2D getHitBox() {
        return hitBox;
    }
    //Sets hitbox
    public void setHitBox(Rectangle2D hitBox) {
        this.hitBox = hitBox;
    }
    //Sets velocity
    public void setVel(double vel) {
        this.vel = vel;
    }
    //Returns PADDLE_WIDTH
    public int getPADDLE_WIDTH() {
        return PADDLE_WIDTH;
    }
    //Returns PADDLE_HEIGHT
    public int getPADDLE_HEIGHT() {
        return PADDLE_HEIGHT;
    }
    //Returns image
    public Image getImage() {
        return i;
    }
    //Returns x
    public float getX() {
        return x;
    }
    //Returns y
    public float getY() {
        return y;
    }
    //Returns if the paddle is moving right
    public boolean isRight() {
        return right;
    }
    //Sets the paddle to move right
    public void setRight(boolean right) {
        this.right = right;
    }
    //Returns if the paddle is moving left
    public boolean isLeft() {
        return left;
    }
    //Sets the paddle to move left
    public void setLeft(boolean left) {
        this.left = left;
    }
}
