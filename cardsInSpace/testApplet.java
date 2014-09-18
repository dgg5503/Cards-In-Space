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
//Size of window: 1024 x 768
/*IDEAS
 * card holder
 * log for recently played cards/actions/whatever
 * viual representation of both decks
 * click cards to create a new jframe that contains info about the card
 * dragging a line from the card to the ship to perform action
 */
import java.lang.String;
import java.awt.*;
import javax.swing.*;
import java.applet.Applet;  
import java.awt.image.*; 
import java.io.*; 
import java.net.URL; 
import javax.imageio.*; 
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JPanel;
/**
 * Class testApplet - write a description of the class here
 * 
 * @author (your name) 
 * @version (a version number)
 */
public class testApplet extends JApplet implements ActionListener
{
private static Random gen = new Random();
    // instance variables - replace the example below with your own
    private int x;
    private static final int width = 1024;
    private static final int height = 768;
    //private static ArrayList<card> mainDeck;
    private Image fight1;
    private Image fight2;
    private Image attack;
    private Image defense;
    private Image attackb;
    private Image attackd;
    private Image defenseb;
    private Image defensed;
    private Image cardback;
    private Image addAutoCannon;
    private Image addBeamLaser;
    private Image addDoubleMissile;
    private Image addGunTurret;
    private Image addMissile;
    private Image addOctupleMissile;
    private Image addQuadMissile;
    private Image addRailGun;
    private Image addSniperCannon;
    private Image addSpreadGun;
    private Image addSpreadLaser;
    private Image addStandardLaser;
    private Image addTripleMissile;
    private Image autoCannon;
    private Image beamLaser;
    private Image doubleMissile;
    private Image gunTurret;
    private Image octoMissile;
    private Image quadMissile;
    private Image railCannon;
    private Image sniperCannon;
    private Image spreadCannon;
    private Image spreadLaser;
    private Image standardLaser;
    private Image standardMissile;
    private Image tripleMissile;
    Rectangle hp1 = new Rectangle(0,100,20, 160);
    Rectangle hp2 = new Rectangle(width - 20,100,20, 160);
    MouseListener listener;
    private boolean cardSelect = true;
    private int count = 0;
    Label top;
    private int z = -1;
    Deck tmpJunkyard;
    Deck junkyard;
    executionDeck mainDeck;
    passiveDeck pDeck;
    actionDeck aDeck;
    Deck tmpDeck;
    //Button b1;
    player p1;
    player p2;
    player current;
    int currentPlayer;
    int turn = 1;
    int times = 0;
    int phase = 0;
    boolean end = false;
    
    
    //int phase = 2;
     
    
    public void init()
    {
        
        
        rootPane.putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        /*fight1 = this.getImage(this.getCodeBase() , "fighter-png.jpg");
        fight2 = this.getImage(this.getCodeBase() , "fighterR-png.jpg");
        attack = this.getImage(this.getCodeBase() , "attack-png.jpg");
        defense = this.getImage(this.getCodeBase() , "Defense-png.jpg");
        attackb = this.getImage(this.getCodeBase() , "attackbuff-png.jpg");
        attackd = this.getImage(this.getCodeBase() , "attackdebuff-png.jpg");
        defenseb = this.getImage(this.getCodeBase() , "defensebuff-png.jpg");
        defensed = this.getImage(this.getCodeBase() , "defensedebuff-png.jpg");*/
        listener= new MouseClickedListener();
        addMouseListener(listener);
        
        //System.out.println("Filling decks...");
        pDeck = new passiveDeck(); // sub class for passives and actions and junkyard...
        aDeck = new actionDeck();
        mainDeck = new executionDeck();
        junkyard = new Deck();
        
        //tmpDeck = new Deck();
        tmpDeck = aDeck;
        aDeck.transferCardRange(tmpDeck, 0, 11);
        p1 = new player("Player 1", mainDeck);
        p2 = new player("Player 2", mainDeck);
        currentPlayer = gen.nextInt(2) + 1;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL one = classLoader.getResource("cardsInSpace/fighter-png.jpg");
            fight1 = ImageIO.read(one);
            URL two = classLoader.getResource("cardsInSpace/fighterR-png.jpg");
            fight2 = ImageIO.read(two);
            URL three = classLoader.getResource("cardsInSpace/attack-png.jpg");
            attack = ImageIO.read(three);
            URL four = classLoader.getResource("cardsInSpace/Defense-png.jpg");
            defense = ImageIO.read(four);
            URL five = classLoader.getResource("cardsInSpace/attackbuff-png.jpg");
            attackb = ImageIO.read(five);
            URL six = classLoader.getResource("cardsInSpace/attackdebuff-png.jpg");
            attackd = ImageIO.read(six);
            URL seven = classLoader.getResource("cardsInSpace/defensebuff-png.jpg");
            defenseb = ImageIO.read(seven);
            URL eight = classLoader.getResource("cardsInSpace/defensedebuff-png.jpg");
            defensed = ImageIO.read(eight);
            URL nine = classLoader.getResource("cardsInSpace/cardReverse-png.jpg");
            cardback = ImageIO.read(nine);
            URL ten = classLoader.getResource("cardsInSpace/addAutoCannon.jpg");
            addAutoCannon = ImageIO.read(ten);
            URL eleven = classLoader.getResource("cardsInSpace/addBeamLaser.jpg");
            addBeamLaser = ImageIO.read(eleven);
            URL twelve = classLoader.getResource("cardsInSpace/addDoubleMissile.jpg");
            addDoubleMissile = ImageIO.read(twelve);
            URL thirteen = classLoader.getResource("cardsInSpace/addGunTurret.jpg");
            addGunTurret = ImageIO.read(thirteen);
            URL fourteen = classLoader.getResource("cardsInSpace/addMissile.jpg");
            addMissile = ImageIO.read(fourteen);
            URL fifteen = classLoader.getResource("cardsInSpace/addOctupleMissile.jpg");
            addOctupleMissile = ImageIO.read(fifteen);
            URL sixteen = classLoader.getResource("cardsInSpace/addQuadMissile.jpg");
            addQuadMissile = ImageIO.read(sixteen);
            URL seventeen = classLoader.getResource("cardsInSpace/addRailGun.jpg");
            addRailGun = ImageIO.read(seventeen);
            URL eighteen = classLoader.getResource("cardsInSpace/addSniperCannon.jpg");
            addSniperCannon = ImageIO.read(eighteen);
            URL nineteen = classLoader.getResource("cardsInSpace/addSpreadGun.jpg");
            addSpreadGun = ImageIO.read(nineteen);
            URL twenty = classLoader.getResource("cardsInSpace/addSpreadLaser.jpg");
            addSpreadLaser = ImageIO.read(twenty);
            URL twentyone = classLoader.getResource("cardsInSpace/addStandardLaser.jpg");
            addStandardLaser = ImageIO.read(twentyone);
            URL twentytwo = classLoader.getResource("cardsInSpace/addTripleMissile.jpg");
            addTripleMissile = ImageIO.read(twentytwo);
            URL twentythree = classLoader.getResource("cardsInSpace/beamLaser.jpg");
            beamLaser = ImageIO.read(twentythree);
            URL twentyfour = classLoader.getResource("cardsInSpace/doubleMissile.jpg");
            doubleMissile = ImageIO.read(twentyfour);
            URL twentyfive = classLoader.getResource("cardsInSpace/gunTurret.jpg");
            gunTurret = ImageIO.read(twentyfive);
            URL twentysix = classLoader.getResource("cardsInSpace/octoMissile.jpg");
            octoMissile = ImageIO.read(twentysix);
            URL twentyseven = classLoader.getResource("cardsInSpace/quadMissile.jpg");
            quadMissile = ImageIO.read(twentyseven);
            URL twentyeight = classLoader.getResource("cardsInSpace/railCannon.jpg");
            railCannon = ImageIO.read(twentyeight);
            URL twentynine = classLoader.getResource("cardsInSpace/sniperCannon.jpg");
            sniperCannon = ImageIO.read(twentynine);
            URL thirty = classLoader.getResource("cardsInSpace/spreadCannon.jpg");
            spreadCannon = ImageIO.read(thirty);
            URL thirtyone = classLoader.getResource("cardsInSpace/spreadLaser.jpg");
            spreadLaser = ImageIO.read(thirtyone);
            URL thirtytwo = classLoader.getResource("cardsInSpace/standardLaser.jpg");
            standardLaser = ImageIO.read(thirtytwo);
            URL thirtythree = classLoader.getResource("cardsInSpace/standardMissile.jpg");
            standardMissile = ImageIO.read(thirtythree);
            URL thirtyfour = classLoader.getResource("cardsInSpace/tripleMissile.jpg");
            tripleMissile = ImageIO.read(thirtyfour);
            URL thirtyfive = classLoader.getResource("cardsInSpace/autoCannon.jpg");
            autoCannon = ImageIO.read(thirtyfive);
        } catch (IOException e) {
        }
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        top = new Label("Welcome to Cardsinspace! Active Cards- " + "Current Player: " + currentPlayer);
        Button b = new Button("Choose this card");
        add(top);
        add(b);
        b.addActionListener(this);
        //delay(1000);
        //top.setText("Current Player: " + currentPlayer);
        
        /*
        p1.pickACard(tmpDeck, 3);
        p1.pickACard(tmpDeck, 4);
        p1.pickACard(tmpDeck, 5);
        
        p2.pickACard(tmpDeck, 0);
        p2.pickACard(tmpDeck, 1);
        p2.pickACard(tmpDeck, 2);
        
        p1.resetDeck();
        p2.resetDeck();
        */
        
        //add(new Label("Another Label"));
    }

    public void actionPerformed(ActionEvent e)
    {
if(end == false){
        //Button source = (Button)e.getSource();
       //top.setText("THIS DOESN'T WORK!!!");
       if(z == -1) top.setText("NOT VALID"); else{
       if(phase == 0 || phase == 1)
       {
        if(currentPlayer == 1)
        {
            p1.pickACard(tmpDeck, z);
            times++;
            if(turn == 1){
            currentPlayer = 2; turn = 0;
            }else turn++;
        }else{
        if(currentPlayer == 2)
        {
            p2.pickACard(tmpDeck, z);
            times++;
            if(turn == 1){
            currentPlayer = 1; turn = 0;
            }else turn++;
        }
       }
    }
       if(phase == 2)
       {
           if(currentPlayer == 1)
           {
               p1.playCard(z, p2);
            }else{
                if(currentPlayer == 2)
                {
                    p2.playCard(z-3, p1); //-3 to adjust for +3 in mouseclicked
                }
            }
            mainDeck.fireActions();
            tmpJunkyard = mainDeck.getExpiredDeck();
            if(tmpJunkyard != null)
            {
                tmpJunkyard.transferDeckContentsTo(junkyard);
            }
            //if(aDeck.sizeOfDeck() == 0)
            //{
                aDeck = new actionDeck();
            //}
            
            //if(pDeck.sizeOfDeck() == 0)
            //{
                pDeck = new passiveDeck();
            //}
            int turrettest = 0;
            if(currentPlayer == 1)
            {  
                for(card c : p1.getDeck().returnCards())
                {
                    if(c instanceof addHardPoint) turrettest++;
                }
                if(p1.getShip().vacant() == false || (p1.getShip().numTurrets() + turrettest) >= 6)
                {
                    while(aDeck.getCard(0) instanceof addHardPoint)
                    {
                        aDeck = new actionDeck();
                    }
                }
                p1.pickACard(aDeck, 0);
                p1.pickACard(pDeck, 0);
                currentPlayer = 2;
            }
            else
            {
                if(currentPlayer == 2)
                {
                    for(card c : p2.getDeck().returnCards())
                        {
                            if(c instanceof addHardPoint) turrettest++;
                        }
                    if(p2.getShip().vacant() == false || (p2.getShip().numTurrets() + turrettest) >= 6)
                    {
                        while(aDeck.getCard(0) instanceof addHardPoint)
                        {
                        aDeck = new actionDeck();
                        }
                    }
                    p2.pickACard(aDeck, 0);
                    p2.pickACard(pDeck, 0);
                    currentPlayer = 1;
                }
            }
            
            double delta1 = (p1.getShip().getTotalHealth() / 100 * 160); //new heights of hp bars
            double delta2 = (p2.getShip().getTotalHealth() / 100 * 160);
            
            hp1 = new Rectangle(0,260 - (int)delta1,20, (int)delta1);
            hp2 = new Rectangle(width - 20,260 - (int)delta2,20, (int)delta2);
            
            if(p1.getShip().getTotalHealth() < 0) {top.setText("PLAYER 2 WINS"); end = true;}
            if(p2.getShip().getTotalHealth() < 0) {top.setText("PLAYER 1 WINS"); end = true;}
        }
    if(end == false)
    top.setText("Current Player: " + currentPlayer);
    
        if(times >= 6)
            if(phase < 2){ phase++; times = 0;
                if(phase == 1)
                top.setText("Passive Cards- " + "Current Player: " + currentPlayer);
                if(phase == 2)
                top.setText("Battle! " + "Current Player: " + currentPlayer);
            }
            
      if(phase == 1) tmpDeck = pDeck;
      
      if(phase == 2) {
          //if(currentPlayer == 1)
          p1.resetDeck(); 
          //else
          p2.resetDeck();}
      
      if(currentPlayer == 1) {current = p1;}
      if(currentPlayer == 2) {current = p2;}
      
      repaint();
    }
    }
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
        if(currentPlayer ==1)top.setText("PLAYER 2 WINS");
        if(currentPlayer ==2)top.setText("PLAYER 1 WINS");
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
     * -Setup two players, allow them to name their first ship 
     * *Let each player chose their starting ship
     * -Main decks are filled and shuffled
     * -Get top 6 cards for players to pick
     * -Players chose cards in 1,2,2,1,1,2
     * -Random on who gets to start
     * -Play game
     */
    public static void main(String args[])
    {


        JFrame frame = new JFrame("FIIIIIIIIIIGGGGGHHHHHHHHHHHTORRRZZZZZ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testApplet test = new testApplet();
        frame.getContentPane().add(test);
        test.init();
        //frame.pack();
        frame.setSize(1042, 793);
        frame.setVisible(true);
        test.start();

        
    }

    /**
     * Paint method for applet.
     * 
     * @param  g   the Graphics object for this applet
     */
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle rect = new Rectangle(0,0,1024,768);
        g2.setColor(Color.white);
        g2.fill(rect);
        //attack atk = new attack();
        //atk.draw(g2, this);
        if(phase == 0)
        {
        for(int i = 0; i < 6; i ++)
       {
           if(tmpDeck.getCard(i).status() == true)
           g2.drawImage(cardback, 82 + (82 + 75) * i, 214, 75, 120, this);
           else{
           if(tmpDeck.getCard(i) instanceof fireAllLasers || tmpDeck.getCard(i) instanceof fireEverything || tmpDeck.getCard(i) instanceof fireAllMissiles || tmpDeck.getCard(i) instanceof fireAllGuns)
           g2.drawImage(attack, 82 + (82 + 75) * i, 214, 75, 120, this);
           if(tmpDeck.getCard(i) instanceof defend)
           g2.drawImage(defense, 82 + (82 + 75) * i, 214, 75, 120, this);
           if(tmpDeck.getCard(i) instanceof addHardPoint)
           drawTurret(tmpDeck.getCard(i),82 + (82 + 75) * i, 214, 75, 120, g2);
        }
        }
        for(int i = 6; i < 12; i ++)
       {
           if(tmpDeck.getCard(i).status() == true)
            g2.drawImage(cardback, 82 + (82 + 75) * (i - 6), 434, 75, 120, this);
           else{
           if(tmpDeck.getCard(i) instanceof fireAllLasers || tmpDeck.getCard(i) instanceof fireEverything || tmpDeck.getCard(i) instanceof fireAllMissiles || tmpDeck.getCard(i) instanceof fireAllGuns)
           g2.drawImage(attack, 82 + (82 + 75) * (i - 6), 434, 75, 120, this);
           if(tmpDeck.getCard(i) instanceof defend)
           g2.drawImage(defense, 82 + (82 + 75) * (i - 6), 434, 75, 120, this);
           if(tmpDeck.getCard(i) instanceof addHardPoint)
           drawTurret(tmpDeck.getCard(i), 82 + (82 + 75) * (i - 6), 434, 75, 120, g2);
        }
        }
    }
        if(phase == 1)
        {
            for(int i = 0; i < 6; i ++)
       {
           if(tmpDeck.getCard(i).status() == true)
           g2.drawImage(cardback, 82 + (82 + 75) * i, 214, 75, 120, this);
           else{
           g2.drawImage(defense, 82 + (82 + 75) * i, 214, 75, 120, this);
        }
        }
        for(int i = 6; i < 12; i ++)
       {
           if(tmpDeck.getCard(i).status() == true)
           g2.drawImage(cardback, 82 + (82 + 75) * (i - 6), 434, 75, 120, this);
           else{
           g2.drawImage(defense, 82 + (82 + 75) * (i - 6), 434, 75, 120, this);
        }
        }
    }
        

        if(phase == 2)
        {
        g2.setColor(Color.red);
        g2.fill(hp1);
        g2.fill(hp2);
        g2.drawImage(fight1, 25, 100, 200, 160, this );
        g2.drawImage(fight2, width - 200 - 25, 100, 200, 160, this);
        tmpDeck = p1.getDeck();
        for(int i = 0; i < 3; i ++)
       {
           if(tmpDeck.getCard(i) instanceof fireAllLasers || tmpDeck.getCard(i) instanceof fireEverything || tmpDeck.getCard(i) instanceof fireAllMissiles || tmpDeck.getCard(i) instanceof fireAllGuns)
           if(currentPlayer == 2)
           g2.drawImage(cardback, 50 + (50 + 75) * i, 355, 75, 120, this);
           else
           g2.drawImage(attack, 50 + (50 + 75) * i, 355, 75, 120, this);
           
           if(tmpDeck.getCard(i) instanceof defend)
           if(currentPlayer == 2)
           g2.drawImage(cardback, 50 + (50 + 75) * i, 355, 75, 120, this);
           else
           g2.drawImage(defense, 50 + (50 + 75) * i, 355, 75, 120, this);
           
           if(tmpDeck.getCard(i) instanceof addHardPoint)
           if(currentPlayer == 2)
           g2.drawImage(cardback, 50 + (50 + 75) * i, 355, 75, 120, this);
           else
           drawTurret(tmpDeck.getCard(i), 50 + (50 + 75) * i, 355, 75, 120, g2);
        }
        tmpDeck = p2.getDeck();
        for(int i = 0; i < 3; i ++)
       {
           if(tmpDeck.getCard(i) instanceof fireAllLasers || tmpDeck.getCard(i) instanceof fireEverything || tmpDeck.getCard(i) instanceof fireAllMissiles || tmpDeck.getCard(i) instanceof fireAllGuns)
           if(currentPlayer == 1)
           g2.drawImage(cardback, 649 + (50 + 75) * i, 355, 75, 120, this);
           else
           g2.drawImage(attack, 649 + (50 + 75) * i, 355, 75, 120, this);
           
           if(tmpDeck.getCard(i) instanceof defend)
           if(currentPlayer == 1)
           g2.drawImage(cardback, 649 + (50 + 75) * i, 355, 75, 120, this);
           else
           g2.drawImage(defense, 649 + (50 + 75) * i, 355, 75, 120, this);
           
           if(tmpDeck.getCard(i) instanceof addHardPoint)
           if(currentPlayer == 1)
           g2.drawImage(cardback, 649 + (50 + 75) * i, 355, 75, 120, this);
           else
           drawTurret(tmpDeck.getCard(i), 649 + (50 + 75) * i, 355, 75, 120, g2);
        }
        //g2.drawImage(attack, 25, 355, 75, 120, this);
        //g2.drawImage(defense, width - 25 - 75, 355, 75, 120, this);
        //g2.drawImage(attackb, 195, 240, 30, 30, this );
        //g2.drawImage(attackd, width - 200 - 25 + 30, 240, 30, 30, this);
        //g2.drawImage(defenseb, width - 200 - 25, 240, 30, 30, this);
        //g2.drawImage(defensed, 155, 240, 30, 30, this);
        for(int i = 0; i < 3; i++)
        {
        g2.drawImage(cardback, 475 + 2 * i, 355 - 2 * i, 75, 120, this);
        }
        
        //draw turrets
        int c = 0;
        for(hardpoint h : p1.getShip().returnHardpoints())
        {
            drawTurret(h, 50 + 50 * c, 600, 50, 50, g2);
            c++;
        }
        c = 0;
        for(hardpoint h : p2.getShip().returnHardpoints())
        {
            drawTurret(h, 649 + 50 * c, 600, 50, 50, g2);
            c++;
        }
        
       
    }
    if(end == true)
    {
        g2.setColor(Color.black);
        g2.setFont(new Font("Arial", Font.PLAIN, 40));
        g2.drawString("Gluei Dev: Austin Chen", 275, 150);
        g2.drawString("Engine Dev: Douglas Gliner", 275, 200);
        g2.drawString("Graphics Dev: George Wang", 275, 250);
    }

    }

    public void drawTurret(card c, int x, int y, int width, int height, Graphics2D g2)
    {
        switch(c.returnType())
        {
            case 0: g2.drawImage(addStandardLaser, x, y, width, height, this); break;
            case 1: g2.drawImage(addBeamLaser, x, y, width, height, this); break;
            case 2: g2.drawImage(addSpreadLaser, x, y, width, height, this); break;
            
            case 3: g2.drawImage(addMissile, x, y, width, height, this); break;
            case 4: g2.drawImage(addDoubleMissile, x, y, width, height, this); break;
            case 5: g2.drawImage(addTripleMissile, x, y, width, height, this); break;
            case 6: g2.drawImage(addQuadMissile, x, y, width, height, this); break;
            case 7: g2.drawImage(addOctupleMissile, x, y, width, height, this); break;
           
            case 8: g2.drawImage(addGunTurret, x, y, width, height, this); break;
            case 9: g2.drawImage(addAutoCannon, x, y, width, height, this); break;
            case 10: g2.drawImage(addSpreadGun, x, y, width, height, this); break;
            case 11: g2.drawImage(addSniperCannon, x, y, width, height, this); break;
            case 12: g2.drawImage(addRailGun, x, y, width, height, this);
        }
    }
    
    public void drawTurret(hardpoint h, int x, int y, int width, int height, Graphics2D g2)
    {
        if(h instanceof autoCannon) g2.drawImage(autoCannon, x, y, width, height, this);
        if(h instanceof spreadGun) g2.drawImage(spreadCannon, x, y, width, height, this);
        if(h instanceof railCannon) g2.drawImage(railCannon, x, y, width, height, this);
        if(h instanceof sniperCannon) g2.drawImage(sniperCannon, x, y, width, height, this);
        if(h instanceof beamLaser) g2.drawImage(beamLaser, x, y, width, height, this);
        if(h instanceof standardLaser) g2.drawImage(standardLaser, x, y, width, height, this);
        if(h instanceof spreadLaser) g2.drawImage(spreadLaser, x, y, width, height, this);
        if(h instanceof standardMissile) g2.drawImage(standardMissile, x, y, width, height, this);
        if(h instanceof doubleMissile) g2.drawImage(doubleMissile, x, y, width, height, this);
        if(h instanceof tripleMissile) g2.drawImage(tripleMissile, x, y, width, height, this);
        if(h instanceof quadMissile) g2.drawImage(quadMissile, x, y, width, height, this);
        if(h instanceof octoMissile) g2.drawImage(octoMissile, x, y, width, height, this);
        if(h instanceof gunTurret) g2.drawImage(gunTurret, x, y, width, height, this);
    }
    
    
    public class MouseClickedListener implements MouseListener
    {
        public void mousePressed(MouseEvent event)
        {
            if(end == false){
            //x = z;
            z = getCardPressed(event.getX(), event.getY());
            //top.setText("" + z + "");
            //Integer i = new Integer(z);
            //if(i != -1 && z != x) count++;
            //if(cardSelect == true)
            //{
            //if(count >= 12) cardSelect = false;
            //String s = new String();
            //s = i.toString();

            if(z == -1) 
            {
                top.setText("NOT VALID");
            }
            else
            {
               if(phase == 0 || phase == 1)
               {
                   if(tmpDeck.getCard(z).status() == false) //this function (what ever it does) covered the whole else part of the
                                                            //if else statement, it was looking through tmpDeck but that isnt even used
                                                            //in phase 2............. wow austin
                   {
                       top.setText(tmpDeck.getCard(z).getTitle());
                   }
                   else
                   { 
                       top.setText("PICKED "); z = -1;
                   }
               }
               
               if(phase == 2)
               {
                 
                  top.setText("" + z + "");
                  if(z < 3)
                  {
                    //System.out.println(p1.getDeck().getCard(z).getTitle());
                    top.setText(p1.getDeck().getCard(z).getTitle());
                  }
                  
                  if(z >= 3) //this is needed to show other players cards
                  {
                      //System.out.println(p2.getDeck().getCard(z - 3).getTitle());
                      top.setText(p2.getDeck().getCard(z - 3).getTitle());
                  }
               }
               
                    
                //z = z-3;
               // top.setText(p2.getDeck().getCard(z).getTitle());
            
            }
               repaint();
            //top.setText("" + z + "");
            //top.setText("x = " + event.getX() + ", y = " + event.getY() + ".");
        //}
        }
    }
        
        public void mouseClicked(MouseEvent event) {}
        public void mouseReleased(MouseEvent event) {}
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
        
  }
  
  public int getCardPressed(int x, int y)
  {
      if(phase == 0 || phase == 1)
      {
      for(int i = 0; i < 6; i++)
      {
          if((x >= (82 + (82 + 75) * i)) && (x <= (82 + (82 + 75) * i + 75)))
          {
              if(y >= 214 && y <= 334)
              return i;
              if(y >= 434 && y <= 554)
              return i + 6;
            }
        }
    }
    
    if(phase == 2){
        
        if(y >= 355 && y <= 475)
        {
            for(int i = 0; i < 3; i++)
            {
                if(x >= 50 + ((50 + 75) * i) && x <= 125 + ((50 + 75) * i))
                {
                    //top.setText("" + i + "");
                    if(currentPlayer == 1)
                    return i;
                }
            }
            
            for(int i = 0; i < 3; i++)
            {
                if(x >= 649 + ((50 + 75) * i) && x <= 649 + 75 + ((50 + 75) * i))
                {
                    //top.setText("" + w + "");
                    if(currentPlayer == 2)
                    return i+3;
                }
            }
            
        }
    }
      return -1;
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
    
     public static void delay(int count)
    {
        for(int i = 0; i < count; i++)
            for(int j = 0; j < count; j++);    
    }
  public JRootPane PANE()
  {
      return getRootPane(); 
    }
}
