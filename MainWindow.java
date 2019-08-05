package life;

import javax.swing.*;

public class MainWindow extends JFrame {
    private JLabel generationCount = new JLabel();
    private JLabel aliveCount = new JLabel();

    public MainWindow() {
        super("Game of Life");
        setName("Game of Life");
        setSize(500, 500);
        setLocationRelativeTo(null);

        generationCount.setBounds(0,0, 100, 30);
        generationCount.setText("Generation #xxx");
        add(generationCount);

        aliveCount.setBounds(0,20,100, 30);
        aliveCount.setText("Alive: xxx");
        add(aliveCount);

        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }
}
