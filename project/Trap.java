import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * traps zombies
 * 
 * @author (Victor)
 */
public class Trap extends Actor
{
    protected GreenfootImage landMine = new GreenfootImage("LandMine.png");
    /**
     * Act - do whatever the Trap wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Trap()
    {
        landMine.scale(50, 25);
        setImage(landMine);
    }
    
    public void act()
    {
        Zombie z = (Zombie)getOneIntersectingObject(Zombie.class);
        
        if (z != null)
        {
            getWorld().addObject(new Explosion(120), getX(), getY());
            getWorld().removeObject(this);
        }
    }
}
