package joguin;

import javax.swing.*;

public class Jogo extends JFrame implements Runnable{
    JFrame janela;
    Painel painel;
    Thread gameThread;
    LoadAssets assets;
    int FPS = 60;

    Jogo(){
        assets = new LoadAssets();
        janela = new JFrame();
        painel = new Painel(this,assets);

        janela.setResizable(false);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setTitle("Joguin Teste");

        
        janela.add(painel);
        
        
        janela.pack();
        janela.setVisible(true);
        startThread();
    }

    public static void main(String[] args) {
        new Jogo();
    }

    public void startThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        double interval = (double) 1000000000 /FPS;
        double proxatt = System.nanoTime() + interval;

        while(gameThread.isAlive()){
            painel.update();
            
            painel.repaint();

            try{
                double temporestante = proxatt - System.nanoTime();
                temporestante /= 1000000;

                if(temporestante < 0){temporestante = 0;}

                Thread.sleep((long) temporestante);

                proxatt += interval;
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }
    }

}