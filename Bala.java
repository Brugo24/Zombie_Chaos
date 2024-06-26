package joguin;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Bala extends Projetil {
    private final double direction;
    private int dmg;
    BufferedImage bulletImage;
    
    public Bala(BufferedImage bulletImage, int startX, int startY, int endX, int endY){
        this.x = startX + 75;
        this.y = startY + 47;
        this.xf = endX;
        this.yf = endY;
        this.bulletImage = bulletImage;
        //System.out.println("mX: "+xf+" mY: "+yf);
        velocidade = 20;
        dmg = 1;
        direction=Math.atan2(yf-y-2, xf-x-2);
    }

    void update(){
        x+= (int) (velocidade* Math.cos(direction));
        y+= (int) (velocidade* Math.sin(direction));
    }

    public int getX(){return x;}
    
    public int getY(){return y;}

    public int getDmg(){return dmg;}

    void draw(Graphics2D g){
        AffineTransform oldState;
        oldState = g.getTransform();
        //g2.setColor(Color.red);
        //g2.drawLine(X, Y, Xf, Yf);
        g.rotate(direction,x,y);
        g.drawImage(bulletImage,x,y-15,25,35,null);
        g.setTransform(oldState);
    }

}
