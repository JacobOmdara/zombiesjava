import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * parameters to change the game up
 * 
 * @author (Code: Daniel, Grapics + Proofread: Jacob) 
 * @version (a version number or a date)
 */
public class UserSettings extends World
{
    /**
     * The indexes that represent the option the user is on
     */
    private int playerHealthIndex = 2;
    private int playerStartMoneyIndex = 0;
    private int leftPlayerClassIndex = 0;
    private int rightPlayerClassIndex = 0;
    private int increasedZombieIndex = 0;
    private int zombieIncreaseDamageIndex = 2;
    private int zombieIncreaseSpeedIndex = 2;
    /**
     * Arrays that represent the options that are given to the user that affect the player
     */
    String[] playerHealth = {"-20%","-10%","0%","10%","20%","30%","40%","50%","60%","70%","80%","90%","100%"};
    double[] playerHealthVal = {-0.2, -0.1, 0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
    String[] playerStartMoney = {"None","$100","$200","$300"};
    int[] playerStartMoneyVal = {0, 100 , 200, 300};
    String[] leftPlayerClass = {"Junkie","Trapper","Old Man", "Medic", "Tank", "Rich Dude","Gigachad"};
    String[] rightPlayerClass={"Junkie","Trapper","Old Man","Medic", "Tank", "Rich Dude","Gigachad"};
    /**
     * Buttons created for the interface
     */
    Button leftHealthButton = new Button(0);
    Button rightHealthButton = new Button(1);   
    Button leftStartMoneyButton = new Button(0);
    Button rightStartMoneyButton = new Button(1);
    Button leftPlayerClassOfLeftP = new Button(0);
    Button rightPlayerClassOfLeftP = new Button(1);
    Button leftPlayerClassOfRightP = new Button(0);
    Button rightPlayerClassOfRightP = new Button(1);

    Button leftIncreasedZombiesButton = new Button(0);
    Button rightIncreasedZombiesButton = new Button(1);
    Button leftZombieIncreasedDamageButton = new Button(0);
    Button rightZombieIncreasedDamageButton = new Button(1);
    Button leftIncreasedZombieSpeedButton = new Button(0);
    Button rightIncreasedZombieSpeedButton = new Button(1);
    /**
     * Arrays for the zombie part of the inerface
     */
    String[] zombieSpawnIncrease = {"0%", "20%", "40%", "60%", "80%"};
    double[] zombieSpawnIncreaseVal = {0.0,0.2, 0.4, 0.6,0.8};
    String[] zombieDamageIncrease = {"-20%","-10%","0%", "20%", "40%", "60%", "80%", "100%"};
    double[] zombieDamageIncreaseVal = {-0.2, -0.1, 0.0,0.2, 0.4, 0.6,0.8, 1.0};
    String[] zombieSpeedIncrease = {"-20%","-10%","0%", "20%", "40%", "60%", "80%", "100%"};
    double[] zombieSpeedIncreaseVal = {-0.2, -0.1, 0.0,0.2, 0.4, 0.6,0.8, 1.0};

    /**
     * The display of text which shows what the current option the user has chosen
     */
    private SuperDisplayLabel playerHealthLabel = new SuperDisplayLabel(Color.WHITE, Color.DARK_GRAY, new Font ("Average", true, false, 20), 160, "0%");
    private SuperDisplayLabel playerStartMoneyLabel = new SuperDisplayLabel(Color.WHITE, Color.DARK_GRAY, new Font ("Average", true, false, 20), 160, "None");
    private SuperDisplayLabel leftPlayerClassLabel = new SuperDisplayLabel(Color.WHITE, Color.DARK_GRAY, new Font ("Average", true, false, 20), 160, "Junkie");
    private SuperDisplayLabel rightPlayerClassLabel = new SuperDisplayLabel(Color.WHITE, Color.DARK_GRAY, new Font ("Average", true, false, 20), 160, "Junkie");

    private SuperDisplayLabel increasedZombiesLabel = new SuperDisplayLabel(Color.WHITE, Color.DARK_GRAY, new Font ("Average", true, false, 20), 160, "0%");
    private SuperDisplayLabel zombiesIncreasedDamageLabel = new SuperDisplayLabel(Color.WHITE, Color.DARK_GRAY, new Font ("Average", true, false, 20), 160, "0%");
    private SuperDisplayLabel zombiesIncreasedSpeedLabel = new SuperDisplayLabel(Color.WHITE, Color.DARK_GRAY, new Font ("Average", true, false, 20), 160, "0%");

    private SuperDisplayLabel playerHealthLabelTitle = new SuperDisplayLabel(Color.WHITE, Color.DARK_GRAY, new Font ("Average", true, false, 20), 160, "Player Health");
    private SuperDisplayLabel playerStartMoneyLabelTitle = new SuperDisplayLabel(Color.WHITE, Color.DARK_GRAY, new Font ("Average", true, false, 20), 160, "Start Money");
    private SuperDisplayLabel leftPlayerClassLabelTitle = new SuperDisplayLabel(Color.WHITE, Color.DARK_GRAY, new Font ("Average", true, false, 20), 160, "Player 1 Class");
    private SuperDisplayLabel rightPlayerClassLabelTitle = new SuperDisplayLabel(Color.WHITE, Color.DARK_GRAY, new Font ("Average", true, false, 20), 160, "Player 2 Class");

    private SuperDisplayLabel increasedZombiesLabelTitle = new SuperDisplayLabel(Color.WHITE, Color.DARK_GRAY, new Font ("Average", true, false, 20), 300, "Increased Zombies Spawn %");
    private SuperDisplayLabel zombiesIncreasedDamageLabelTitle = new SuperDisplayLabel(Color.WHITE, Color.DARK_GRAY, new Font ("Average", true, false, 20), 250, "Zombie Damage Increase");
    private SuperDisplayLabel zombiesIncreasedSpeedLabelTitle = new SuperDisplayLabel(Color.WHITE, Color.DARK_GRAY, new Font ("Average", true, false, 20), 250, "Zombie Speed Increase");

    private SuperDisplayLabel nextWorld = new SuperDisplayLabel(Color.WHITE, Color.DARK_GRAY, new Font ("Average", true, false, 20), 250, "Continue");
    protected GreenfootSound[] buttonSounds;
    protected int buttonSoundsIndex = 0;

    public UserSettings()
    {    
        super(1080, 800, 1); 
        buttonSounds = new GreenfootSound[20];
        for(int i = 0; i < buttonSounds.length; i++)
        {
            buttonSounds[i] = new GreenfootSound("mouseclick.wav");
            buttonSounds[i].setVolume(70);
        }   
        /**
         * Adding all the buttons, displays, and titles
         */
        int rowSpacing = 150;
        addObject(playerHealthLabelTitle, 170,40);
        addObject(leftHealthButton, 40, 100);
        addObject(playerHealthLabel, 170,100);
        addObject(rightHealthButton, 300, 100);

        addObject(playerStartMoneyLabelTitle,170 , 40 +rowSpacing);
        addObject(leftStartMoneyButton, 40, 100 + rowSpacing);
        addObject(playerStartMoneyLabel, 170, 100 + rowSpacing);
        addObject(rightStartMoneyButton, 300, 100 + rowSpacing);

        rowSpacing+=150;

        addObject(leftPlayerClassLabelTitle, 170, 40+ rowSpacing);
        addObject(leftPlayerClassOfLeftP, 40, 100+ rowSpacing);
        addObject(leftPlayerClassLabel, 170, 100+ rowSpacing);
        addObject(rightPlayerClassOfLeftP, 300, 100+ rowSpacing);

        rowSpacing+= 150;

        addObject(rightPlayerClassLabelTitle, 170, 40+ rowSpacing);
        addObject(leftPlayerClassOfRightP, 40, 100+ rowSpacing);
        addObject(rightPlayerClassLabel, 170, 100+ rowSpacing);
        addObject(rightPlayerClassOfRightP, 300, 100+ rowSpacing);

        rowSpacing = 150;
        int laneSpacing = 400;
        addObject(increasedZombiesLabelTitle, 170+ laneSpacing,40);
        addObject(leftIncreasedZombiesButton, 40+ laneSpacing, 100);
        addObject(increasedZombiesLabel, 170 + laneSpacing,100);
        addObject(rightIncreasedZombiesButton, 300 + laneSpacing, 100);

        addObject(zombiesIncreasedDamageLabelTitle, 170 + laneSpacing,40+ rowSpacing);
        addObject(leftZombieIncreasedDamageButton, 40 + laneSpacing, 100+ rowSpacing);
        addObject(zombiesIncreasedDamageLabel, 170 + laneSpacing,100 + rowSpacing);
        addObject(rightZombieIncreasedDamageButton, 300 + laneSpacing, 100+ rowSpacing);

        rowSpacing += 150;

        addObject(zombiesIncreasedSpeedLabelTitle, 170 + laneSpacing,40+ rowSpacing);
        addObject(leftIncreasedZombieSpeedButton, 40 + laneSpacing, 100+ rowSpacing);
        addObject(zombiesIncreasedSpeedLabel, 170 + laneSpacing,100 + rowSpacing);
        addObject(rightIncreasedZombieSpeedButton, 300 + laneSpacing, 100+ rowSpacing);

        addObject(nextWorld, 880, 680);

    }   

    /**
     * The act method checks whether any of the buttons have been clicked on and calls the method to change the Label values
     */
    public void act(){
        if(Greenfoot.mouseClicked(leftHealthButton))
        {
            playerHealthMoveLeft();
            buttonSounds[buttonSoundsIndex].play();
            buttonSoundsIndex++;
        }
        else if(Greenfoot.mouseClicked(rightHealthButton)){
            playerHealthMoveRight();
            buttonSounds[buttonSoundsIndex].play();
            buttonSoundsIndex++;
        }

        if(Greenfoot.mouseClicked(leftStartMoneyButton)){
            playerStartMoneyMoveLeft();
            buttonSounds[buttonSoundsIndex].play();
            buttonSoundsIndex++;
        }
        else if(Greenfoot.mouseClicked(rightStartMoneyButton)){
            playerStartMoneyMoveRight();
            buttonSounds[buttonSoundsIndex].play();
            buttonSoundsIndex++;
        }
        if(Greenfoot.mouseClicked(leftPlayerClassOfLeftP)){
            leftPlayerClassMoveLeft();
            buttonSounds[buttonSoundsIndex].play();
            buttonSoundsIndex++;
        }
        else if(Greenfoot.mouseClicked(rightPlayerClassOfLeftP)){
            leftPlayerClassMoveRight();
            buttonSounds[buttonSoundsIndex].play();
            buttonSoundsIndex++;
        }

        if(Greenfoot.mouseClicked(leftPlayerClassOfRightP)){
            rightPlayerClassMoveLeft();
            buttonSounds[buttonSoundsIndex].play();
            buttonSoundsIndex++;
        }
        else if(Greenfoot.mouseClicked(rightPlayerClassOfRightP)){
            rightPlayerClassMoveRight();
            buttonSounds[buttonSoundsIndex].play();
            buttonSoundsIndex++;
        }

        if(Greenfoot.mouseClicked(leftIncreasedZombiesButton)){
            increasedZombiesMoveLeft();
            buttonSounds[buttonSoundsIndex].play();
            buttonSoundsIndex++;
        }
        else if(Greenfoot.mouseClicked(rightIncreasedZombiesButton)){
            increasedZombiesMoveRight();
            buttonSounds[buttonSoundsIndex].play();
            buttonSoundsIndex++;
        }

        if(Greenfoot.mouseClicked(leftZombieIncreasedDamageButton)){
            zombieDamageMoveLeft();
            buttonSounds[buttonSoundsIndex].play();
            buttonSoundsIndex++;
        }
        else if(Greenfoot.mouseClicked(rightZombieIncreasedDamageButton)){
            zombieDamageMoveRight();
            buttonSounds[buttonSoundsIndex].play();
            buttonSoundsIndex++;
        }

        if(Greenfoot.mouseClicked(leftIncreasedZombieSpeedButton)){
            zombieSpeedIncreaseMoveLeft();
            buttonSounds[buttonSoundsIndex].play();
            buttonSoundsIndex++;
        }
        else if(Greenfoot.mouseClicked(rightIncreasedZombieSpeedButton)){
            zombieSpeedIncreaseMoveRight();
            buttonSounds[buttonSoundsIndex].play();
            buttonSoundsIndex++;
        }

        if(Greenfoot.mouseClicked(nextWorld)){
            buttonSounds[buttonSoundsIndex].play();
            buttonSoundsIndex++;
            Greenfoot.setWorld(new MyWorld(playerHealthVal[playerHealthIndex], playerStartMoneyVal[playerStartMoneyIndex], leftPlayerClass[leftPlayerClassIndex], rightPlayerClass[rightPlayerClassIndex], zombieSpawnIncreaseVal[increasedZombieIndex], zombieDamageIncreaseVal[zombieIncreaseDamageIndex], zombieSpeedIncreaseVal[zombieIncreaseSpeedIndex]));     
        }
        if (buttonSoundsIndex > buttonSounds.length - 1)
        {
            buttonSoundsIndex = 0;
        }
    }

    /**
     * This method below adjust the values of the display that the user sees when interacted with
     */
    public void playerHealthMoveLeft(){
        if(playerHealthIndex > 0){
            playerHealthIndex--;
            playerHealthLabel.update(playerHealth[playerHealthIndex], true);
        }
    }

    /**
     * This method below adjust the values of the display that the user sees when interacted with
     */
    public void playerHealthMoveRight(){
        if(playerHealthIndex < playerHealth.length-1){
            playerHealthIndex++;  
            playerHealthLabel.update(playerHealth[playerHealthIndex], true);
        }
    }

    /**
     * This method below adjust the values of the display that the user sees when interacted with
     */
    public void playerStartMoneyMoveLeft(){
        if(playerStartMoneyIndex > 0){
            playerStartMoneyIndex--;
            playerStartMoneyLabel.update(playerStartMoney[playerStartMoneyIndex], true);
        }
    }

    /**
     * This method below adjust the values of the display that the user sees when interacted with
     */
    public void playerStartMoneyMoveRight(){
        if(playerStartMoneyIndex < playerStartMoney.length-1){
            playerStartMoneyIndex++;
            playerStartMoneyLabel.update(playerStartMoney[playerStartMoneyIndex], true);

        }
    }

    /**
     * This method below adjust the values of the display that the user sees when interacted with
     */
    public void leftPlayerClassMoveLeft(){
        if(leftPlayerClassIndex > 0){
            leftPlayerClassIndex--;
            leftPlayerClassLabel.update(leftPlayerClass[leftPlayerClassIndex],true);
        }
    }

    /**
     * This method below adjust the values of the display that the user sees when interacted with
     */
    public void leftPlayerClassMoveRight(){
        if(leftPlayerClassIndex < leftPlayerClass.length-1){
            leftPlayerClassIndex++;
            leftPlayerClassLabel.update(leftPlayerClass[leftPlayerClassIndex],true);
        }
    }

    /**
     * This method below adjust the values of the display that the user sees when interacted with
     */
    public void rightPlayerClassMoveLeft(){
        if(rightPlayerClassIndex > 0){
            rightPlayerClassIndex--;
            rightPlayerClassLabel.update(rightPlayerClass[rightPlayerClassIndex],true);
        }
    }

    /**
     * This method below adjust the values of the display that the user sees when interacted with
     */
    public void rightPlayerClassMoveRight(){
        if(rightPlayerClassIndex < rightPlayerClass.length-1){
            rightPlayerClassIndex++;
            rightPlayerClassLabel.update(rightPlayerClass[rightPlayerClassIndex],true);
        }
    }

    /**
     * This method below adjust the values of the display that the user sees when interacted with
     */
    public void increasedZombiesMoveLeft(){
        if(increasedZombieIndex > 0){
            increasedZombieIndex--;
            increasedZombiesLabel.update(zombieSpawnIncrease[increasedZombieIndex],true);
        }
    }

    /**
     * This method below adjust the values of the display that the user sees when interacted with
     */
    public void increasedZombiesMoveRight(){
        if(increasedZombieIndex < zombieSpawnIncrease.length-1){
            increasedZombieIndex++;
            increasedZombiesLabel.update(zombieSpawnIncrease[increasedZombieIndex],true);
        }
    }

    /**
     * This method below adjust the values of the display that the user sees when interacted with
     */
    public void zombieDamageMoveLeft(){
        if(zombieIncreaseDamageIndex > 0){
            zombieIncreaseDamageIndex--;
            zombiesIncreasedDamageLabel.update(zombieDamageIncrease[zombieIncreaseDamageIndex],true);

        }
    }

    /**
     * This method below adjust the values of the display that the user sees when interacted with
     */
    public void zombieDamageMoveRight(){
        if(zombieIncreaseDamageIndex < zombieDamageIncrease.length-1){
            zombieIncreaseDamageIndex++;
            zombiesIncreasedDamageLabel.update(zombieDamageIncrease[zombieIncreaseDamageIndex],true);
        }
    }

    /**
     * This method below adjust the values of the display that the user sees when interacted with
     */
    public void zombieSpeedIncreaseMoveLeft(){
        if(zombieIncreaseSpeedIndex > 0){
            zombieIncreaseSpeedIndex--;
            zombiesIncreasedSpeedLabel.update(zombieSpeedIncrease[zombieIncreaseSpeedIndex], true);
        }
    }

    /**
     * This method below adjust the values of the display that the user sees when interacted with
     */
    public void zombieSpeedIncreaseMoveRight(){
        if(zombieIncreaseSpeedIndex < zombieSpeedIncrease.length-1){
            zombieIncreaseSpeedIndex++;
            zombiesIncreasedSpeedLabel.update(zombieSpeedIncrease[zombieIncreaseSpeedIndex], true);
        }
    }
}
