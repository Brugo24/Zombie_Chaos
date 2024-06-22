import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadAssets {
    private BufferedImage[][] imagesCharacter=new BufferedImage[3][20];
    private BufferedImage[][] imagesZombie=new BufferedImage[2][17];

    LoadAssets(){
      loadCharacterImages();
      loadZombieImages();
    }

    void loadCharacterImages(){
         InputStream is;
        try{

            for(int i=0;i<1;i++){
              for(int j=0;j<20;j++){
                is=getClass().getResourceAsStream("./res/images/sprites/move_handgun/"+j+".png");
                imagesCharacter[i][j]=ImageIO.read(is);
              }
            }
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
                is=getClass().getResourceAsStream("./res/images/sprites/zombie/zombie_move/"+j+".png");
                imagesZombie[i][j]=ImageIO.read(is);
              }
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }




    
}
