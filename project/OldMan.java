import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author: Victor
 */
public class OldMan extends Human
{
    private boolean enraged = false;
    private int shootBoost = 0; 
    private int speedBoost = 0;
    private int armourBoost = 0;

    /**
     * Act - do whatever the OldMan wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public OldMan(int playerHealth, double speed, int direction, int armour, int tag, String classType)
    {
        super(playerHealth, speed, direction, armour, tag, classType);

        this.speed = 2.5;
        this.health = 50;
        this.money = 100;
        this.shootSpeed = 20;
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
    /**
     * This method is a method that represents this survivor class's upgrades. Each survivor class myUpgrade method has different effects.
     */
    public void act()
    {
        if (enraged)
        {
            rageMode();
        }
        else
        {
            normalMode();
        }

        // Every 10 seconds, swap states
        if (classActs % 600 == 0)
        {
            if (enraged)
            {
                enraged = false;
            }
            else
            {
                enraged = true;
            }
        }
        doSomething();

        classActs++;
    }

    public void normalMode()
    {
        speed = 2.5;
        armour = 0;
    }

    public void rageMode()
    {
        shootSpeed = 15 + shootBoost;
        speed = 5 + speedBoost;
        armour = 10 + armourBoost;
    }

    public void myUpgrade()
    {
        level++;

        // Up to level 12, improve rage state (decrease shootBoost by 1 and increase speedBoost by 0.25 and armour by 1)
        if (level <= 12)
        {
            shootBoost -= 1;
            speedBoost += 0.25;
            armourBoost += 1;
        }
        // Else, increase full health by 3
        else
        {
            playerMaxHealth += 3;
        }
    }
}
