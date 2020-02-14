import java.awt.Font;
public class Grid implements Animation{
    private Tile[][] grid;//2d array of Boxes
    public int score, gridLength;//score, length
    long startTime, totalTime;//for keeping track of time
    Grid testGrid;//for testing grid moves
    boolean gameOver;//whether the game is over or not
    
    int numberToWin = 2048;//number tile required to win
  
  
    //new Grid
    public Grid(){
        gridLength = 4;
    
        grid = new Tile[gridLength][gridLength];
    
        score = 0;
        startTime = System.currentTimeMillis();
        
        gameOver = false;
        
        //creates new grid of empty tiles
        for(int x = 0; x < gridLength; x++){
            for(int y = 0; y < gridLength; y++){
                grid[x][y] = new EmptyTile(x,y);
            }
        }
        
        //adds beginning numbers in random locations
        addTile();
        addTile();
    }
  
    //copy Board for testing Board(other Board to copy)
    public Grid(Grid other){
    gridLength = other.gridLength;
    grid = new Tile[gridLength][gridLength];
    score = other.score;
    
    //copies all of the other grid to new grid
    for(int x = 0; x < gridLength; x++){
        for(int y = 0; y < gridLength; y++){
            if(other.grid[x][y] instanceof EmptyTile)
                grid[x][y] = new EmptyTile(x,y);
            else
                grid[x][y] = new ValueTile(other.grid[x][y].value,x,y);
            }
        }
    }
  
    //move grid up
    public void shiftTilesUp(boolean isTest){
        for(int y = 1; y< grid[0].length; y++){
            for(int p=y; p>0; p--){
                for(int x = 0; x < grid.length; x++){
                    compare(x,p,0,-1);
                }
                if(!isTest){
                    display();
                    StdDraw.show(20);
                }
            }
        }
    }
  
    //move grid down
    public void shiftTilesDown(boolean isTest){
        for(int y = grid[0].length-2; y>=0; y--){
            for(int p=y; p<grid[0].length-1; p++){
                for(int x = 0; x < grid.length; x++){
                    compare(x,p,0,1);
                }
                if(!isTest){
                    display();
                    StdDraw.show(20);
                }
            }
        }
    }
  
    //move grid left
    public void shiftTilesLeft(boolean isTest){
        for(int x = 1; x < grid.length; x++){
            for(int p = x; p > 0; p--){
                for(int y = 0; y < grid[0].length; y++){
                    compare(p,y,-1,0);
                }
                if(!isTest){
                    display();
                    StdDraw.show(20);
                }
            }
        }
    }
  
    //move grid right
    public void shiftTilesRight(boolean isTest){
        for(int x = grid.length-2; x >= 0; x--){
            for(int p = x; p < grid.length-1; p++){
                for(int y = 0; y < grid[0].length; y++){
                    compare(p,y,1,0);
                }
                if(!isTest){
                    display();
                    StdDraw.show(20);
                }
            }
        }
    }
  
    //returns true if boards are the same, false if different
    public boolean compareBoards(Grid other){
        for(int x=0; x< grid.length; x++){
            for(int y=0; y< grid.length; y++){
                if(!(this.grid[x][y].compare(other.grid[x][y]))){
                    return false;
                }
            }
        }
        return true;
    }
  
    //draws board on screen
    public void display(){
        //draw boxes
        for(int x = 0; x < grid.length; x++){
            for(int y = 0; y < grid[x].length; y++){
                grid[x][y].xPosition = x;
                grid[x][y].yPosition = y;
                grid[x][y].display();//draw box
            }
        }
    }
  
    //drop new number in a random location
    public void addTile(){
        if(!boardFull()){
            int x,y;
            do{
                x = (int)(Math.random()*gridLength);
                y = (int)(Math.random()*gridLength);
            }while(grid[x][y] instanceof ValueTile);
         
            int number = 2;
            if(Math.random()>.5){
                number = 4;
            }
        
            grid[x][y] = new ValueTile(number,x,y);
        }
    } 
  
    //used with up, down, left, right to shift and combine Boxes
    public void compare(int x, int y, int xIncrement, int yIncrement){
        if(grid[x][y] instanceof ValueTile){
            if(grid[x+xIncrement][y+yIncrement].combinable && grid[x][y].compare(grid[x+xIncrement][y+yIncrement])){
                grid[x][y] = new EmptyTile(x,y);//replace old tile
                grid[x+xIncrement][y+yIncrement].combinable = false;
                grid[x+xIncrement][y+yIncrement].combineValue();//add [x][y] and [x+xDir][y+yDir]
                score += grid[x+xIncrement][y+yIncrement].getValue();
            }
            else if(grid[x+xIncrement][y+yIncrement] instanceof EmptyTile){//if next tile is empty
                grid[x+xIncrement][y+yIncrement] = grid[x][y];//next tile equals this tile
                grid[x][y] = new EmptyTile(x,y);//replace old tile
            }
            else{
                if(xIncrement>0 && x+xIncrement < gridLength){
                    grid[x+xIncrement][y+yIncrement].combinable = false;
                }
                else if(xIncrement<0 && x+xIncrement >= 0){
                    grid[x+xIncrement][y+yIncrement].combinable = false;
                }
                else if(yIncrement>0 && y+yIncrement < gridLength){
                    grid[x+xIncrement][y+yIncrement].combinable = false;
                }
                else if(yIncrement<0 && y+yIncrement >= 0){
                    grid[x+xIncrement][y+yIncrement].combinable = false;
                }
            }
        }
    }
  
    //returns true if board is full
    public boolean boardFull(){
        boolean isFull = true;
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] instanceof EmptyTile){isFull = false;}
            }
        }
        return isFull;
    }
  
    //return true if 2048 is in array
    public boolean won(){
        for(int x = 0; x<gridLength;x++){
            for(int y = 0; y<gridLength; y++){
                if(grid[x][y].value==numberToWin)
                    return true;
            }
        }
        return false;
    } 
  
    //resets boxes to make them collapsable
    public void resetBoxes(){
        for(int x = 0; x < grid.length; x++){
            for(int y = 0; y < grid[x].length; y++){
                grid[x][y].combinable = true;
            }
        }
    }
  
    //check can go up
    public boolean canGoUp(){
        testGrid = new Grid(this);
        testGrid.shiftTilesUp(true);
        return !compareBoards(testGrid);
    }
  
    //check can go down
    public boolean canGoDown(){
        testGrid = new Grid(this);
        testGrid.shiftTilesDown(true);
        return !compareBoards(testGrid);
    }
  
    //check can go left
    public boolean canGoLeft(){
        testGrid = new Grid(this);
        testGrid.shiftTilesLeft(true);
        return !compareBoards(testGrid);
    }
  
    //check can go right
    public boolean canGoRight(){
        testGrid = new Grid(this);
        testGrid.shiftTilesRight(true);
        return !compareBoards(testGrid);
    }
  
    public void checkStatus(){
        if(!gameOver){
            totalTime = (System.currentTimeMillis() - startTime)/1000;
        }
        if(won()){
            gameOver = true;
            StdDraw.clear(StdDraw.DARK_GRAY);
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.filledSquare(320, 420, 320);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.setFont(new Font("Arial",0,30));
            StdDraw.text(320, 420, "YOU WIN");
            StdDraw.text(320, 450, "Score: " + score);
            StdDraw.text(320, 480, "Time: " + totalTime);

        }
        if(boardFull()){
            if(!checkCanCombine()){
                gameOver = true;
                StdDraw.clear(StdDraw.DARK_GRAY);
                StdDraw.setPenColor(StdDraw.DARK_GRAY);
                StdDraw.filledSquare(320, 420, 320);
                StdDraw.setPenColor(StdDraw.WHITE);
                StdDraw.setFont(new Font("Arial",0,30));
                StdDraw.text(320, 420, "YOU LOSE");
                StdDraw.text(320, 450, "Score: " + score);
                StdDraw.text(320, 480, "Time: " + totalTime);
            }
        }
    }
    private boolean checkCanCombine()
    {
        return (canGoUp()||canGoDown()||canGoLeft()||canGoRight());
    }
}
