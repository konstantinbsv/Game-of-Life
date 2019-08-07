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
        final int universeSize = 40;

        Universe universe = Universe.get(universeSize);
        GameOfLife gameOfLife = new GameOfLife(universeSize);

        int aliveCells;

        while (true) {

            if (!gameOfLife.isPaused()) {
                clearConsole();
                aliveCells = universe.countAliveCells();

                // update console
                System.out.println("Generation: #" + Evolution.getCurrentGenNumber());
                System.out.println("Alive: " + aliveCells);
                universe.printUniverse();

                // update main window
                gameOfLife.setGenerationNumber(Evolution.getCurrentGenNumber());
                gameOfLife.setAliveNumber(aliveCells);
                gameOfLife.updateGrid(universe.getUniverseArray());

                Evolution.evolve(universe);
            }

                sleep(gameOfLife.getEvolutionSpeed());
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
