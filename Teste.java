import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Teste extends JPanel implements MouseMotionListener, MouseListener {
    private int mouseX, mouseY;
    private int gunX, gunY;
    private java.util.List<Bullet> bullets;

    public Teste() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.gunX = 400; // Posição X inicial da arma
        this.gunY = 300; // Posição Y inicial da arma
        this.bullets = new ArrayList<>();
        Timer timer = new Timer(20, e -> updateBullets());
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(gunX - 5, gunY - 5, 10, 10); // Desenha a arma
        
        g.setColor(Color.RED);
        for (Bullet bullet : bullets) {
            g.fillOval(bullet.x, bullet.y, 5, 5); // Desenha as balas
        }
    }

    private void updateBullets() {
        for (Bullet bullet : bullets) {
            bullet.move();
        }
        bullets.removeIf(bullet -> bullet.x < 0 || bullet.x > getWidth() || bullet.y < 0 || bullet.y > getHeight());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double angle = Math.atan2(mouseY - gunY, mouseX - gunX);
        bullets.add(new Bullet(gunX, gunY, angle));
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shooting Game");
        Teste gamePanel = new Teste();
        frame.add(gamePanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class Bullet {
        int x, y;
        double angle;
        final int speed = 10;

        Bullet(int x, int y, double angle) {
            this.x = x;
            this.y = y;
            this.angle = angle;
        }

        void move() {
            x += speed * Math.cos(angle);
            y += speed * Math.sin(angle);
        }
    }
}
