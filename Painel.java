package joguin;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Painel extends JPanel{

    Jogo jogo;
    BufferedImage imagem;
    KeyHandler keyH = new KeyHandler();
    MouseMotionHandler mouseMH = new MouseMotionHandler();
    MouseHandler mouseH = new MouseHandler();
    Controle control;
    Pistola pistola;
    LoadAssets assets;
    Personagem personagem;
    BufferedImage[][] personagemImages;
    BufferedImage playerIcon;
    BufferedImage ground;
    InputStream is;
    Thread gameThread;
    AffineTransform oldState;
    final int FPS = 60;
    
    public Painel(Jogo jogo, LoadAssets assets){
        this.jogo = jogo;
        this.assets = assets;
        
        setPreferredSize(new Dimension(1200,720));
        setBackground(Color.gray);
        setCursorimg();

        personagemImages = assets.getCharacterImages();
        playerIcon = assets.getPlayericon();
        personagem = new Personagem(this,keyH,mouseMH,mouseH,personagemImages);
        control = new Controle(personagem, assets);
        pistola = new Pistola(assets.getBulletImage(),mouseH,control,personagem);
        pistola.atiraSound(0f);

        ground = assets.getBackground();

        this.addKeyListener(keyH);
        this.addMouseMotionListener(mouseMH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
    }

    void setCursorimg(){
        try {
            BufferedImage cursorimg = ImageIO.read(getClass().getResourceAsStream("./res/sprites/cursor/mira.png"));
            Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg, new Point(0,0), "Sight");
            setCursor(cursor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void update(){
        personagem.update();

        pistola.update(personagem.getX(),personagem.getY());
    }
    
    public void paintComponent(Graphics g){
        //System.out.println("Running");
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        Font font = new Font("Comic Sans MS",Font.PLAIN,30);
        g2.drawImage(ground,0,119,null);
        g2.drawImage(ground,611,119,null);
        oldState = g2.getTransform();

        personagem.draw(g2);

        g2.setTransform(oldState);
        control.tick(g2,oldState);

        g2.setColor(Color.gray);
        g2.fillRect(0,0,1200,120);
        personagem.drawlifebar(g2);
        g2.drawImage(playerIcon,15,15,100,100,null);
        g2.drawImage(assets.getAmmoCountIcon(),125,50,20,50,null);
        g2.setTransform(oldState);
        g2.setFont(font);
        g2.setColor(Color.black);
        g2.drawString("X"+Integer.toString(personagem.getAmmo()),160,80);
        g2.setTransform(oldState);
        g2.dispose();
    }

}
