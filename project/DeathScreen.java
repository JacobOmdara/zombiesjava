import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * When a character dies, this world will be generated 
 * 
 * @author (Jacob (all) ) 
 */
public class DeathScreen extends World
{
    private int player;
    private GreenfootImage deathScreen;
    protected GreenfootSound sadMusic;
    /**
     * Constructor for objects of class DeathScreen.
     */
    public DeathScreen(int player)
    {    
        super(1080, 800, 1); 
        Greenfoot.setSpeed(50);
        this.player = player;
        // plays sad music
        sadMusic = new GreenfootSound("losemusic.mp3");
        if (player == 1)
        {
            // will have different death screen based on who died
            deathScreen = new GreenfootImage("deadscreenred.png");
            setBackground(deathScreen);
        }

        if (player == 2)
        {
            deathScreen = new GreenfootImage("deadscreenblue.png");
            setBackground(deathScreen);
        }
        sadMusic.setVolume(70);
        sadMusic.playLoop();
    }
    
    /**
     * when the world is paused, the music stops 
     * 
     */
    public void stopped()
    {
        sadMusic.stop();
    }
}
