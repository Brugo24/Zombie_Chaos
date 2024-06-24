package joguin;

import java.awt.*;
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

        vida = 100;
    }

    public int getX(){return posX;}
    
    public int getY(){return posY;}

    public int getLargura(){return largura;}

    public int getComprimento(){return comprimento;}

    public double getAngulo(){return angulo;}

    public int getvida(){return vida;}

    public void setX(int x){this.posX = x;}

    public void setY(int y){this.posY = y;}

    public void recebedano(int dano){this.vida -= dano;}

    void updateangulo(){
        //angulo,posX+(int)(Constants.SHOOTER_WIDTH/2.5)/2,posY+(int)(Constants.SHOOTER_HEIGHT/2.5)/2
        distanceX = mouseMH.posX - 75 - posX;
        distanceY = mouseMH.posY - 47 - posY;
        angulo = Math.atan2(distanceY, distanceX);
    }

    public void update(){
        if(keyH.anyKPressed){
            spriteCounter++;
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
        if(spriteCounter == 20) spriteCounter = 0;
    }

    void drawlifebar(Graphics2D g){
        g.setColor(Color.green);
        g.fillRect(120, 20, (int)(vida * 3), 25);
    }

    public void draw(Graphics2D g){
        g.setColor(Color.BLUE);
        //System.out.println("angulo:"+angulo+" angulorotação:"+angulorotacao);
        g.rotate(angulo,posX+75,posY+47);
        //g.setColor(Color.blue);
        //g.drawRect(posX, posY, comprimento, largura);
        g.drawImage(sprites[arma][spriteCounter], posX, posY, comprimento, largura, null);
    }
}
