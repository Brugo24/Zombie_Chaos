package joguin;

import java.awt.event.*;

public class MouseHandler implements MouseListener{
    public boolean leftclicked = false;
    public boolean rightclicked = false;
    public boolean mouseHold = false;
    public int posX;
    public int posY;
    public boolean shootOnRelese = true;
    public boolean loja = false;
    public boolean[] clicked = new boolean[9];
    public boolean clickedAny = false;

    public void mouseClicked(MouseEvent e) {
        clickedAny = true;
        if(!loja) {
            shootOnRelese = true;
            if (e.getButton() == MouseEvent.BUTTON1) {
                leftclicked = true;
            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                rightclicked = true;
            }
            posX = e.getX() + 16;
            posY = e.getY() + 16;
        }
        else{
            int x = e.getX();
            int y = e.getY();
            if(x >= 15 && y >= 205+120 && x<= 265 && y<=280+120){
                clicked[0] = true;
            }
            if(x >= 322 && y >= 205+120 && x<= 572 && y<=280+120){
                clicked[1] = true;
            }
            if(x >= 629 && y >= 205+120 && x<= 879 && y<=280+120){
                clicked[2] = true;
            }
            if(x >= 936 && y >= 205+120 && x<= 1186 && y<=280+120){
                clicked[3] = true;
            }
            /////////////////////////////////
            if(x >= 15 && y >= 505+120 && x<= 265 && y<=580+120){
                clicked[4] = true;
            }
            if(x >= 322 && y >= 505+120 && x<= 572 && y<=580+120){
                clicked[5] = true;
            }
            if(x >= 629 && y >= 505+120 && x<= 879 && y<=580+120){
                clicked[6] = true;
            }
            if(x >= 936 && y >= 505+120 && x<= 1186 && y<=580+120){
                clicked[7] = true;
            }
            if(x >= 860 && y>=24 && x<=860+322 && y<=24+75){
                clicked[8] = true;
            }
        }
    }
    
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            mouseHold = true;
        }
    }
    
    
    public void mouseReleased(MouseEvent e) {
        mouseHold = false;
        if(e.getButton() == MouseEvent.BUTTON1 && shootOnRelese) {
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
