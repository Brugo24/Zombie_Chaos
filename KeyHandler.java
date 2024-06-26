package joguin;

import java.awt.event.*;;

public class KeyHandler implements KeyListener{

    public boolean upPressed = false;
    public boolean downPressed = false;
    public boolean rightPressed = false;
    public boolean leftPressed = false;
    public boolean anyKPressed = false;

    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        //System.out.println("Pressionada");
        
        if(key == KeyEvent.VK_W){
            upPressed = true;
        }
        if(key == KeyEvent.VK_S){
            downPressed = true;
        }
        if(key == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(key == KeyEvent.VK_A){
            leftPressed = true;
        }

        if(leftPressed || rightPressed || downPressed || upPressed){anyKPressed = true; }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        //System.out.println("Solta");

        if(key == KeyEvent.VK_W){
            upPressed = false;
        }
        if(key == KeyEvent.VK_S){
            downPressed = false;
        }
        if(key == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(key == KeyEvent.VK_A){
            leftPressed = false;
        }

        if(!upPressed && !downPressed && !rightPressed && !leftPressed) anyKPressed = false;
    }
    
}
