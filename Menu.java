import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;


public class Menu extends JFrame implements MouseMotionListener{
    ImageIcon selImagePlay;
    ImageIcon selImageDevs;
    ImageIcon backgroundImage=new ImageIcon("./res/images/Menu/background.png");
    ImageIcon buttonPlayNormal=new ImageIcon("./res/images/Menu/buttonPlayNormal.png");
    ImageIcon buttonDevsNormal=new ImageIcon("./res/images/Menu/buttonDevsNormal.png");
    ImageIcon buttonPlayHover=new ImageIcon("./res/images/Menu/buttonPlayHover.png");
    ImageIcon buttonDevsHover=new ImageIcon("./res/images/Menu/buttonDevsHover.png");
    JPanel panel;
   
    Menu(){
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
             g.drawImage(backgroundImage.getImage(), 0, 0, 800,600,this);
             g.drawImage(selImagePlay.getImage(), 300,150, 180, 55, this);
             g.drawImage(selImageDevs.getImage(), 300,235, 180, 55, this);
            }
        };
        panel.setLayout(new BorderLayout());
        panel.addMouseMotionListener(this);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if((e.getX()>=300 && e.getX()<=480) && (e.getY()>=150 && e.getY()<=235)){

                    Jogo jogo=new Jogo();
                    hideJFrame();
                  
                    
                }else if((e.getX()>=300 && e.getX()<=480) && (e.getY()>=235 && e.getY()<=290)){
                    
                    
                    
                  }
            }
        });
        setContentPane(panel);

        setVisible(true);
        startZombieChaosAudio();
    }
    public static void main(String[] args) {
        new Menu();
    }
    private void startZombieChaosAudio(){
     String filePath="./res/audios/zombie_chaos.wav";
     File initialAudio=new File(filePath);
     //System.out.println("Entrou");
     try {
       Clip clip=AudioSystem.getClip();
       clip.open(AudioSystem.getAudioInputStream(initialAudio));
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
    public void mouseMoved(MouseEvent e){
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
    public void mouseDragged(MouseEvent e){

    }
   
}