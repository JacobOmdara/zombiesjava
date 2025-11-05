import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author: Victor
 */
public class Medic extends Human
{
    private boolean healed = false;
    private double heal = 1;

    /**
     * Act - do whatever the Medic wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public Medic(int playerHealth, double speed, int direction, int armour, int tag, String classType)
    {
        super(playerHealth, speed, direction, armour, tag, classType);

        this.health = 100;
    }

    public void addedToWorld(World w){

        MyWorld world = (MyWorld) getWorld();
        health = health + (health* world.getPlayerHealthIncrease());
        playerMaxHealth = health;

        healthBar = new SuperStatBar ((int)playerMaxHealth, (int)health, this, 32, 6, -32, Color.RED, Color.WHITE, false, Color.BLACK, 1);
        money+= world.getMoneyIncrease();
        getWorld().addObject (healthBar, getX(), getY());

        healthBar.update((int)playerMaxHealth);
    }

    public void act()
    {
        // Heal for 1 every 5 seconds
        if (classActs % 300 == 0 && health + heal < playerMaxHealth)
        {
            health += heal;
            healthBar.update((int)health);
        }
        // If after heal, health is more than the max, set health to max (to avoid more than max health)
        else if (classActs % 300 == 0 && health + heal >= playerMaxHealth)
        {
            health = playerMaxHealth;
        }
        classActs++;

        // If medic reaches less than 10 health for the first time, instant heal to 90
        if (!healed && health < 10)
        {
            health = 90;
            healed = true;
            healthBar.update((int)health);

        }
        doSomething();
    }

    /**
     * This method is a method that represents this survivor class's upgrades. Each survivor class myUpgrade method has different effects.
     */
    public void myUpgrade()
    {
        level++;

        // Levels 3, 6, 9, 12 ... boost heal by 1 for instant
        if (level % 3 == 0)
        {
            heal+= 1.0;
            health = playerMaxHealth/2;
        }
        // Levels 1, 3, 5, 7 ... increase full health by 2 and heal for 2
        if (level % 2 == 1)
        {
            playerMaxHealth += 2.0;
            health += 2.0;
        }
    }
}
