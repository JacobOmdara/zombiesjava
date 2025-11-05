import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * @author: Victor
 */
public class Trapper extends Human
{
    protected int trapLimit;
    /**
     * Act - do whatever the Trapper wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Trapper(int playerHealth, double speed, int direction, int armour, int tag, String classType)
    {
        super(playerHealth, speed, direction, armour, tag, classType);

        this.money = 100;
    }

    public void addedToWorld(World w){
        ArrayList<Trapper> list = (ArrayList<Trapper>)getWorld().getObjects(Trapper.class);
        trapLimit = list.size()*6;
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
        doSomething();
        ArrayList<Trap> t = (ArrayList<Trap>)getWorld().getObjects(Trap.class);
        if(Greenfoot.getRandomNumber(120) == 0 && t.size() < trapLimit){
            setTrap();
        }
    }
    /**
     * This method is a method that represents this survivor class's upgrades. Each survivor class myUpgrade method has different effects.
     */
    public void myUpgrade(){
        level++;
        //Increases the maximum traps the survivor can place
        if (level % 3 == 0)
        {
            trapLimit++;
        }
        
    }
    /**
     * This method sets a trap on the caller's coordinates.
     */
    public void setTrap()
    {
        getWorld().addObject(new Trap(), getX(), getY());
    }
}
