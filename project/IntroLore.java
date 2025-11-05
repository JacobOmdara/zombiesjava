import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world of introlore, 
 * 
 * @author (Jacob, AUDIO is daniel and jacob ) 
 * @version (a version number or a date)
 */
public class IntroLore extends World
{
    private GreenfootImage background, playerA, playerB;
    private int systemTimer;
    /**
     * Constructor for objects of class IntroLore.
     */
    public IntroLore()
    {   
        super(1080, 800, 1, false); 
        background = new GreenfootImage("backgroundthing.jpg");
        setBackground(background);
    }
    
    /**
     * basically timed sequences of lore/story.
     */
    public void act()
    {
        if (systemTimer == 0)
            section1();
        if (systemTimer == 120)
            section2();
        if (systemTimer == 180)
            section3();
        // add getter method and multiply by # of conversations
        if (systemTimer == 1260)
            section4();
        systemTimer++;
        if (systemTimer == 1400)
            Greenfoot.setWorld(new UserSettings());
    }
    
    /**
     * All sections below follow a system timer, and are timed sequences. 
     * Section 1 describes the characters walking in.
     */
    public void section1()
    {
        addObject(new LorePerson(1), 1000, 450);
        addObject(new LorePerson(0), 400, 450);
    }
    
    /**
     * section 2 has the characters zoomed in, and are about to start conversation
     */
    public void section2()
    {
        background = new GreenfootImage("backgroundthing2.jpg");
        setBackground(background);
    }
    
    /**
     * section 3 has conversation objects, which start a conversation between each other.
     */
    public void section3()
    {
        addObject(new Conversation(0), 580, 250);
        addObject(new Conversation(1), 550, 250);
    }
    
    /**
     * section 4 has the characters zoomed out, and they walk off the map.
     */
    public void section4()
    {
        background = new GreenfootImage("backgroundthing.jpg");
        setBackground(background);
        addObject(new LorePerson(3), 500, 450);
        addObject(new LorePerson(2), 400, 450);

    }
}
