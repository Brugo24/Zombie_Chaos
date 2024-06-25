package joguin;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.Random;

public class Controle {
    LinkedList<Bala> bl = new LinkedList<Bala>();
    LinkedList<Zombie> zl = new LinkedList<Zombie>();
    LinkedList<Ammo> al = new LinkedList<Ammo>();

    Personagem personagem;
    LoadAssets assets;
    Zombie zombie;
    Bala bala;
    private int zombiemaxnumber = 20;
    private int zombienumber = 0;
    private int timer = 0;
    private int dmgcooldown = 100;

    Controle(Personagem personagem,LoadAssets assets){
        this.personagem = personagem;
        this.assets = assets;
    }

    private void entityColision(){
        for (int i = 0; i < al.size(); i++){
            Ammo ammo = al.get(i);
            int difX = personagem.getcolisionX() - ammo.getX();
            int difY = personagem.getcolisionY() - ammo.getY();
            double dist = Math.sqrt(difX * difX + difY * difY);
            if(dist <= 40){
                al.remove(ammo);
                personagem.gainammo();
            }
        }
    }

    private void zombieColision(){
        for(int i=0;i<zl.size();i++){
            zombie = zl.get(i);
            int difX = zombie.getX() - personagem.getcolisionX();
            int difY = zombie.getY() - personagem.getcolisionY();
            double distance = Math.sqrt(difX*difX+difY*difY);
            if(zombie.distance <= 10 && !zombie.isattacking && dmgcooldown <=0){
                zombie.attack();
            }
            if((distance <= 0.1) && dmgcooldown <= 0){
                personagem.recebedano(5);
                dmgcooldown = 100;
            }
        }
    }

    private void bulletColision(){
        for(int i=0;i<zl.size();i++){
            zombie=zl.get(i);
              for(int j=0;j<bl.size();j++){
                bala=bl.get(j);

                if(bala.getX()>= zombie.getX() && bala.getX()<=zombie.getX()+(int)(288/3) && bala.getY()>=zombie.getY() && bala.getY()<=zombie.getY()+(int)(311/3)){
                    Random random = new Random();
                    int drop = random.nextInt(1);
                    if(drop == 0){
                        al.add(new Ammo(assets.getAmmoImages(),zombie.getX(),zombie.getY()));
                    }

                    zl.remove(zombie);
                    zombienumber--;
                    bl.remove(bala);
                }
              }
        }
    }

    public void tick(Graphics2D g, AffineTransform oldState){
        Random random = new Random();
        dmgcooldown--;
        if(timer == 0 && zombienumber < zombiemaxnumber){
            timer = random.nextInt(100)+100;
            spawnzombie();
            zombienumber++;
        }
        else{
            if(timer != 0) timer--;
        }
        bulletColision();
        zombieColision();
        entityColision();
        for (int i=0; i<bl.size(); i++){
            Bala bala = bl.get(i);
            if(bala.x > 1200 || bala.y > 720 || bala.x < 0 || bala.y < 120){
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

        for(int i=0; i<al.size(); i++){
            Ammo ammo = al.get(i);
            ammo.draw(g);
        }
    }

    public void addbala(Bala bala){
        bl.add(bala);
        personagem.shot();
    }

    public void addzombie(int x, int y){
        zl.add(new Zombie(assets.getZombieImages(), personagem, x, y));
    }

    void spawnzombie(){
        int x, y, aux;
        Random random = new Random();
        aux = random.nextInt(2);
        if(aux == 0){
            x = -random.nextInt(100);
        }
        else{
            x = random.nextInt(100)+1200;
        }
        aux = random.nextInt(2);
        if(aux == 0){
            y = -random.nextInt(100);
        }
        else {
            y = random.nextInt(100)+600;
        }
        addzombie(x,y);
    }
}
