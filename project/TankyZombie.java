import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * chases down human
 * 
 * @author Daniel 
 */
public class TankyZombie extends Zombie
{
    protected boolean justEnteredWindow = true;
    /**
     * Act - do whatever the FastZombie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public TankyZombie(int direction, int type){
        super(direction, type);
        MyWorld world = (MyWorld) getWorld();

        health = 15;
        speed = 2;
        zombieMaxHealth = health;
        damage = 5;
        healthBar = new SuperStatBar ((int)zombieMaxHealth, (int)health, this, 32, 6, -32, Color.RED, Color.WHITE, false, Color.BLACK, 1);

    }

    public void addedToWorld(World w){
        MyWorld world = (MyWorld) getWorld();
        speed = speed + (speed* world.getZombieSpeed());
        damage = damage + (damage* world.getZombieDamage());
        w.addObject (healthBar, getX(), getY());
        healthBar.update((int)(zombieMaxHealth));
        damage += world.getWave();
    }

    public void act()
    {
        doSomething();
    }
}
