package joguin;

import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;

public class Pistola{
    private int dmg;
    BufferedImage bulletImage;
    Controle control;
    MouseHandler mouseH;
    MouseMotionHandler mouseMH;
    Personagem personagem;
    private int tick;
    private int tickSet = 40;
    
    public Pistola(BufferedImage bulletImage, MouseHandler mouseH,MouseMotionHandler mouseMH, Controle control,Personagem personagem){
        this.mouseH = mouseH;
        this.control = control;
        this.bulletImage = bulletImage;
        this.personagem = personagem;
        this.mouseMH = mouseMH;
        setDefaultValues();
    }

    void setDefaultValues(){
        dmg = 1;
        tick = tickSet;
    }

    public void atiraSound(float volume){
        try{
            BufferedInputStream myStream = new BufferedInputStream(getClass().getResourceAsStream("res/sounds/pistola/pistola_tiro.wav"));
            Clip clip=AudioSystem.getClip();
            AudioInputStream inputStream=AudioSystem.getAudioInputStream(myStream);
            clip.open(inputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(volume));
            clip.start();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void atira(int X, int Y){
        atiraSound(1f);
        control.addbala(new Bala(bulletImage,X, Y, mouseMH.posX, mouseMH.posY,dmg));
    }

    public void buffFireRate(){
        tickSet /= 2;
    }

    public void buffDmg(){
        dmg++;
    }

    public int getDmg(){
        return dmg;
    }

    public void update(int X, int Y){
        if(mouseH.mouseHold){
            tick--;
            if(tick <= 0){
                mouseH.shootOnRelese = false;
            }
        }
        if(personagem.ismelee || !control.canShoot){
            mouseH.leftclicked = false;
        }
        if(((mouseH.leftclicked && !mouseH.mouseHold) || (mouseH.mouseHold && tick<=0)) && personagem.getAmmo() > 0 && control.canShoot){
            tick=tickSet;
            atira(X, Y);
            mouseH.leftclicked=false;
        }
    }

}
