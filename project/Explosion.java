import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Destroys anything within a 200 pixel radius, fades out
 * @ author: Victor, grapics Jacob
 */
public class Explosion extends Actor
{
    private int duration;
    protected GreenfootImage image = new GreenfootImage("explosion.png");
    public Explosion (int duration)
    {
        this.duration = duration;
        setImage(image);
        this.getImage().scale(250, 250);
    }

    public void act()
    {   
        if (duration == 0){
            getWorld().removeObject(this);
        }
        else if (duration > 100)
        {
            // While an explosion is active, find all instances of zombies and kill them
            ArrayList<Zombie> zombies = (ArrayList<Zombie>) getObjectsInRange(200, Zombie.class);
            for (Zombie z : zombies){
                z.die();
            }
        }
        // Fade out
        this.getImage().setTransparency(duration * 2);
        duration--;
    }
}