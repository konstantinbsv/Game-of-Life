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

        Universe universe = new Universe(universeSize, randomSeed);
        universe.printUniverse();


    }
}
