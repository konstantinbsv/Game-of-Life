package life;

import java.io.IOException;
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
        final int GENERATION_LIMIT = 100;

        int universeSize = scanner.nextInt();
        Universe universe = new Universe(universeSize, System.currentTimeMillis());

        for (int currentGen = 1;  currentGen <= GENERATION_LIMIT; currentGen ++) {
            clearConsole();

            System.out.println("Generation: #" + currentGen);
            System.out.println("Alive: " + universe.countAliveCells());
            universe.printUniverse();
            Evolution.evolve(universe);

            sleep(500);
        }
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
