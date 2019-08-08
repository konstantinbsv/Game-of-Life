package life;

class Evolution {
    private final static boolean ALIVE = true;
    private final static boolean DEAD  = false;

    private static int currentGenNumber = 1;

    static void evolve(Universe universe) {
        boolean[][] currentGen = universe.getUniverseArray();
        boolean[][] nextGen    = universe.getUniverseArray();

        for (int i = 0; i < currentGen.length; i++) {
            for (int j = 0; j < currentGen.length; j++) {
                int aliveNeighbours = countAliveNeighbours(currentGen, i, j);

                if (aliveNeighbours == 3) {
                    nextGen[i][j] = ALIVE;
                } else if (aliveNeighbours == 2) {
                    // nextGen[i][j] = currentGen[i][j];
                } else {
                    nextGen[i][j] = DEAD;
                }
            }
        }

        currentGenNumber++;
        universe.setUniverse(nextGen);
    }

    public static void resetEvolution() {
        Evolution.currentGenNumber = 1;
    }

    public static int getCurrentGenNumber() {
        return currentGenNumber;
    }

    private static int countAliveNeighbours(boolean[][] universeArray, int row, int col) {
        int aliveCount = 0;

        int north = row - 1;
        int south = row + 1;
        int west  = col - 1;
        int east  = col + 1;

        if (north == -1) {
            north = universeArray.length - 1;
        }
        if (south == universeArray.length) {
            south = 0;
        }
        if (west == -1) {
            west = universeArray.length -1;
        }
        if (east == universeArray.length) {
            east = 0;
        }

        boolean[] neighbours = { universeArray[north][west], universeArray[north][col], universeArray[north][east],
                                 universeArray[ row ][west],    /* current cell */      universeArray[ row ][east],
                                 universeArray[south][west], universeArray[south][col], universeArray[south][east]};

        for (boolean neighbour: neighbours) {
            if (neighbour) {
                aliveCount++;
            }
        }
        return aliveCount;
    }
}
