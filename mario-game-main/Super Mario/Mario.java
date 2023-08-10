import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Mario
{
    //instance variables
    private int x,y;
    private int width, height;
    private int vx,vy;
    private boolean jump,left,right, down;
    private int screenWidth, screenHeight;
    private boolean canJump, canFall;
    private double score;
    private boolean floorUnderMe;
    private ImageIcon marioImageIcon1, marioImageIcon2, marioImageIcon3, marioImageIcon4, marioImageIcon5;
    private Image marioImage1, marioImage2, marioImage3, marioImage4, marioImage5;
    private ImageIcon marioImageIcon6, marioImageIcon7, marioImageIcon8, marioImageIcon9, marioImageIcon10;
    private Image marioImage6, marioImage7, marioImage8, marioImage9, marioImage10;
    private long runningTime;
    
    //Default Constructor
    public Mario(int argX, int argY, int argScreenWidth, int argScreenHeight)
    {
        x = argX;
        y = argY;
        screenWidth = argScreenWidth;
        screenHeight = argScreenHeight;
        
        vx = 0;
        vy = 0;
        
        width = 34; 
        height = 50; 
        
        canJump = false; //false because the player is starting the in air for now at least
        canFall = false;
        
        left = false;
        right = false;
        down = false;
        floorUnderMe = true;
        score = 0;
        
        runningTime = 0;
        
        //RIGHT IMAGES
        marioImageIcon1 = new ImageIcon(Mario.class.getResource("rightIdle.png"));
        marioImage1 = marioImageIcon1.getImage();
        
        marioImageIcon2 = new ImageIcon(Mario.class.getResource("rightRunning1.png"));
        marioImage2 = marioImageIcon2.getImage();
        
        marioImageIcon3 = new ImageIcon(Mario.class.getResource("rightRunning2.png"));
        marioImage3 = marioImageIcon3.getImage();
        
        marioImageIcon4 = new ImageIcon(Mario.class.getResource("rightRunning3.png"));
        marioImage4 = marioImageIcon4.getImage();
        
        marioImageIcon5 = new ImageIcon(Mario.class.getResource("rightRunning4.png"));
        marioImage5 = marioImageIcon5.getImage();
        
        //LEFT IMAGES
        marioImageIcon6 = new ImageIcon(Mario.class.getResource("leftIdle.png"));
        marioImage6 = marioImageIcon6.getImage();
        
        marioImageIcon7 = new ImageIcon(Mario.class.getResource("leftRunning1.png"));
        marioImage7 = marioImageIcon7.getImage();
        
        marioImageIcon8 = new ImageIcon(Mario.class.getResource("leftRunning2.png"));
        marioImage8 = marioImageIcon8.getImage();
        
        marioImageIcon9 = new ImageIcon(Mario.class.getResource("leftRunning3.png"));
        marioImage9 = marioImageIcon9.getImage();
        
        marioImageIcon10 = new ImageIcon(Mario.class.getResource("leftRunning4.png"));
        marioImage10 = marioImageIcon10.getImage();        
    }
    
    public void setFloorUnderMe(boolean f)
    {
        floorUnderMe = f;
    }
    
    //accessor methods
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public int getXandWidth()
    {
        return (x + width);
    }
        
    
    public int getVx()
    {
        return vx;
    }
    
    public boolean getRight()
    {
        return right;
    }
    
    public double getScore()
    {
        return score;
    }
    
    //Methods for Marios control
    public void setJump(boolean argJump)
    {
        jump = argJump;
    }
    
    public void setLeft(boolean argLeft)
    {
        left = argLeft;
    }
    
    public void setRight(boolean argRight)
    {
        right = argRight;
    }
    
    public void setDown(boolean argDown)
    {
        down = argDown;
    }
    
    public void setVX(int vxP)
    {
        vx = vxP;
    }
    
    public void setVY(int vyP)
    {
        vy = vyP;
    }
    
    public void setCanFall(boolean argFall)
    {
        canFall = argFall;
    }
    
    //Method for drawing Mario (right now just a rectangle)
    public void drawMario(Graphics g, long currentTime)
    { 
        Graphics2D g2d = (Graphics2D)g;
        
        if(!right && !left)
            g2d.drawImage(marioImage1, x, y, width, height, null);
        else if(right)
        {
            if(runningTime + 100 > currentTime)
                g2d.drawImage(marioImage2, x, y, width, height, null);
            else if(runningTime + 200 > currentTime)
                g2d.drawImage(marioImage3, x, y, width, height, null);
            else if(runningTime + 300 > currentTime)
                g2d.drawImage(marioImage4, x, y, width, height, null);
            else if(runningTime + 400 > currentTime)
                g2d.drawImage(marioImage5, x, y, width, height, null);
            else
            {
                runningTime = currentTime;
                g2d.drawImage(marioImage5, x, y, width, height, null);
            }
        }
        else if(left)
        {
            if(runningTime + 100 > currentTime)
                g2d.drawImage(marioImage6, x, y, width, height, null);
            else if(runningTime + 200 > currentTime)
                g2d.drawImage(marioImage7, x, y, width, height, null);
            else if(runningTime + 300 > currentTime)
                g2d.drawImage(marioImage8, x, y, width, height, null);
            else if(runningTime + 400 > currentTime)
                g2d.drawImage(marioImage9, x, y, width, height, null);
            else if(runningTime + 500 > currentTime)
                g2d.drawImage(marioImage10, x, y, width, height, null);
            else
            {
                runningTime = currentTime;
                g2d.drawImage(marioImage10, x, y, width, height, null);
            }
        }  
        //g.setColor(Color.RED);
        //g.fillRect(x, y, 25, 50);
    }
    
    //Method for marios movement
    public void movement(Platform[] plats)
    {
        x += vx;
        y += vy;
        
        if(right)
        {
            if(x+30 <= (screenWidth/2)) 
                x += 5;
        }
        
        if(left)
        {
            if(x-5 >= 0) 
                x -= 5;
        }
        Platform platUnderMe = getPlatformUnderMe(plats);
        //System.out.println("plat under me " + platUnderMe);
        
        
        if(platUnderMe != null)
            floorUnderMe = true;
        else
            floorUnderMe = false;
        
        //System.out.println("floor Under ME " + floorUnderMe);
        // System.out.println("y for floorunder me " + platUnderMe.getY());
        
        if(vy > 0 && floorUnderMe && y + height >= platUnderMe.getY() )//stopping him from hitting the floor
        {
            y = platUnderMe.getY() - height;
            canJump = true;
            vy = 3;
        }
        else
        {
            canJump = false;
            if(down)
                vy += 3;
            else
                vy++;
        }    
    }
    
    //distance method
    private double distance(int x1, int y1, int x2, int y2)
    {
        double ans = Math.sqrt((Math.pow((x2-x1),2) + (Math.pow((y2-y1), 2))));
        return ans;
    }
    
    //Method to give the player points when collecting coins
    public boolean collect(Coin coin, int rectX) 
    {
        //checking to see if the distance between the centers is less than the radius of the coin and the "radius" of the rectangle
        for(int i = x; i <= x + width; i++)
        {
            for(int j = y; j <= y + height; j++)
            {
                if((distance(i,j,coin.getCenterXCoin() + rectX,coin.getCenterYCoin()) < coin.getDiam()/2))
                {
                    score += 10; 
                    
                    return true;
                    
                }
            }
        }
        return false;
    }
    
    public boolean canJump()
    {
        return canJump;
    }
    
    public void jump()
    {
        vy = -15;
    }
            
    //helper method that c hecks if a specific platform is under me
    private boolean platUnderMe(Platform p)
    {
        if(y  < p.getY()) //I am above it
        {
            if(x >= p.getX() && x <= p.getX() + p.getWidth() || x + width >= p.getX() && x + width <= p.getX()+p.getWidth())
                return true;
            else
                return false;
        }
        else 
            return false;
    }
    
    public Platform getPlatformUnderMe(Platform[] plats)
    {
        int count = 0;
        for(int i = 0; i < plats.length; i ++)
        {
            Platform curr = plats[i];
            if(platUnderMe(curr))
                count++;
        }
        //at this moment I know how many platforms are under me
        
        if(count == 0)
            return null; //NO PLATFORM IS UNDER ME, I SHOULD FALL AHHHH
        else 
        {
            Platform[] platsUnderMe = new Platform[count];
            int index = 0;
            for(int i = 0; i < plats.length; i ++)
            {
                Platform curr = plats[i];
                if(platUnderMe(curr))
                {
                    platsUnderMe[index] = curr;
                    index++;
                }
            }
            //at this moment I have all of the platforms under me in a new array
            if(platsUnderMe.length == 1)
                return platsUnderMe[0];
            else
            {
                Platform min = platsUnderMe[0];
                for(int i = 0; i < platsUnderMe.length; i ++)
                {
                    if(platsUnderMe[i].getY() < min.getY())//the curr platform is higher
                        min = platsUnderMe[i];
                }
                return min;
            }
        }
        
    }
    
    //method to check if there is a goomba under Mario
    public boolean squishGoomba(Goomba goomba)
    {
         for(int i = x; i <= x + width; i++)
         {
            // checking the distance between all the points at the bottom of mario to the center of goomba
            if(distance(i, y+height, (goomba.getX() + goomba.getDiam()/2),(goomba.getY() + goomba.getDiam()/2)) < (goomba.getDiam()/2))
            {
                score += 20;
                return true;
            }     
         }
         return false;
    }
    
    //method that will end the game when mario interacts with the goomba
    public boolean goombaEndGame(Goomba goomba)
    {
        for(int i = x; i <= x + width; i++)
        {
            for(int j = y; j < y + height; j++)
            {

                if((distance(i,j, (goomba.getX() + goomba.getDiam()/2 ), (goomba.getY()) + goomba.getDiam()/2) < goomba.getDiam()/2))
                {
                    return true;
                }
            }
        }
        return false;
    }
}