package joguin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadAssets {
    private final BufferedImage[][] imagesCharacter=new BufferedImage[3][20];
    private final BufferedImage[][] imagesZombie=new BufferedImage[2][17];
    private BufferedImage bulletImage;
    private final int[] qteSpriteZombie = {17, 9};
    private BufferedImage ground;
    private BufferedImage playericon;
    private BufferedImage ammoImage;

    LoadAssets(){
      loadCharacterImages();
      loadZombieImages();
      loadBackgroundImages();
      loadBulletImages();
      loadAmmoImage();
    }

    void loadCharacterImages(){
         InputStream is;
        try{

            for(int i=0;i<1;i++){
              for(int j=0;j<20;j++){
                is=getClass().getResourceAsStream("./res/sprites/personagem/"+j+".png");
                imagesCharacter[i][j]=ImageIO.read(is);
              }
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        is=getClass().getResourceAsStream("./res/sprites/personagemicon/icon.png");
        try {
            playericon = ImageIO.read(is);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    void loadBulletImages(){
        InputStream is;
        try{
            is = getClass().getResourceAsStream("./res/sprites/bullet/bullet.png");
            bulletImage = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void loadAmmoImage(){
        InputStream is;
        try{
            is = getClass().getResourceAsStream("./res/sprites/entity/Ammo.jpeg");
            ammoImage = ImageIO.read(is);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    BufferedImage getBulletImage(){
        return bulletImage;
    }

    BufferedImage[][] getCharacterImages(){
        return imagesCharacter;
    }
    BufferedImage[][] getZombieImages(){
        return imagesZombie;
    }

    BufferedImage getAmmoImages(){
        return ammoImage;
    }

    void loadZombieImages(){
        InputStream is;
        try{
            for(int i=0;i<qteSpriteZombie.length;i++){
              for(int j=0;j<qteSpriteZombie[i];j++){
                switch (i){
                    case 0:
                        is=getClass().getResourceAsStream("./res/sprites/zombie/zombie_move/"+j+".png");
                        imagesZombie[i][j]=ImageIO.read(is);
                        break;
                    case 1:
                        is=getClass().getResourceAsStream("./res/sprites/zombie/zombie_attack/"+j+".png");
                        imagesZombie[i][j]=ImageIO.read(is);
                        break;
                }
              }
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    void loadBackgroundImages(){
        InputStream is;
        is=getClass().getResourceAsStream("./res/sprites/ground/ground.jpg");
        try {
            ground = ImageIO.read(is);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    BufferedImage getBackground(){return ground;}

    BufferedImage getPlayericon(){return playericon;}
}
