package life;

import java.util.Arrays;
import java.util.Random;

public class Universe {
    private boolean[][] universe;

    public Universe (int universeSize, long randomSeed) {
        universe = new boolean[universeSize][universeSize];
        Random rand = new Random(randomSeed);

        for (int i = 0; i < universeSize; i++) {
            for (int j = 0; j < universeSize; j++) {
                universe[i][j] = rand.nextBoolean();
            }
        }
    }


    public boolean[][] getUniverse() {
        return  Arrays.stream(universe).map(r -> r.clone()).toArray(boolean[][]::new);
    }

    public void setUniverse(boolean[][] universe) {
        this.universe = Arrays.stream(universe).map(r -> r.clone()).toArray(boolean[][]::new);
    }

    public void printUniverse() {
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
