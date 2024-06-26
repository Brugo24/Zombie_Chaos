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
    private boolean mostrarIntro;
    private  int tickIntro=300;
    private int tickDie=150;
    float alpha=0.f;
    final int FPS = 60;
    private boolean isdead = false;
    private float deadalpha = 0f;
    
    public Painel(Jogo jogo, LoadAssets assets){
        this.jogo = jogo;
        this.assets = assets;
        
        setPreferredSize(new Dimension(1200,720));
        setBackground(Color.gray);
        setCursorimg();

        personagemImages = assets.getCharacterImages();
        playerIcon = assets.getPlayericon();
        personagem = new Personagem(this,keyH,mouseMH,mouseH,personagemImages);
        control = new Controle(personagem, assets,this);
        pistola = new Pistola(assets.getBulletImage(),mouseH,mouseMH,control,personagem);
        pistola.atiraSound(0f);

        ground = assets.getBackground();

        this.addKeyListener(keyH);
        this.addMouseMotionListener(mouseMH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
        mostrarIntro=true;
    }

    void showIntro(Graphics2D g){
        Color c=new Color(0,0,0,alpha);
        g.setColor(c);
        alpha=alpha+0.005f;
        if(alpha>0.75f)alpha=0.75f;
        g.fillRect(0,120,1200,600);
        tickIntro--;
    }
    void hideIntro(Graphics2D g){
        Color c=new Color(0,0,0,alpha);
        g.setColor(c);
        alpha=alpha-0.005f;
        if(alpha<0f)alpha=0f;

        g.fillRect(0,120,1200,600);
        tickIntro--;
    }

    void deadPlayer(Graphics2D g){
        //System.out.println(deadalpha);
        Color c=new Color(1f,0,0,deadalpha);
        g.setColor(c);
        if(deadalpha>0.5f) jogo.interruptThread();
        else deadalpha=deadalpha+0.005f;
        g.fillRect(0,120,1200,600);
    }

    void setCursorimg(){
        try {
            BufferedImage cursorimg = ImageIO.read(getClass().getResourceAsStream("res/sprites/cursor/mira.png"));
            Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg, new Point(0,0), "Sight");
            setCursor(cursor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    void die(){
        isdead = true;
    }

    public void update(){
        if(!isdead){
            personagem.update();
            pistola.update(personagem.getX(),personagem.getY());
        }
        if(personagem.getvida() <= 0 && !isdead) die();
        control.rodada();
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

        if(tickIntro <= 0) control.tick(g2,oldState);
        g2.setColor(Color.gray);
        g2.fillRect(0,0,1200,120);
        personagem.drawlifebar(g2);
        g2.drawImage(playerIcon,15,15,100,100,null);
        g2.setTransform(oldState);
        g2.drawImage(assets.getAmmoCountIcon(), 125, 60, 20, 50, null);
        g2.setTransform(oldState);
        g2.setFont(font);
        g2.setColor(Color.black);
        g2.drawString("X" + Integer.toString(personagem.getAmmo()), 155, 100);
        g2.drawImage(assets.getMoneyIcon(), 165 + Integer.toString(personagem.getAmmo()).length() * 20, 55, 60, 60, null);
        g2.setColor(Color.green);
        g2.drawString(Integer.toString(personagem.getdinheiros()), 215 + Integer.toString(personagem.getAmmo()).length() * 20, 100);
        //g2.setColor();
        g2.setTransform(oldState);
        if(tickIntro>150)showIntro(g2);
        else hideIntro(g2);
        if(isdead) deadPlayer(g2);
        g2.dispose();
    }

    public void setTickIntro(){tickIntro=300;}

}
