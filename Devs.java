package joguin;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Devs extends JFrame implements MouseMotionListener, MouseListener {
    BufferedImage image;
    MenuP menu;
    JPanel panel;
    Devs(MenuP menu){
        loadImage();
        this.menu=menu;
        setSize(800,600);
        setResizable(false);
        setLocationRelativeTo(null);

        panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image,0,0,800,600,this);
            }
        };
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        setContentPane(panel);

        setVisible(true);

    }

    private void loadImage(){
        try{
            image=ImageIO.read(getClass().getResourceAsStream("res/devs/devs.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getX()>=30 && e.getX()<=110 && e.getY()>=10 && e.getY()<=90){
            menu.setVisible(true);
            dispose();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {

        if(e.getX()>=30 && e.getX()<=110 && e.getY()>=10 && e.getY()<=90){
            panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }else{

            panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        }
    }
}
