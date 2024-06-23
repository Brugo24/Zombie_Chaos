package joguin;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

public class Controle {
    LinkedList<Bala> bl = new LinkedList<Bala>();
    LinkedList<Zombie> zl = new LinkedList<Zombie>();
    Personagem personagem;
    LoadAssets assets;
    Zombie zombie;
    Bala bala;

    Controle(Personagem personagem,LoadAssets assets){
        this.personagem = personagem;
        this.assets = assets;
    }

    private void zombieColision(){
        for(int i=0;i<zl.size();i++){
            zombie = zl.get(i);
            if(zombie.getX() == personagem.getX() && zombie.getY() == personagem.getY()){
                personagem.recebedano(2);
            }
        }
    }

    private void bulletColision(){
        for(int i=0;i<zl.size();i++){
            zombie=zl.get(i);
              for(int j=0;j<bl.size();j++){
                bala=bl.get(j);

                if(bala.getX()>= zombie.getX() && bala.getX()<=zombie.getX()+(int)(288/3) && bala.getY()>=zombie.getY() && bala.getY()<=zombie.getY()+(int)(311/3)){

                    zl.remove(zombie);
                    bl.remove(bala);
                }
              }
        }
    }

    public void tick(Graphics2D g, AffineTransform oldState){
        bulletColision();
        zombieColision();
        for (int i=0; i<bl.size(); i++){
            Bala bala = bl.get(i);
            if(bala.x > 1200 || bala.y > 600 || bala.x < 0 || bala.y < 0){
                bl.remove(i);
                continue;
            }
            bala.draw(g);
            bala.update();
        }

        for (int i=0; i<zl.size(); i++){
            Zombie zombie = zl.get(i);
            zombie.tick();
            zombie.render(g, oldState);
        }
    }

    public void addbala(Bala bala){
        bl.add(bala);
    }

    public void addzombie(){
        zl.add(new Zombie(assets.getZombieImages(), personagem, 800, 400));
    }
}
