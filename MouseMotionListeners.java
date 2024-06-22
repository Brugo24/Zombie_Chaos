
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionListeners  implements MouseMotionListener{
    private Character character;
    private Point point;
  
    MouseMotionListeners(Character character,Point point){
        this.character=character;
        this.point=point;
        
        
    }

    public void mouseDragged(MouseEvent e){

    }
    public void mouseMoved(MouseEvent e){
        //Calculo da rotacao do personagem

      int imageWidth=(int)(Constants.SHOOTER_WIDTH/2.5);
      int imageHeight=(int)(Constants.SHOOTER_HEIGHT/2.5);
      
      int centerX=character.getPosX()+imageWidth/2;
      int centerY=character.getPosY()+imageHeight/2;
      centerY=Constants.HEIGHT-28-centerY;
    
      double angle=Math.atan2(e.getY()- (character.getPosY()+imageHeight/2), e.getX()-(character.getPosX()+imageWidth/2));
      
      character.setAngle(angle);
      //System.out.println(angle);
      int ortogonalPlanX=(character.getPosX()+(int)(Constants.SHOOTER_WIDTH/2.5)-7);
      int ortogonalPlanY=Constants.HEIGHT-28-(character.getPosY()+(int)(Constants.SHOOTER_HEIGHT/2.5)-22);

      int newGunPosX=(int)((ortogonalPlanX-centerX)* Math.cos(angle)-(ortogonalPlanY-centerY)*(-1)*Math.sin(angle))+centerX;
      int newGunPosY=(int)((ortogonalPlanX-centerX)*(-1)* Math.sin(angle)+(ortogonalPlanY-centerY)* Math.cos(angle))+centerY;

      newGunPosY=(Constants.HEIGHT-28)-newGunPosY;
      
      character.setGunPosX(newGunPosX);
      character.setGunPosY(newGunPosY);

      point.setLocation(e.getX(), e.getY());
      //System.out.println(newGunPosX+" "+newGunPosY);


    }
    
}
