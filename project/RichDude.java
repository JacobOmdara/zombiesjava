import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author: Victor
 */
public class RichDude extends Human
{
    /**
     * Act - do whatever the RichDude wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public RichDude(int playerHealth, double speed, int direction, int armour, int tag, String classType)
    {
        super(playerHealth, speed, direction, armour, tag, classType);
        
        this.health = 80;
        this.money = 200;
        this.moneyBaseGain = 6;
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
    public void act(){
        doSomething();
    }
    /**
     * This method is a method that represents this survivor class's upgrades. Each survivor class myUpgrade method has different effects.
     */
    public void myUpgrade()
    {
        level++;
        
        // Levels 1, 4, 7, 10 ... receive 10% discount on max health
        if (level % 3 == 1)
        {
            maxHealthCost = (int) ((double)maxHealthCost * 0.9);
        }
        // Levels 2, 5, 8, 11 ... receive 10% discount on armor
        else if (level % 3 == 2)
        {
            armorCost = (int) ((double)armorCost * 0.9);
        }
        // Levels 3, 6, 9, 12 ... receive 10% discount on damage
        else if (level % 3 == 0)
        {
            damageCost = (int) ((double)damageCost * 0.9);
        }        
        // Levels 5, 10, 15, 20, 25   lower cost of health potion by 1 and increase money gained by killing zombies by 1
        if (level % 5 == 0 && level < 26)
        {
            healthPotionCost--;
            moneyBaseGain++;
        }
    }
}
