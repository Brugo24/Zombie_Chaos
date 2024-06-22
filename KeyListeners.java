import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListeners implements KeyListener {
    
   Character character;
  private Point point;
   KeyListeners(Character character,Point point){
    this.character=character;
    this.point=point;
    
    

   }



    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            character.setVelX(7);
            
        }else if(e.getKeyCode()== KeyEvent.VK_LEFT){
            character.setVelX(-7);
          
        }else if(e.getKeyCode() == KeyEvent.VK_UP){
            character.setVelY(-7);
            
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
           character.setVelY(7);
            
        }
        //System.out.println(character.getPosX()+" "+character.getPosY());
        
        //character.updateLocation();
        character.setIndexAnimation();

       int imageWidth=(int)(Constants.SHOOTER_WIDTH/2.5);
      int imageHeight=(int)(Constants.SHOOTER_HEIGHT/2.5);
      
      int centerX=character.getPosX()+imageWidth/2;
      int centerY=character.getPosY()+imageHeight/2;
      centerY=Constants.HEIGHT-28-centerY;
    
      double angle=Math.atan2(point.getY()- (character.getPosY()+imageHeight/2), point.getX()-(character.getPosX()+imageWidth/2));
      
      character.setAngle(angle);
      //System.out.println(angle);
      int ortogonalPlanX=(character.getPosX()+(int)(Constants.SHOOTER_WIDTH/2.5)-7);
      int ortogonalPlanY=Constants.HEIGHT-28-(character.getPosY()+(int)(Constants.SHOOTER_HEIGHT/2.5)-22);

      int newGunPosX=(int)((ortogonalPlanX-centerX)* Math.cos(angle)-(ortogonalPlanY-centerY)*(-1)*Math.sin(angle))+centerX;
      int newGunPosY=(int)((ortogonalPlanX-centerX)*(-1)* Math.sin(angle)+(ortogonalPlanY-centerY)* Math.cos(angle))+centerY;

      newGunPosY=(Constants.HEIGHT-28)-newGunPosY;
      
      character.setGunPosX(newGunPosX);
      character.setGunPosY(newGunPosY);

        
    }

    public void keyReleased(KeyEvent e){

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            character.setVelX(0);
          }else if(e.getKeyCode()== KeyEvent.VK_LEFT){
            character.setVelX(0);
          }else if(e.getKeyCode() == KeyEvent.VK_UP){
              character.setVelY(0);
          }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
              character.setVelY(0);
          }
          //character.setIndexAnimation();
          int imageWidth=(int)(Constants.SHOOTER_WIDTH/2.5);
      int imageHeight=(int)(Constants.SHOOTER_HEIGHT/2.5);
      
      int centerX=character.getPosX()+imageWidth/2;
      int centerY=character.getPosY()+imageHeight/2;
      centerY=Constants.HEIGHT-28-centerY;
    
      double angle=Math.atan2(point.getY()- (character.getPosY()+imageHeight/2), point.getX()-(character.getPosX()+imageWidth/2));
      
      character.setAngle(angle);
      //System.out.println(angle);
      int ortogonalPlanX=(character.getPosX()+(int)(Constants.SHOOTER_WIDTH/2.5)-7);
      int ortogonalPlanY=Constants.HEIGHT-28-(character.getPosY()+(int)(Constants.SHOOTER_HEIGHT/2.5)-22);

      int newGunPosX=(int)((ortogonalPlanX-centerX)* Math.cos(angle)-(ortogonalPlanY-centerY)*(-1)*Math.sin(angle))+centerX;
      int newGunPosY=(int)((ortogonalPlanX-centerX)*(-1)* Math.sin(angle)+(ortogonalPlanY-centerY)* Math.cos(angle))+centerY;

      newGunPosY=(Constants.HEIGHT-28)-newGunPosY;
      
      character.setGunPosX(newGunPosX);
      character.setGunPosY(newGunPosY);


    }

    public void keyTyped(KeyEvent e){

    }
    
}
