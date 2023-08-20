import java.awt.Graphics2D;
import java.awt.Image;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class World {
    //Instance fields
    private int col, row, tileSum;
    private String w1, background;
    private boolean moving;
    private FileReader f;
    private Scanner in;
    private Random ran = new Random();
    private ImageIcon io;
    private Image i;
    private Tile[][] w;
    private Ball ball;
    //Constructor
    public World(String w1, Ball ball) throws IOException {
        this.w1 = w1;
        f = new FileReader(w1);
        in = new Scanner(f);
        background = in.nextLine();
        init();
        col = Integer.parseInt(in.nextLine());
        row = Integer.parseInt(in.nextLine());
        io = new ImageIcon(this.getClass().getResource(background));
        i = io.getImage();
        w = new Tile[row][col];
        tileSum = row*col;
        generateWorld();
        this.ball =  ball;
    }
    //Inits some fields and prepares for world generation
    public void init() {
        String s = in.nextLine();
        if(s.equals("true")) moving = true;
        else moving = false;
    }
    //Generates world from file
    public void generateWorld() throws IOException {
        for(int r=0; r<row; r++) {
            String s = in.nextLine();
            for(int c=0; c<col; c++) {
                char q = s.charAt(c);
                if(q == '1') w[r][c] = new Tile(c*40,r*16,255,0,0);
                else if(q == '1') w[r][c] = new Tile(c*40,r*16,255,165,0);
                else if(q == '2') w[r][c] = new Tile(c*40,r*16,255,165,0);
                else if(q == '3') w[r][c] = new Tile(c*40,r*16,255,255,0);
                else if(q == '4') w[r][c] = new Tile(c*40,r*16,0,255,0);
                else if(q == '5') w[r][c] = new Tile(c*40,r*16,0,0,255);
                else if(q == '6') w[r][c] = new Tile(c*40,r*16,128,0,128);
                else if(q == '7') w[r][c] = new Tile(c*40,r*16,255,105,180);
                else if(q == '8') w[r][c] = new Tile(c*40,r*16,255,255,255);
                else if(q == '9') w[r][c] = new Tile(c*40,r*16,0,0,0);
                else if(q == 'r') w[r][c] = new Tile(c*40,r*16,ran.nextInt(255)+1,ran.nextInt(255)+1,ran.nextInt(255)+1);
                else {
                    Tile t = new Tile(c*0,r*0,0,0,0);
                    w[r][c] = t;
                    t.hide();
                    tileSum--;
                }
            }
        }
        f.close();
        in.close();
    }
    //Updates game logic
    public void tick() throws IOException {
        for(int r = 0; r<w.length; r++) {
            for(int c = 0; c<w[r].length; c++) {
                w[r][c].tick(ball);
            }
        }
    }
    //Renders tiles
    public void render(Graphics2D g2) {
        for(int r = 0; r<w.length; r++) {
            for(int c = 0; c<w[r].length; c++) {
                Tile t = w[r][c];
                g2.drawImage(t.getImage(),(int)t.getX(),(int)t.getY(),t.getWIDTH(),t.getHEIGHT(),null);
                if(!t.isHide()) {
                    g2.setColor(t.getColor());
                    g2.fill(t.getColorBox());
                }
            }
        }
    }
    //Returns color integer
    public int getCol() {
        return col;
    }
    //Returns row
    public int getRow() {
        return row;
    }
    //Returns the sum of the tiles
    public int getTileSum() {
        return tileSum;
    }
    //Returns the tile array
    public Tile[][] getWorld() {
        return w;
    }
    //Returns i
    public Image getI() {
        return i;
    }
    //Returns the background image
    public String getBackground() {
        return background;
    }
    //Returns if the background is moving or not
    public boolean isMoving() {
        return moving;
    }
}