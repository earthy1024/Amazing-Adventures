package student;


import student.adventure.Adventure;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {
        // TODO: Complete this method.
        /*
        These are the instance variables and objects that are used in the while loop
         */
        boolean gameActive = true;
        Adventure adventure = new Adventure();
        int currentRoomIndex = 0;

        /*
        The initial user entry that submits the file path to the Adventure constructor.
        The game is then created.
         */
        System.out.println("Enter json file path to use for map:");
        Scanner scanner = new Scanner(System.in);
        String inputFile = scanner.nextLine();
        adventure.gameInitialization(inputFile);

        /*
        This loop executes the rest of the game functionality.
        The current room is sent to the console and is checked for if the game has ended.
        If the user types in any version of exit or quit the game will stop.
        Then the user will be prompted to send in a direction based on the choices given
        If the choice is invalid the console will say so and the user will have to repeat the decision.
        If a correct choice is given the current room index is updated and the loop reiterates.
         */
        while(gameActive) {
            adventure.getCurrentLocation(currentRoomIndex);
            if (adventure.getCurrentRoom(currentRoomIndex).equals(adventure.getEndRoom())) {
                System.out.println("You have reached the end room");
                System.exit(0);
            }
            if (currentRoomIndex == 0) {
                System.out.println("Your journey begins here");
            }
            adventure.getCurrentInstructions(currentRoomIndex);
            String userDirection = scanner.nextLine();
            if (userDirection.toUpperCase().equals("EXIT") || userDirection.toUpperCase().equals("QUIT")) {
                System.exit(0);
                break;
            }
            int tempIndex = currentRoomIndex;
            currentRoomIndex = adventure.updateLocation(userDirection, currentRoomIndex);
            if (currentRoomIndex == -1) {
                adventure.errorMessage(userDirection);
                currentRoomIndex = tempIndex;
            }

        }



    }
}