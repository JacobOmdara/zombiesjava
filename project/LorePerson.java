import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * is an object person in the lore world
 * @author (Jacob) 
 */
public class LorePerson extends Actor
{
    private GreenfootImage playerA, playerB;
    private int systemTimer, person;
    /**
     * Constructor, passes in a specific integer and saves it
     * param int person
     */
    public LorePerson(int person)
    {
        this.person = person;
        playerA = new GreenfootImage("playera.png");
        playerB = new GreenfootImage("playerb.png");
        if (person == 0 || person == 2)
        setImage(playerA);
        if (person == 1 || person == 3)
        setImage(playerB);
    }
    
    
    public void act()
    {
        section1();
        section2();
        systemTimer++;
        if (systemTimer == 1260)
            section3();
    }
    
    /**
     * it will cause the player on the right to move
     */
    public void section1()
    {
        if (person == 0)
        {
            if (systemTimer == 105)
            {
                playerA = new GreenfootImage("playeraright.png");
                setImage(playerA);
            }
        }
        
        if (person == 1)
        {
            if (systemTimer < 95)
            {
                move(-5);
            }
        }
        
        if (person == 2 || person == 3)
        move(-5);
        

    }
    
    /**
     * it will scale the lore person up, and will set the new location
     */
    public void section2()
    {
        if (systemTimer == 120)
        {
            if (person == 0 || person == 1)
            this.getImage().scale(getImage().getWidth() * 2, getImage().getHeight() * 2);
            if (person == 1)
                setLocation(getX() + 200, getY());
        }
    }
    
    /**
     * removes lore person
     */
    public void section3()
    {
        getWorld().removeObject(this);
        
    }
}
