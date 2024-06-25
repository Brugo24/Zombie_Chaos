package joguin;

import java.awt.event.*;

public class MouseHandler implements MouseListener{
    public boolean clicked = false;
    public int posX;
    public int posY;

    public void mouseClicked(MouseEvent e) {
        clicked = true;
        posX = e.getX() + 16;
        posY = e.getY() + 16;
    }
    
    public void mousePressed(MouseEvent e) {
    }
    
    
    public void mouseReleased(MouseEvent e) {
        clicked = true;
        posX = e.getX() + 16;
        posY = e.getY() + 16;
    }


    public void mouseEntered(MouseEvent e) {

    }


    public void mouseExited(MouseEvent e) {

    }
    
}
