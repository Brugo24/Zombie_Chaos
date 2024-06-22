import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListeners implements MouseListener {
  
    private Character character;
    private Jogo jogo;
    private Controller controller;
    private Point point;
    MouseListeners(Character character,Jogo jogo,Controller controller,Point point){
        this.character=character;
        this.jogo=jogo;
        this.controller=controller;
        this.point=point;

        
    }
    public void mouseClicked(MouseEvent e){
        controller.addBullet(new Bullets(character.getGunPosX(),character.getGunPosY(), jogo,character.getGunPosX(),character.getGunPosY(),(int)point.getX(),(int)point.getY()));
          
    }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e){

    }
    public void mousePressed(MouseEvent e){

    }
    public void mouseReleased(MouseEvent e){
        
    }
    
}
