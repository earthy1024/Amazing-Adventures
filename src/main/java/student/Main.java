package student;


import student.adventure.Adventure;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {
        // TODO: Complete this method.
        boolean gameActive = true;
        Adventure adventure = new Adventure();
        int currentRoomIndex = 0;

        System.out.println("Enter json file path to use for map:");
        Scanner scanner = new Scanner(System.in);
        String inputFile = scanner.nextLine();
        adventure.gameInitialization(inputFile);


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
                gameActive = false;
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