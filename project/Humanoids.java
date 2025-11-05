import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * @author: Daniel, Grapics: Jacob
 */

public class Humanoids extends SuperSmoothMover
{
    //each individual array refers to a room in the building with 0 being the hallway and odd indexes being left rooms going down and even being right rooms going down
    //First 2 values being the x range of the rooms so the 0th array x range would be 450-650
    //The 3rd and 4th values representing the y range of the rooms so for the 0th array would be a y range of 0-717

    protected double speed;
    protected double health;
    protected boolean movingToTargetRoom = false;
    protected Window target;
    protected double closestDistance;
    protected String higherLower;
    protected Room startingRoom;
    protected int targetYOffSet = 50;
    protected int acts = 0;
    Room hallWay = new Room (475,625,100,817, "hallWay");
    Room r1 = new Room(200,475,100,320, "r1");
    Room r2 = new Room(625,900,100,320, "r2");
    Room r3 = new Room(200,475,320,600, "r3");
    Room r4 = new Room(625,900,320,600, "r4");
    Room r5 = new Room(200,475,600,817, "r5");
    Room r6 = new Room(625,900,600,817, "r6");
    protected Room[]rooms = {hallWay, r1, r2, r3, r4, r5, r6};

    protected GreenfootImage leftImage;
    protected GreenfootImage rightImage;

    public void act() 
    {
        // Add your action code here.
    }    

    /**
     * This method sets if the image should be facing left or right depending on it's rotation (90 - > 0 or 270 - > 360 right, anything else left)
     */
    protected void setImageRotation(){
        if(getRotation() < 90 || getRotation() > 270){
            setImage(rightImage);
        }
        else{
            setImage(leftImage);
        }
    }

    /**
     * This method takes the given actor and returns which room it is in
     * @param a     An actor where you want to get which room it's in
     * @return Room     Returns an object of room which the actor is in
     */
    protected Room getRoom(Actor a){
        for(int i = 0; i < rooms.length; i++){
            if(rooms[i].getInRange(a.getX(),a.getY())){
                return rooms[i];
            }
        }
        return rooms[0];
    }

    /**
     * This method checks whether the given actor is within the domain and range of the rooms
     * @param a     An actor where you want to check whether it is in a room or not
     * @return boolean      Returns true if it is in a room and false if not
     */
    protected boolean getInRoom(Actor a){
        for(int i = 0; i < rooms.length; i++){
            if(rooms[i].getInRange(a.getX(),a.getY())){
                return true;
            }
        }
        return false;
    }

    /**
     * This method goes into a series of if statements to have the actor which calls this move to a certain actor
     * @param goal      An actor where the actor called wants to move to
     * 
     */
    protected void moveToTarget(Actor goal){
        startingRoom = getRoom(this);
        if(getInRoom(this) && getInRoom(goal)){

            //Checks if their in the same room or not and does a different scenario whether they are in a room or hallway
            if(getRoom(this) != getRoom(goal) && getRoom(this) != hallWay){
                startingRoom = getRoom(this);

                //Has the actor turn towards the doorway according to the room
                if(getRoom(this).getXRange()[0] < getWorld().getWidth()/2){
                    turnTowards(getRoom(this).getXRange()[1],getRoom(this).getYRange()[1]);
                }
                else{
                    turnTowards(getRoom(this).getXRange()[0],getRoom(this).getYRange()[1]);
                }

                Wall w = (Wall)getOneObjectAtOffset(0, getImage().getHeight()/2 + (int)speed, Wall.class);
                //moves until it's at the wall near the doorway
                if(w != null){
                    turnTowards(getWorld().getWidth()/2,getY());
                }
            }
            else if(getRoom(this) == hallWay && getRoom(goal) != hallWay){
                if(!checkWallVertically()){
                    if(!movingToTargetRoom){
                        //If it's a left room
                        if(getRoom(goal).getXRange()[0] < getWorld().getWidth()/2){
                            //If the room is across from them
                            if(goal != null && startingRoom.getYRange()[1] == getRoom(goal).getYRange()[1]){
                                turnTowards(getRoom(goal).getXRange()[1]+25,getRoom(goal).getYRange()[1]-targetYOffSet);
                                movingToTargetRoom = true;
                            }
                            else{
                                turnTowards(getRoom(goal).getXRange()[1]+25,getRoom(goal).getYRange()[1]-targetYOffSet);
                                movingToTargetRoom = true;

                            }
                        }
                        //If it's a right room
                        else{
                            //If the room is across from them
                            if(goal != null && startingRoom.getYRange()[1] == getRoom(goal).getYRange()[1]){
                                turnTowards(getRoom(goal).getXRange()[0]-25,getRoom(goal).getYRange()[1]-targetYOffSet);
                                movingToTargetRoom = true;
                            }
                            //Anything Otherwise
                            else{
                                turnTowards(getRoom(goal).getXRange()[0]-25,getRoom(goal).getYRange()[1]-targetYOffSet);
                                movingToTargetRoom = true;
                            }

                        }
                    }
                    if(Math.abs(getY() - (getRoom(goal).getYRange()[1]-targetYOffSet)) <= speed && movingToTargetRoom){
                        if(getRoom(goal).getXRange()[0] < getWorld().getWidth()/2){
                            turnTowards(getRoom(goal).getXRange()[0],getRoom(goal).getYRange()[1]-targetYOffSet);
                        }
                        else{
                            turnTowards(getRoom(goal).getXRange()[1],getRoom(goal).getYRange()[1]-targetYOffSet);
                        }
                        movingToTargetRoom = true;
                    }
                    else{
                        movingToTargetRoom = false;
                    }
                }
            }
            else{
                startingRoom = getRoom(this);
                turnTowards(goal);
            }
            move(speed);
        }
    }

    /**
     * This method returns an actor that the current actor has set to as target and wants to move to
     * @return Actor    returns the actor that the caller is targetting
     */
    public Actor getTarget(){
        return target;
    }

    /**
     * This method checks whether the actor is touching a wall from above or below
     * @return boolean      Returns true if it is touching a wall and false otherwise
     */
    protected boolean checkWallVertically(){
        Wall topLeft = (Wall)getOneObjectAtOffset(-(int)speed - getImage().getWidth()/2, -(int)speed - getImage().getHeight()/2, Wall.class);
        Wall topRight = (Wall)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, -(int)speed - getImage().getHeight()/2, Wall.class);
        Wall bottomLeft = (Wall)getOneObjectAtOffset(-(int)speed - getImage().getWidth()/2, (int)speed + getImage().getHeight()/2, Wall.class);
        Wall bottomRight = (Wall)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, (int)speed + getImage().getHeight()/2, Wall.class);
        return topLeft != null && topRight != null && bottomLeft != null && bottomRight != null;
    }

    /**
     * This method checks whether the actor is near a window or not
     * @return boolean      Returns true if it is near a window and false otherwise
     */
    protected boolean checkNearWindow(){
        Window topLeft = (Window)getOneObjectAtOffset(-(int)speed - getImage().getWidth()/2, -(int)speed - getImage().getHeight()/2, Window.class);
        Window topRight = (Window)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, -(int)speed - getImage().getHeight()/2, Window.class);
        Window bottomLeft = (Window)getOneObjectAtOffset(-(int)speed - getImage().getWidth()/2, (int)speed + getImage().getHeight()/2, Window.class);
        Window bottomRight = (Window)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, (int)speed + getImage().getHeight()/2, Window.class);
        return topLeft != null || topRight != null || bottomLeft != null || bottomRight != null;
    }

    /**
     * This method returns an actor which is the closest window from the actor
     * @return Window   The window which is closest to the actor
     */    
    protected Window getNearestWindow()
    {
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
        return target;
    }

    /**
     * This method has the actor turn towards the closest window
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
     * This method is a basic take damage method which adjusts the actor's health to the damage taken
     * @param Enemydamage   The enemy's damage
     */
    protected void takeDamage(double Enemydamage){
        health -= Enemydamage;

    }

    /**
     * Static method that gets the distance between the x,y coordinates of two Actors
     * using Pythagorean Theorum.
     *
     * @param a     First Actor
     * @param b     Second Actor
     * @return float
     */
    public float getDistance (Actor a, Actor b)
    {
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return (float)distance;
    }
}
