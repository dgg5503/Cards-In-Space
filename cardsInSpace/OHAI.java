package cardsInSpace;
import java.awt.*;
import javax.swing.*;

/**
 * Write a description of class OHAI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OHAI
{
public static void main(String[] args)
{
    JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testApplet test = new testApplet();
        frame.getContentPane().add(test);
        test.init();
        frame.pack();
        frame.setVisible(true);
        test.start();
    }
    }
