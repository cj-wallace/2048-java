import java.awt.Color;

public class EmptyTile extends Tile implements Animation {
  
    //creates empty box at x and y
    public EmptyTile(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
        xCoordinate = xPosition * 140 + 110;
        yCoordinate = yPosition * 140 + 320;
        value = 0;
        col = Color.LIGHT_GRAY;
        combinable = true;
        textSize = 0;
      
        colors.add(new Color(150));//color is always same
    }
  
    public Color setColor(){
        return colors.get(0);
    }
  
    public int setTextSize(){
        return 30;
    }
  
    //draws box around x and y
    public void display(){
        StdDraw.setPenColor(col);
        StdDraw.filledSquare(xCoordinate,yCoordinate,size/2);
    }
  
    public void combineValue(){}//does nothing because value is 0
  
    //get methods
    public int getValue(){return 0;}
    public String toString(){return "";}
    public int getTextSize(){return textSize;}

    public boolean isEmpty()
    {
        return true;
    }
}