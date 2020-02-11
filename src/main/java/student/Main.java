package student;


import student.adventure.Adventure;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
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
            if (currentRoomIndex == 0) {
                System.out.println("Your journey begins here");
            }
            adventure.getCurrentInstructions(currentRoomIndex);
            String userDirection = scanner.next();
            adventure.updateLocation(userDirection, currentRoomIndex);
        }



    }
}