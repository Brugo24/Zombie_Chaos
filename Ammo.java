package joguin;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ammo extends Entity{

    Ammo(BufferedImage icon, int x, int y){
        this.icon = icon;
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}

    public int getY(){return y;}

    public void draw(Graphics2D g){
        g.drawImage(icon,x,y,40,40,null);
    }
}
