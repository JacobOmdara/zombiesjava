import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * chases down human
 * 
 * @author Daniel 
 */
public class Zombie extends Humanoids
{   
    protected SuperStatBar healthBar = new SuperStatBar();
    protected double zombieMaxHealth;
    protected int direction;
    protected boolean inBuilding = false;
    protected double closestDistance;
    protected boolean targetSet = false;
    protected double damage;
    protected boolean didWindowBreak = false;
    protected Window target; 
    protected Human humanTarget;
    private boolean alive = true;
    protected int hitCoolDown = 60;
    protected int zombieType;
    protected boolean justEnteredWindow = true;

    /**
     * Act - do whatever the Zombie wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Zombie(int direction, int type){
        enableStaticRotation();
        this.direction = direction;
        zombieType = type;

        if(type == 0){
            //If Fast Zombie
            rightImage = new GreenfootImage("zombie1.png");
            rightImage.scale(40,80);

            leftImage = new GreenfootImage("zombie1.png");
            leftImage.scale(40,80);
            leftImage.mirrorHorizontally();

            setImage(rightImage);
        }
        else if(type == 1){
            //If Strong Zombie
            rightImage = new GreenfootImage("zombie2.png");
            rightImage.scale(40,80);

            leftImage = new GreenfootImage("zombie2.png");
            leftImage.scale(40,80);
            leftImage.mirrorHorizontally();

            setImage(rightImage);

        }
        else if(type == 2){
            //If Tanky Zombie
            rightImage = new GreenfootImage("zombie3.png");
            rightImage.scale(40,80);

            leftImage = new GreenfootImage("zombie3.png");
            leftImage.scale(40,80);
            leftImage.mirrorHorizontally();

            setImage(rightImage);

        }
        if(direction == -1){
            setImage(leftImage);
        }
    }

    public void doSomething(){
        setImageRotation();
        if(acts % hitCoolDown == 0){
            checkHitHuman();
        }
        //First sets the window it's moving towards
        ArrayList<Human> humanList = (ArrayList<Human>)getWorld().getObjects(Human.class);
        if(humanList.size() != 0){
            if(!didWindowBreak){
                if(!targetSet){
                    turnTowardsWindow();
                    targetSet = true;
                }
                if(!checkNearWindow() && !checkNearWall()){
                    if(direction == -1){
                        setImage(leftImage);
                    }
                    else{
                        setImage(rightImage);
                    }
                    //Moves if it is not touching a window or wall
                    //Might need to change is Touching to Object at offset but it would be at a random angle so fixed offsets won't work
                    move(speed);

                }
                else if(target != null && checkNearWall() && !checkNearWindow()){
                    //If the zombie made it to the wall but not quite at the window. This code will fix the zombie and move it
                    //to infront of thr window

                    //fix 0 maybe add a parameter to zombie on which direction its comign from
                    if(direction < 0){
                        setRotation(180);
                    }
                    else
                    {
                        setRotation(0);
                    }
                    setLocation(getX(), getY() + (target.getY()-getY()));
                }
                else if(checkNearWindow()){
                    //Once it;s at the window it will start attacking the window until the window breaks.
                    //boolean switch for it to move through after the broken image appears
                    if(!didWindowBreak){
                        if(getNearWindow().getDurability() <= 0){
                            didWindowBreak = true;
                        }
                        else if(acts % hitCoolDown == 0){
                            if(attackWindow() <= 0){
                                didWindowBreak = true;
                            }
                            acts = 0;
                        }

                    }

                }
            }
            else{
                //enter building

                move(speed);
                if(!isTouching(Human.class)){
                    if(getInRoom(this)){
                        justEnteredWindow = false;
                        moveToTarget(getClosestPlayer());
                        if(getY() < 125){
                            setLocation(getX(), getY()+10);
                        }
                        if(getY() > 775){
                            setLocation(getX(), getY()-20);
                        }
                        if(getX() <= 225){
                            setLocation(250 + 20, getY());
                        }
                        if(getX() >= 875){
                            setLocation(850, getY());
                        }
                        if(isAtEdge() || getY() <= 25){
                            getWorld().removeObject(this);
                        }
                    }
                    else{
                        if(justEnteredWindow){
                            turnTowards(getWorld().getWidth()/2, getY());
                        }
                        else{
                            if(getY() < 125){
                                setLocation(getX(), getY()+10);
                            }
                            if(getY() > 775){
                                setLocation(getX(), getY()-20);
                            }
                            if(getX() <= 225){
                                setLocation(250 + 20, getY());
                            }
                            if(getX() >= 875){
                                setLocation(850, getY());
                            }
                            if(isAtEdge() || getY() <= 25){
                                getWorld().removeObject(this);
                            }
                        }
                    }
                    move(speed);
                }
                else{

                }

            }
        }

        acts++;
    }

    /**
     * This method has the caller turn towards the closest window
     */
    protected void turnTowardsWindow(){

        ArrayList<Window> windows = (ArrayList<Window>)getWorld().getObjects(Window.class);
        target = windows.get(0);
        closestDistance = getDistance(this, windows.get(0));
        for (Window w : windows)
        {
            if(getDistance(this, w) < closestDistance){
                closestDistance = getDistance(this, w);
                target = w;
            }
        }
        turnTowards(target.getX(), target.getY());

    }

    /**
     * This method gets the closest player within the world
     * @return Human    Returns the closest player
     */
    public Human getClosestPlayer(){
        ArrayList<Human> killList = (ArrayList<Human>)getWorld().getObjects(Human.class);
        humanTarget = killList.get(0);
        closestDistance = getDistance(this, killList.get(0));
        for(Human h : killList){
            if(getDistance(this, h) < closestDistance){
                closestDistance = getDistance(this, h);
                humanTarget = h;
            }
        }
        return humanTarget;
    }

    /**
     * A protected method that uses getObjectAtOffset at the image's corners to check whether it is touching a human
     * Once it does touch a human, the human will take damage depending on the zombie's damage
     */
    protected void checkHitHuman(){
        Human humanTopLeft = (Human)getOneObjectAtOffset((-1*(int)speed) - getImage().getWidth()/2, (-1*(int)speed) - getImage().getHeight()/2, Human.class);
        Human humanTopRight = (Human)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, (-1*(int)speed) - getImage().getHeight()/2, Human.class);
        Human humanBottomLeft = (Human)getOneObjectAtOffset((-1*(int)speed) - getImage().getWidth()/2, (int)speed + getImage().getHeight()/2, Human.class);
        Human humanBottomRight = (Human)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, (int)speed + getImage().getHeight()/2, Human.class);
        if(humanTopLeft != null){
            humanTopLeft.takeDamage((double)damage);
        }
        else if(humanTopRight != null){
            humanTopRight.takeDamage((double)damage);
        }
        else if(humanBottomLeft != null){
            humanBottomLeft.takeDamage((double)damage);
        }
        else if(humanBottomRight != null){
            humanBottomRight.takeDamage((double)damage);
        }
    }

    /**
     * This protected method is used in all the zombie subclasses. It is used when the zombie attempts to break the window. 
     * Once the window takes a certain amount of damage, it will return an integer represeting the stage of health that it's based on
     * 
     */
    protected int attackWindow(){
        if(checkNearWindow()){
            Window w = getNearWindow();
            w.takeDamage(damage);
            return w.getDurability();
        }
        else{
            return -1000;
        }
    }

    /**
     * This method checks whether the caller is near a window or not
     * @return boolean      Returns true if near a building and false if not
     */
    public boolean checkNearWindow(){
        Window w = (Window)getOneObjectAtOffset((int)speed * direction + getImage().getWidth()/2, 0, Window.class);
        if(w != null){
            return true;
        }
        else{
            w = (Window)getOneObjectAtOffset((int)speed * direction - getImage().getWidth()/2, 0, Window.class);
            if(w != null){
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns the method that is is close to
     * @return Window       Returns the window the caller is close to
     */
    public Window getNearWindow(){
        Window w = (Window)getOneObjectAtOffset((int)speed * direction + getImage().getWidth()/2, 0, Window.class);
        if(w != null){
            return w;
        }
        else{
            w = (Window)getOneObjectAtOffset((int)speed * direction - getImage().getWidth()/2, 0, Window.class);
            if(w != null){
                return w;
            }
        }
        return w;
    }

    /**
     * This method returns whether the caller is near a wall or not
     * @return boolean      Returns true if near a wall and false otherwise
     */
    public boolean checkNearWall(){
        Wall w = (Wall)getOneObjectAtOffset((int)speed * direction + getImage().getWidth()/2, 0, Wall.class);
        if(w != null){
            return true;
        }
        else{
            w = (Wall)getOneObjectAtOffset((int)speed * direction - getImage().getWidth()/2, 0, Wall.class);
            if(w != null){
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns the wall that the caller is touching
     * @return Wall     Returns a wall that is close to the caller
     */
    public Wall getNearWall(){
        Wall w = (Wall)getOneObjectAtOffset((int)speed * direction + getImage().getWidth()/2, 0, Wall.class);
        if(w != null){
            return w;
        }
        else{
            w = (Wall)getOneObjectAtOffset((int)speed * direction - getImage().getWidth()/2, 0, Wall.class);
            if(w != null){
                return w;
            }
        }
        return w;
    }

    /**
     * This method returns the health of the caller
     * @return double   Returns the health of the cllaer
     */
    public double getHealth(){
        return health;
    }

    /**
     * This method removes the caller from the world
     */
    public void die()
    {
        alive = false;

        getWorld().removeObject(this);
    }

    /**
     * This method returns the health of the caller
     * @return double   Returns the health of the cllaer
     */
    public double isAlive(){
        return health;
    }

    /**
     * This method adjusts the health of the caller
     * @param Enemydamage       the value of the enemy's damage
     */
    public void takeDamage(double Enemydamage)
    {

        // Damage is reduced by the amount of armour
        health = health - ((double)Enemydamage);
        healthBar.update((int)health);
    }
}
