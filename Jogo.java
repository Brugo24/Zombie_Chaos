package joguin;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Jogo extends JFrame implements Runnable{
    JFrame janela;
    Painel painel;
    Thread gameThread;
    LoadAssets assets;
    MenuP menu;
    int FPS = 60;
    AtomicBoolean isRunning = new AtomicBoolean(false);
    AtomicBoolean isStoped = new AtomicBoolean(false);
    Jogo(MenuP menu){
        this.menu=menu;

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

    void interupt(){
        isRunning.set(false);
        gameThread.interrupt();
    }

    boolean isrunning(){
        return isRunning.get();
    }

    boolean isStoped(){
        return isStoped.get();
    }

    public void startThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void interruptThread(){
        janela.dispose();
        menu.setVisible(true);
    }

    public void wakeUp(){
        gameThread.notify();
    }

    public void run(){
        isRunning.set(true);
        isStoped.set(false);
        while(isRunning.get()) {
            double interval = (double) 1000000000 / FPS;
            double proxatt = System.nanoTime() + interval;

            while (isRunning.get()) {
                painel.update();

                painel.repaint();

                try {
                    double temporestante = proxatt - System.nanoTime();
                    temporestante /= 1000000;

                    if (temporestante < 0) {
                        temporestante = 0;
                    }

                    Thread.sleep((long) temporestante);

                    proxatt += interval;
                }
                catch (InterruptedException e) {
                }

            }
        }
        isStoped.set(true);
    }
}