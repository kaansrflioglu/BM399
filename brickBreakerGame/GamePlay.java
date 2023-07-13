import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 48;
    //Topun hızını ayarlamak için timer kullanıyoruz.
    private final Timer Timer;
    private int playerX = 510;

    // Random sınıfı ile her oyun başlangıcında rastgele konumu değiştirilir.
    Random rand = new Random();
    int rand1 = rand.nextInt(100,1100);
    int rand2 = rand.nextInt(200,400);
    private int ballPosX = rand1;
    private int ballPosY = rand2;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapGenerator map;
    public GamePlay() {
        map = new MapGenerator(4, 12);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        int delay = 1;
        Timer = new Timer(delay, this);
        Timer.start();
    }
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(1, 1, 1200, 700);
        map.draw((Graphics2D) g);
        g.setColor(Color.gray);
        g.setFont(new Font("arial", Font.BOLD, 25));
        g.drawString(String.valueOf(score), 600, 30);
        g.setColor(Color.blue);
        g.fillRect(playerX, 600, 120, 8);
        g.setColor(Color.BLACK);
        //Topun boyutu.
        g.fillOval(ballPosX, ballPosY, 15, 15);
        if (ballPosY > 600) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("arial", Font.BOLD, 30));
            g.drawString("Oyun Bitti!", 470, 300);
            g.setFont(new Font("arial", Font.BOLD, 30));
            g.drawString("Enter tuşuna basıp", 470, 340);
            g.drawString("oyunu yeniden başlatın.", 470, 370);
            g.drawString("Skorunuz: "+score, 470, 400);
        }
        if(totalBricks == 0){
            play = false;
            ballYdir = -2;
            ballXdir = -1;
            g.setColor(Color.red);
            g.setFont(new Font("arial",Font.BOLD,30));
            g.drawString("Tebrikler Kazandınız!",470,300);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Enter tuşuna basıp", 470, 340);
            g.drawString("oyunu yeniden başlatın.", 470, 370);
            g.drawString("Skorunuz: "+score, 470, 400);
        }
        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Timer.start();
        if (play) {
            if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 600, 120, 8))) {
                ballYdir = -ballYdir;
            }
            A:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.bricksWidth + 80;
                        int brickY = i * map.bricksHeight + 50;
                        int bricksWidth = map.bricksWidth;
                        int bricksHeight = map.bricksHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);

                        if (ballRect.intersects(rect)) {
                            map.setBricksValue(0, i, j);
                            totalBricks--;
                            score += 5;
                            if (ballPosX + 19 <= rect.x || ballPosX + 1 >= rect.x + bricksWidth) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballPosX += ballXdir;
            ballPosY += ballYdir;
            // Topun yanlardan sekmesinin ayarları.
            if (ballPosX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballPosY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballPosX > 1170) {
                ballXdir = -ballXdir;
            }
        }
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {    }
    @Override
    public void keyReleased(KeyEvent e) {    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 1080) {
                playerX = 1080;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 0) {
                playerX = 0;
            } else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                ballPosX = rand1;
                ballPosY = rand2;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                playerX = 310;
                totalBricks = 48;
                map = new MapGenerator(4, 12);
                repaint();
            }
        }
    }
    public void moveRight ()
    {
        play = true;
        playerX += 25;
    }
    public void moveLeft ()
    {
        play = true;
        if(playerX<=25)
        {
            playerX = 0;
        } else {
            playerX -= 25;
        }

    }
}
