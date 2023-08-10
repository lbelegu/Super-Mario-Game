import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Coin
{
    //instance variables
    private int x,y, diam;
    private int centerX, centerY;
    private ImageIcon coinImageIcon;
    private Image coinImage;
    
    //Default constructor
    public Coin()
    {
        x = 0;
        y = 0;
        
        diam = 0;
        
        centerX = 0;
        centerY = 0;
        
    }
    
    //Paramaterized Constructor
    public Coin(int argX, int argY)
    {
        x = argX;
        y = argY;
        
        diam = 20;
        
        centerX = x + diam/2;
        centerY = y + diam/2;
        
        coinImageIcon = new ImageIcon(Coin.class.getResource("coin.png"));
        coinImage = coinImageIcon.getImage();
        
    }
    
    //accessor methods
    public int getCx()
    {
        return x;
    }
    
    public int getCy()
    {
        return y;
    }
    
    public int getCenterXCoin()
    {
        return centerX;
    }
    
    
    public int getCenterYCoin()
    {
        return centerY;
    }
    
    public int getDiam()
    {
        return diam;
    }
    
    
    //Method for drawing the coins (right now just a circle)
    public void drawCoins(Graphics g, int argX)
    { 
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.drawImage(coinImage, x + argX, y, diam, diam, null);
        
        //g.setColor(Color.YELLOW);
        //g.fillOval(x + rectX, y, diam, diam);
    }
    
    public void move(int speed)
    {
    
        x += speed;
    }
}