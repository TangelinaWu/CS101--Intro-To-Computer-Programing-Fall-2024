package edu.nyu.cs.assignment6;

import java.util.Scanner;
import java.util.Arrays;

public class TestDrive {

    /**
     * The main function is automatically called first in a Java program.
     * It should instantiate a Moped and contain the main logic of the program,
     * following the instructions.
     * 
     * @param args An array of any command-line arguments.
     */
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        Moped m = new Moped();

        // solution
        System.out.println(
                "Thanks for jumping on the moped. We're currently parked outside Dr. Rossinsky DDS's office at 10th St. and 5th Ave, facing South.  May I say your teeth look very clean.");
        System.out.println("What would you like to do? At any time, say 'help' for assistance.");

        String response = "";
        while (!response.equals("park")) {
            response = scn.nextLine();
            switch (response) {
                // For every move that the user moped makes, the drunk driver mopeds will move
                // too.
                case "turn left":
                    m.turnLeft();
                    m.printLocation();
                    break;
                case "turn right":
                    m.turnRight();
                    m.printLocation();
                    break;
                case "straight on":
                    m.goStraight();
                    m.printLocation();
                    break;
                case "back up":
                    m.goBackwards();
                    m.printLocation();
                    break;
                case "fill it up":
                    m.fillGas();
                    break;
                case "how we doin'?":
                    m.printGasLevel();
                    break;
                case "park":
                    m.park();
                    break;
                case "go to Xi'an Famous Foods":
                    // Drunk driver mopeds move the same amount as how much the user moped moves
                    // when "go to Xi'an Famous Foods" is called.
                    m.goToXianFamousFoods();
                    break;
                case "help":
                    String help = m.getHelp();
                    System.out.println(help);
                    break;
                default:
                    System.out
                            .println("I'm sorry, I don't understand that command. Type 'help' for a list of commands");
                    break;
            }

            if (m.getGasLevel() == 0) {
                m.printGasLevel();
                break;
            }
        }
        scn.close();
    }
}