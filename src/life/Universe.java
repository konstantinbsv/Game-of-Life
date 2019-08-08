package life;

import java.util.Arrays;
import java.util.Random;

class Universe {
    private boolean[][] universe;

    public Universe (int universeSize) {
        universe = new boolean[universeSize][universeSize];
        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < universeSize; i++) {
            for (int j = 0; j < universeSize; j++) {
                universe[i][j] = rand.nextBoolean();
            }
        }
    }

    boolean[][] getUniverseArray() {
        return  Arrays.stream(universe).map(r -> r.clone()).toArray(boolean[][]::new);
    }

    void setUniverse(boolean[][] universe) {
        this.universe = Arrays.stream(universe).map(r -> r.clone()).toArray(boolean[][]::new);
    }

    int countAliveCells() {
        int aliveCells = 0;
        for (boolean[] row : universe) {
            for (boolean cell : row) {
                if (cell) {
                    aliveCells++;
                }
            }
        }
        return aliveCells;
    }

    void printUniverse() {
        final char ALIVE = 'O';
        final char DEAD = ' ';

        for (boolean[] row : universe) {
            for (boolean cell : row) {
                if (cell) {
                    System.out.print(ALIVE);
                } else {
                    System.out.print(DEAD);
                }
            }
            System.out.println();
        }
    }
}
