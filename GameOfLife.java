package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {
    final Font font = new Font("Dialog", Font.PLAIN, 16);
    final int labelWidth = 150;
    final int labelHeight = 15;
    final int xOffset = 5;

    private JLabel generationCount = new JLabel();
    private JLabel aliveCount = new JLabel();
    private SquareGrid grid;

    public GameOfLife(int gridSize) {
        super("Game of Life");
        setName("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(22 + SquareGrid.squareSize*gridSize,  80 + gridSize*SquareGrid.squareSize);
        setLocationRelativeTo(null);

        generationCount.setBounds(xOffset,2, labelWidth, labelHeight);
        generationCount.setFont(font);
        generationCount.setText("Generation #0");
        generationCount.setName("GenerationLabel");
        add(generationCount);

        aliveCount.setBounds(xOffset,19, labelWidth, labelHeight);
        aliveCount.setFont(font);
        aliveCount.setText("Alive: 0");
        aliveCount.setName("AliveLabel");
        add(aliveCount);

        grid = new SquareGrid(gridSize);
        add(grid);
        grid.repaint();

        setLayout(null);
        // setResizable(false);
        setVisible(true);
    }

    public void setGenerationNumber(int generationNumber) {
        generationCount.setText("Generation #" + generationNumber);
    }

    public void setAliveNumber(int aliveNumber) {
        aliveCount.setText("Alive: " + aliveNumber);
    }

    public void updateGrid(boolean[][] universeGrid) {
        grid.setUniverseGrid(universeGrid);
        add(grid);
        grid.repaint();
    }

    private class SquareGrid extends JPanel {
        final static int squareSize = 15;

        private int gridSize;
        private boolean[][] universeGrid;

        private SquareGrid(int gridSize) {
            this.gridSize = gridSize;
            setBounds(xOffset, 40, squareSize*gridSize, squareSize*gridSize);
            setLayout(new GridLayout(gridSize, gridSize));
        }

        private void setUniverseGrid(boolean[][] universeGrid) {
            this.universeGrid = universeGrid;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);

            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    if (universeGrid == null) {
                        g.drawRect( j*squareSize,i*squareSize ,squareSize, squareSize);
                    } else if (universeGrid[i][j]) {
                        g.fillRect( j*squareSize,i*squareSize ,squareSize, squareSize);
                    } else {
                        g.drawRect( j*squareSize,i*squareSize ,squareSize, squareSize);
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        GameOfLife mainWindow = new GameOfLife(40);

    }
}
