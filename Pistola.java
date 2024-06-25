package joguin;

import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Pistola{
    public int dano;
    public int velocidade;
    public int cadencia;
    BufferedImage bulletImage;
    Controle control;
    MouseHandler mouseH;
    Personagem personagem;
    
    public Pistola(BufferedImage bulletImage, MouseHandler mouseH, Controle control,Personagem personagem){
        this.mouseH = mouseH;
        this.control = control;
        this.bulletImage = bulletImage;
        this.personagem = personagem;
        setDefaultValues();
    }

    void setDefaultValues(){
        dano = 5;
        velocidade = 10;
        cadencia = 0;
    }

    public void atiraSound(float volume){
        try{
            Clip clip=AudioSystem.getClip();
            AudioInputStream inputStream=AudioSystem.getAudioInputStream(getClass().getResourceAsStream("./res/sounds/pistola/pistola_tiro.wav"));
            clip.open(inputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(volume));
            System.out.println("volume:"+volume+"dB");
            clip.start();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void atira(int X, int Y){
        atiraSound(1f);
        System.out.println("mX: "+mouseH.posX+" mY: "+mouseH.posY);
        control.addbala(new Bala(bulletImage,X, Y, mouseH.posX, mouseH.posY));
    }

    public void update(int X, int Y){
        if(mouseH.leftclicked && personagem.getAmmo() > 0){
            atira(X, Y);
            mouseH.leftclicked = false;
        }
    }

}
