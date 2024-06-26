package joguin;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Zombie {
    private int x,y;
    private double zombieSpeed;
    private BufferedImage[][] zombieImages;
    private int indexImage;
    private Personagem personagem;
    public boolean isattacking;
    private int zombieState;
    public double distance;
    public int diffX;
    public int diffY;
    private int tickinterval = 2;
    private int vida;
    private int zombieDMG;

    Zombie(BufferedImage[][] zombieImage,Personagem personagem,int x,int y,int vida,int zombieSpeed,int zombieDMG){
        this.personagem = personagem;
        //zombieSpeed=1;
        indexImage=0;
        isattacking = false;
        zombieState=0;
        this.zombieImages=zombieImage;
        this.x=x;
        this.y=y;
        this.vida=vida;
        this.zombieSpeed=zombieSpeed;
        this.zombieDMG=zombieDMG;
    }

    public void tick(){
        move();
        if(tickinterval==0) {
            setIndexImage();
            tickinterval = 2;
        }
        else{
            tickinterval--;
        }
    }

    public int getVida(){return vida;}

    public int getZombieDMG(){return zombieDMG;}

    public void dano(int dano){vida-=dano;}

    private void setIndexImage(){
        indexImage++;
        if(!isattacking) {
            if (indexImage > 16) {
                indexImage = 0;
            }
        }
        else {
            if (indexImage > 8){
                indexImage = 0;
                isattacking = false;
                zombieState = 0;
            }
        }
        if(indexImage>16)indexImage=0;
    }

    public void attack(){
        isattacking=true;
        indexImage = 0;
        zombieState = 1;
    }

    public void render(Graphics g,AffineTransform old){
        int imageWidth=(int)(288/3);
        int imageHeight=(int)(311/3);
      
        int centerX=x+imageWidth/2;
        int centerY=y+imageHeight/2;
        centerY=Constants.HEIGHT-28-centerY;
    
        double angle=Math.atan2(personagem.getcolisionY() - (y+imageHeight/2), personagem.getcolisionX() - (x+imageWidth/2));
        //double angle=Math.atan2(personagem.getcolisionY(), personagem.getcolisionX());
      

        Graphics2D g2=(Graphics2D)g;
        g2.rotate(angle,x+(int)(288/3)/2,y+(int)(311/3)/2);
        g2.drawImage(zombieImages[zombieState][indexImage],this.x,this.y,(int)(288/3),(int)(311/3),null);

        g2.setTransform(old);
    }

    private void move(){
        diffX = personagem.getcolisionX() - x;
        diffY = personagem.getcolisionY() - y;
        distance = (double)(Math.sqrt(diffX * diffX + diffY * diffY));

        if (distance >= 0.1 && !isattacking) {
            x += (int) (zombieSpeed * (diffX / distance));
            y += (int) (zombieSpeed * (diffY / distance));
        } else{
            x += (int) ((zombieSpeed - 2) * (diffX / distance));
            y += (int) ((zombieSpeed - 2) * (diffY / distance));
        }
    }

    public int getX(){return x;}
    public int getY(){return y;}
}
