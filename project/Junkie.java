import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author: Victor
 */
public class Junkie extends Human
{
    private boolean spedUp = false;
    private int tempSpeedBoost = 300;

    /**
     * Act - do whatever the Junkie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Junkie(int playerHealth, double speed, int direction, int armour, int tag, String classType)
    {
        super(playerHealth, speed, direction, armour, tag, classType);

        this.speed = 3.5;
        this.health = 60;
        this.playerMaxHealth = health;
        healthBar = new SuperStatBar ((int)playerMaxHealth, (int)health, this, 32, 6, -32, Color.RED, Color.WHITE, false, Color.BLACK, 1);

    }

    public void addedToWorld(World w){

        MyWorld world = (MyWorld) getWorld();
        health = health + (health* world.getPlayerHealthIncrease());
        playerMaxHealth = health;

        money+= world.getMoneyIncrease();
        w.addObject (healthBar, getX(), getY());

        healthBar.update((int)playerMaxHealth);
        //System.out.println(tag + " " + classType);
    }

    public void act()
    {
        // Every 20 seconds, get a x2 speed boost for 5 seconds
        if (classActs % 1200 == 0 && money > 5)
        {
            spedUp = true;
            money -= 5;
        }
        if (spedUp)
        {
            speed = 7;
            tempSpeedBoost--;
        }
        if (tempSpeedBoost <= 0)
        {
            spedUp = false;
            tempSpeedBoost = 300;
        }
        classActs++;

        // On average, every 10 seconds, Junkie heals for 5
        if (Greenfoot.getRandomNumber(900) == 0)
        {
            health += 6;
            if (health > playerMaxHealth)
            {
                health = playerMaxHealth;
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

        // Levels 5, 10, 15 ... increase damage by 1
        if (level % 5 == 0)
        {
            damage += 1;
        }
        // All other levels, increase full health by 2
        else
        {
            playerMaxHealth += 2;
        }
    }
}
