package joguin;

import java.awt.event.*;

public class MouseMotionHandler implements MouseMotionListener{
    public int posX = 0;
    public int posY = 0;
    public static boolean moved = false;
    public static boolean loja = false;
    public boolean[] hover = new boolean[9];
    public boolean hoverAny = false;

    public void mouseDragged(MouseEvent e) {
            moved = true;
            posX = e.getX() + 16;
            posY = e.getY() + 16;
    }

    public void mouseMoved(MouseEvent e) {
        hoverAny = true;
        if(!loja) {
            moved = true;
            posX = e.getX() + 16;
            posY = e.getY() + 16;
        }else{
            int x = e.getX();
            int y = e.getY();
            if(x >= 15 && y >= 205+120 && x<= 265 && y<=280+120){
                hover[0] = true;
            }else{
                hoverAny = false;
                hover[0] = false;
            }
            if(x >= 322 && y >= 205+120 && x<= 572 && y<=280+120){
                hover[1] = true;
            }else{
                hoverAny = false;
                hover[1] = false;
            }
            if(x >= 629 && y >= 205+120 && x<= 879 && y<=280+120){
                hover[2] = true;
            }else{
                hoverAny = false;
                hover[2] = false;
            }
            if(x >= 936 && y >= 205+120 && x<= 1186 && y<=280+120){
                hover[3] = true;
            }else{
                hoverAny = false;
                hover[3] = false;
            }
            ////////////////////////////////////////////////////////////////
            if(x >= 15 && y >= 505+120 && x<= 265 && y<=580+120){
                hover[4] = true;
            }else{
                hoverAny = false;
                hover[4] = false;
            }
            if(x >= 322 && y >= 505+120 && x<= 572 && y<=580+120){
                hover[5] = true;
            }else{
                hoverAny = false;
                hover[5] = false;
            }
            if(x >= 629 && y >= 505+120 && x<= 879 && y<=580+120){
                hover[6] = true;
            }else{
                hoverAny = false;
                hover[6] = false;
            }
            if(x >= 936 && y >= 505+120 && x<= 1186 && y<=580+120){
                hover[7] = true;
            }else{
                hoverAny = false;
                hover[7] = false;
            }
            if(x >= 860 && y>=24 && x<=860+322 && y<=24+75){
                hover[8] = true;
            }else{
                hoverAny = false;
                hover[8] = false;
            }
        }
    }
    
}
