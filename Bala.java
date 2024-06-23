package joguin;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bala extends Projetil {
    private final double direction;
    
    public Bala(int startX, int startY, int endX, int endY){
        this.x = startX + 75;
        this.y = startY + 47;
        this.xf = endX;
        this.yf = endY;
        //System.out.println("mX: "+xf+" mY: "+yf);
        velocidade = 20;
        direction=Math.atan2(yf-y-2, xf-x-2);
    }

    void update(){
        x+= (int) (velocidade* Math.cos(direction));
        y+= (int) (velocidade* Math.sin(direction));
    }

    public int getX(){return x;}
    
    public int getY(){return y;}

    void draw(Graphics2D g){
        //g2.setColor(Color.red);
        //g2.drawLine(X, Y, Xf, Yf);
        g.setColor(Color.yellow);
        g.fillOval(x,y,10,10);
    }

}
