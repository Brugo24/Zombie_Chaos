import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
    
    private LinkedList<Bullets> b=new LinkedList<Bullets>();
    private LinkedList<Zombie>z=new LinkedList<Zombie>();

    Zombie tempZombie;

    Bullets tempBullet;
    private Jogo jogo;
    private Point point;
    private Zombie zombie;
    private int horda;
    private int zombieQtde;
    private Random random;
    private Character character;
    private LoadAssets loadAssets;

    Controller(Jogo jogo,Point point,Character character,LoadAssets loadAssets){
     this.jogo=jogo;
     this.point=point;
     horda=1;
     this.character=character;
     this.loadAssets=loadAssets;
     setQtdeZombie();
     addZombie();


    }

    private void setQtdeZombie(){
        random=new Random();
        
        if(horda==1){
            zombieQtde=random.nextInt(11)+10;
        }

    }

    public void tick(){
        
        bulletColision();
        
        for(int i=0;i<b.size();i++){
            //System.out.println(i);

            tempBullet=b.get(i);

            if(tempBullet.getX()<0 || tempBullet.getX()>Constants.WIDTH || tempBullet.getY()<0 || tempBullet.getY()>Constants.HEIGHT)removeBullet(tempBullet);
            tempBullet.updateLocation();;
            //System.out.println(tempBullet.getX()+" "+tempBullet.getY());
        }

        for(int i=0;i<zombieQtde;i++){
            //System.out.println(i);

            tempZombie=z.get(i);

            
           tempZombie.tick();
            //System.out.println(tempBullet.getX()+" "+tempBullet.getY());
        }
       
      

    }

    public void render(Graphics g,AffineTransform old){
        
        for(int i=0;i<b.size();i++){
            tempBullet=b.get(i);
            
            tempBullet.render(g);
            }

             for(int i=0;i<z.size();i++){
                tempZombie=z.get(i);
                
                tempZombie.render(g,old);
            }

    }

    private void addZombie(){
        random=new Random();
        int posX,posY;
       for(int i=0;i<zombieQtde;i++){
          posX=random.nextInt(Constants.WIDTH);
          posY=random.nextInt(Constants.HEIGHT);

          z.add(new Zombie(loadAssets, character, posX, posY));


       }
    }

    public void addBullet(Bullets bullet){

        //System.out.println("adicionei");

        b.add(bullet);

    }

    public void removeBullet(Bullets bullet){
        b.remove(bullet);
    }

    private void bulletColision(){
        for(int i=0;i<z.size();i++){
            tempZombie=z.get(i);
              for(int j=0;j<b.size();j++){
                tempBullet=b.get(j);

                if(tempBullet.getX()>= tempZombie.getX() && tempBullet.getX()<=tempZombie.getX()+(int)(288/3) && tempBullet.getY()>=tempZombie.getY() && tempBullet.getY()<=tempZombie.getY()+(int)(311/3)){

                    removeZombie(tempZombie);
                    removeBullet(tempBullet);
                    zombieQtde--;
                    
                    
                }
                
              }
        }
    }

    private void removeZombie(Zombie zombie){
     z.remove(zombie);
    }
}
