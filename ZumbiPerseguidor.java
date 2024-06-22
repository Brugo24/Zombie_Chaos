import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ZumbiPerseguidor extends JPanel implements Runnable {
    private int playerX = 400;
    private int playerY = 300;
    private int enemyX = 100;
    private int enemyY = 100;
    private int playerSpeed = 10;
    private int enemySpeed = 1;

    public ZumbiPerseguidor() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> playerY -= playerSpeed;
                    case KeyEvent.VK_DOWN -> playerY += playerSpeed;
                    case KeyEvent.VK_LEFT -> playerX -= playerSpeed;
                    case KeyEvent.VK_RIGHT -> playerX += playerSpeed;
                }
            }
        });

        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (true) {
            moveEnemy();
            repaint();
            try {
                Thread.sleep(20);  // Atualiza a cada 20 milissegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveEnemy() {
        int diffX = playerX - enemyX;
        int diffY = playerY - enemyY;
        double distance = Math.sqrt(diffX * diffX + diffY * diffY);

        if (distance != 0) {
            enemyX += (int) (enemySpeed * (diffX / distance));
            enemyY += (int) (enemySpeed * (diffY / distance));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(playerX, playerY, 20, 20);  // Desenha o jogador
        g.setColor(Color.RED);
        g.fillOval(enemyX, enemyY, 20, 20);   // Desenha o inimigo
    }
}
