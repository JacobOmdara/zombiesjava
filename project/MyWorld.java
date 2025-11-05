import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;


/**
 * This simulation takes places in a zombie apocalypse where 2 survivors find each other and take refuge in a building. Unluckily, zombies started swarming the building and
 * the simulation is decided by who dies first by the zombies
 * 
 * Features:
 * - Cool cut scene at the beginning
 * - 7 Unique classes the user can decide in the interface
 * - 3 Different types of walls the survivors upgrade over time
 * - Alternating waves between safetime and zombie spawning time
 * - A currency system with an upgrading system using a shop from a merchant that spawns at the upper middle of the building during safetime
 * 
 * Notes:
 * -Could be some unbalances within the survivor classes due to lack of time
 * -Should not be any run time errors
 * - You can speed up the Greenfoot speed slider to speed up the process to get to the end
 * - GigaChad is a joke class which would always die last due to the sheer amount of health
 * 
 * Known bugs:
 * - Rarely happens, but sometimes the characters walk into walls 
 * 
 * 
 * 
 * * Class Features:
 * 
 * Junkie: 
 * - Weak, with low health. Uses enhancers to improve performance temporarily
 * - Every 20 seconds, gain a speed boost for 5 seconds (costs 5 money)
 * - Has a chance to heal 5 about every 10 seconds
 * - Upgrade: Every 5 levels, increase damage. Otherwise, increase full health
 * 
 * Medic: 
 * - Auto regenerates every 5 seconds
 * - When health reaches lower than 10 for the first time, instant full heal
 * - Upgrade: Levels 1, 3, 5 ... increase full health by 2 and heal for 2. Levels 3, 6, 9, 12 ... improve auto regen skill
 * 
 * Old Man:
 * - Cranky and weak old man that switches from calm to raged state every 10 seconds
 * - Rage mode increases speed, damage, and fire rate
 * - Upgrade: Up to level 12, improve raged state. After level 12, increase full health
 * 
 * Rich Dude: 
 * - Business man that only cares about money
 * - Starts with 200 money, additional money after every kill
 * - Upgrade: Alternating, get 10% discounts on upgrades. Every 5 levels, get discounts on other shop items
 * 
 * Tank: 
 * - Strong bulky buff dude
 * - Every 10 seconds, receive a 3 second shield
 * - Panic Mode: When tank reaches less than 50 health, increase speed
 * - Upgrade: Levels 1, 3, 5, 7 ... increase armour. Levels 2, 4, 6, 8 ... increase health.
 * 
 * Trapper:
 * - Sets traps at windows
 * - When zombies come into contact with his trap, a big explosion catches any nearby zombies
 * - Upgrade: Increase number of traps that can be placed at once
 * 
 * Gigachad:
 * - Joke character
 * - Has insane amount of health, armour, and damage
 * - Wipes out all zombies about every 30 seconds 
 * - Upgrade: improve this skill by lessening the time
 * 
 * Roles: 
 * Audio = Daniel + Jacob
 * Grapics + CodeMaster = Jacob 
 * 
 * Coders = Everyone
 * Victor = Credits manager
 * Troubleshooter + Code helper = Daniel
 * 
 * Credits:
 * 
 * Background Music:
 * (City Crisis, by RADWIMPS) https://www.youtube.com/watch?v=MvQYEvClTdI&feature=youtu.be
 * (東京上空, by RADWIMPS) https://www.youtube.com/watch?v=pa68fhuSifA
 * (Failure, by Wii Fit Plus OST) https://www.youtube.com/watch?app=desktop&v=xY6ZkVpaHb4
 * 
 * Sound Effects:
 * Money: (N/A) https://www.fesliyanstudios.com/royalty-free-sound-effects-download/coin-272
 * Mouse Click: (Gaming Sound FX) https://www.youtube.com/watch?app=desktop&v=h6_8SlZZwvQ
 * Barricade: (Call of Duty) https://www.youtube.com/watch?app=desktop&v=KBAtaD-zF8Q
 * Gunshot: (Mister Sound) https://m.youtube.com/watch?v=f53fti1kwgc 
 * 
 * Graphics:
 * Background: (Thomas McDonald) https://www.pinterest.ca/pin/pixelart-desert-topdown-mengo-fedorov-on-artstation-at-httpswwwartstationcomartworkbmdze8--501447739766207214/
 * Intro Background: (N/A) https://www.pxfuel.com/en/desktop-wallpaper-dvwgh
 * Sand Background: (PixelChoice) https://www.dreamstime.com/pixel-art-desert-background-seamless-sand-texture-backdrop-vector-illustration-image223655056
 * Title Screen: (Zatun) https://apkpure.com/pixel-zombie-shooting-game/com.zatun.pixelzombiehootinggame
 * End Screen: (Plants vs. Zombies) https://plantsvszombies.fandom.com/wiki/Brain/Gallery?file=PvZ1ZombiesWon.png
 * Trap: (benito1964) https://imgbin.com/png/Vb6RLDkE/animation-youtube-pixel-art-land-mine-png
 * Trap 2: (N/A) https://www.vhv.rs/viewpic/hxRwbTx_bear-trap-pixel-art-png-download-beartrap-2d/
 * Health Pack: (Fortnite) https://fortnite.fandom.com/wiki/Med_Kit
 * Guns: (Tuna Oktay) https://tunaoktay.artstation.com/projects/qAJ1NP
 * Shield: (N/A) https://www.pngwing.com/en/free-png-brxdd
 * Sand: (Pixel Worlds) https://pixelworlds.fandom.com/wiki/Sand?file=Sand.png
 * Sandstone: (Pixel Worlds) https://pixelworlds.fandom.com/wiki/Sandstone?file=Sandstone.png
 * Wood: (Steven Holmes) https://www.redbubble.com/people/stevenfholmes/works/27901396-pixel-art-wood-planks
 * Concrete: (Pixel Worlds) https://pixelworlds.fandom.com/wiki/Concrete_Block_1x2?file=Concrete_Block_1x2.png
 * Explosion: (Iryna Palmina) https://www.vecteezy.com/vector-art/6512803-destructive-pixel-explosion-red-detonation-burst-of-energy-with-yellow-fire-flaming-and-glowing-blazing-vector-sparks
 * 
 * LCP:
 * (Credit to: sanderFrenken) https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/
 * 
 * Red Character: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?body=Body_color_light&head=Human_male_light&shadow=Shadow_shadow&wound_arm=none&wound_brain=none&wound_ribs=none&wound_eye=none&wound_mouth=none&ears=none&beard=none&hair=Messy3_light_brown&headcover=Kerchief_maroon&bandana=none&facial=none&eyepatch=none&neck=none&accessory=none&shoulders=none&arms=Armour_silver&bandages=none&clothes=Shortsleeve_red&vest=none&jacket=none&armour=none&cape=none&legs=Pants_red&weapon=none&eyes=Eyes_blue&bauldron=none
 * With Armour: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?body=Body_color_light&head=Human_male_light&shadow=Shadow_shadow&wound_arm=none&wound_brain=none&wound_ribs=none&wound_eye=none&wound_mouth=none&ears=none&beard=none&hair=Messy3_light_brown&headcover=Kerchief_maroon&bandana=none&facial=none&eyepatch=none&neck=none&accessory=none&shoulders=none&arms=Armour_silver&bandages=none&clothes=Shortsleeve_red&vest=none&jacket=none&armour=Plate_silver&cape=none&legs=Pants_red&weapon=none&eyes=Eyes_blue&bauldron=none&shield=none
 * 
 * Blue Character: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?body=Body_color_light&head=Human_male_light&shadow=Shadow_shadow&wound_arm=none&wound_brain=none&wound_ribs=none&wound_eye=none&wound_mouth=none&ears=none&beard=none&hair=Messy2_dark_brown&headcover=Tied_headband_bluegray&bandana=none&facial=none&eyepatch=Eyepatch_eyepatch&neck=none&accessory=none&shoulders=Legion_gold&arms=none&bandages=none&clothes=Longsleeve_blue&vest=none&jacket=none&armour=none&cape=none&legs=Pants_blue&weapon=none&eyes=Eyes_blue&hat=none
 * With Armour: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?body=Body_color_light&head=Human_male_light&shadow=Shadow_shadow&wound_arm=none&wound_brain=none&wound_ribs=none&wound_eye=none&wound_mouth=none&ears=none&beard=none&hair=Messy2_dark_brown&headcover=Tied_headband_bluegray&bandana=none&facial=none&eyepatch=Eyepatch_eyepatch&neck=none&accessory=none&shoulders=Legion_gold&arms=none&bandages=none&clothes=Longsleeve_blue&vest=none&jacket=none&armour=Legion_gold&cape=none&legs=Pants_blue&weapon=none&eyes=Eyes_blue&hat=none&shield=none
 * 
 * Normal Zombie: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?body=Zombie_zombie&head=Zombie_zombie
 * Wounded Zombie 1: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?body=Zombie_zombie&head=Zombie_zombie&ears=none&wound_arm=Arm_arm&wound_ribs=Ribs_ribs&wound_eye=Eye_eye&prosthesis_hand=none&wound_mouth=none&wound_brain=Brain_brain
 * Wounded Zombie 2: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?body=Zombie_zombie&head=Zombie_zombie&ears=none&wound_arm=Arm_arm&wound_ribs=Ribs_ribs&wound_eye=Eye_eye&prosthesis_hand=none&wound_mouth=none&wound_brain=none
 * Wounded Zombie 3: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?body=Zombie_zombie&head=Zombie_zombie&ears=none&wound_arm=Arm_arm&wound_ribs=none&wound_eye=Eye_eye&prosthesis_hand=none&wound_mouth=none&wound_brain=Brain_brain
 * Wounded Zombie 4: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?body=Zombie_zombie&head=Zombie_zombie&ears=none&wound_arm=none&wound_ribs=Ribs_ribs&wound_eye=none&prosthesis_hand=none&wound_mouth=none&wound_brain=none
 * Wounded Zombie 5: https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/#?body=Zombie_zombie&head=Zombie_zombie&ears=none&wound_arm=none&wound_ribs=none&wound_eye=Eye_eye&prosthesis_hand=none&wound_mouth=none&wound_brain=Brain_brain
 * 
 * Used partial code inspiration (based off of) from Sound slides from Mr. Cohen, SuperDisplayLabel + SuperSmoothMover from Mr. Cohen, Super Stat bar
 * 
 * Commenting in this class written by Victor
 * 
 * @ Author: Jacob, Daniel, Victor
 */

public class MyWorld extends World
{
    protected final int numZombieTypes = 3;
    protected int waveNumber = 0; // If no wave is happening, the wave number will be equal to 0
    protected boolean isWave = true;
    protected int acts = 0;
    private SuperDisplayLabel timer = new SuperDisplayLabel(Color.BLACK, Color.RED, new Font ("Comic Sans MS", true, false, 20), 600, "0");
    private SuperDisplayLabel player1Money = new SuperDisplayLabel(Color.BLACK, Color.RED, new Font ("Comic Sans MS", true, false, 20), 100, "$0");
    private SuperDisplayLabel player2Money = new SuperDisplayLabel(Color.BLACK, Color.RED, new Font ("Comic Sans MS", true, false, 20), 100, "$0");
    protected Human p1;
    protected Human p2;
    protected Merchant m = new Merchant();
    private SuperDisplayLabel player1Kills = new SuperDisplayLabel(Color.BLACK, Color.RED, new Font ("Comic Sans MS", true, false, 20), 150, "0");
    private SuperDisplayLabel player2Kills = new SuperDisplayLabel(Color.BLACK, Color.RED, new Font ("Comic Sans MS", true, false, 20), 150, "0");
    //Time per wave (seconds)
    protected final static int time = 30;
    protected int curTime = time;
    protected int windowOffSet = 0;
    protected double playerHealthIncrease;
    protected int moneyIncrease;
    protected String player1Class;
    protected String player2Class;
    protected double zombieSpawn;
    protected double zombieDamage;
    protected int spawnRate = 100;
    protected double zombieSpeed;
    protected boolean spawnChanged;
    protected int zombieType;
    private GreenfootImage background;
    protected GreenfootSound mainMusic = new GreenfootSound("MainMusic.mp3");
    public MyWorld(double playerHealthIncrease, int moneyIncrease, String player1Class, String player2Class, double zombieSpawn, double zombieDamage, double zombieSpeed)
    {    
        super(1080, 800, 1); 
        Greenfoot.setSpeed(50);
        this.playerHealthIncrease = playerHealthIncrease;
        this.moneyIncrease = moneyIncrease;
        this.player1Class = player1Class;
        this.player2Class = player2Class;
        this.zombieSpawn = zombieSpawn;
        this.zombieDamage = zombieDamage;
        this.zombieSpeed = zombieSpeed;
        addObject(timer, this.getWidth()/2,20);

        addObject(player1Money, 50, 20);
        addObject(player2Money, 1030, 20);

        addObject(player1Kills, 175, 20);
        addObject(player2Kills, 910, 20);
        //Wall for the windo sides
        spawnVerticalWall(200, "left");
        spawnVerticalWall(900, "right");
        //Walls for the top and bottom
        spawnHorizontalWall(250, 100, 13);
        spawnHorizontalWall(225, 65, 14);
        spawnHorizontalWall(250, 817, 13);

        //Walls for the left inner horizontal walls
        spawnHorizontalWall(225,320,5);
        spawnHorizontalWall(225,600,5);

        //Walls for the right inner horizontal walls
        spawnHorizontalWall(675, 320, 5);
        spawnHorizontalWall(675,600,5);

        spawnInnerVerticalWall(450, 175);
        spawnInnerVerticalWall(450, 395);
        spawnInnerVerticalWall(450, 670);

        spawnInnerVerticalWall(650, 175);
        spawnInnerVerticalWall(650, 395);
        spawnInnerVerticalWall(650, 670);
        if(player1Class.equals("Gigachad")){
            p1 = new Gigachad(100,3.0,2,2, 1, player1Class);
        }
        else if(player1Class.equals("Junkie")){
            p1 = new Junkie(100,3.0,2,2, 1, player1Class);
        }
        else if(player1Class.equals("Medic")){
            p1 = new Medic(100,3.0,2,2, 1, player1Class);
        }
        else if(player1Class.equals("Old Man")){
            p1 = new OldMan(100,3.0,2,2, 1, player1Class);
        }
        else if(player1Class.equals("Rich Dude")){
            p1 = new RichDude(100,3.0,2,2, 1, player1Class);
        }
        else if(player1Class.equals("Tank")){
            p1 = new Tank(100,3.0,2,2, 1, player1Class);
        }
        else if(player1Class.equals("Trapper")){
            p1 = new Trapper(100,3.0,2,2, 1, player1Class);
        }

        if(player2Class.equals("Gigachad")){
            p2 = new Gigachad(100,3.0,2,2, 2, player2Class);
        }
        else if(player2Class.equals("Junkie")){
            p2 = new Junkie(100,3.0,2,2, 2, player2Class);
        }
        else if(player2Class.equals("Medic")){
            p2 = new Medic(100,3.0,2,2, 2, player2Class);
        }
        else if(player2Class.equals("Old Man")){
            p2 = new OldMan(100,3.0,2,2, 2, player2Class);
        }
        else if(player2Class.equals("Rich Dude")){
            p2 = new RichDude(100,3.0,2,2, 2, player2Class);
        }
        else if(player2Class.equals("Tank")){
            p2 = new Tank(100,3.0,2,2, 2, player2Class);
        }
        else if(player2Class.equals("Trapper")){
            p2 = new Trapper(100,3.0,2,2, 2, player2Class);
        }

        //p1 = new Human(100,3.0,2,2, 1, player1Class);
        //p2 = new Human(100,3.0,2,2, 2, player2Class);
        addObject(p1, 400, 400);
        addObject(p2, 700, 400);
        background = new GreenfootImage("background.png");
        setBackground(background);
        started();
    }

    /**
     * This public method is used to set up the walls with the windows by spawning singular wall and window objects with set intervals
     * @ param x    x value of where the wall will be
     */
    public void spawnVerticalWall(int x, String direction){
        if(direction.equals("left")){
            windowOffSet = 5; 
        }
        else{
            windowOffSet = -5;
        }
        int y = 100;
        int increment = 100;
        addObject(new Wall("vertical"), x, y);
        addObject(new Window(), x + windowOffSet, y+=increment);
        addObject(new Wall("vertical"), x, y+=increment);
        addObject(new Wall("vertical"), x, y+=increment);
        addObject(new Window(), x + windowOffSet, y+=increment);
        addObject(new Wall("vertical"), x, y+=increment);
        addObject(new Window(), x + windowOffSet, y+=increment);
        addObject(new Wall("vertical"), x, y+=increment);

    }

    public void started(){
        mainMusic.setVolume(70);
        mainMusic.playLoop();
    }

    public void stopped(){
        mainMusic.stop();
    }

    /**
     * This method returns the increase in health the player will gain from the user option
     * @return double   The health gain
     */
    public double getPlayerHealthIncrease(){
        return playerHealthIncrease;
    }

    /**
     * This method returns the money increase from the user option
     * @return int  Returns the bonus money player recieves from the interface
     */
    public int getMoneyIncrease(){
        return moneyIncrease;
    }

    /**
     * This method returns a string which represent the left player's class
     * @return String   Returns a string representing the player 1's class
     */
    public String getPlayer1Class(){
        return player1Class;
    }

    /**
     * This method returns a string which represent the right player's class
     * @return String   Returns a string representing player 2's class
     */
    public String getPlayer2Class(){
        return player2Class;   
    }

    /**
     * This method returns a double which represents the increase zombie spawn rate
     * @return double   Returns the increase in zombie spawn chance
     */
    public double getZombieSpawn(){
        return zombieSpawn;
    }

    /**
     * This method returns a double which represents the increase zombie damage
     * @return double   Returns the increase in zombie damage
     */
    public double getZombieDamage(){
        return zombieDamage;
    }

    /**
     * This methos returns a double which represents the increase in zombie speed
     * @return double   Returns the increase in zombie speed
     */
    public double getZombieSpeed(){
        return zombieSpeed;
    }

    /**
     * This public method is used to set up the horrizontal walls of the bulding with many wall objects with set intervals
     * @ param x    x value of where the wall will be set at
     * @ param y    y value of where the wall will start
     * @ param length   the length of the walls by having that number of wall acors
     */
    public void spawnHorizontalWall(int x, int y, int length){
        int increment = 50;
        addObject(new Wall("horizontal"), x, y);
        for(int i = 0; i < length-1; i++){
            addObject(new Wall("horizontal"), x+= increment, y);
        }

    }

    /**
     * This method spawns a vertical wall at a given x and y value
     * @param x     An integer which decides where the vertical wall should be placed horizontally
     * @param y     An integer which decides where the vertical wall should be placed vertically
     */
    public void spawnInnerVerticalWall(int x, int y){
        int increment = 100;
        addObject(new Wall("vertical"),x,y);
    }

    /**
     * This method returns the wave number the survivors are on
     * @return int      Returns the wave number the survivors are on
     */
    public int getWave()
    {
        return waveNumber;
    }

    /**
     * This method returns whether it is a grace period wave or a wave where zombies spawn
     * @param boolean   True if it is a wave where zombies spawn and false for a grace period wave
     */
    public boolean getIsWave(){
        return isWave;
    }

    /**
     * This method returns the acts the current world is on to keep track of time
     * @return int  Returns the number of acts the world is on
     */
    public int getActs(){
        return acts;
    }

    /**
     * This method updates the amount of money the players have on the score bar
     */
    public void renderDisplay(){
        player1Money.update("P1: $" +String.valueOf(p1.getMoney()),true);
        player2Money.update("P2: $" +String.valueOf(p2.getMoney()),true);
    }

    /**
     * This method updates the amount of kills each survivor has on the score bar
     */
    public void renderKills(){
        player1Kills.update("P1 Kills: " + String.valueOf(p1.getKills()), true);
        player2Kills.update("P2 Kills: " + String.valueOf(p2.getKills()), true);
    }

    public void act(){
        renderDisplay();
        renderKills();
        /**
         * Updates the timer and other statuses every second
         */
        if(acts % 60 == 0){
            timer.update(waveStatus() + String.valueOf(curTime) + " WAVE ACTIVE: " + getIsWave() + " Wave Number " + String.valueOf(waveNumber),true);
            curTime--;
            if(curTime == 0){
                curTime = time;
            }
        }
        /**
         * If it is currently a wave
         */
        if(isWave){
            ArrayList<Zombie> killList = (ArrayList<Zombie>)getObjects(Zombie.class);

            if(killList.size() < 6 && Greenfoot.getRandomNumber(spawnRate - (int)(spawnRate * getZombieSpawn())) == 0){
                zombieType = Greenfoot.getRandomNumber(numZombieTypes);
                if(Greenfoot.getRandomNumber(2) == 0){
                    if(zombieType == 0){
                        addObject(new FastZombie(1,0),0, 100+ Greenfoot.getRandomNumber(720)); 

                    }
                    else if(zombieType == 1){
                        addObject(new StrongZombie(1,1),0, 100+ Greenfoot.getRandomNumber(720)); 
                    }
                    else if(zombieType == 2){
                        addObject(new TankyZombie(1,2),0, 100+ Greenfoot.getRandomNumber(720)); 
                    }
                }
                else{
                    if(zombieType == 0){
                        addObject(new FastZombie(-1,0),1079, Greenfoot.getRandomNumber(720)); 
                    }
                    else if(zombieType == 1){
                        addObject(new StrongZombie(-1,1),1079, Greenfoot.getRandomNumber(720)); 
                    }
                    else if(zombieType == 2){
                        addObject(new TankyZombie(-1,2),1079, Greenfoot.getRandomNumber(720)); 
                    }
                }
            }
        }
        /**
         * Once the time is up switch to the alternate status of the current world 
         */
        if(acts == time*60){
            if(!spawnChanged){
                spawnChanged = true;
                spawnRate--;
            }
            if(isWave){
                isWave = false;
                addObject(m, 525, 155);
            }
            else{
                isWave = true;
                spawnChanged = false;
                waveNumber++;
                removeObject(m);
            }
            acts = 0;
        }
        acts++;
    }

    /**
     * Returns a string to help update the score bar
     * @return String   returns a string representing whether it is grace period or survive period
     */
    public String waveStatus(){
        if(isWave){
            return "Survive For: ";
        }
        else{
            return "Wave In ";
        }
    }
}
