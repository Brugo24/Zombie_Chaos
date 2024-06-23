package joguin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadAssets {
    private final BufferedImage[][] imagesCharacter=new BufferedImage[3][20];
    private final BufferedImage[][] imagesZombie=new BufferedImage[2][17];
    private BufferedImage ground;
    private BufferedImage playericon;

    LoadAssets(){
      loadCharacterImages();
      loadZombieImages();
      loadBackgroundImages();
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

    BufferedImage[][] getCharacterImages(){
        return imagesCharacter;
    }
    BufferedImage[][] getZombieImages(){
        return imagesZombie;
    }

    void loadZombieImages(){
        InputStream is;
        try{

            for(int i=0;i<1;i++){
              for(int j=0;j<17;j++){
                is=getClass().getResourceAsStream("./res/sprites/zombie/zombie_move/"+j+".png");
                imagesZombie[i][j]=ImageIO.read(is);
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
