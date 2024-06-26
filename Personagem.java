package joguin;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Personagem {
    private int posX;
    private int posY;
    private final int largura = 65;
    private final int comprimento = 80;
    private int vida;
    private double angulo;
    private int velocidade;
    private final BufferedImage[][] sprites;
    private int spriteCounter;
    private int distanceX;
    private int distanceY;
    private int arma = 0;
    private int colisionX;
    private int colisionY;
    private int ammo;
    private int personagemState;
    public boolean ismelee;
    private int tickinterval = 2;
    private int meleeRangeX;
    private int meleeRangeY;
    private int meleeDMG;
    private int dinheiros;

    Painel gp;
    KeyHandler keyH;
    MouseMotionHandler mouseMH;
    MouseHandler mouseH;
    Pistola pistola;

    public Personagem(Painel gp, KeyHandler keyH, MouseMotionHandler mouseMH, MouseHandler mouseH, BufferedImage[][] sprites){
        this.gp = gp;
        this.keyH = keyH;
        this.mouseMH = mouseMH;
        this.mouseH = mouseH;
        this.sprites = sprites;
        setDefaultValues();
    }

    void setDefaultValues(){
        colisionX = posX = 600;
        colisionY = posY = 300;
        velocidade = 5;
        spriteCounter = 0;
        angulo = 0;

        ismelee = false;
        personagemState = 0;

        ammo = 1000;
        vida = 10;
        dinheiros = 100;
        meleeDMG = 1;
    }

    public int getX(){return posX;}
    
    public int getY(){return posY;}

    public int getcolisionX(){return colisionX - 40;}

    public int getcolisionY(){return colisionY - 32;}

    public int getLargura(){return largura;}

    public int getComprimento(){return comprimento;}

    public double getAngulo(){return angulo;}

    public int getvida(){return vida;}

    public void setX(int x){this.posX = x;}

    public void setY(int y){this.posY = y;}

    public void recebedano(int dano){this.vida -= dano;}

    public int getAmmo(){return ammo;}

    public int getdinheiros(){return dinheiros;}

    public void ganhadinheiros(int ganhas){dinheiros+=ganhas;}

    public int getMeleeDMG(){return meleeDMG;}

    public void heal(int vida){
        this.vida += vida;
        if(this.vida>100) this.vida = 100;
    }

    public void buffSpeed(){
        velocidade++;
    }

    public void buffMeleeDmg(){
        meleeDMG++;
    }

    void updateangulo(){
        //angulo,posX+(int)(Constants.SHOOTER_WIDTH/2.5)/2,posY+(int)(Constants.SHOOTER_HEIGHT/2.5)/2
        distanceX = mouseMH.posX - 75 - posX;
        distanceY = mouseMH.posY - 47 - posY;
        angulo = Math.atan2(distanceY, distanceX);
        colisionX = (-(int)(30 * Math.cos(angulo) - 12 * Math.sin(angulo)) + posX + 75);
        colisionY = (-(int)(30 * Math.sin(angulo) + 12 * Math.cos(angulo)) + posY + 47);
        meleeRangeX = ((int)(15 * Math.cos(angulo) - 5 * Math.sin(angulo)) + posX + 75);
        meleeRangeY = ((int)(15 * Math.sin(angulo) + 5 * Math.cos(angulo)) + posY + 47);
    }

    public void shot(){ammo--;}

    public void gainammo(int ammo){this.ammo += ammo;}

    void melee(){
        ismelee = true;
        personagemState = 1;
        spriteCounter = 0;
    }

    public int getMeleeRangeX(){return meleeRangeX;}

    public int getMeleeRangeY(){return meleeRangeY;}

    public void update(){
        if(mouseH.rightclicked && !ismelee){
            melee();
        }
        if(tickinterval == 0) {
            if (keyH.anyKPressed || ismelee) {
                spriteCounter++;
            }
            tickinterval = 2;
        }else{
            tickinterval--;
        }

        if(ammo == 0){
            mouseH.leftclicked = false;
        }

        updateangulo();

        if(keyH.upPressed && posY > 150){
            //System.out.println("cima");
            posY -= velocidade;
        }
        if(keyH.downPressed && (posY < Constants.HEIGHT-(int)(Constants.SHOOTER_HEIGHT) - 20)){
            //System.out.println("baixo");
            posY += velocidade;
        }
        if(keyH.rightPressed && posX < (Constants.WIDTH-(int)(Constants.SHOOTER_WIDTH) - 30)){
            //System.out.println("direita");
            posX += velocidade;
        }
        if(keyH.leftPressed && posX > 0) {
            //System.out.println("esquerda");
            posX -= velocidade;
        }
        if(spriteCounter == 20 && personagemState == 0) spriteCounter = 0;
        else if(spriteCounter == 14 && personagemState == 1) {
            spriteCounter = 0;
            personagemState = 0;
            ismelee = false;
            mouseH.rightclicked = false;
        }
    }

    void drawlifebar(Graphics2D g){
        g.setColor(Color.green);
        g.fillRect(120, 20, (int)(vida * 3), 25);
        g.setColor(Color.red);
        g.fillRect(vida*3 + 120, 20, 300 - vida*3, 25);
    }

    public void draw(Graphics2D g){
        AffineTransform at = g.getTransform();
        g.setColor(Color.BLUE);
        //System.out.println("angulo:"+angulo+" angulorotação:"+angulorotacao);
        g.rotate(angulo,posX+75,posY+47);
        //g.setColor(Color.blue);
        //g.drawRect(posX, posY, comprimento, largura);
        if(personagemState == 0) {
            g.drawImage(sprites[personagemState][spriteCounter], posX, posY, comprimento, largura, null);
        }
        else{
            g.drawImage(sprites[personagemState][spriteCounter], posX, posY, 110, 89, null);
        }
        g.setTransform(at);
        //g.setColor(Color.red);
        //g.fillOval(meleeRangeX, meleeRangeY, 5, 5);
    }
}
