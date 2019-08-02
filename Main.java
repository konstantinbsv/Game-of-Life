package life;

import java.io.IOException;
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
        int generations = scanner.nextInt();

        Universe universe = new Universe(universeSize, System.currentTimeMillis());

        while (generations-- > 0) {
            Evolution.evolve(universe);
            universe.printUniverse();
        }
    }

    public static void clearConsole(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch (IOException | InterruptedException e) {}
    }
}
