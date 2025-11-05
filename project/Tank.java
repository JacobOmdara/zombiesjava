import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author: Victor
 */
public class Tank extends Human
{
    private int tempShield = 180;
    private boolean shieldActive = false;
    private int tempArmour;
    
    /**
     * Act - do whatever the Tank wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Tank(int playerHealth, double speed, int direction, int armour, int tag, String classType)
    {
        super(playerHealth, speed, direction, armour, tag, classType);
        
        this.health = 250;
        this.playerMaxHealth = health;
        this.speed = 1.5;
        this.armour = 20;
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
        // When tank reaches less than 50 health, increase speed
        if (health < 50)
        {
            speed = 3;
        }
        else
        {
            speed = 1.5;
        }
        
        // Every 10 seconds, receive a 3 second shield (10 more armour than current)
        tempArmour = armour + 10;
        if (classActs % 600 == 0)
        {
            shieldActive = true;
        }
        if (shieldActive)
        {
            armour = tempArmour;
            tempShield--;
        }
        if (tempShield <= 0)
        {
            shieldActive = false;
            tempShield = 180;
        }
        classActs++;
        doSomething();
    }
    /**
     * This method is a method that represents this survivor class's upgrades. Each survivor class myUpgrade method has different effects.
     */
    public void myUpgrade()
    {
        level++;
        
        // Levels 2, 4, 6, 8 ... increase full health by 5 and heal for 5
        if (level % 2 == 0)
        {
            health += 5;
            playerMaxHealth += 5;
        }
        // Levels 1, 3, 5, 7 ... increase armour by 2
        else
        {
            armour += 2;
        }
    }
}
