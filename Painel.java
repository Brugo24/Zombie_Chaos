package joguin;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Painel extends JPanel{

    Jogo jogo;
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
    Cursor cursor;
    BufferedImage[] progressBar = new BufferedImage[8];
    BufferedImage[] buffIcons;
    BufferedImage buffsLoja;
    BufferedImage[] comprarButton;
    BufferedImage[] buttonVoltaLoja;
    private boolean mostrarIntro;
    private  int tickIntro=300;
    private int tickDie=150;
    float alpha=0.f;
    final int FPS = 60;
    private boolean isdead = false;
    private float deadalpha = 0f;
    private boolean lojaActive = false;
    private int[] progress = new int[8];
    private boolean disableLoja = false;
    private boolean endGame = false;
    String str;
    Font font = new Font("Comic Sans MS",Font.PLAIN,30);

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

        buffsLoja = assets.getIconsLoja();
        comprarButton = assets.getButtonLoja();
        progressBar = assets.getProgressBarLoja();
        buttonVoltaLoja = assets.getButtonLojaVolta();
        buffIcons = assets.getIconsBuff();

        Arrays.fill(progress,0);

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
        Color c2=new Color(1f,1f,1f,alpha);
        g.setColor(c2);
        g.drawString("Horda "+(control.getHorda()+1),550,400);
        tickIntro--;
    }
    void hideIntro(Graphics2D g){
        Color c=new Color(0,0,0,alpha);
        g.setColor(c);
        alpha=alpha-0.005f;
        if(alpha<0f)alpha=0f;

        g.fillRect(0,120,1200,600);
        Color c2=new Color(1f,1f,1f,alpha);
        g.setColor(c2);
        g.drawString("Horda "+(control.getHorda()+1),550,400);

        tickIntro--;
    }

    void deadPlayer(Graphics2D g){
        //System.out.println(deadalpha);
        Color c=new Color(1f,0,0,deadalpha);
        g.setColor(c);
        if(deadalpha>0.5f){
            jogo.interupt();
        }
        else deadalpha=deadalpha+0.005f;
        g.fillRect(0,120,1200,600);

    }

    void setCursorimg(){
        try {
            BufferedImage cursorimg = ImageIO.read(getClass().getResourceAsStream("res/sprites/cursor/mira.png"));
            cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg, new Point(0,0), "Sight");
            setCursor(cursor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    void die(){
        isdead = true;
    }

    void loja(Graphics2D g){
        boolean isEndGame=true;
        g.drawImage(buffsLoja, 15, 120, null);
        AffineTransform oldState = g.getTransform();
        for(int y=205+120, i=0; y<=505+120; y+=300){
            for(int x=15; x<=1186; x+=307,i++){
                g.drawImage(progressBar[progress[i]], x, y-36, null);
                if(progress[i]!=4){isEndGame = false;}
                if(mouseMH.hover[i] && progress[i] < 4){
                    g.drawImage(comprarButton[1], x, y, null);
                    g.setColor(Color.green);
                    g.setFont(font);
                    String str = Integer.toString(200 * (progress[i]+1));
                    g.drawString("-$"+str, 560, 50);
                }else{
                    g.drawImage(comprarButton[0], x, y, null);
                }
            }
        }
        g.setTransform(oldState);
        if(isEndGame) endGame=true;
        if(mouseMH.hover[8]){
            g.drawImage(buttonVoltaLoja[1],860,24,null);
        }else{
            g.drawImage(buttonVoltaLoja[0],860,24,null);
        }
        g.dispose();
    }

    public void update(){
        if(tickIntro <=0) control.canShoot = true;
        if(!isdead){
            personagem.update();
            pistola.update(personagem.getX(),personagem.getY());
        }
        if(personagem.getvida() <= 0 && !isdead) die();
        control.rodada();
        if(lojaActive){
            if(mouseH.clicked[0] && progress[0] < 4 && personagem.getMoney() >= (200*(progress[0]+1))){
                personagem.buy(200 * (progress[0] + 1));
                progress[0]++;
                pistola.buffDmg();
                mouseH.clicked[0] = false;
            }else if(mouseH.clicked[1] && progress[1] < 4 && personagem.getMoney() >= (200*(progress[1]+1))){
                personagem.buy(200 * (progress[1] + 1));
                progress[1]++;
                pistola.buffFireRate();
                mouseH.clicked[1] = false;
            }else if(mouseH.clicked[2] && progress[2] < 4 && personagem.getMoney() >= (200*(progress[2]+1))){
                personagem.buy(200 * (progress[2] + 1));
                progress[2]++;
                control.buffAmmoDropRate();
                mouseH.clicked[2] = false;
            }else if(mouseH.clicked[3] && progress[3] < 4 && personagem.getMoney() >= (200*(progress[3]+1))){
                personagem.buy(200 * (progress[3] + 1));
                progress[3]++;
                control.buffAmmoReceive();
                mouseH.clicked[3] = false;
            }else if(mouseH.clicked[4] && progress[4] < 4 && personagem.getMoney() >= (200*(progress[4]+1))){
                personagem.buy(200 * (progress[4] + 1));
                progress[4]++;
                personagem.buffMeleeDmg();
                mouseH.clicked[4] = false;
            }else if(mouseH.clicked[5] && progress[5] < 4 && personagem.getMoney() >= (200*(progress[5]+1))){
                personagem.buy(200 * (progress[5] + 1));
                progress[5]++;
                personagem.buffSpeed();
                mouseH.clicked[5] = false;
            }else if(mouseH.clicked[6] && progress[6] < 4 && personagem.getMoney() >= (200*(progress[6]+1))){
                personagem.buy(200 * (progress[6] + 1));
                progress[6]++;
                control.buffMedKitDropRate();
                mouseH.clicked[6] = false;
            }else if(mouseH.clicked[7] && progress[7] < 4 && personagem.getMoney() >= (200*(progress[7]+1))){
                personagem.buy(200 * (progress[7] + 1));
                progress[7]++;
                control.buffMedkitReceive();
                mouseH.clicked[7] = false;
            }else if(mouseH.clicked[8]){
                mouseH.clicked[8] =false;
                disableLoja = true;
                setCursor(cursor);
            }
        }
    }
    
    public void paintComponent(Graphics g){
        //System.out.println("Running");
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(ground,0,119,null);
        g2.drawImage(ground,611,119,null);
        oldState = g2.getTransform();
        if(tickIntro <= 0 && !lojaActive) control.tick(g2,oldState);

        g2.setColor(Color.gray);
        g2.fillRect(0,0,1200,120);
        personagem.drawlifebar(g2);
        g2.drawImage(playerIcon,15,15,100,100,null);
        g2.setTransform(oldState);
        g2.drawImage(assets.getAmmoCountIcon(), 125, 60, 20, 50, null);
        g2.setTransform(oldState);
        g2.setFont(font);
        g2.setColor(Color.black);
        if(!endGame) str = Integer.toString(personagem.getAmmo());
        else str = "∞";
        g2.drawString("X" + str, 155, 100);
        g2.drawImage(assets.getMoneyIcon(), 165 + Integer.toString(personagem.getAmmo()).length() * 20, 55, 60, 60, null);
        g2.setColor(Color.green);
        g2.drawString(Integer.toString(personagem.getdinheiros()), 215 + Integer.toString(personagem.getAmmo()).length() * 20, 100);

        if(!lojaActive){

            personagem.draw(g2);

            g2.setTransform(oldState);

            //g2.drawImage(buffIcons[0],700,4,50,25,null);
            //g2.drawImage(progressBar[progress[0]],760,4,100,25,null);
            for(int x=700,i=0; x<=870;x+=170){
                for(int y=4; y<=(4+29*3);y+=29,i++) {
                    g2.drawImage(buffIcons[i], x, y, 50, 25, null);
                    g2.drawImage(progressBar[progress[i]], x+60, y, 100, 25, null);
                }
            }
        }

        //g2.setColor();
        g2.setTransform(oldState);
        if(tickIntro>150)showIntro(g2);
        else if(!lojaActive && (tickIntro==150) && !disableLoja && control.getHorda() != 0){
            lojaActive = true;
            mouseH.loja = true;
            mouseMH.loja = true;
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        if(control.getHorda() == 0 && tickIntro <=150){
            hideIntro(g2);
        }
        if(disableLoja){
            hideIntro(g2);
            lojaActive = false;
            if(tickIntro<=0) {
                Arrays.fill(mouseH.clicked,false);
                mouseH.loja = false;
                disableLoja = false;
                mouseMH.loja = false;
            }
        }
        if(lojaActive){
            loja(g2);
        }
        if(isdead) deadPlayer(g2);
        g2.dispose();
    }

    public void setTickIntro(){tickIntro=300;}

}
