package cardsInSpace;


/**
 * Overall things to go over
 * -Defense in ship, how will defense points be handled? How many should be the max?
 * -Should there be multiple ships, if so should ships carry mini-decks to hold cards they could use?
 * 
 * Ideas for cards
 * -There should be some cards that can hurt the user but at the same time help him (like stimpack from sc2)
 * -
 */

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * Class testApplet - write a description of the class here
 * 
 * @author (your name) 
 * @version (a version number)
 */
public class testApplet extends JApplet
{
    // instance variables - replace the example below with your own
    private int x;
    
    private static Random gen = new Random();
    //private static ArrayList<card> mainDeck;
     /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * has been loaded into the system. It is always called before the first 
     * time that the start method is called.
     */
    public void init()
    {
        // this is a workaround for a security conflict with some browsers
        // including some versions of Netscape & Internet Explorer which do 
        // not allow access to the AWT system event queue which JApplets do 
        // on startup to check access. May not be necessary with your browser. 
        JRootPane rootPane = this.getRootPane();    
        rootPane.putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
    
        // provide any initialisation necessary for your JApplet
    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it 
     * should start its execution. It is called after the init method and 
     * each time the JApplet is revisited in a Web page. 
     */
    public void start()
    {
        // provide any code requred to run each time 
        // web page is visited
    }

    /** 
     * Called by the browser or applet viewer to inform this JApplet that
     * it should stop its execution. It is called when the Web page that
     * contains this JApplet has been replaced by another page, and also
     * just before the JApplet is to be destroyed. 
     */
    public void stop()
    {
        // provide any code that needs to be run when page
        // is replaced by another page or before JApplet is destroyed 
    }
    
    
    //To-do (5/15/13)
    //+Continue implementing Deck class...
    //-Include some form of input
    
    /*
     * General way a game works (from what I can attempt to read)
     * 
     * Key:
     * - = Will be implemented
     * * = May or may not be implemented (discuss with others)
     * + = An idea OR one of the last things to go in
     * 
     * 
     * //other ideas
     * -Priority for certain cards so that a player playing the defense card does not die
     * from an attack just because the attack was initiated first. perhaps defense ROUNDS
     * and attack ROUNDS or just give priority over defense cards (ie. setPriority function,
     * then sort by priority in the execution deck)
     * 
     * The following note applies to the below suggestion
     * -Me and Austin agreed on having the game as turn based so no need for speed
     * as cards will be played and then executed for each turn a player gets rather then simultaneously 
     * firing both cards... speed functions and anything related to speed have been removed.
     * VVVVVVVVVVVVVVVVVVVV
     * -Ship "speed" decides who the winner is in certain situations by placing a priority over
     * certain ships for attack cards.
     * 
     * 
     * (example for equation, % chance of person going first is (speed1)/(speed1+speed2))
     * -Hardpoints for each ship, points in which an attachable can be placed, the hardpoints array
     * is an array of points or x,y coords in which a weapon (image) can be properly attached
     * 
     * 
     * //main game
     * -Setup two players, allow them to name their first ship 
     * *Let each player chose their starting ship
     * -Main decks are filled and shuffled
     * -Get top 6 cards for players to pick
     * -Players chose cards in 1,2,2,1,1,2
     * -Random on who gets to start
     * -Play game
     * 
     * -person to chose cards first goes second.
     */
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        
        //Setup decks (fillDeck automatically called when constructed for decks that need it)
        System.out.println("Filling decks...");
        passiveDeck pDeck = new passiveDeck(); // sub class for passives and actions and junkyard...
        actionDeck aDeck = new actionDeck();
        executionDeck mainDeck = new executionDeck();
        Deck junkyard = new Deck();
        
        //Set up 2 players
        System.out.print("Enter Player 1 Name: ");
        player p1 = new player(sc.next(), mainDeck); //input for player 1 name
        System.out.print("Enter Player 2 Name: ");
        player p2 = new player(sc.next(), mainDeck); //input for player 2 name
        
        //Randomly pick player to select first card
        int firstPlayer = gen.nextInt(2) + 1;
        System.out.println("Player " + firstPlayer + " picks the first card.");
        //player firstPlayer starts the picking, second starts the actual game.
        
        //Pick 12 cards from passive deck, allow each player to pick 3, passive cards are executed immediately as seen in the pickCard function in player
        //Pick 12 cards from action deck, allow each player to pick 3, action cards are placed into the player deck as seen in the pickCard function in player
        //1,2,2,1 picking fashion commences
        Deck tmpDeck;
        for(int i = 0; i < 2; i++)
        {
            tmpDeck = new Deck();
            
            if(i == 0)
            {
                pDeck.transferCardRange(tmpDeck, 0, 11); //12 cards picked from passive deck to tmpDeck for ease of use
            }
            
            if(i == 1)
            {
                aDeck.transferCardRange(tmpDeck, 0, 11); //12 cards picked from action deck to tmpDeck for ease of use
            }
            
            System.out.println("----AVAILABLE CARDS----");
            System.out.println(tmpDeck);
            
            //---------THIS WOULD BE THE FIRST PICK, SOME PLAYER firstPlayer PICKS ONLY 1 CARD THEN firstPlayer VARIABLE SWITCHES TO THE NEXT PLAYER----------
            if(firstPlayer == 1)
            {
                System.out.print("Player 1 chose card: ");
                p1.pickACard(tmpDeck, sc.nextInt() - 1);
                firstPlayer = 2;
            }
            else
            {
                if(firstPlayer == 2)
                {
                   System.out.print("Player 2 chose card: ");
                   p2.pickACard(tmpDeck, sc.nextInt() - 1);
                   firstPlayer = 1;
                }
            }
            //---------THIS WOULD BE THE FIRST PICK, SOME PLAYER firstPlayer PICKS ONLY 1 CARD THEN firstPlayer VARIABLE SWITCHES TO THE NEXT PLAYER----------
            
            int total = 0; //
            
            //---------THIS WOULD BE THE SECOND AND THIRD PICK, SOME PLAYER firstPlayer PICKS 2 CARDS THEN firstPlayer VARIABLE SWITCHES TO THE NEXT PLAYER----------
            while(total < 3)
            {
                System.out.println("----AVAILABLE CARDS----");
                System.out.println(tmpDeck);
                
                if(firstPlayer == 2)
                {
                    System.out.print("Player 2 chose card: ");
                    p2.pickACard(tmpDeck, sc.nextInt() - 1);
                    System.out.println("----AVAILABLE CARDS----");
                    System.out.println(tmpDeck);
                    System.out.print("Player 2 chose card: ");
                    p2.pickACard(tmpDeck, sc.nextInt() - 1);
                    total+=2;
                    firstPlayer = 1;
                }
                else
                {
                    if(firstPlayer == 1)
                    {
                        System.out.print("Player 1 chose card: ");
                        p1.pickACard(tmpDeck, sc.nextInt() - 1);
                        System.out.println("----AVAILABLE CARDS----");
                        System.out.println(tmpDeck);
                        System.out.print("Player 1 chose card: ");
                        p1.pickACard(tmpDeck, sc.nextInt() - 1);
                        total+=2;
                        firstPlayer = 2;
                    }
                }
                
            }
            //---------THIS WOULD BE THE SECOND AND THIRD PICK, SOME PLAYER firstPlayer PICKS 2 CARDS THEN firstPlayer VARIABLE SWITCHES TO THE NEXT PLAYER----------
            
            System.out.println("----AVAILABLE CARDS----");
            System.out.println(tmpDeck);
            
            //---------THIS WOULD BE THE LAST PICK,SOME PLAYER firstPlayer PICKS ONLY 1 CARD THEN firstPlayer VARIABLE SWITCHES TO THE NEXT PLAYER----------
            if(firstPlayer == 1)
            {
                System.out.print("Player 1 chose card: ");
                p1.pickACard(tmpDeck, sc.nextInt() - 1);
                firstPlayer = 2;
            }
            else
            {
                if(firstPlayer == 2)
                {
                   System.out.print("Player 2 chose card: ");
                   p2.pickACard(tmpDeck, sc.nextInt() - 1);
                   firstPlayer = 1;
                }
            }
            //---------THIS WOULD BE THE LAST PICK,SOME PLAYER firstPlayer PICKS ONLY 1 CARD THEN firstPlayer VARIABLE SWITCHES TO THE NEXT PLAYER----------
            
            if(i == 0)
            {
                tmpDeck.transferDeckContentsTo(pDeck); //transfers any left over passive cards to the bottom of the passiveDeck
            }
            
            if(i == 1)
            {
                tmpDeck.transferDeckContentsTo(aDeck); //transfers any left over action cards to the bottom of the passiveDeck
            }
        } 
        
        //First player is made to be the one who DIDNT pick first.
        if(firstPlayer == 1)
        {
            firstPlayer = 2;
        }
        else
        {
            if(firstPlayer == 2)
            {
                firstPlayer = 1;
            }
        }
        
        /*
        //Create the temp junkyard, to be used as a checker for all expired cards in the mainAction deck
        //if the cards in that current turns mainAction deck have expired then tmpJunkyard will NOT return null
        //if it returns null then obviously no cards have expired meaning they have a turnDecay greater than 0
        
        
        //Fires the actions of all the cards that have been played by the current player for that turn
        //if that players card(s) have a turnDecay greater than 0 then they will continue to fire until
        //that card(s) turnDecay has reached 0 in which case it will be added to the expiredCards arrayList
        //in the mainDeck class and getExpiredDeck() will NOT return null which will mean that those expired
        //cards will go to the junkyard.
        //THIS FIREACTIONS IS ONLY FOR THE PASSIVE CARDS!!!!
        mainDeck.fireActions();
        
        //the check as mentioned previously
        tmpJunkyard = mainDeck.getExpiredDeck();
        if(tmpJunkyard != null)
        {
            tmpJunkyard.transferDeckContentsTo(junkyard);
        }
        */
        
        //Display initial stats after executing passive cards
        System.out.println("----Initial Setup----");
        System.out.println("Ship\t\tHealth\t\tDefense");
        System.out.println(p1.getShip().getName() + "\t\t" + p1.getShip().getTotalHealth() + "\t\t" + p1.getShip().getTotalDefense() + "");
        System.out.println(p2.getShip().getName() + "\t\t" + p2.getShip().getTotalHealth() + "\t\t" + p2.getShip().getTotalDefense() + "");
        System.out.println("----Initial Setup----");
        
        
        int i = 1;
        
        //Shuffles cards
        pDeck.shuffle(5);
        aDeck.shuffle(5);
        
        Deck tmpJunkyard;
        
        while(p1.getShip().getTotalHealth() > 0 && p2.getShip().getTotalHealth() > 0) //game keeps going until some ship has negative health
        {
            //turn based.
            if(firstPlayer == 1)
            {
                System.out.print("Player 1 pick card: ");
                System.out.println(p1.getPlayerDeck()); //Display their hand
                p1.playCard(sc.nextInt() - 1, p2); //allow them to pick
            }
            else
            {
                if(firstPlayer == 2)
                {
                    System.out.print("Player 2 pick card: ");
                    System.out.println(p2.getPlayerDeck()); //Display their hand
                    p2.playCard(sc.nextInt() - 1, p1); //allow them to pick
                }
            }
            
            //Fires the actions of all the cards that have been played by the current player for that turn
            //if that players card(s) have a turnDecay greater than 0 then they will continue to fire until
            //that card(s) turnDecay has reached 0 in which case it will be added to the expiredCards arrayList
            //in the mainDeck class and getExpiredDeck() will NOT return null which will mean that those expired
            //cards will go to the junkyard.
            mainDeck.fireActions();
            
            //to be used as a checker for all expired cards in the mainAction deck
            //if the cards in that current turns mainAction deck have expired then tmpJunkyard will NOT return null
            //if it returns null then obviously no cards have expired meaning they have a turnDecay greater than 0
            tmpJunkyard = mainDeck.getExpiredDeck();
            
            //the check as mentioned previously
            if(tmpJunkyard != null)
            {
                tmpJunkyard.transferDeckContentsTo(junkyard);
            }
            
            System.out.println("----ACTION ROUND----");
            System.out.println("Ship\t\tHealth\t\tDefense");
            System.out.println(p1.getShip().getName() + "\t\t" + p1.getShip().getTotalHealth() + "\t\t" + p1.getShip().getTotalDefense() + "");
            System.out.println(p2.getShip().getName() + "\t\t" + p2.getShip().getTotalHealth() + "\t\t" + p2.getShip().getTotalDefense() + "");
            System.out.println();
            
            //Check for empty decks.
            //if a deck is empty recreate it (this automatically refills it and shuffles)
            if(aDeck.sizeOfDeck() == 0)
            {
                aDeck = new actionDeck();
            }
            
            //if a deck is empty recreate it (this automatically refills it and shuffles)
            if(pDeck.sizeOfDeck() == 0)
            {
                pDeck = new passiveDeck();
            }
            
            //Repicking round...
            //A passive card and action card is automatically picked for the player who just finished playing their card...
            if(firstPlayer == 1)
            {  
                p1.pickACard(aDeck, 0); //this card goes into playerDeck since its an action card
                p1.pickACard(pDeck, 0); //this card autoexectues since passive 
                firstPlayer = 2; //switch player
            }
            else
            {
                if(firstPlayer == 2)
                {
                    p2.pickACard(aDeck, 0); //this card goes into playerDeck since its an action card
                    p2.pickACard(pDeck, 0); //this card autoexectues since passive 
                    firstPlayer = 1; //switch player
                }
            }
            
            System.out.println("----PASSIVE ROUND----");
            System.out.println("Ship\t\tHealth\t\tDefense");
            System.out.println(p1.getShip().getName() + "\t\t" + p1.getShip().getTotalHealth() + "\t\t" + p1.getShip().getTotalDefense() + "");
            System.out.println(p2.getShip().getName() + "\t\t" + p2.getShip().getTotalHealth() + "\t\t" + p2.getShip().getTotalDefense() + "");
            System.out.println("----End of Turn " + i + "----");
            System.out.println();
            
            i++;
        }
        
        //OLD DEBUG STUFF
        /*
        //AUTOMATED CARD PICKS
        pDeck.transferCardRange(p1.getPlayerDeck(), 0, 1);
        pDeck.transferCard(p2.getPlayerDeck(), 0);
        
        aDeck.transferCard(p1.getPlayerDeck(), 0);
        aDeck.transferCardRange(p2.getPlayerDeck(), 0, 1);
        */
        
        //Game starts (maybe a class for this?)
        
        
       
        //OLD DEBUG STUFF
        /*
        //Set up 2 players
        player p1 = new player("p1");
        player p2 = new player("p2");
        
        //Setup decks (fillDeck automatically called when constructed for decks that need it)
        passiveDeck pDeck = new passiveDeck(); // sub class for passives and actions and junkyard...
        actionDeck aDeck = new actionDeck();
        executionDeck mainDeck = new executionDeck();
        Deck junkyard = new Deck();
        
        //Autotransfer
        pDeck.transferCardRange(p1.getPlayerDeck(), 0, 1);
        pDeck.transferCard(p2.getPlayerDeck(), 0);
        
        aDeck.transferCard(p1.getPlayerDeck(), 0);
        aDeck.transferCardRange(p2.getPlayerDeck(), 0, 1);
        
        //Text mode setup for this thing. debug
        System.out.println("Ship\t\tHealth\t\tDefense");
        System.out.println(p1.getShip().getName() + "\t\t" + p1.getShip().getTotalHealth() + "\t\t" + p1.getShip().getTotalDefense() + "");
        System.out.println(p2.getShip().getName() + "\t\t" + p2.getShip().getTotalHealth() + "\t\t" + p2.getShip().getTotalDefense() + "");
        System.out.println("---------");
        
        
        //Player 1 goes first
        
        
        int i = 1;
        //while(p1.getPlayerDeck().sizeOfDeck() != 0 || p2.getPlayerDeck().sizeOfDeck() != 0)
        for(int z = 0; z < 3; z++)
        {
            //Players select cards
            mainDeck.addCard(p1.playCard(0,p1,p2));
            mainDeck.addCard(p2.playCard(0,p2,p1));
            
            //Fire actions
            mainDeck.fireActions();
            
            //Create tmp deck so we can check if it recieved cards
            Deck tmpJunkyard = mainDeck.getExpiredDeck();
            
            //Place expired cards in junkYard deck
            if(tmpJunkyard != null)
            {
                tmpJunkyard.transferDeckContentsTo(junkyard);
            }
           
            //Display Stats
            System.out.println("Ship\t\tHealth\t\tDefense");
            System.out.println(p1.getShip().getName() + "\t\t" + p1.getShip().getTotalHealth() + "\t\t" + p1.getShip().getTotalDefense() + "");
            System.out.println(p2.getShip().getName() + "\t\t" + p2.getShip().getTotalHealth() + "\t\t" + p2.getShip().getTotalDefense() + "");
            System.out.println("----End of Turn " + i + "----");
            
            
            
            i++;
        }
        
        System.out.print(junkyard.toString());
        */
    }

    /**
     * Paint method for applet.
     * 
     * @param  g   the Graphics object for this applet
     */
    public void paint(Graphics g)
    {
        
    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * is being reclaimed and that it should destroy any resources that it
     * has allocated. The stop method will always be called before destroy. 
     */
    public void destroy()
    {
        // provide code to be run when JApplet is about to be destroyed.
    }


    /**
     * Returns information about this applet. 
     * An applet should override this method to return a String containing 
     * information about the author, version, and copyright of the JApplet.
     *
     * @return a String representation of information about this JApplet
     */
    public String getAppletInfo()
    {
        // provide information about the applet
        return "Title:   \nAuthor:   \nA simple applet example description. ";
    }


    /**
     * Returns parameter information about this JApplet. 
     * Returns information about the parameters than are understood by this JApplet.
     * An applet should override this method to return an array of Strings 
     * describing these parameters. 
     * Each element of the array should be a set of three Strings containing 
     * the name, the type, and a description.
     *
     * @return a String[] representation of parameter information about this JApplet
     */
    public String[][] getParameterInfo()
    {
        // provide parameter information about the applet
        String paramInfo[][] = {
                 {"firstParameter",    "1-10",    "description of first parameter"},
                 {"status", "boolean", "description of second parameter"},
                 {"images",   "url",     "description of third parameter"}
        };
        return paramInfo;
    }
}
