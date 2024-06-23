package joguin;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Pistola{
    public int dano;
    public int velocidade;
    public int cadencia;
    Controle control;
    MouseHandler mouseH;
    
    public Pistola(MouseHandler mouseH,Controle control){
        this.mouseH = mouseH;
        this.control = control;

        setDefaultValues();
    }

    void setDefaultValues(){
        dano = 5;
        velocidade = 10;
        cadencia = 0;
    }

    void atiraSound(){
        try{
            Clip clip=AudioSystem.getClip();
            AudioInputStream inputStream=AudioSystem.getAudioInputStream(getClass().getResourceAsStream("./res/sounds/pistola/pistola_tiro.wav"));
            clip.open(inputStream);
            float volume = clip.getLevel();
            System.out.println("volume:"+volume+"dB");
            clip.start();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void atira(int X, int Y){
        atiraSound();
        System.out.println("mX: "+mouseH.posX+" mY: "+mouseH.posY);
        control.addbala(new Bala(X, Y, mouseH.posX, mouseH.posY));
    }

    public void update(int X, int Y){
        if(mouseH.clicked){
            atira(X, Y);
            mouseH.clicked = false;
        }
    }

}
