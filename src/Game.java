import javax.swing.*;

/**
 * Created by seyfer on 25.08.15.
 */
public class Game
{
    public static void main(String[] args) {
        System.out.println("Hello");

        JFrame window = new JFrame("Platformer");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new GamePanel());
        window.pack();
        window.setVisible(true);
    }
}
