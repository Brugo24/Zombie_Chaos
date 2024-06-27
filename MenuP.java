package joguin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.sound.sampled.*;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;


public class MenuP extends JFrame{
    BufferedImage selImagePlay;
    BufferedImage selImageDevs;
    BufferedImage backgroundImage;
    BufferedImage buttonPlayNormal;
    BufferedImage buttonDevsNormal;
    BufferedImage buttonPlayHover;
    BufferedImage buttonDevsHover;
    JPanel panel;
   
    MenuP(){
        loadimages();
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        selImageDevs=buttonDevsNormal;
        selImagePlay=buttonPlayNormal;
        panel=new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
             super.paintComponent(g);
             g.drawImage(backgroundImage, 0, 0, 800,600,this);
             g.drawImage(selImagePlay, 300,150, 180, 55, this);
             g.drawImage(selImageDevs, 300,235, 180, 55, this);
            }
        };
        panel.setLayout(new BorderLayout());
        panel.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {

            }
            public void mouseMoved(MouseEvent e) {
                if((e.getX()>=300 && e.getX()<=480) && (e.getY()>=150 && e.getY()<=235)){

                    panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    selImagePlay=buttonPlayHover;
                    selImageDevs=buttonDevsNormal;
                    panel.repaint();


                }else if((e.getX()>=300 && e.getX()<=480) && (e.getY()>=235 && e.getY()<=290)){
                    panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    selImagePlay=buttonPlayNormal;
                    selImageDevs=buttonDevsHover;
                    panel.repaint();


                }else{
                    panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    selImagePlay=buttonPlayNormal;
                    selImageDevs=buttonDevsNormal;
                    panel.repaint();
                }
            }
        });
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if((e.getX()>=300 && e.getX()<=480) && (e.getY()>=150 && e.getY()<=235)){
                    startGame();
                    hideJFrame();
                  
                    
                }else if((e.getX()>=300 && e.getX()<=480) && (e.getY()>=235 && e.getY()<=290)){
                    startDevsMenu();
                    hideJFrame();
                    
                  }
            }

        });
        setContentPane(panel);

        setVisible(true);
        startZombieChaosAudio();
    }

    void startGame(){
        Jogo jogo=new Jogo(this);
    }

    void startDevsMenu(){
        Devs devs = new Devs(this);
    }

    public static void main(String[] args) {
        new MenuP();
    }
    void loadimages(){
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("res/Menu/background.png"));
            buttonPlayNormal = ImageIO.read(getClass().getResourceAsStream("res/Menu/buttonPlayNormal.png"));
            buttonDevsNormal = ImageIO.read(getClass().getResourceAsStream("res/Menu/buttonDevsNormal.png"));
            buttonPlayHover = ImageIO.read(getClass().getResourceAsStream("res/Menu/buttonPlayHover.png"));
            buttonDevsHover = ImageIO.read(getClass().getResourceAsStream("res/Menu/buttonDevsHover.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void startZombieChaosAudio(){
     try {
         BufferedInputStream myStream = new BufferedInputStream(getClass().getResourceAsStream("res/audio/zombie_chaos.wav"));
         AudioInputStream myAudio = AudioSystem.getAudioInputStream(myStream);
         Clip clip=AudioSystem.getClip();
        clip.open(myAudio);
        clip.start();
        Thread.sleep(clip.getMicrosecondLength()/1000);
        clip.close();
     } catch (IOException e) {
        e.printStackTrace();
     }catch(UnsupportedAudioFileException e){
      e.printStackTrace();
     }catch(LineUnavailableException e){
        e.printStackTrace();
     }catch(InterruptedException e){
        e.printStackTrace();
     }
    }

   private void hideJFrame(){
    this.setVisible(false);
   }
   
}
