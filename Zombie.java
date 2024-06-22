import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Zombie {
    private int x,y;
    private double zombieSpeed=2;
    private BufferedImage[][] zombieImages;
    private int indexImage;
    private Character character;
    Zombie(LoadAssets loadAssets,Character character,int x,int y){
     this.character=character;
      //zombieSpeed=1;
      indexImage=0;
      zombieImages=loadAssets.getZombieImages();
      this.x=x;
      this.y=y;
    }

    public void tick(){
        setIndexImage();
        move();
    }

    private void setIndexImage(){
        indexImage++;
        if(indexImage>16)indexImage=0;
    }
    
    public void render(Graphics g,AffineTransform old){
      int imageWidth=(int)(288/3);
      int imageHeight=(int)(311/3);
      
      int centerX=x+imageWidth/2;
      int centerY=y+imageHeight/2;
      centerY=Constants.HEIGHT-28-centerY;
    
      double angle=Math.atan2(character.getPosY()- (y+imageHeight/2), character.getPosX()-(x+imageWidth/2));
      

        Graphics2D g2=(Graphics2D)g;      
         g2.rotate(angle,x+(int)(288/3)/2,y+(int)(311/3)/2);
         g2.drawImage(zombieImages[0][indexImage],this.x,this.y,(int)(288/3),(int)(311/3),null);

         g2.setTransform(old);
    }

    private void move(){
        int diffX = character.getPosX() - x;
        int diffY = character.getPosY() - y;
        double distance = Math.sqrt(diffX * diffX + diffY * diffY);

        if (distance != 0) {
            x += (int) (zombieSpeed * (diffX / distance));
            y += (int) (zombieSpeed * (diffY / distance));
        }
    }

    public int getX(){return x;}
    public int getY(){return y;}
}
