import javax.swing.JFrame;
import java.awt.Color;

public class Main {

    public static void main(String[] args) {
            JFrame obj = new JFrame();
            Gameplay gameplay = new Gameplay(); //Gameplay class
            obj.setTitle("Snake game.");
            obj.setBounds(10, 10, 905, 700); //Границы окна
            obj.setBackground(Color.DARK_GRAY); // Фон окна
            obj.setResizable(false); // Масштабирование окна(оно не нужно)
            obj.setVisible(true);
            obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            obj.add(gameplay);
        }
}
