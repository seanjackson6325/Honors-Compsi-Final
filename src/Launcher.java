import java.io.IOException;

/*
*Name: Sean Jackson
*Date: May 8, 2019
*Program Name: Super Breakout
*Description: The launcher class for Super Breakout 
*/

public class Launcher {
    //Main method
    public static void main(String[] args) throws IOException {
        //Creates and sets up frame object
        Frame frame = new Frame();
        frame.setPanel();
        frame.run();
    }
}
