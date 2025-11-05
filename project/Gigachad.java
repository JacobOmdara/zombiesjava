import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * @author: Victor
 */
public class Gigachad extends Human
{
    private int luck = 1800;
    
    /**
     * Act - do whatever the Gigachad wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Gigachad(int playerHealth, double speed, int direction, int armour, int tag, String classType)
    {
        super(playerHealth, speed, direction, armour, tag, classType);
        
        this.health = 1000;
        this.speed = 4;
        this.armour = 50;
        this.damage = 10;
    }
    public void addedToWorld(World w){

        MyWorld world = (MyWorld) getWorld();
        health = health + (health* world.getPlayerHealthIncrease());
        playerMaxHealth = health;

        healthBar = new SuperStatBar ((int)playerMaxHealth, (int)health, this, 32, 6, -32, Color.RED, Color.WHITE, false, Color.BLACK, 1);
        money+= world.getMoneyIncrease();
        w.addObject (healthBar, getX(), getY());

        healthBar.update((int)playerMaxHealth);
    }
    public void act()
    {
        // With base stats, Gigachad wipes out all zombies on average every 30 seconds
        if (Greenfoot.getRandomNumber(luck) == 0)
        {
            ArrayList<Zombie> zombies = (ArrayList<Zombie>) getWorld().getObjects(Zombie.class);
            for (Zombie z : zombies){
                z.die();
            }            
        }
        doSomething();
    }
    /**
     * This method is a method that represents this survivor class's upgrades. Each survivor class myUpgrade method has different effects.
     */
    public void myUpgrade()
    {
        level++;
        if (level <= 25)
        {
            // With every level upgrade until 25, Gigachad's chance of wiping all zombies is lowered by 1 second
            luck -= 60;
        }
        // Levels above 25, increase armour by 10
        else
        {
            armour += 10;
        }
    }
}
