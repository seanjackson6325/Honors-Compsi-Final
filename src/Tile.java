import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;

/*
*Name: Sean Jackson
*Date: May 11, 2019
*Program Name: Tile.java
*Description:
*/

public class Tile {
    //Instance fields
    private final int WIDTH = 40, HEIGHT = 16, TRANSPARENCY = 127;
    private int r,g,b;
    private float x,y;
    private boolean isHide;
    private ImageIcon io;
    private Image i;
    private Rectangle2D hitBox;
    private Rectangle2D colorBox;
    private Ball ball;
    private Color color;
    private Random random;
    //Constructor
    public Tile(int x, int y, int r, int g, int b) {
        this.x = x;
        this.y = y;
        io = new ImageIcon(this.getClass().getResource("TILE.png"));
        i = io.getImage();
        hitBox = new Rectangle2D.Double(x,y,WIDTH,HEIGHT);
        colorBox = new Rectangle2D.Double(x+(WIDTH/20),y+(HEIGHT/16)*2,WIDTH-2*(WIDTH/20),HEIGHT-4*(HEIGHT/16));
        random = new Random();
        color = new Color(r,g,b,TRANSPARENCY);
    }
    //Makes the tile not collidable and hides the image
    public void hide() {
        hitBox.setRect(0,0,0,0);
        isHide = true;
        i = null;
    }
    //Updates tile logic
    public void tick(Ball ball) {
        this.ball = ball;
        if(hitBox.intersects(this.ball.getHitBox())) hide();
    }
    //Returns hitBox
    public Rectangle2D getHitBox() {
        return hitBox;
    }
    //returns the colorbox the rectangle
    public Rectangle2D getColorBox() {
        return colorBox;
    }
    //Returns tile image
    public Image getImage() {
        return i;
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
    //Sets y
    public void setY(float y) {
        this.y = y;
    }
    //Returns i
    public Image getI(){
        return i;
    }
    //Sets i
    public void setI(Image i) {
        this.i = i;
    }
    //Returns width
    public int getWIDTH() {
        return WIDTH;
    }
    //Returns height
    public int getHEIGHT() {
        return HEIGHT;
    }
    //Returns color of tile
    public Color getColor() {
        return color;
    }
    //Returns if the tile is hidden or not
    public boolean isHide() {
        return isHide;
    }
}
