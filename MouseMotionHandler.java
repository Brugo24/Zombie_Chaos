package joguin;

import java.awt.event.*;

public class MouseMotionHandler implements MouseMotionListener{
    public int posX = 0;
    public int posY = 0;
    public static boolean moved = false;

    public void mouseDragged(MouseEvent e) {
        moved = true;
        posX = e.getX()+16;
        posY = e.getY()+16;
    }

    public void mouseMoved(MouseEvent e) {
        moved = true;
        posX = e.getX()+16;
        posY = e.getY()+16;
        //System.out.println("x:"+posX+" y:"+posY);
    }
    
}
