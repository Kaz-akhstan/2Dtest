import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen
    final int rawTileSize = 16; //16x16 tiles
    final int scale = 3;
    final int tileSize = rawTileSize * scale; // 3x scaling for better visibility
    final int maxScreenSizeHorizontal = 16;
    final int maxScreenSizeVertical = 12; //4:3 ratio
    final int screenWidth = tileSize * maxScreenSizeHorizontal; //768
    final int screenHeight = tileSize * maxScreenSizeVertical; //576

    int FPS = 60;

    InputHandler inputH = new InputHandler();
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(inputH);
        this.setFocusable(true);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1)
            {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000)
            {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update()
    {
        if(inputH.upPressed)
        {
            playerY -= playerSpeed;
        }
        else if(inputH.downPressed)
        {
            playerY += playerSpeed;
        }
        else if(inputH.leftPressed)
        {
            playerX -= playerSpeed;
        }
        else if(inputH.rightPressed)
        {
            playerX += playerSpeed;
        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
