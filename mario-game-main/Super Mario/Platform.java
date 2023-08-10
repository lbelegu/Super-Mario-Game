import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Platform
{
    private Rectangle plat;
    private ImageIcon platformImageIcon;
    private Image platformImage;
    
    public Platform(int x, int y, int w, int h, int type)
    {
        plat = new Rectangle(x,y,w,h);
        if(type == 1)
        {
            platformImageIcon = new ImageIcon(Platform.class.getResource("ground.png"));
            platformImage = platformImageIcon.getImage();
        }
        else
        {
            platformImageIcon = new ImageIcon(Platform.class.getResource("brick.png"));
            platformImage = platformImageIcon.getImage();
        }
            
    }
    
    public String toString()
    {
        return "plats x = " + getX() + ", y = " + getY();
    }
    
    public int getX()
    {
        return (int)plat.getX();
    }
    
    public int getY()
    {
        return (int)plat.getY();
    }
    
    public int getWidth()
    {
        return (int)plat.getWidth();
    }
    
    public int getHeight()
    {
        return (int)plat.getHeight();
    }
    
    public void drawSelf(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.drawImage(platformImage,(int)plat.getX(),(int)plat.getY(),(int)plat.getWidth(),(int)plat.getHeight(),null);//438
    }
    
    public void move(int speed)
    {
    
        plat.translate(speed, 0);
    }
    
}