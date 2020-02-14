import java.awt.Font;

public class Runner
{
    Grid grid;
    
    public Runner()
    {
        grid = new Grid();
    }
    
    public void update(){
        if(StdDraw.isKeyPressed(32)){//Space Key
            grid.addTile();
        }
        if(StdDraw.isKeyPressed(38)){//Up key
            grid.resetBoxes();
            //System.out.println(grid.canGoUp());
            if(grid.canGoUp()){
                grid.shiftTilesUp(false);//moves board up
                grid.addTile();//add random EmptyTile
            }
        }
        if(StdDraw.isKeyPressed(40)){//Down key
            grid.resetBoxes();
            //System.out.println(grid.canGoDown());
            if(grid.canGoDown()){
                grid.shiftTilesDown(false);//moves board up
                grid.addTile();//add random EmptyTile
            }
        }
        if(StdDraw.isKeyPressed(37)){//Left key
            grid.resetBoxes();
            //System.out.println(grid.canGoLeft());
            if(grid.canGoLeft()){
                grid.shiftTilesLeft(false);//moves board up
                grid.addTile();//add random EmptyTile
            }
        }
        if(StdDraw.isKeyPressed(39)){//Right key
            grid.resetBoxes();
            //System.out.println(grid.canGoRight());
            if(grid.canGoRight()){
                grid.shiftTilesRight(false);//moves board up
                grid.addTile();//add random EmptyTile
            }
        }
        
        grid.checkStatus();
    }
    
    
    public void display(){
        if(!grid.gameOver)
        {
            grid.display();
            displayTime();
            displayScore();
        }
    }
    
    public void displayTime(){
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.setFont(new Font("Arial",0,30));
        StdDraw.textRight(370,50,"Time: " + grid.totalTime);
    }
    public void displayScore(){
        //Draw text
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.setFont(new Font("Arial",0,30));
        StdDraw.textLeft(50,50,"Score: " + grid.score);
    }
}
