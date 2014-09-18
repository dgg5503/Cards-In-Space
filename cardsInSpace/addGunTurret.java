package cardsInSpace;


/**
 * Write a description of class addGunTurret here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class addGunTurret extends actionCard
{
    public addGunTurret()
    {
        init();
        editTitle("Add Gun Turret");
        editDescription("Adds gun turret which deals 10 hits of one damage, take 2 turns to install.");
        editTurnDecay(2);
    }
    
    public void mainAction()
    {
        ship s = getShipToPerformAction();
        if(getTurnDecay() == 0)
        {
            s.addHardpoints(new gunTurret());
            modifyTurnDecay(-1);
        }
    }
}
