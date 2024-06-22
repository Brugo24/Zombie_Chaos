import java.awt.*;
public class Bullets {

    private double x;
    private double y;
    private Jogo jogo;
    private int gunX,gunY;
    private int xDestiny;
    private int yDestiny;
    private double angle;
    Color bulletCOlor=new Color(252, 211, 3);
    Bullets(double x,double y,Jogo jogo,int gunX,int gunY,int xDestiny,int yDestiny){
        
        this.x=x; 
        this.y=y;
        this.jogo=jogo;
        this.gunX=gunX;
        this.gunY=gunY;
       this.xDestiny=xDestiny;
       this.yDestiny=yDestiny;
       angle=Math.atan2(yDestiny-gunY-2, xDestiny-gunX-2);
    }

    public void updateLocation(){
     x+=10* Math.cos(angle);
     y+=10* Math.sin(angle);
    }

    public void render(Graphics g){
       Graphics2D g2=(Graphics2D)g;
       
       g2.setColor(bulletCOlor);
       g2.fillRect((int)x, (int)y, 10, 10);
    }

public int getX(){return (int)x;}
public int getY(){return (int)y;}
    
}
