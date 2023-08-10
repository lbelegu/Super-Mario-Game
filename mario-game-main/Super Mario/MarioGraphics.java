import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.applet.*;

public class MarioGraphics extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{
    //instance variables
    private int WIDTH;
    private int HEIGHT;
    private int rectX,rectY, rectWidth;
    private Mario mario;
    private int rectVx;
    private ImageIcon groundImageIcon;
    private Image groundImage;
    private ImageIcon skyImageIcon, smallCloudImageIcon, bigCloudImageIcon;
    private Image skyImage, smallCloudImage, bigCloudImage;
    private Coin coin;
    private Coin[] coins;
    private Goomba goomba;
    private Goomba goomba2;
    private Goomba[] goombas;
    private long startTime, currentTime;
    private Platform floor1;
    private Platform[] allPlats;
    private boolean gameOver;
    private int cloudX, cloudY, cloudW, cloudH, cloudVX;
    private int bigCloudX, bigCloudY, bigCloudW, bigCloudH;
    private int cloudCounter;
    private int level, coinCounter;
    private ImageIcon introImageIcon, flagpoleImageIcon, arrowImageIcon;
    private Image introImage, flagpoleImage, arrowImage;
    private ImageIcon coinImageIcon;
    private Image coinImage;
    private ImageIcon smallBushImageIcon, midBushImageIcon, bigBushImageIcon;
    private Image smallBushImage, midBushImage, bigBushImage;
    private AudioClip themeSong, deathSong, winningSong, levelUpSong, coinEffect, goombaEffect;
    
    
    //Default Constructor
    public MarioGraphics()
    {
        //initializing instance variables
        WIDTH = 1000;
        HEIGHT = 500;
        
        rectX = 0;
        rectY = 400;
        rectVx = 0;
        rectWidth = WIDTH+300;
        //floor1 = new Platform(0, 400, rectWidth, HEIGHT-rectY, 2);
        
        cloudX = WIDTH + 50;
        cloudY = 50;
        cloudW = 149;
        cloudH = 100;
        cloudVX = -2;
        
        bigCloudX = WIDTH + 50;
        bigCloudY = 50;
        bigCloudW = 219;
        bigCloudH = 108;
        cloudCounter = 0;
        level = 1;
        coinCounter = 0;
        
        //groundImageIcon = new ImageIcon(MarioGraphics.class.getResource("ground.jpg"));
        //groundImage = groundImageIcon.getImage();
        
        skyImageIcon = new ImageIcon(MarioGraphics.class.getResource("Sky.jpeg"));
        skyImage = skyImageIcon.getImage();
        
        smallCloudImageIcon = new ImageIcon(MarioGraphics.class.getResource("smallCloud.png"));
        smallCloudImage = smallCloudImageIcon.getImage();
        
        bigCloudImageIcon = new ImageIcon(MarioGraphics.class.getResource("bigCloud.png"));
        bigCloudImage = bigCloudImageIcon.getImage();
        
        introImageIcon = new ImageIcon(MarioGraphics.class.getResource("intro.jpg"));
        introImage = introImageIcon.getImage();
        
        flagpoleImageIcon = new ImageIcon(MarioGraphics.class.getResource("flagpole.png"));
        flagpoleImage = flagpoleImageIcon.getImage();
        
        arrowImageIcon = new ImageIcon(MarioGraphics.class.getResource("arrow.png"));
        arrowImage = arrowImageIcon.getImage();
        
        coinImageIcon = new ImageIcon(MarioGraphics.class.getResource("coin.png"));
        coinImage = coinImageIcon.getImage();
        
        smallBushImageIcon = new ImageIcon(MarioGraphics.class.getResource("smallBush.png"));
        smallBushImage = smallBushImageIcon.getImage();
        
        midBushImageIcon = new ImageIcon(MarioGraphics.class.getResource("midBush.png"));
        midBushImage = midBushImageIcon.getImage();
        
        bigBushImageIcon = new ImageIcon(MarioGraphics.class.getResource("bigBush.png"));
        bigBushImage = bigBushImageIcon.getImage();
        
        
        startTime = System.currentTimeMillis();
        currentTime = 0;
        
        gameOver = false;
        createObjects();
        
        themeSong = Applet.newAudioClip(this.getClass().getResource("themeSong.wav"));
        deathSong = Applet.newAudioClip(this.getClass().getResource("died.wav"));
        winningSong = Applet.newAudioClip(this.getClass().getResource("winning.wav"));
        levelUpSong = Applet.newAudioClip(this.getClass().getResource("levelUp.wav"));
        coinEffect = Applet.newAudioClip(this.getClass().getResource("coin.wav"));
        goombaEffect = Applet.newAudioClip(this.getClass().getResource("goomba.wav"));
        themeSong.loop();
     
        //Setting up the GUI
        JFrame gui = new JFrame(); //this makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes sure program can close
        gui.setTitle("* SUPER MARIO *"); //This is the title of the game, you can change it
        gui.setPreferredSize(new Dimension(WIDTH, HEIGHT)); //setting the size for the gui
        gui.setResizable(false); //makes sure the gui cannot be changed
        gui.getContentPane().add(this); //adding this class to the gui

        /*If after you finish everything, you can declare your buttons or other things
         *at this spot. AFTER gui.getContentPane().add(this) and BEFORE gui.pack();
         */

        gui.pack(); //packs everything together
        gui.setLocationRelativeTo(null); //makes so the gui opens in the center of the screen
        gui.setVisible(true); //makes the gui visible
        gui.addKeyListener(this); //stating that the object will listen to the keyboard
        gui.addMouseListener(this); //stating that the object will listen to the mouse
        gui.addMouseMotionListener(this); //stating that the object will acknowledge when tne mouse moves 
        
    }
    
    //accessor methods to get the info on the rectangles position/speed
    public int getRectX()
    {
        return rectX;
    }
    
    public int getRectY()
    {
        return rectY;
    }
    
    public int getRectVx()
    {
        return rectVx;
    }
    
    //All your UI drawing goes in here
    public void paintComponent(Graphics g)
    {
        if(!gameOver)
        {
            if(level != 5)
            {
                Graphics2D g2d = (Graphics2D)g;

                //drawing the sky
                g2d.drawImage(skyImage, 0, 0, WIDTH, HEIGHT, null);

                //drawing the clouds
                g2d.drawImage(smallCloudImage, cloudX, cloudY, cloudW, cloudH, null);

                g2d.drawImage(bigCloudImage, bigCloudX, bigCloudY, bigCloudW, bigCloudH, null) ;

                //wooden sign
                g2d.drawImage(arrowImage, 10 + allPlats[0].getX(), 315, 90, 105, null);
                
                //Super Mario Sign
                g2d.drawImage(introImage, 150 + allPlats[0].getX(), 50, 300, 150, null);
                
                //small bushes
                g2d.drawImage(smallBushImage, 900 + allPlats[0].getX(), 230, 300, 300, null);
                g2d.drawImage(smallBushImage, 2250 + allPlats[0].getX(), 230, 300, 300, null);
                g2d.drawImage(smallBushImage, 5300 + allPlats[0].getX(), 230, 300, 300, null);
                g2d.drawImage(smallBushImage, 5680 + allPlats[0].getX(), 230, 300, 300, null);
                g2d.drawImage(smallBushImage, 6550 + allPlats[0].getX(), 230, 300, 300, null);
                
                //medium bushes
                g2d.drawImage(midBushImage, 300 + allPlats[0].getX(), 230, 300, 300, null);
                g2d.drawImage(midBushImage, 2550 + allPlats[0].getX(), 230, 300, 300, null);
                g2d.drawImage(midBushImage, 4850 + allPlats[0].getX(), 230, 300, 300, null);
                
                //big bushes
                g2d.drawImage(bigBushImage, 1800 + allPlats[0].getX(), 230, 300, 300, null);
                g2d.drawImage(bigBushImage, 4550 + allPlats[0].getX(), 230, 300, 300, null);
                g2d.drawImage(bigBushImage, 6200 + allPlats[0].getX(), 230, 300, 300, null);
                g2d.drawImage(bigBushImage, 8800 + allPlats[0].getX(), 230, 300, 300, null);
                
                //flagpole
                g2d.drawImage(flagpoleImage, (allPlats[39].getX() + allPlats[39].getWidth()/2) - 60, 90, 86, 319, null);

                //drawing the floor
                for(int i = 0; i < allPlats.length; i++)
                {
                    allPlats[i].drawSelf(g);
                }

                //Drawing the user-controlled rectangle AKA Mario
                mario.drawMario(g, currentTime);

                //drawing the coins
                coin.drawCoins(g, rectX);

                for(int i = 0; i < coins.length; i++)
                {
                    coins[i].drawCoins(g, rectX);
                }

                //drawing goomba
                goomba.drawGoomba(g, currentTime);
                //goomba2.drawGoomba(g, currentTime);

                for(int i = 0; i < goombas.length; i++)
                {
                    goombas[i].drawGoomba(g, currentTime);
                }

                //drawing the text box that displays MARIO
                Font font = new Font("Arial", Font.BOLD, 20);
                g.setFont(font);
                g.setColor(Color.WHITE);
                g.drawString("MARIO", 15, 30);

                //drawing the text box that displays THE SCORE
                g.setFont(font); //same font as MARIO
                g.setColor(Color.WHITE);
                g.drawString("" + (int)(mario.getScore()), 15, 55);

                //drawing the text box that displays JUST TIME
                g.setFont(font);
                g.setColor(Color.WHITE);
                g.drawString("TIME", WIDTH - 60, 30);

                //drawing the text box that displays THE TIME
                g.setFont(font);
                g.setColor(Color.WHITE);
                g.drawString("" + currentTime/1000, WIDTH - 60, 55);

                //drawing the text box that displays WORLD
                g.setFont(font);
                g.setColor(Color.WHITE);
                g.drawString("WORLD", WIDTH - 300, 30);

                //drawing the text boc that displays the CURRENT LEVEL
                g.setFont(font);
                g.setColor(Color.WHITE);
                g.drawString("1 - " + level, WIDTH - 281, 55);
                
                //drawing the coin next to the coin counter
                g2d.drawImage(coinImage, 253, 15, 17, 17, null);
                g.setFont(font);
                g.setColor(Color.WHITE);
                g.drawString("x " + coinCounter, 275, 30);
            }
            else
            {
                Graphics2D g2d = (Graphics2D)g;

                long usersTime = currentTime/1000;

                g2d.drawImage(skyImage, 0, 0, WIDTH, HEIGHT, null);

                Font font = new Font("Helvetica", Font.BOLD, 50);
                g.setFont(font);
                g.setColor(Color.BLACK);
                g.drawString("YOU WIN!", 385, 100);

                Font font2 = new Font("Helvetica", Font.BOLD, 30);
                g.setFont(font2);
                g.setColor(Color.BLACK);
                g.drawString("Time: " + usersTime, 355 , 200);

                g.setFont(font2);
                g.setColor(Color.BLACK);
                g.drawString("Score: " + (int)(mario.getScore()), 355 , 250);

                g.setFont(font2);
                g.setColor(Color.BLACK);
                g.drawString("Coins: " + coinCounter, 355 , 300);
                
                g.setFont(font2);
                g.setColor(Color.BLACK);
                g.drawString("Level: " + level, 355 , 350);
            }
        }
        else
        {
            Graphics2D g2d = (Graphics2D)g;
            
            long usersTime = currentTime/1000;

            g2d.drawImage(skyImage, 0, 0, WIDTH, HEIGHT, null);
            
            g2d.drawImage(smallCloudImage, cloudX, cloudY, cloudW, cloudH, null);

            g2d.drawImage(bigCloudImage, bigCloudX, bigCloudY, bigCloudW, bigCloudH, null) ;
            
            Font font = new Font("Helvetica", Font.BOLD, 50);
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString("GAME OVER", 350, 100);
            
            //check to see if game over is centered
            //g.setFont(font);
            //g.setColor(Color.BLACK);
            //g.drawString("I", WIDTH/2, 100);
            
            Font font2 = new Font("Helvetica", Font.BOLD, 30);
            g.setFont(font2);
            g.setColor(Color.BLACK);
            g.drawString("Time: " + usersTime, 355 , 200);
            
            g.setFont(font2);
            g.setColor(Color.BLACK);
            g.drawString("Score: " + (int)(mario.getScore()), 355 , 250);
            
            g.setFont(font2);
            g.setColor(Color.BLACK);
            g.drawString("Coins: " + coinCounter, 355 , 300);
            
            g.setFont(font2);
            g.setColor(Color.BLACK);
            g.drawString("Level: " + level, 355 , 350);
        }
        
        
    }
    
    
    public void loop()
    {
        //updating currentTime
        if(!gameOver && level != 5)
        {
            currentTime = System.currentTimeMillis() - startTime;
        }
        
        
        //moving the cloud
        cloudX += cloudVX;
        bigCloudX += cloudVX;
        
        //respawning the cloud
        if(cloudCounter%2 == 0)
        {
            if(bigCloudX + bigCloudW < 0 && cloudX + cloudW < 0)
            {
                cloudX = WIDTH + 50;
                cloudY = (int)(Math.random()*100 + 50);
                cloudCounter++;
                //System.out.println(cloudCounter);
            }
        }
        else
        {
            if(cloudX + cloudW < 0 && bigCloudX + bigCloudW < 0)
            {
                bigCloudX = WIDTH + 50;
                bigCloudY = (int)(Math.random()*100 + 50);
                cloudCounter++;
                //System.out.println("after big cloud " + cloudCounter);
            }

        }
        
        mario.movement(allPlats);
         
        marioFell();   
        
        // making the rectangle(ground) move when the player moves to the right
        groundMovement();
        
        //allowing mario to collect one coin
        if(mario.collect(coin, allPlats[0].getX()) == true) 
        {
            coin = new Coin();
            coinCounter++;
            coinEffect.play();
        }
        
        //allowing mario to collect an array of coins
        for(int i = 0; i<coins.length; i++)
        {
            if(mario.collect(coins[i], allPlats[0].getX() ) == true)
            {
                coins[i] = new Coin();
                coinCounter++;
                coinEffect.play();
            }
        }
        
        //adding goombas movement
        goomba.movement(); //allPlats[0].getX(), allPlats[0].getWidth()
        //goomba2.movement();
        
        for(int i = 0; i < goombas.length; i++)
        {
            goombas[i].movement();
        }
        
        //allowing mario to interact with goomba
        if(!gameOver)
        {
            if(mario.squishGoomba(goomba) == true )
            {
                goomba.killGoomba();
                goombaEffect.play();
            }
        }
        
        if(mario.goombaEndGame(goomba) == true && !gameOver)
        {
             gameOver = true;
             mario = new Mario(0,0,0,0);
             themeSong.stop();
             deathSong.play();
        }
        
//        //allowing mario to interact with two goombas
//        if(!gameOver)
//        {
//            if(mario.squishGoomba(goomba2) == true )
//                goomba2.killGoomba();
//        }
//        
//        if(mario.goombaEndGame(goomba2) == true)
//             gameOver = true;
        
        //allowing mario to interact with the array of goombas
        if(!gameOver)
        {
            for(int i = 0; i < goombas.length; i++)
            {
                if(mario.squishGoomba(goombas[i]) == true )
                {
                    goombas[i].killGoomba();
                    goombaEffect.play();
                }
            }
        }
        
        for(int i = 0; i < goombas.length; i++)
        {
            if(mario.goombaEndGame(goombas[i]) == true && !gameOver)
            {
                gameOver = true;
                themeSong.stop();
                deathSong.play();
            }
        }
        
        //adding the levels
        if(mario.getXandWidth() >= allPlats[4].getX() + allPlats[4].getWidth() && level == 1)  
        {
            level = 2;
            levelUpSong.play();
        }
        if(mario.getXandWidth() >= allPlats[17].getX() + allPlats[17].getWidth() && level == 2)
        {
            level = 3;
            levelUpSong.play();
        }
        if(mario.getXandWidth() >= allPlats[26].getX() + allPlats[26].getWidth()/2 && level == 3)
        {
            level = 4;
            levelUpSong.play();
        }
        if(mario.getXandWidth() >= allPlats[39].getX() + allPlats[39].getWidth()/2 && level == 4)
        {
            level = 5;
            themeSong.stop();
            winningSong.play();
        }

        
        //Do not write below this
        repaint();
    }
    
    //method to make the ground move
    public void groundMovement()
    {
        if(mario.getX() + 25 == (WIDTH/2) && mario.getRight() == true)
        {
            //floor1.move(-5);
            for(int i = 0; i < coins.length; i++)
                coins[i].move(-5);
            for(int i = 0; i < allPlats.length; i++)
                allPlats[i].move(-5);
            
            coin.move(-5);
            goomba.move(-5);
            //goomba2.move(-5);
            
            for(int i = 0; i < goombas.length; i++)
                goombas[i].move(-5);
        }
    }
    
    //method to see if mario fell off the map
    public void marioFell()
    {
        if(mario.getY() > HEIGHT && !gameOver)
        {
            gameOver = true;
            themeSong.stop();
            deathSong.play();
        }
                    
    }
    
   
    
    //These methods are required by the compiler.
    //You might write code in these methods depending on your goal.
    
    //This method will acknowledge user input
    public void keyPressed(KeyEvent e)
    {
       if((e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) && mario.canJump())
       {
           mario.jump();
           mario.setDown(false);
       }
       else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
       {
           mario.setLeft(true);
       }
       else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
       {
           mario.setRight(true);
       }
       else if((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) && !mario.canJump())
       {
           //mario.setCanFall(true);
           mario.setDown(true);
       }
       
    }
    
    public void keyReleased(KeyEvent e)
    {   
       if((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_A))
       {
           mario.setLeft(false);
       }
       else if((e.getKeyCode() == KeyEvent.VK_RIGHT) || (e.getKeyCode() == KeyEvent.VK_D))
       {
           mario.setRight(false);
       }
       else if((e.getKeyCode() == KeyEvent.VK_DOWN) || (e.getKeyCode() == KeyEvent.VK_S) && !mario.canJump())
       {
           //mario.setCanFall(false);
           mario.setDown(false);
       }
    }
    
    public void keyTyped(KeyEvent e)
    {
        
    }
    
    //Mouse Shtuff most likely wont be used
    public void mousePressed(MouseEvent e)
    {
    
    }

    public void mouseReleased(MouseEvent e)
    {
    
    }
    
    public void mouseClicked(MouseEvent e)
    {
    
    }
        
    public void mouseEntered(MouseEvent e)
    {
        
    }
    
    public void mouseExited(MouseEvent e)
    {
        
    }
    
    public void mouseMoved(MouseEvent e)
    {
        
    }
    
    public void mouseDragged(MouseEvent e)
    {
        
    }
    
    public void start(final int ticks){
        Thread gameThread = new Thread(){
            public void run(){
                while(true){
                    loop();
                    try{
                        Thread.sleep(1000 / ticks);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
        gameThread.start();
    }
    
    private void createObjects()
    {
        mario = new Mario(100,50,WIDTH,HEIGHT);
        coin = new Coin(400,350);
        
        coins = new Coin[116];
        coins[0] = new Coin(450,350);
        coins[1] = new Coin(400,300);
        coins[2] = new Coin(450,300);
        coins[3] = new Coin(450,250);
        coins[4] = new Coin(500,300);
        coins[5] = new Coin(400,250);
        coins[6] = new Coin(350,300); //325
        coins[7] = new Coin(500,250);
        coins[8] = new Coin(500,350);
        coins[9] = new Coin(300,300);
        
        coins[10] = new Coin(850,350);
        coins[11] = new Coin(900,350);
        coins[12] = new Coin(950,350);
        coins[13] = new Coin(1000,350);
        coins[14] = new Coin(1350,350);
        coins[15] = new Coin(1300, 350);
        coins[16] = new Coin(960,250);
        coins[17] = new Coin(990,250);
        coins[18] = new Coin(1020,250);
        coins[19] = new Coin(1216,175);
        coins[20] = new Coin(1282,175);
        
        coins[21] = new Coin(1750,350);
        coins[22] = new Coin(1850,350);
        coins[23] = new Coin(1950,350);
        coins[24] = new Coin(2050,350);
        coins[25] = new Coin(1750,250);
        coins[26] = new Coin(1800,250);
        coins[27] = new Coin(1900,175);
        coins[28] = new Coin(1950,175);
        coins[29] = new Coin(2050,100);
        coins[30] = new Coin(2100,100);
        
        coins[31] = new Coin(2430,350);
        coins[32] = new Coin(2560,350);
        coins[33] = new Coin(2690,350);
        coins[34] = new Coin(2920,350);
        coins[35] = new Coin(2750,100);
        coins[36] = new Coin(2810,100);
        
        coins[37] = new Coin(3130,80);
        coins[38] = new Coin(3190,80);
        coins[39] = new Coin(3420,150);
        coins[40] = new Coin(3360,150);
        coins[41] = new Coin(3650,250);
        coins[42] = new Coin(3750,250);
        
        coins[43] = new Coin(3950,175);
        coins[44] = new Coin(4050,175);
        coins[45] = new Coin(4250,250);
        coins[46] = new Coin(4350,250);
        
        coins[47] = new Coin(4630,350);
        coins[48] = new Coin(4760,350);
        coins[49] = new Coin(4890,350);
        coins[50] = new Coin(5020,350);
        
        //plat 19
        coins[51] = new Coin(5365,350);
        coins[52] = new Coin(5430,350);
        coins[53] = new Coin(5495,350);
        coins[54] = new Coin(5560,350);
        coins[55] = new Coin(5625,350);
        coins[56] = new Coin(5690,350);
        coins[57] = new Coin(5755,350);
        coins[58] = new Coin(5820,350);
        coins[59] = new Coin(5885,350);
        
        //plat 20
        coins[60] = new Coin(4975,250);
        coins[61] = new Coin(5025,250);
        coins[62] = new Coin(5075,250);
        
        //plat 21
        coins[63] = new Coin(5300,175);
        coins[64] = new Coin(5350,175);
        
        //plat 23
        coins[65] = new Coin(5800,10);
        coins[66] = new Coin(5825,10);
        coins[67] = new Coin(5850,10);
        
        //plat 24
        coins[68] = new Coin(6015,-100);
        coins[69] = new Coin(6040,-100);
        coins[70] = new Coin(6065,-100);
        coins[71] = new Coin(6090,-100);
        coins[72] = new Coin(6115,-100);
        coins[73] = new Coin(6140,-100);
        
        //plat 25
        coins[74] = new Coin(5975,100);
        coins[75] = new Coin(6025,100);
        
        //plat 28
        coins[76] = new Coin(6250,175);
        coins[77] = new Coin(6275,175);
        coins[78] = new Coin(6300,175);
        coins[79] = new Coin(6325,175);
        coins[80] = new Coin(6350,175);
        
        //plat 27
        coins[81] = new Coin(6275,10);
        coins[82] = new Coin(6300,10);
        coins[83] = new Coin(6325,10);
        coins[84] = new Coin(6350,10);
        coins[85] = new Coin(6375,10);
        coins[86] = new Coin(6400,10);
        coins[87] = new Coin(6250,10);
        
        //plat 26
        coins[88] = new Coin(6200,350);
        coins[89] = new Coin(6300,350);
        coins[90] = new Coin(6400,350);
        coins[91] = new Coin(6500,350);
        coins[92] = new Coin(6600,350);
        coins[93] = new Coin(6700,350);
        
        //plat 29
        coins[94] = new Coin(7000,375);
        coins[95] = new Coin(7030,375);
        
        //plat 30
        coins[96] = new Coin(7015,425);
        coins[97] = new Coin(7045,425);
        
        //plat 32
        coins[98] = new Coin(7395,275);
        coins[99] = new Coin(7420,275);
        coins[100] = new Coin(7445,275);
        
        //plat 33
        coins[101] = new Coin(7153,145);
        coins[102] = new Coin(7186,145);
        
        //plat 34
        coins[103] = new Coin(7170,65);
        
        //plat 36
        coins[104] = new Coin(7625,65);
        coins[105] = new Coin(7650,65);
        coins[106] = new Coin(7675,65);
        
        //plat 38
        coins[107] = new Coin(8170,165);
        coins[108] = new Coin(8200,165);
        coins[109] = new Coin(8230,165);
        coins[110] = new Coin(8260,165);
        
        //plat 39
        coins[111] = new Coin(8504,350);
        coins[112] = new Coin(8558,350);
        coins[113] = new Coin(8612,350);
        coins[114] = new Coin(8666,350);
        coins[115] = new Coin(8720,350);
        
//        int xPos = 650;
//        for(int i = 10; i<coins.length; i++ )
//        {
//            coins[i] = new Coin(xPos, 350);
//            xPos+=50;
//        }
        
        allPlats = new Platform[40];
        
        allPlats[0]= new Platform(0, 400, 650, HEIGHT-rectY, 1);
        //dont need this plat anymore
        allPlats[2] = new Platform(-400, 300, 200, 40, 2);
        
        allPlats[1] = new Platform(750, 400, 650, 100, 1);
        allPlats[5] = new Platform(900, 300, 200, 40, 2);
        allPlats[6] = new Platform(1150, 225, 200, 40, 2);
        
        allPlats[3] = new Platform(1500, 400, 650, 100, 1);
        allPlats[7] = new Platform(1700, 300, 200, 40, 2);
        allPlats[8] = new Platform(1850, 225, 200, 40, 2);
        allPlats[9] = new Platform(2000, 150, 200, 40, 2);
        
        allPlats[4] = new Platform(2250, 400, 650, 100, 1);
        allPlats[10] = new Platform(2700, 150, 150, 40, 2);
        allPlats[11] = new Platform(2900, 225, 100, 40, 2);
        
        allPlats[12] = new Platform(3000, 300, 200, 40, 2);
        allPlats[13] = new Platform(3300, 200, 200, 40, 2);
        allPlats[14] = new Platform(3050, 130, 200, 40, 2);
        allPlats[15] = new Platform(3600, 300, 200, 40, 2);
        allPlats[16] = new Platform(3900, 225, 200, 40, 2);
        allPlats[17] = new Platform(4200, 300, 200, 40, 2);
        allPlats[18] = new Platform(4500, 400, 650, 100, 1);
        
        allPlats[19] = new Platform(5300, 400, 650, 100, 1);
        allPlats[20] = new Platform(4950, 300, 150, 40, 2);
        allPlats[21] = new Platform(5250, 225, 150, 40, 2);
        allPlats[22] = new Platform(5550, 125, 150, 40, 2);
        allPlats[23] = new Platform(5775, 50, 100, 40, 2);
        //plat above the GUI
        allPlats[24] = new Platform(6025, -60, 200, 40, 2);
        
        allPlats[25] = new Platform(5925, 150, 150, 40, 2);
        allPlats[26] = new Platform(6125, 400, 650, 100, 1);
        allPlats[27] = new Platform(6250, 50, 150, 40, 2);
        allPlats[28] = new Platform(6225, 225, 150, 40, 2);
        
        //THE ENDING
        allPlats[29] = new Platform(6965, 400, 100, 20, 2);
        allPlats[30] = new Platform(6980, 460, 100, 20, 2);
        allPlats[31] = new Platform(7120, 430, 100, 20, 2);
        allPlats[32] = new Platform(7370, 300, 100, 20, 2);
        allPlats[33] = new Platform(7120, 170, 100, 20, 2);
        allPlats[34] = new Platform(7120, 90, 100, 20, 2);
        allPlats[35] = new Platform(7400, 90, 100, 20, 2);
        allPlats[36] = new Platform(7600, 90, 100, 20, 2);
        allPlats[37] = new Platform(7825, -16, 100, 20, 2);
        allPlats[38] = new Platform(8150, 190, 150, 40, 2);
        allPlats[39] = new Platform(8450, 400, 650, 100, 1);
        //allPlats[40] = new Platform(6175, 500, 4000, 20, 1);
       
        goomba = new Goomba(allPlats[0], 2);
        //goomba2 = new Goomba(allPlats[0], 2);
        
        goombas = new Goomba[23];
        goombas[0] = new Goomba(allPlats[1], 0);
        goombas[1] = new Goomba(allPlats[6], 1);
        
        goombas[2] = new Goomba(allPlats[3], 1);
        goombas[3] = new Goomba(allPlats[3], 0);
        goombas[4] = new Goomba(allPlats[8], 0);
        
        goombas[5] = new Goomba(allPlats[4], 0);
        goombas[6] = new Goomba(allPlats[4], 2);
        goombas[7] = new Goomba(allPlats[11], 0);
        
        goombas[8] = new Goomba(allPlats[12], 0);
        goombas[9] = new Goomba(allPlats[16], 0);
        goombas[10] = new Goomba(allPlats[16], 1);
        goombas[11] = new Goomba(allPlats[17], 0);
        
        goombas[12] = new Goomba(allPlats[18], 1);
        goombas[13] = new Goomba(allPlats[18], 0);
        
        goombas[14] = new Goomba(allPlats[22], 0);
        goombas[15] = new Goomba(allPlats[25], 0);
        goombas[16] = new Goomba(allPlats[28], 1);
        
        goombas[17] = new Goomba(allPlats[26], 0);
        goombas[18] = new Goomba(allPlats[26], 1);
        goombas[19] = new Goomba(allPlats[26], 2);
        
        goombas[20] = new Goomba(allPlats[30], 1);
        goombas[21] = new Goomba(allPlats[34], 0);
        goombas[22] = new Goomba(allPlats[38], 2);
        
//        goombas[23] = new Goomba(allPlats[39], 0);
//        goombas[24] = new Goomba(allPlats[39], 2);

    }
    
    
    
    public static void main(String[] args)
    {
        MarioGraphics g = new MarioGraphics();

        g.start(60);
    }

}