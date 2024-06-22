import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.time.ZoneId;

import javax.swing.JFrame;

public class Jogo extends JFrame implements Runnable{

    private GamePanel game_panel;
    private Thread thread;
    private Character character;
    private Controller controller;
    private Point point;
    private LoadAssets loadAssets;
    
    
    Jogo(){
    
        loadAssets=new LoadAssets();
        point=new Point();
        point.setLocation(MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY());
        character = new Character();
        //zombie=new Zombie(loadAssets,character);
        controller=new Controller(this,point,character,loadAssets);
         game_panel=new GamePanel(character,controller,this,point,loadAssets);

        setTitle(Constants.TITLE);
        setSize(Constants.WIDTH, Constants.HEIGHT );
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(game_panel);
        setVisible(true);
        
        start();
    }
    public static void main(String[] args) {
        new Jogo();
    }

    public synchronized void  start(){

        if(game_panel.getIsRunning() == true)return;

        game_panel.setIsRunning(true);
        thread=new Thread(this);
        thread.start();
        
    }

    public synchronized void stop(){
        if(!game_panel.getIsRunning())return;

        game_panel.setIsRunning(false);
        try {
            
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(1);

    }

    @Override
    public void run() {
        //GAME LOOP

        long lastTime=System.nanoTime();
        double ns=1000000000/Constants.AMOUNT_OF_TICKS;
        double delta=0;
        int updates=0;
        int frames=0;
       long timer=System.currentTimeMillis();

        
        while (game_panel.getIsRunning() == true) {
            long now=System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime=now;
            if(delta>=1){
                //tick()
                delta--;
                updates++;
                character.updateLocation();
                game_panel.repaint();
                controller.tick();
                //zombie.tick();
            }
            //render()
            frames++;

            if(System.currentTimeMillis()-timer>1000){
                timer+=1000;
                updates=0;
                frames=0;
            }


            
            
            

            

        }
        stop();
    }
}