import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * @author: Daniel, grapics victor
 */
public abstract class Human extends Humanoids
{
    // These values change depending on the difficulty or player input
    protected SuperStatBar healthBar = new SuperStatBar();
    protected double health;
    protected int money;
    protected boolean alreadyRandomTarget = false;
    protected Zombie nearestZombie;
    protected Window randomTarget;
    protected int direction;  // 1 means right, -1 means left
    protected int armour;  // Percentage of damage blocked
    protected int tag;
    protected boolean moveToFurthest;
    protected int damage = 3;
    protected double playerMaxHealth;
    protected int kills = 0;
    protected int level = 0;
    protected int moneyBaseGain = 5;
    //protected double fullHealth;
    protected int classActs = 0;
    protected int damageCost = 50;
    protected int healthPotionCost = 10;
    protected int armorCost = 50;
    protected int maxHealthCost = 50;
    protected int myUpgradeCost = 100;
    protected static int windowUpgradeCost = 100;
    protected int shootSpeed = 30;
    protected String[] upgradeList = {"damageCost", "armorCost", "healthCost", "myUpgradeCost", "windowUpgradeCost"};
    protected int[] upgradeListCost = {damageCost, armorCost, maxHealthCost, myUpgradeCost, windowUpgradeCost};
    protected boolean upgradeBought;
    protected String classType;
    protected GreenfootSound[] moneyDrop;
    protected int moneyDropIndex = 0;
    protected GreenfootSound moneySpend = new GreenfootSound("spendcashier.wav");
    protected GreenfootSound[] gunShot;
    protected int gunShotIndex = 0;
    public Human(int playerHealth, double speed, int direction, int armour, int tag, String classType)
    {
        this.health = playerHealth;
        this.classType = classType;
        this.speed = 3.0;
        this.tag = tag;
        this.direction = direction;
        this.armour = armour;  
        this.money = 0;
        enableStaticRotation();
        moneyDrop = new GreenfootSound[20];
        gunShot = new GreenfootSound[20];
        for(int i = 0; i < moneyDrop.length; i++)
        {
            moneyDrop[i] = new GreenfootSound("coindroppingonfloor.mp3");
            moneyDrop[i].setVolume(70);
        }
        for(int i = 0; i < gunShot.length; i++)
        {
            gunShot[i] = new GreenfootSound("gunshotsound.wav");
            gunShot[i].setVolume(70);
        }

        if(tag == 1){
            if(classType.equals("Old Man")){
                leftImage = new GreenfootImage("playeraImages/redak.png");
                leftImage.scale(40,80);
                rightImage = new GreenfootImage("playeraImages/redak.png");
                rightImage.scale(40,80);
                rightImage.mirrorHorizontally();
                setImage(leftImage);
            }
            else{
                leftImage = new GreenfootImage("playeraImages/reedarmorpistol.png");
                leftImage.scale(40,80);
                rightImage = new GreenfootImage("playeraImages/reedarmorpistol.png");
                rightImage.scale(40,80);
                rightImage.mirrorHorizontally();

                setImage(leftImage);
            }
        }
        else{
            if(classType.equals("Old Man")){
                leftImage = new GreenfootImage("playerbImages/blueak.png");
                leftImage.scale(40,80);
                rightImage = new GreenfootImage("playerbImages/blueak.png");
                rightImage.scale(40,80);
                rightImage.mirrorHorizontally();
                setImage(leftImage);
            }
            else{
                leftImage = new GreenfootImage("playerbImages/bluearmorpistol.png");
                leftImage.scale(40,80);
                rightImage = new GreenfootImage("playerbImages/bluearmorpistol.png");
                rightImage.scale(40,80);
                rightImage.mirrorHorizontally();

                setImage(leftImage);
            }
        }
    }

    public void act() 
    {
        doSomething();
    }
    /**
     * This method decides what the caller should do
     */
    public void doSomething(){
        // If there is currently a wave...

        setImageRotation();
        MyWorld world = (MyWorld) getWorld();
        /**
         * Checks whether the player is outside the building or not or inside it
         */
        if(getY() < 125){
            setLocation(getX(), getY()+20);
        }
        if(getY() > 775){
            setLocation(getX(), getY()-20);
        }
        if(getX() <= 225){
            setLocation(250, getY());
        }
        if(getX() >= 875){
            setLocation(850, getY());
        }
        /**
         * This block of code tells the survivor what to do
         */
        if(returnIsWave())
        {
            upgradeBought = false;
            if(anyZombies() && world.getActs() % shootSpeed == 0 && getInRoom(getClosestZombie()) && (getRoom(getClosestZombie()) == getRoom(this))){
                shootZombies();
            }
            if(anyZombies() && getOneIntersectingObject(Human.class) == null){
                moveToTarget(getClosestZombie());
            }
            else if(anyZombies() && getOneIntersectingObject(Human.class) != null){
                target = null;
                if(!alreadyRandomTarget){
                    randomTarget = getRandomWindow();
                    alreadyRandomTarget = true;
                }
                else if(getRoom(randomTarget) == getRoom(this)){
                    moveToFurthest = false;
                    randomTarget = getRandomWindow();
                }
                moveToTarget(randomTarget);
            }
            else{
                if(moveToFurthest){
                    target = null;
                    if(!alreadyRandomTarget){
                        randomTarget = getRandomWindow();
                        alreadyRandomTarget = true;
                    }
                    else if(getRoom(randomTarget) == getRoom(this)){
                        moveToFurthest = false;
                        randomTarget = getRandomWindow();
                    }
                    moveToTarget(randomTarget);
                }
                else if(anyBrokenWindows() && !anyZombies()){
                    ArrayList<Human> humans = (ArrayList<Human>)getWorld().getObjects(Human.class);
                    for(Human h: humans){
                        if(h.getTarget() == this.getTarget()){
                            if(this.getTag() < h.getTag()){
                                moveToFurthest= true;
                            }
                        }
                    }
                    if(!moveToFurthest){
                        moveToTarget(getNearestBrokenWindow());
                        if(checkNearWindow() && getNearestBrokenWindow().isWindowBroken() && world.getActs() % 15 == 0){
                            getNearestBrokenWindow().repairWindow();
                        }
                    }
                    alreadyRandomTarget = false;
                }
                else if(!anyZombies()){
                    target = null;
                    if(!alreadyRandomTarget){
                        randomTarget = getRandomWindow();
                        alreadyRandomTarget = true;
                    }
                    else if(getRoom(randomTarget) == getRoom(this)){
                        randomTarget = getRandomWindow();
                    }
                    moveToTarget(randomTarget);
                } 
            }
        }
        else{
            if(anyZombies() && world.getActs() % shootSpeed == 0 && getInRoom(getClosestZombie()) && (getRoom(getClosestZombie()) == getRoom(this))){
                shootZombies();
            }
            if(anyZombies() && getOneIntersectingObject(Human.class) == null){
                moveToTarget(getClosestZombie());
            }
            else if(anyZombies() && getOneIntersectingObject(Human.class) != null){
                target = null;
                if(!alreadyRandomTarget){
                    randomTarget = getRandomWindow();
                    alreadyRandomTarget = true;
                }
                else if(getRoom(randomTarget) == getRoom(this)){
                    moveToFurthest = false;
                    randomTarget = getRandomWindow();
                }
                moveToTarget(randomTarget);
            }
            else if(!anyZombies() && !upgradeBought){
                moveToTarget(getWorld().getObjects(Merchant.class).get(0));
                if(isTouching(Merchant.class) && !upgradeBought){
                    if(getAffordable().size() != 0){
                        upgrades();
                    }
                    upgradeBought = true;
                }
            }
            else{
                if(moveToFurthest){
                    target = null;
                    if(!alreadyRandomTarget){
                        randomTarget = getRandomWindow();
                        alreadyRandomTarget = true;
                    }
                    else if(getRoom(randomTarget) == getRoom(this)){
                        moveToFurthest = false;
                        randomTarget = getRandomWindow();
                    }
                    moveToTarget(randomTarget);
                }
                else if(anyBrokenWindows() && !anyZombies()){
                    ArrayList<Human> humans = (ArrayList<Human>)getWorld().getObjects(Human.class);
                    for(Human h: humans){
                        if(h.getTarget() == this.getTarget()){
                            if(this.getTag() < h.getTag()){
                                moveToFurthest= true;
                            }
                        }
                    }
                    if(!moveToFurthest){
                        moveToTarget(getNearestBrokenWindow());
                        if(checkNearWindow() && getNearestBrokenWindow().isWindowBroken() && world.getActs() % shootSpeed == 0){
                            getNearestBrokenWindow().repairWindow();
                        }
                    }
                    alreadyRandomTarget = false;
                }
                else if(!anyZombies() && upgradeBought){
                    target = null;
                    if(!alreadyRandomTarget){
                        randomTarget = getRandomWindow();
                        alreadyRandomTarget = true;
                    }
                    else if(getRoom(randomTarget) == getRoom(this)){
                        randomTarget = getRandomWindow();
                    }
                    moveToTarget(randomTarget);
                } 
            }
        }
    }
    protected abstract void myUpgrade();

    /**
     * This method upgrades the stats of the survivor based off the amount of money they have
     */
    protected void upgrades(){
        ArrayList<String> affortable = getAffordable();
        int random = Greenfoot.getRandomNumber(affortable.size());
        String choice = affortable.get(random);
        if(choice.equals("damageCost")){
            damage++;
            money -= damageCost;
            damageCost += 100;
            upgradeListCost[0] = damageCost;
        }
        else if(choice.equals("armorCost")){
            armour += 5;
            money -= armorCost;

            armorCost += 50;
            upgradeListCost[1] = armorCost;

        }
        else if(choice.equals("healthCost")){
            playerMaxHealth += 20;
            getWorld().removeObject(healthBar);
            healthBar = new SuperStatBar ((int)playerMaxHealth, (int)health, this, 32, 6, -32, Color.RED, Color.WHITE, false, Color.BLACK, 1);
            MyWorld world = (MyWorld) getWorld();
            world.addObject(healthBar, getX(), getY());
            money -= maxHealthCost;

            maxHealthCost += 100;
            upgradeListCost[2] = maxHealthCost;

        }
        else if(choice.equals("myUpgradeCost")){
            //Calls myUpgradeMethod -> Method will change the values
            money -= myUpgradeCost;
            myUpgradeCost += 100;
            upgradeListCost[3] = myUpgradeCost;

        }
        else if(choice.equals("windowUpgradeCost")){
            money -= windowUpgradeCost;
            windowUpgradeCost += 100;
            upgradeListCost[4] = windowUpgradeCost;
            ArrayList<Window> windows = (ArrayList<Window>)getWorld().getObjects(Window.class);
            for(Window w: windows){
                w.upgradeWindow();
            }
        }
        moneySpend.play();
    }

    /**
     * This method returns an arraylist which represent a list of items the survivor can afford
     * @return ArrayList<String>    Returns an arraylist which represent a list of items the survivor can afford
     */
    protected ArrayList<String> getAffordable(){
        ArrayList<String> affortable = new ArrayList<String>();

        for(int i = 0; i < upgradeList.length; i++){
            if(upgradeListCost[i] < money){
                if(i == 4){
                    if(getNearestWindow().getUpgradeLevel() != getNearestWindow().getUpgradeLevelMax()){
                        affortable.add(upgradeList[i]);
                    }
                }
                else{
                    affortable.add(upgradeList[i]);
                }
            }
        }
        return affortable;
    }

    /**
     * This method returns the amount of money the survivor has
     * @return int      Returns the amount of money the survivor has
     */
    protected int getMoney(){
        return money;
    }

    /**
     * This method returns the tag of the player
     * @return int  Returns the tag of the player
     */
    protected int getTag(){
        return tag;
    }

    /**
     * This method returns the closest broken window
     * @return Window       Returns the closest broken window
     */
    protected Window getNearestBrokenWindow()
    {
        ArrayList<Window> windows = (ArrayList<Window>)getWorld().getObjects(Window.class);
        closestDistance = 10000;
        for (Window w : windows)
        {
            if(getDistance(this, w) < closestDistance && w.isWindowBroken()){
                closestDistance = getDistance(this, w);
                target = w;
            }

        }
        return target;
    }

    /**
     * This method returns the furthest broken window
     * @return Window       Returns the furthest broken window
     */
    protected Window getFurthestBrokenWindow(){
        ArrayList<Window> windows = (ArrayList<Window>)getWorld().getObjects(Window.class);
        closestDistance = 10000;
        for (Window w : windows)
        {
            if(getDistance(this, w) > closestDistance && w.isWindowBroken() && !w.checkSomeoneOnTheFix()){
                closestDistance = getDistance(this, w);
                target = w;
            }
        }
        return target;
    }

    /**
     * This method increases the amount of money the player gains by a random number
     */
    protected void gainMoney(){
        money += moneyBaseGain + Greenfoot.getRandomNumber(10);
        moneyDrop[moneyDropIndex].play();
        moneyDropIndex++;
        if (moneyDropIndex > moneyDrop.length - 1)
        {
            moneyDropIndex = 0;
        }
    }

    /**
     * This methos returns a random window within the world
     * @return Window       Returns a random window within the world
     */
    protected Window getRandomWindow(){
        ArrayList<Window> windows = (ArrayList<Window>)getWorld().getObjects(Window.class);
        return windows.get(Greenfoot.getRandomNumber(windows.size()));
    }

    /**
     * This method returns a boolean whether there are any broken windows within the world
     * @return boolean      True if there are any broken windows, False if none are broken
     */
    protected boolean anyBrokenWindows(){
        ArrayList<Window> windows = (ArrayList<Window>)getWorld().getObjects(Window.class);

        closestDistance = getDistance(this, windows.get(0));
        for (Window w : windows)
        {
            if(w.isWindowBroken()){

                return true;
            }
        }
        return false;
    }

    /**
     * This methos spawns a bullet which track the closest zombie
     */
    public void shootZombies()
    {
        if (anyZombies())
        {
            gunShot[gunShotIndex].play();
            gunShotIndex++;
            if (gunShotIndex > gunShot.length - 1)
            {
                gunShotIndex = 0;
            }
            Bullet b = new Bullet(getClosestZombie(), this, damage);
            getWorld().addObject(b, this.getX(), getY());
        }
    }

    /**
     * This method returns the cloest zombie from the actor calling it
     * @return Zombie   Returns the closest zombie
     */
    public Zombie getClosestZombie(){
        ArrayList<Zombie> killList = (ArrayList<Zombie>)getWorld().getObjects(Zombie.class);
        nearestZombie = killList.get(0);
        closestDistance = getDistance(this, killList.get(0));
        for(Zombie z : killList){
            if(getDistance(this, z) < closestDistance && getInRoom(z)){
                closestDistance = getDistance(this, z);
                nearestZombie = z;
            }
        }
        turnTowards(nearestZombie.getX(), nearestZombie.getY());
        return nearestZombie;
    }

    /**
     * This method returns if there are any zombies within the world
     * @return boolean      Returns true if there are any zombies, false if none
     */
    public boolean anyZombies(){
        ArrayList<Zombie> killList = (ArrayList<Zombie>)getWorld().getObjects(Zombie.class);
        if(!killList.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * This method returns the cloest zombie from the actor calling it
     * @return Zombie   Returns the closest zombie
     */
    protected Zombie getNearestZombie()
    {
        ArrayList<Zombie> zombies = (ArrayList<Zombie>)getWorld().getObjects(Zombie.class);
        nearestZombie = zombies.get(0);
        closestDistance = getDistance(this, zombies.get(0));
        for (Zombie z : zombies)
        {
            if(getDistance(this, z) < closestDistance){
                closestDistance = getDistance(this, z);
                nearestZombie = z;
            }
        }
        return nearestZombie;
    }

    /**
     * This method adjusts the caller's health
     * @param Enemydamage   The damage the enemy deals
     */
    public void takeDamage(double Enemydamage)
    {
        if(Enemydamage - armour <= 0){
            Enemydamage = 1;
        }
        else{
            Enemydamage = Enemydamage - armour;
        }
        // Damage is reduced by the amount of armour
        health = health - (Enemydamage);
        healthBar.update((int)health);
        if(health < 0){
            getWorld().stopped();
            if(tag == 1){
                Greenfoot.setWorld(new DeathScreen(1));
            }
            else{
                Greenfoot.setWorld(new DeathScreen(2));
            }
            getWorld().removeObject(this);
        }
    }

    /**
     * Return whether it is an active wave or grace period
     * @return boolean      Returns whether it is a grace period or not 
     */
    public boolean returnIsWave()
    {
        MyWorld world = (MyWorld) getWorld();

        return world.getIsWave();
    }

    /**
     * Increases the kills of the caller by 1
     */
    public void increaseKill(){
        kills++;
    }

    /**
     * Returns the number of kills by the caller
     * @return int      Returns the number of kills
     */
    protected int getKills(){
        return kills;
    }

    /**
     * Returns the amount of health the caller has
     * return double    Returns the amount of health
     */
    public double getHealth(){
        return health;
    }
}