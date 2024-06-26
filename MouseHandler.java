package joguin;

import java.awt.event.*;

public class MouseHandler implements MouseListener{
    public boolean leftclicked = false;
    public boolean rightclicked = false;
    public boolean mouseHold = false;
    public int posX;
    public int posY;
    public boolean machineGun = false;

    public void mouseClicked(MouseEvent e) {
        mouseHold = false;
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftclicked = true;
        }
        if(e.getButton() == MouseEvent.BUTTON3) {
            rightclicked = true;
        }
        posX = e.getX() + 16;
        posY = e.getY() + 16;
    }
    
    public void mousePressed(MouseEvent e) {
        if(machineGun){
            mouseHold = true;
        }
    }
    
    
    public void mouseReleased(MouseEvent e) {
        mouseHold = false;
        if(e.getButton() == MouseEvent.BUTTON1) {
            leftclicked = true;
        }
        if(e.getButton() == MouseEvent.BUTTON3) {
            rightclicked = true;
        }
        posX = e.getX() + 16;
        posY = e.getY() + 16;
    }


    public void mouseEntered(MouseEvent e) {

    }


    public void mouseExited(MouseEvent e) {

    }
    
}
