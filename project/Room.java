/**
 * It is seperated rooms for algorithms
 * 
 * @author (Daniel) 
 */
public class Room  
{
    // instance variables - replace the example below with your own
    protected int xLowerRange;
    protected int yLowerRange;
    protected int xHigherRange;
    protected int yHigherRange;
    protected String name;
    /**
     * Constructor for objects of class room
     */
    public Room(int x1, int x2, int y1, int y2, String name)
    {
        this.name = name;
        xLowerRange = x1;
        xHigherRange = x2;
        yLowerRange = y1;
        yHigherRange = y2;
    }
    public boolean getInRange(int x, int y){
        if(xLowerRange <= x && x <= xHigherRange && yLowerRange <= y && y <= yHigherRange){
            return true;
        }
        return false;
    }
    public int[] getXRange(){
        int[] arr = {xLowerRange, xHigherRange};
        return arr;
    }
    public int[] getYRange(){
        int[] arr = {yLowerRange, yHigherRange};
        return arr;
    }
    public String getName(){
        return name;
    }
}
