import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Its a wall, sets boundaries of buildings
 * 
 * @author (Daniel)
 */
public class Wall extends Actor
{
    /**
     * Act - do whatever the Wall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected GreenfootImage sideWalls = new GreenfootImage("sideWall.png");
    protected GreenfootImage verticalWalls = new GreenfootImage("verticalWall.png");

    protected String type;
    public Wall(String type){
        this.type = type;
        if(type.equals("vertical")){
            setImage(verticalWalls);
            getImage().scale(50,100);
        }
        else{
            setImage(sideWalls);
            getImage().scale(100,50);
        }  
    }

    public void act()
    {
        
    }
}
