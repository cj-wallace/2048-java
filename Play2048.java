public class Play2048
{
    public static void main(String[] args)
    {
        StdDraw.setCanvasSize(640, 840);
        StdDraw.setXscale(0, 640);
        StdDraw.setYscale(840, 0);
        
        Runner game = new Runner();
        
        while (true)
        {
            StdDraw.clear(StdDraw.GRAY);
            game.update();
            game.display();
            StdDraw.show(100);
        }
    }
}
