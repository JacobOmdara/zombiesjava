import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * buttons used in usersettings
 * 
 * @author (Code: Jacob + Daniel, Grapics: Jacob) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private GreenfootImage image;
    private int direction;
    public Button(int direction)
    {
        this.direction = direction;
    }

    public void act()
    {
        /**
         * Sets the image of the buttons according to it's direction
         */
        if (direction == 0)
        {
            image = new GreenfootImage("leftButton.png");
        }

        if (direction == 1)
        {
            image = new GreenfootImage("rightButton.png");
        }

        if( direction == 2){
            image = new GreenfootImage(325,87);
            image.setColor(Color.GREEN);
            image.fill();
            image.setColor(Color.BLACK);
            image.drawString("", 60,40);
            image.setTransparency(0);
        }
        setImage(image);
    }
}
