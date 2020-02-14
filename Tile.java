import java.awt.Color;
import java.util.ArrayList;
import java.util.PriorityQueue;

public abstract class Tile{
    public int value;//number value
    public int xPosition, yPosition;//x position, y position
    public static int size = 120;//size of Tile
    public Color col;//color of Tile
    public int xCoordinate, yCoordinate;//location for animation
    public boolean combinable;//if Box can be combined
    public int textSize;//size of number on box
    
    ArrayList<Color> colors = new ArrayList<Color>();
  
    PriorityQueue<Integer> numbersPQ = new PriorityQueue<Integer>(11);
  
    //abstract methods
    public abstract Color setColor();
    public abstract int setTextSize();
  
    public abstract void display();
  
    public abstract void combineValue();
  
    public abstract int getValue();
    public abstract String toString();
    public abstract int getTextSize();
  
    public abstract boolean isEmpty();
      
    public boolean compare(Tile other){
        return this.value == other.value;
    }
}