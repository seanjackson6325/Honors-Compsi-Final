import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

/*
*Name: Sean Jackson
*Date: May 18, 2019
*Program Name: Background.java
*Description:
*/

public class Background {
    //Instance fields
    private int paddleX, WIDTH, HEIGHT;
    
    private String path;
    
    private boolean moving;
    
    private ImageIcon io;
    private Image i;
    //Constructor
    public Background(int WIDTH, int HEIGHT, String path, boolean moving) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.path = path;
        this.moving = moving;
        ImageIcon io = new ImageIcon(this.getClass().getResource(path));
        i = io.getImage();
    }
    //Handles logic
    public void tick(int paddleX) {
        this.paddleX = paddleX;
    }
    //Renders graphics
    public void render(Graphics2D g2) {
        if(moving) {
            if(paddleX<0) g2.drawImage(i,0+(int)paddleX*-2-WIDTH, 0, WIDTH, HEIGHT,null);  //Background image
            g2.drawImage(i,0+(int)paddleX*-2+WIDTH, 0, WIDTH, HEIGHT,null); //Background image
            if(paddleX<325) g2.drawImage(i,0+(int)paddleX*-2, 0, WIDTH, HEIGHT,null); //Background image
            if(paddleX>315) g2.drawImage(i,0+(int)paddleX*-2+WIDTH*2, 0, WIDTH, HEIGHT,null); //Background image
        }
        else g2.drawImage(i,0, 0, WIDTH, HEIGHT,null); //Background image
    }
    //Sets background image
    public void setBackground(String path) {
        this.path = path;
    }
    //Tells the background object if it is moving or not
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
