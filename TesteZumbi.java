import javax.swing.JFrame;

public class TesteZumbi {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Inimigo Perseguidor");
        ZumbiPerseguidor gamePanel = new ZumbiPerseguidor();
        
        frame.add(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
