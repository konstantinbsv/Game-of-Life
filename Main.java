package life;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        if (args.length != 2) {
            System.out.println("Requires two arguments: <universe size> <random seed>");
            System.exit(-1);
        }
         */
        Scanner scanner = new Scanner(System.in);
        int universeSize = scanner.nextInt();
        int randomSeed = scanner.nextInt();

        boolean[][] universe = initializeUniverse(universeSize, randomSeed);
        printUniverse(universe);

    }

    public static boolean[][] initializeUniverse(int universeSize, int randomSeed) {
        boolean[][] universe = new boolean[universeSize][universeSize];
        Random rand = new Random(randomSeed);

        for (int i = 0; i < universeSize; i++) {
            for (int j = 0; j < universeSize; j++) {
                universe[i][j] = rand.nextBoolean();
            }
        }
        return universe;
    }

    public static void printUniverse(boolean[][] universe) {
        final String ALIVE = "O";
        final String DEAD  = " ";

        for (boolean[] row: universe) {
            for (boolean cell: row) {
                if (cell) {
                    System.out.print(ALIVE);
                } else {
                    System.out.print(DEAD);
                }
               // System.out.print(" ");
            }
            System.out.println();
        }
    }
}
