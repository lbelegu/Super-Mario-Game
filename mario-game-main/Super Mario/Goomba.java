import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Goomba
{
    //instance variables
    private int x,y,diam;
    private int vx;
    private boolean facingRight;
    private ImageIcon goombaImageIcon1, goombaImageIcon2, goombaImageIcon3, goombaImageIcon4, goombaImageIcon5, goombaImageIcon6;
    private Image goombaImage1, goombaImage2, goombaImage3, goombaImage4, goombaImage5, goombaImage6; 
    private long goombaWalkTime;
    private Platform currentPlat;
    
    //Paramaterized Constructor
    public Goomba(Platform argPlat, int loc) // int loc represents the goombas location so that they dont spawn on top of each other
    {
        currentPlat = argPlat;
        if(loc == 0)
            x = currentPlat.getX() + currentPlat.getWidth()/4;
        else if(loc == 1)
            x = currentPlat.getX() + currentPlat.getWidth()/2;
        else if (loc == 2)
            x = currentPlat.getX() + 3*currentPlat.getWidth()/4;
        
        diam = 25;
        
        y = currentPlat.getY() - diam;
        
        vx = 3;
        
        facingRight = false;
        
        goombaWalkTime = 0;
        
        
        
        //RIGHT GOOMBA
        goombaImageIcon1 = new ImageIcon(Goomba.class.getResource("rightGoomba1.png"));
        goombaImage1 = goombaImageIcon1.getImage();
        
        goombaImageIcon2 = new ImageIcon(Goomba.class.getResource("rightGoomba2.png"));
        goombaImage2 = goombaImageIcon2.getImage();
        
        goombaImageIcon3 = new ImageIcon(Goomba.class.getResource("rightGoomba3.png"));
        goombaImage3 = goombaImageIcon3.getImage();
        
        //LEFT GOOMBA
        goombaImageIcon4 = new ImageIcon(Goomba.class.getResource("leftGoomba1.png"));
        goombaImage4 = goombaImageIcon4.getImage();
        
        goombaImageIcon5 = new ImageIcon(Goomba.class.getResource("leftGoomba2.png"));
        goombaImage5 = goombaImageIcon5.getImage();
        
        goombaImageIcon6 = new ImageIcon(Goomba.class.getResource("leftGoomba3.png"));
        goombaImage6 = goombaImageIcon6.getImage();
    }
    
    //accessor method
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getVx()
    {
        return vx;
    }
    
    public int getDiam()
    {
        return diam;
    }
    
    
    //method for drawing goomba
    public void drawGoomba(Graphics g, long currentTime)
    {
        //g.setColor(Color.ORANGE);
        //g.fillOval(x + argX, y, diam, diam);
        
        Graphics2D g2d = (Graphics2D)g;
        
        if(facingRight)
        {
            if(goombaWalkTime + 200 > currentTime)
                g2d.drawImage(goombaImage1, x, y, diam, diam, null);
            else if(goombaWalkTime + 300 > currentTime)
                g2d.drawImage(goombaImage2, x, y, diam, diam, null);
            else if(goombaWalkTime + 400 > currentTime)
                g2d.drawImage(goombaImage3, x, y, diam, diam, null);
            else
            {
                goombaWalkTime = currentTime;
                g2d.drawImage(goombaImage3, x, y, diam, diam, null);
            }
        }
        else
        {
            if(goombaWalkTime + 200 > currentTime)
                g2d.drawImage(goombaImage4, x, y, diam, diam, null);
            else if(goombaWalkTime + 300 > currentTime)
                g2d.drawImage(goombaImage5, x, y, diam, diam, null);
            else if(goombaWalkTime + 400 > currentTime)
                g2d.drawImage(goombaImage6, x, y, diam, diam, null);
            else
            {
                goombaWalkTime = currentTime;
                g2d.drawImage(goombaImage6, x, y, diam, diam, null);
            }
        }
    }
    
    //method to make the array of goombas move to the left 
    public void move(int speed)
    {
        x += speed;
    }
    
    //method for goomba movement
    public void movement() //shtuff in param are the platforms info
    {
        //goombas movement is based on which way goomba is facing
        if(facingRight)
            move(2);
        else
            move(-2);
        
        if(facingRight && x+diam > currentPlat.getX() + currentPlat.getWidth())
            facingRight = false;
        else if(!facingRight && x < currentPlat.getX())
            facingRight = true;
    }
    
    //methoid to change the goombas values to 0 once its squished
    public void killGoomba()
    {
        x = 0;
        y = 0;
        vx = 0;
        diam = 0;
    }
   
}