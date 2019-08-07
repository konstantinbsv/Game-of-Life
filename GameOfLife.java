package life;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame {
    final Font font = new Font("Dialog", Font.PLAIN, 16);
    final int labelWidth = 150;
    final int labelHeight = 15;
    final int buttonWidth = 100;
    final int buttonHeight = 25;
    final int xOffset = 150;
    final int topMargin = 5;
    final int leftMargin = 10;

    private JLabel generationCount = new JLabel();
    private JLabel aliveCount = new JLabel();
    private JToggleButton startStopButton = new JToggleButton();
    private JButton resetButton = new JButton();
    private JSlider speedSlider = new JSlider(100, 500, 250);
    private SquareGrid grid;

    private boolean isPaused = false;
    private int evolutionSpeed;

    public GameOfLife(int gridSize) {
        super("Game of Life");
        setName("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(xOffset + leftMargin*2 + SquareGrid.squareSize*gridSize,  50 + gridSize*SquareGrid.squareSize);
        setLocationRelativeTo(null);

        generationCount.setBounds(leftMargin, topMargin, labelWidth, labelHeight);
        generationCount.setFont(font);
        generationCount.setText("Generation #0");
        generationCount.setName("GenerationLabel");
        add(generationCount);

        aliveCount.setBounds(leftMargin, topMargin + 17, labelWidth, labelHeight);
        aliveCount.setFont(font);
        aliveCount.setText("Alive: 0");
        aliveCount.setName("AliveLabel");
        add(aliveCount);

        startStopButton.setBounds(leftMargin, topMargin + 40, buttonWidth, buttonHeight);
        startStopButton.setName("PlayToggleButton");
        startStopButton.setText("Start/Stop");
        // startStopButton.setIcon();
        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPaused = !isPaused;
            }
        });
        add(startStopButton);

        resetButton.setBounds(leftMargin, topMargin + 70, buttonWidth, buttonHeight);
        resetButton.setName("ResetButton");
        resetButton.setText("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Universe.resetUniverse();
            }
        });
        add(resetButton);

        speedSlider.setBounds(leftMargin, topMargin + 110, buttonWidth + 30, buttonHeight+20);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setMajorTickSpacing(100);
        speedSlider.setMinorTickSpacing(50);
        evolutionSpeed = speedSlider.getValue();
        speedSlider.setName("SpeedSlider");
        speedSlider.setToolTipText("Slide to adjust evolution speed");
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                evolutionSpeed = speedSlider.getValue();
            }
        });
        add(speedSlider);

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

    public boolean isPaused() {
        return isPaused;
    }

    public void updateGrid(boolean[][] universeGrid) {
        grid.setUniverseGrid(universeGrid);
        add(grid);
        grid.repaint();
    }

    public int getEvolutionSpeed() {
        return evolutionSpeed;
    }

    private class SquareGrid extends JPanel {
        final static int squareSize = 15;

        private int gridSize;
        private boolean[][] universeGrid;

        private SquareGrid(int gridSize) {
            this.gridSize = gridSize;
            setBounds(xOffset, topMargin, squareSize*gridSize, squareSize*gridSize);
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
