/*
*Name: Sean Jackson
*Date: May 8, 2019
*Program Name: Frame.java
*Description: The frame class for Super Breakout
*/

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {
    //Instance Fields
    private static final long serialVersionUID = 1L;
    private static Game superBreakout;
    private final JPanel PANEL;
    private int startGame = -1;
    
    //Constructor
    public Frame() throws IOException {
        superBreakout = new Game();
        PANEL = new JPanel();
        PANEL.add(superBreakout);
        PANEL.setBackground(Color.BLACK);
    }
    //Sets Panel when called
    public void setPanel() {
        this.setTitle("Super Breakout!!!");
        this.add(PANEL);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        init();
    }
    //Starts Game when called
    public void run() throws IOException {
        superBreakout.start();
    }
    //Inits panel and keylistener
    private void init() {
        PANEL.add(superBreakout);
        //Uses switch statement to check if keys are pressed
        super.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            
            public void keyPressed(KeyEvent e) { 
                switch(e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    superBreakout.setRightPressed(true);
                    break;
                case KeyEvent.VK_LEFT:
                    superBreakout.setLeftPressed(true);
                    break;
                case KeyEvent.VK_D:
                    superBreakout.setRightPressed(true);
                    break;
                case KeyEvent.VK_A:
                    superBreakout.setLeftPressed(true);
                    break;
                case KeyEvent.VK_SPACE:
                    startGame = -startGame;
                    if(startGame == 1) {
                        superBreakout.setSpacePressed(true);
                        superBreakout.setStartScreen(false);
                    }
                    else
                        superBreakout.setSpacePressed(false);
                    break;
                case KeyEvent.VK_ESCAPE:
                    startGame = -startGame;
                    if(startGame == 1) {
                        superBreakout.setSpacePressed(true);
                        superBreakout.setStartScreen(false);
                    }
                    else
                        superBreakout.setSpacePressed(false);
                    break;
                }
            }
            public void keyReleased(KeyEvent e) {
                switch(e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    superBreakout.setRightPressed(false);
                    break;
                case KeyEvent.VK_LEFT:
                    superBreakout.setLeftPressed(false);
                    break;
                case KeyEvent.VK_D:
                    superBreakout.setRightPressed(false);
                    break;
                case KeyEvent.VK_A:
                    superBreakout.setLeftPressed(false);
                    break;
                }
            }
        });
    }
}
