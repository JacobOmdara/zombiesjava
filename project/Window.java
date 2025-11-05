import greenfoot.*; 
import java.util.ArrayList;
/**
 * @author: EVERYONE
 */
public class Window extends Actor
{
    protected int durability;
    protected int durabilityMax = 5;
    
    private GreenfootSound upgradeWindows;
    private GreenfootSound[] repairWindows;
    private GreenfootSound[] breakWindows;
    protected int repairWindowsIndex, breakWindowsIndex;
    protected double percentageHealth;
    protected int windowStage = 5;
    protected int windowHealthGain = 1;
    protected GreenfootImage woodStage0 = new GreenfootImage("woodWindow/window0.png");
    protected GreenfootImage woodStage1 = new GreenfootImage("woodWindow/window1.png");
    protected GreenfootImage woodStage2 = new GreenfootImage("woodWindow/window2.png");
    protected GreenfootImage woodStage3 = new GreenfootImage("woodWindow/window3.png");
    protected GreenfootImage woodStage4 = new GreenfootImage("woodWindow/window4.png");
    protected GreenfootImage woodStage5 = new GreenfootImage("woodWindow/window5.png");
    protected GreenfootImage woodSkins[] = {woodStage0, woodStage1, woodStage2,woodStage3,woodStage4,woodStage5};

    protected GreenfootImage ironStage0 = new GreenfootImage("ironWindow/window0.png");
    protected GreenfootImage ironStage1 = new GreenfootImage("ironWindow/window1.png");
    protected GreenfootImage ironStage2 = new GreenfootImage("ironWindow/window2.png");
    protected GreenfootImage ironStage3 = new GreenfootImage("ironWindow/window3.png");
    protected GreenfootImage ironStage4 = new GreenfootImage("ironWindow/window4.png");
    protected GreenfootImage ironStage5 = new GreenfootImage("ironWindow/window5.png");
    protected GreenfootImage ironSkin[] = {ironStage0,ironStage1,ironStage2,ironStage3,ironStage4,ironStage5};

    protected GreenfootImage goldStage0 = new GreenfootImage("goldWindow/window0.png");
    protected GreenfootImage goldStage1 = new GreenfootImage("goldWindow/window1.png");
    protected GreenfootImage goldStage2 = new GreenfootImage("goldWindow/window2.png");
    protected GreenfootImage goldStage3 = new GreenfootImage("goldWindow/window3.png");
    protected GreenfootImage goldStage4 = new GreenfootImage("goldWindow/window4.png");
    protected GreenfootImage goldStage5 = new GreenfootImage("goldWindow/window5.png");
    protected GreenfootImage goldSkin[] = {goldStage0,goldStage1,goldStage2,goldStage3,goldStage4,goldStage5};

    protected GreenfootImage skinList[][] = {woodSkins, ironSkin, goldSkin};
    protected GreenfootImage activeSkinList[] = woodSkins;
    protected int upgradeLevel = 0;
    public Window()
    {
        
        getImage().scale(40,100);
        setImage(activeSkinList[5]);
        durabilityMax += (upgradeLevel * 5);
        durability = durabilityMax;
        percentageHealth = 100;
        breakWindowsIndex = 0; 
        breakWindows = new GreenfootSound[5];
        for (int i = 0; i < breakWindows.length; i++)
        {
            breakWindows[i] = new GreenfootSound("zombieBreak.wav");
            breakWindows[i].setVolume(70);
        }
        
        repairWindowsIndex = 0;
        repairWindows = new GreenfootSound[5];
        for (int i = 0; i < repairWindows.length; i++)
        {
            repairWindows[i] = new GreenfootSound("humanRepair.wav");
            repairWindows[i].setVolume(70);
        }
        
        upgradeWindows = new GreenfootSound("upgradeWindows.wav");
    }    

    /**
     * Renders the image of the window every act
     */
    public void act(){
        percentageHealth = (((double)(durability))/((double)(durabilityMax)))*100;
        renderWindowImage();
    }

    /**
     * Checks whether there is another actor trying to fix it
     * @return boolean      Returns true if there is someone trying to fix false otherwise
     */
    public boolean checkSomeoneOnTheFix(){
        ArrayList<Human> humans = (ArrayList<Human>)getWorld().getObjects(Human.class);
        for(Human h: humans){
            if(h.getTarget() == this){
                return true;
            }
        }
        return false;
    }

    /**
     * This method adjust's windows health
     * @param damage    The damage of the attacker
     */
    public void takeDamage(double damage)
    {
        durability -= damage;
        percentageHealth = (((double)(durability))/((double)(durabilityMax)))*100;
        renderWindowImage();
        breakWindows[breakWindowsIndex].play();
        breakWindowsIndex++;
        if (breakWindowsIndex > breakWindows.length - 1)
        {
            breakWindowsIndex = 0;
        }
    }

    /**
     * This methos returns whether the window is broken
     * @return boolean      True if the window is broken and false otherwise
     */
    protected boolean isWindowBroken()
    {
        if (durability < durabilityMax)
        {
            return true;
        }
        return false;
    }

    /**
     * This protected method checks and changes the window's image between 6 images
     * accordingly based on it's health percentage
     */
    protected void renderWindowImage(){
        if(percentageHealth <= 0.0){
            //set image to no plank image
            setImage(activeSkinList[0]);
            windowStage = 0;
        }
        else if(percentageHealth <= 20.0){
            //set image to 1 plank image
            setImage(activeSkinList[1]);

            windowStage = 1;
        }
        else if(percentageHealth <= 40.0){
            //set image to 2 plank image
            setImage(activeSkinList[2]);
            windowStage = 2;
        }
        else if(percentageHealth <= 60.0){
            //set image to 3 plank image
            setImage(activeSkinList[3]);

            windowStage = 3;
        }
        else if(percentageHealth <= 80.0){
            //set image to 4 plank image
            setImage(activeSkinList[4]);

            windowStage = 4;
        }
        else if(percentageHealth <= 100.0){
            //set image to 5 plank image
            setImage(activeSkinList[5]);

            windowStage = 5;
        }
    }

    /**
     * A public method that returns the durability of the window
     * @ returns int        returns the durability
     */
    public int getDurability()
    {
        return durability;
    }

    /**
     * A public method that allows players to repair the window
     */
    public void repairWindow()
    {
        durability += windowHealthGain;
        
        repairWindows[repairWindowsIndex].play();
        repairWindowsIndex++;
        if (repairWindowsIndex > repairWindows.length - 1)
        {
            repairWindowsIndex = 0;
        }
        
        if(durability > durabilityMax){
            durability = durabilityMax;
        }
        else if(durability < 0){
            durability = 0;
        }
        renderWindowImage();
    }

    /**
     * A public method that returns the stage the window is which is based off of the image it's set to
     * @ returns int    Return durability
     */
    public int getWindowStage(){
        return durability;
    }

    /**
     * A public method that allows players to upgrade windows which increase the durability of the window
     */
    public void upgradeWindow(){
        durabilityMax += 5;
        windowHealthGain++;
        upgradeLevel++;
        durability = durabilityMax;
        activeSkinList = skinList[upgradeLevel];
        renderWindowImage();
        upgradeWindows.setVolume(60);
        upgradeWindows.play();
    }

    /**
     * A method that returns the window's max health
     * @return int      Returns the max health
     */
    public int getMaxDurability()
    {
        return durabilityMax;
    }

    /**
     * A method that returns the window's level
     * @return int      Returns the level of the window
     */
    public int getUpgradeLevel(){
        return upgradeLevel;
    }

    /**
     * Returns the max level of the window
     * @return int      Returns the max level of window
     */
    public int getUpgradeLevelMax(){
        return skinList.length -1;
    }
}

