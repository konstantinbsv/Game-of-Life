package life;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    final Font font = new Font("Dialog", Font.PLAIN, 16);

    private JLabel generationCount = new JLabel();
    private JLabel aliveCount = new JLabel();

    public MainWindow() {
        super("Game of Life");
        setName("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        generationCount.setBounds(5,2, 150, 15);
        generationCount.setFont(font);
        generationCount.setText("Generation #xxx");
        add(generationCount);

        aliveCount.setBounds(5,19,150, 15);
        aliveCount.setFont(font);
        aliveCount.setText("Alive: xxx");
        add(aliveCount);

        setLayout(null);
        setVisible(true);
    }

    public void setGenerationNumber(int generationNumber) {
        generationCount.setText("Generation #" + generationNumber);
    }

    public void setAliveNumber(int aliveNumber) {
        aliveCount.setText("Alive: " + aliveNumber);
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }
}
