import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * @author: Victor, graphics Jacob
 */

public class Bullet extends SuperSmoothMover
{
    protected GreenfootSound shootSound;
    protected Zombie z;
    protected double bulletSpeed = 40;
    protected int damage;
    protected Human owner;
    protected ArrayList<Zombie> killList = new ArrayList<Zombie>();

    public Bullet(Zombie z, Human owner, int damage)
    {
        // shootSound = new GreenfootSound(".wav");
        this.owner = owner;
        this.z = z;
        this.damage = damage;

    }

    public void addedToWorld(World world){
        turnTowards(z.getX(), z.getY());
    }

    public void act()
    {

        checkZombie();
        move(bulletSpeed);
        if(isAtEdge() || isTouching(Wall.class)){
            getWorld().removeObject(this);   
        }
    }

    /**
     * This method returns the actor that was spawned the bullet
     * @ returns the Human that spawned it
     */
    public Human getOwner(){
        return owner;
    }

    /**
     * This method checks whether the bullet has touched a zombie or not.
     * It also does damage to the zombie it touches and manages if it should die or not
     */
    public void checkZombie()
    {
        // If this bullet intersects with a zombie, remove the bullet (* deal with any piercing bullets later)
        Zombie z = (Zombie)getOneIntersectingObject(Zombie.class);
        if (z != null && !killList.contains(z))
        {
            killList.add(z);
            z.takeDamage(damage);
            if(z.getHealth() < 0){
                owner.gainMoney();
                owner.increaseKill();
                z.die();
            }
        }

    }

}
