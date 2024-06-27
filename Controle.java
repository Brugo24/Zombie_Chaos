package joguin;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.Random;

public class Controle {
    LinkedList<Bala> bl = new LinkedList<Bala>();
    LinkedList<Zombie> zl = new LinkedList<Zombie>();
    LinkedList<Ammo> al = new LinkedList<Ammo>();
    LinkedList<Medkit> ml = new LinkedList<Medkit>();

    Personagem personagem;
    LoadAssets assets;
    Zombie zombie;
    Bala bala;
    private int zombiemaxnumber = 10;
    private int zombienumber = 0;
    private int timer = 0;
    private int dmgcooldown = 50;
    private boolean canmeleedmg = true;
    private int horda=0;
    private int hordaMaxNumber=30;
    int matar=0;
    int cont=0;
    private Painel painel;
    private int zombieSpeed = 4;
    private int zombieHP = 1;
    private int zombieDMG = 5;
    private int tickDiv = 1;
    private int ammoDropValue = 0;
    private int medKitDropValue = 0;
    private int ammoValue = 3;
    private int medKitValue = 10;
    public boolean canShoot;

    Controle(Personagem personagem,LoadAssets assets,Painel painel){
        this.personagem = personagem;
        this.assets = assets;
        this.painel = painel;
    }

    void buffAmmoDropRate(){
        ammoDropValue = ammoDropValue + 20;
    }

    void buffMedKitDropRate(){
        medKitDropValue = medKitDropValue + 20;
    }

    void buffAmmoReceive(){
        ammoValue *= 2;
    }

    void buffMedkitReceive(){
        medKitValue += 5;
    }

    private void killZombie(Zombie zombie){
        Random random = new Random();
        int ammodrop = random.nextInt(100);
        int medkitdrop = random.nextInt(100);
        if(ammodrop <= 20 + ammoDropValue){
            al.add(new Ammo(assets.getAmmoImages(),zombie.getX(),zombie.getY()));
        }
        if(medkitdrop <= 20 + medKitDropValue && personagem.getvida() < 100){
            ml.add(new Medkit(assets.getMedKitImage(),zombie.getX(),zombie.getY()));
        }
        zl.remove(zombie);
        zombienumber--;
        personagem.ganhadinheiros(10 + (int)horda*(horda/2));
        cont++;
    }

    private void meleeColision(){
        for(int i=0; i< zl.size(); i++){
            Zombie zombie = zl.get(i);
            int distX = personagem.getMeleeRangeX() - (zombie.getX()+(288/3)/2);
            int distY = personagem.getMeleeRangeY() - (zombie.getY()+(int)(311/3)/2);
            double dist = Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
            if(dist <= 50){
                zombie.dano(personagem.getMeleeDMG());
                if(zombie.getVida() <= 0) {
                    killZombie(zombie);
                }
                canmeleedmg = false;
            }
        }
    }

    private void entityColision(){
        for (int i = 0; i < al.size(); i++){
            Ammo ammo = al.get(i);
            int difX = personagem.getcolisionX() - ammo.getX();
            int difY = personagem.getcolisionY() - ammo.getY();
            double dist = Math.sqrt(difX * difX + difY * difY);
            if(dist <= 40){
                al.remove(ammo);
                personagem.gainammo(ammoValue);
            }
        }
        for (int i = 0; i < ml.size(); i++){
            Medkit medkit = ml.get(i);
            int difX = personagem.getcolisionX() - medkit.getX();
            int difY = personagem.getcolisionY() - medkit.getY();
            double dist = Math.sqrt(difX * difX + difY * difY);
            if(dist <= 40){
                ml.remove(medkit);
                personagem.heal(medKitValue);
            }
        }
    }

    private void zombieColision(){
        for(int i=0;i<zl.size();i++){
            zombie = zl.get(i);
            int difX = zombie.getX() - personagem.getcolisionX();
            int difY = zombie.getY() - personagem.getcolisionY();
            double distance = Math.sqrt(difX*difX+difY*difY);
            if(zombie.distance <= 2 && !zombie.isattacking && dmgcooldown <=0){
                zombie.attack();
            }
            if((distance <= 2) && zombie.isattacking && dmgcooldown <= 0){
                if(personagem.getvida() > 0) {
                    personagem.recebedano(zombieDMG);
                }
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
                    zombie.dano(bala.getDmg());
                    if(zombie.getVida() <= 0){
                        killZombie(zombie);
                    }
                    bl.remove(bala);
                }
            }
        }
    }

    void zombieBuff(){
        zombieSpeed++;
        zombieHP++;
        zombieDMG+=5;
    }

    public int getHorda(){return horda;}

    public void rodada() {
        matar = hordaMaxNumber;
        if ((matar - cont) <= 0) {
            //System.out.println("horda " + horda + " concluida");
            tickDiv++;
            zl.clear();
            zombienumber = 0;
            personagem.gainammo(3 * al.size());
            al.clear();
            horda++;
            if((horda+1)%3 == 0){
                hordaMaxNumber = 30;
                zombieBuff();
                tickDiv=1;
            }
            cont = 0;
            //System.out.println("Iniciando horda " + horda);
            //iniciar transicao

            painel.setTickIntro();
            canShoot = false;
        }
    }

    public void tick(Graphics2D g, AffineTransform oldState){
        Random random = new Random();
        dmgcooldown--;
        if(timer == 0 && zombienumber < zombiemaxnumber){
            timer = random.nextInt(50/tickDiv)+50/tickDiv;
            spawnzombie();
            zombienumber++;
        }
        else{
            if(timer != 0) timer--;
        }
        if(personagem.ismelee && canmeleedmg){
            meleeColision();
        }else if(!personagem.ismelee) {
            canmeleedmg = true;
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

        for(int i=0; i<ml.size(); i++){
            Medkit medkit = ml.get(i);
            medkit.draw(g);
        }
    }

    public void addbala(Bala bala){
        bl.add(bala);
        personagem.shot();
    }

    public void addzombie(int x, int y){
        zl.add(new Zombie(assets.getZombieImages(), personagem, x, y,zombieHP,zombieSpeed,zombieDMG));
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
