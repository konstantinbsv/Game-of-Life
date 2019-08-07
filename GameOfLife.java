package life;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Hashtable;

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
    private JLabel speedSliderLabel = new JLabel();
    private JSlider universeSizeSlider = new JSlider(10, 200, 50);
    private JLabel sizeSliderLabel = new JLabel();
    private static SquareGrid grid;

    Hashtable<Integer, JLabel> sizeSliderTickLabels = new Hashtable<>();

    private boolean isPaused = true;
    private boolean isReset = true;
    private int evolutionSpeed;
    private int universeSize;

    public GameOfLife() {
        super("Game of Life");
        setName("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        startStopButton.setText("Start");
        isReset = true;
        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isReset) {
                    universeSizeSlider.setEnabled(false);
                    initializeGrid(universeSize);
                    isReset = false;
                }

                if (startStopButton.isSelected()) {
                    startStopButton.setText("Pause");
                    isPaused = false;
                } else {
                    startStopButton.setText("Start");
                    isPaused = true;
                }

            }
        });
        add(startStopButton);

        resetButton.setBounds(leftMargin, topMargin + 70, buttonWidth, buttonHeight);
        resetButton.setName("ResetButton");
        resetButton.setText("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startStopButton.setSelected(false);
                startStopButton.setText("Start");
                isReset = true;
                isPaused = true;

                universeSizeSlider.setEnabled(true);
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
                updateSpeedSliderLabel();
            }
        });
        add(speedSlider);

        speedSliderLabel.setBounds(leftMargin + 10, topMargin + 140, buttonWidth + 30, buttonHeight+20);
        speedSliderLabel.setFont(font.deriveFont(9.0f));
        updateSpeedSliderLabel();
        add(speedSliderLabel);

        universeSizeSlider.setBounds(leftMargin , topMargin + 180, buttonWidth + 30, buttonHeight+20);
        universeSizeSlider.setToolTipText("Choose universe size");
        sizeSliderTickLabels.put(10, new JLabel("10"));
        sizeSliderTickLabels.put(50, new JLabel("50"));
        sizeSliderTickLabels.put(100, new JLabel("100"));
        sizeSliderTickLabels.put(150, new JLabel("150"));
        sizeSliderTickLabels.put(200, new JLabel("200"));
        universeSizeSlider.setSnapToTicks(true);
        universeSizeSlider.setLabelTable(sizeSliderTickLabels);
        universeSizeSlider.setPaintLabels(true);
        universeSizeSlider.setPaintTicks(true);
        universeSize = universeSizeSlider.getValue();
        universeSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                universeSize = universeSizeSlider.getValue();
                sizeSliderLabel.setText("Universe size: " + universeSize);
            }
        });
        add(universeSizeSlider);

        sizeSliderLabel.setBounds(leftMargin + 30, topMargin + 210, buttonWidth + 30, buttonHeight+20);
        sizeSliderLabel.setText("Universe size: " + universeSize);
        sizeSliderLabel.setFont(font.deriveFont(9.0f));
        add(sizeSliderLabel);

        setLayout(null);
        setSize(xOffset,  300);
        // setResizable(false);
        setVisible(true);
    }

    private void initializeGrid(int universeSize) {
        if (grid != null) {
            remove(grid);
            grid = null;
        }

        grid = new SquareGrid(universeSize);
        setSize(xOffset + leftMargin*2 + grid.gridPixelDimensions,  50 + grid.gridPixelDimensions);
        setLocationRelativeTo(null);
    }

    private void updateSpeedSliderLabel() {
        speedSliderLabel.setText("Generation delay: " + evolutionSpeed + "ms");
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

    public int getUniverseSize() {
        return universeSize;
    }

    public boolean isReset() {
        return isReset;
    }

    public void setReset(boolean reset) {
        isReset = reset;
    }

    private class SquareGrid extends JPanel {
        private double squareSize;

        private int gridPixelDimensions;
        private boolean[][] universeGrid;

        private SquareGrid(int gridSize) {
            this.squareSize = (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100) / gridSize;
            this.gridPixelDimensions = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100);

            setBounds(xOffset, topMargin, gridPixelDimensions,  gridPixelDimensions);
            setLayout(new GridLayout(gridSize, gridSize));
        }

        private void setUniverseGrid(boolean[][] universeGrid) {
            this.universeGrid = universeGrid;
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            super.paintComponent(g2);
            g.setColor(Color.BLACK);

            for (int i = 0; i < universeGrid.length; i++) {
                for (int j = 0; j < universeGrid.length; j++) {
                    Rectangle2D rect = new Rectangle2D.Double(j * squareSize, i * squareSize, squareSize, squareSize);

                    if (universeGrid == null || !universeGrid[i][j]) {
                        g2.draw(rect);
                        // g2.drawRect(j * squareSize, i * squareSize, squareSize, squareSize);
                    } else {
                        g2.fill(rect);
                        // g2.fillRect(j * squareSize, i * squareSize, squareSize, squareSize);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        GameOfLife mainWindow = new GameOfLife();

    }
}
