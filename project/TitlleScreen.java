import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * INTENTIONAL GAME CROSSED OUT 
 * also intentional wrong name
 * 
 * @author (Code: Daniel, Music: Daniel + Jacob, Graphics: Jacob) 
 * 
 * @version (a version number or a date)
 */
public class TitlleScreen extends World
{
    Button enterButton = new Button(2);
    protected GreenfootSound frontMusic = new GreenfootSound("IntroMusic.mp3");
    /**
     * Constructor for objects of class TitlleScreen.
     * 
     */
    public TitlleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1080, 800, 1);
        addObject(enterButton, 530, 525);
    }

    public void act(){
        if(Greenfoot.mouseClicked(enterButton) || Greenfoot.isKeyDown("enter"))
        {
            stopped();
            Greenfoot.setWorld(new IntroLore());
        }

    }
    public void started(){
        frontMusic.setVolume(50);
        frontMusic.playLoop();
    }
    public void stopped(){
        frontMusic.stop();
    }
}
