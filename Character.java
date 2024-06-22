public class Character {
    private int health;
    private int posX;
    private int posY;
    private double velX=0.;
    private double velY=0.;
    private int indexAnimation=0;
    private int indexGun=0;
    private double angle=0;
    private int gunPosX;
    private int gunPosY;

   Character(){
     posX=Constants.WIDTH/2;
     posY=Constants.HEIGHT/2;
     gunPosX=(posX+(int)(Constants.SHOOTER_WIDTH/2.5)-7);
     gunPosY=(posY+(int)(Constants.SHOOTER_HEIGHT/2.5)-22);
     
   }
    public void updateLocation(){
       posX+=velX;
       posY+=velY;

       if(posX<=0)posX=0;
       if(posX>(Constants.WIDTH-(int)(Constants.SHOOTER_WIDTH/2.5)))posX=Constants.WIDTH-(int)(Constants.SHOOTER_WIDTH/2.5);
       if(posY<=0)posY=0;
       if(posY>=Constants.HEIGHT-(int)(Constants.SHOOTER_HEIGHT/2.5))posY=Constants.HEIGHT-(int)(Constants.SHOOTER_HEIGHT/2.5);

    }

    public void setVelX(double vel){velX=vel;}
    public void setVelY(double vel){velY=vel;}
    public int getIndexAnimation(){return indexAnimation;}
    public int getIndexGun(){return indexGun;}
    
    public void setPosX(int x){
        this.posX=x;
    }

    public void setPosY(int y){
        this.posY=y;
    }

    public void setIndexGun(){

    }

    public void setIndexAnimation(){
        indexAnimation+=1;
        if(indexAnimation==20)indexAnimation=0;
    }
    
    public double getAngle(){return angle;}

    public void setAngle(double angle){
        this.angle=angle;
    }
    
    
    public int  getPosX(){return posX;}
    public int getPosY(){return posY;}

    public int getGunPosX(){return gunPosX;}
    public int getGunPosY(){return gunPosY;}

    public void setGunPosX(int x){this.gunPosX=x;}
    public void setGunPosY(int y){this.gunPosY=y;}






}
