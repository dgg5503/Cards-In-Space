package cardsInSpace;


/**
 * Write a description of class actionDeck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;

public class actionDeck extends Deck
{

    public actionDeck()
    {
        super();
        fillDeck();
        shuffle(5);
    }
    
    public actionDeck(ArrayList<card> c)
    {
        super(c);
    }

    public void fillDeck()
    {
        int z = 0;
        for(int i = 0; i < 20; i++)
        {
                switch (z)
                {
                    case 0: addCard(new fireAllLasers()); z++; break;
                    case 1: addCard(new fireAllMissiles()); z++; break;
                    case 2: addCard(new fireEverything());z++;break;
                    case 3: addCard(new defend()); z++; break;
                    case 4 : addCard(new addHardPoint()); z++; break;                    
                    case 5: addCard(new fireAllGuns()); z = 0; break;
                }
        }
    }
}
