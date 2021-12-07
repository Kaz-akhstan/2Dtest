import javax.swing.*;
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

    Thread gameThread;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLUE);
        this.setDoubleBuffered(true);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

    }
}
