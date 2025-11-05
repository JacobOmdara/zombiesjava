import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Part of the intro lore, the dialogue and whatnot
 * 
 * @author (Jacob) 
 */
public class Conversation extends Actor
{
    private GreenfootImage dialogue0, dialogue1, dialogue2, dialogue3, dialogue4, dialogue5;
    private GreenfootSound sound0, sound1, sound2, sound3, sound4, sound5;
    private int person, systemTimer;
    public Conversation(int person)
    {
        this.person = person;
        dialogue0 = new GreenfootImage("textbox0.png");
        dialogue1 = new GreenfootImage("textbox1.png");
        dialogue2 = new GreenfootImage("textbox2.png");
        dialogue3 = new GreenfootImage("textbox3.png");
        dialogue4 = new GreenfootImage("textbox4.png");
        dialogue5 = new GreenfootImage("textbox5.png");
        sound0 = new GreenfootSound("dialogue0.wav");
        sound1 = new GreenfootSound("dialogue1.wav");
        sound2 = new GreenfootSound("dialogue2.wav");
        sound3 = new GreenfootSound("dialogue3.wav");
        sound4 = new GreenfootSound("dialogue4.wav");
        sound5 = new GreenfootSound("dialogue5.wav");
    }
    /**
     * main method, will automatically time conversation and interactions.
     */
    public void act()
    {
        if (person == 0)
        {
            if (systemTimer == 0)
            {
                setImage(dialogue0);
                sound0.play();
            }
            if (systemTimer == 180)
                this.getImage().setTransparency(0);
            if (systemTimer == 360)
            {
                setImage(dialogue2);
                sound2.play();
            }
            if (systemTimer == 540)
                this.getImage().setTransparency(0);
            if (systemTimer == 720)
            {
                setImage(dialogue4);
                sound4.play();
            }
            if (systemTimer == 900)
            {
                getWorld().removeObject(this);
                return;
            }
        }
        
        if (person == 1)
        {
            if (systemTimer == 0)
            {
                setImage(dialogue1);
                this.getImage().setTransparency(0);
            }
            if (systemTimer == 180)
            {
                this.getImage().setTransparency(255);
                sound1.play();
            }
            if (systemTimer == 360)
                this.getImage().setTransparency(0);
            if (systemTimer == 540)
            {
                setImage(dialogue3);
                sound3.play();
            }
            if (systemTimer == 720)
                this.getImage().setTransparency(0);
            if (systemTimer == 900)
            {
                setImage(dialogue5);
                sound5.play();
            }
            if (systemTimer == 1080)
            {
                getWorld().removeObject(this);
                return;
            }
        }
        systemTimer++;
    }
}
