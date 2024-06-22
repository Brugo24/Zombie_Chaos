import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class GamePanel extends JPanel{
    private boolean running;
    private BufferedImage[][] images;
    Character character;
    private Controller controller;
    private Jogo jogo;
    
    Point point;
    private LoadAssets loadAssets;
    GamePanel(Character character,Controller controller,Jogo jogo,Point point,LoadAssets loadAssets){
        this.loadAssets=loadAssets;
        this.point=point;
        this.jogo=jogo;
        this.character=character;
        this.controller=controller;
       
        requestFocus();
        setFocusable(true);
        loadImages();
        addMouseMotionListener(new MouseMotionListeners(character,point));
        addKeyListener(new KeyListeners(character,point));
        addMouseListener(new MouseListeners(character,jogo,controller,point));
        running=false;

    }

    private void loadImages(){
        images=loadAssets.getCharacterImages();
    }
    
    //verificar se o jogo está pausado ou não
    public boolean getIsRunning(){
        return this.running;
    }
    
    //alterar o valor de running
    public void setIsRunning(boolean running){
     this.running=running;
    }
    

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
         AffineTransform old=g2.getTransform();
         controller.render(g2,old);
         //zombie.paint(g);
       
       
        g2.setTransform(old);
        
        g2.setColor(Color.RED);
        g2.fillOval(character.getGunPosX()-5/2, character.getGunPosY()-5/2, 5, 5);
        
        g2.rotate(character.getAngle(),character.getPosX()+(int)(Constants.SHOOTER_WIDTH/2.5)/2,character.getPosY()+(int)(Constants.SHOOTER_HEIGHT/2.5)/2);
        g2.drawImage(images[character.getIndexGun()][character.getIndexAnimation()], character.getPosX(),character.getPosY(),(int)(Constants.SHOOTER_WIDTH/2.5),(int)(Constants.SHOOTER_HEIGHT/2.5),null);
        
       

        //controller.render(g);

       
    }

    public void tick(){
        //atualizar posição do personagem
    }
    
}
