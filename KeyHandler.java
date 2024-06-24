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

        anyKPressed = true;

        //System.out.println("Pressionada");
        
        if(key == KeyEvent.VK_W || key==KeyEvent.VK_UP){
            upPressed = true;
        }
        if(key == KeyEvent.VK_S || key==KeyEvent.VK_DOWN ){
            downPressed = true;
        }
        if(key == KeyEvent.VK_D || key==KeyEvent.VK_RIGHT ){
            rightPressed = true;
        }
        if(key == KeyEvent.VK_A || key==KeyEvent.VK_LEFT ){
            leftPressed = true;
        }
        anyKPressed=(upPressed||leftPressed||rightPressed||downPressed);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        //System.out.println("Solta");

        if(key == KeyEvent.VK_W || key==KeyEvent.VK_UP){
            upPressed = false;
        }
        if(key == KeyEvent.VK_S || key==KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(key == KeyEvent.VK_D || key==KeyEvent.VK_RIGHT ){
            rightPressed = false;
        }
        if(key == KeyEvent.VK_A || key==KeyEvent.VK_LEFT ){
            leftPressed = false;
        }

        anyKPressed=(upPressed || downPressed || rightPressed || leftPressed);
    }
    
}
