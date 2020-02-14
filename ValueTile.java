import java.awt.Color;
import java.awt.Font;

public class ValueTile extends Tile implements Animation{

    int numberQueuePosition;
    
    public ValueTile(int val, int x, int y){
        this.xPosition = x;
        this.yPosition = y;
        xCoordinate = xPosition * 140 + 110;
        yCoordinate = yPosition * 140 + 320;
        
        for(int i = 1; i <= 11; i++){
            numbersPQ.add((Integer)i);
        }
        
        if(val == 2){
            numberQueuePosition = numbersPQ.remove();
        }
        else{
            numbersPQ.remove();
            numberQueuePosition = numbersPQ.remove();
        }
        value = val;
        
        textSize = setTextSize();
        combinable=true;
               
        //ArrayList of possible colors
        colors.add(new Color(229, 219, 169));
        colors.add(new Color(230, 201, 155));
        colors.add(new Color(232, 184, 142));
        colors.add(new Color(234, 166, 129));
        colors.add(new Color(236, 149, 116));
        colors.add(new Color(238, 131, 103));
        colors.add(new Color(240, 114, 89));
        colors.add(new Color(242, 96, 76));
        colors.add(new Color(244, 79, 63));
        colors.add(new Color(246, 61, 50));
        colors.add(new Color(248, 44, 37));
        colors.add(new Color(255, 25, 24));

        col = setColor();
    }
  
    //sets color based on value of box
    public Color setColor(){
        int exponent = (int)(Math.log(value)/Math.log(2));

        if(exponent>11){
            exponent = 11;
        }
        
        return colors.get(exponent);
    }
  
    //sets textSize based on length of number digits
    public int setTextSize(){
        String stringVal = value+"";
        int sLength = stringVal.length();
        if(sLength == 1){return 30;}
        return 2 * (30-((sLength-2)*5));
    }
  
    //draws box around x and y
    public void display(){
        xCoordinate = xPosition * 140 + 110;
        yCoordinate = yPosition * 140 + 320;
        StdDraw.setPenColor(col);
        StdDraw.filledSquare(xCoordinate,yCoordinate,size/2);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setFont(new Font("Arial", 0, setTextSize()));
        StdDraw.text(xCoordinate, yCoordinate, toString());
  }
    
    //doubles box value
    public void combineValue(){
        value = value + value;
        numberQueuePosition = numbersPQ.remove();
        col = setColor();
        textSize = setTextSize();
    }
    
    //get methods
    public int getValue(){return value;}
    public String toString(){return value+"";}
    public int getTextSize(){return textSize;}

    public boolean isEmpty()
    {
        return false;
    }
}