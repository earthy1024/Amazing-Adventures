package student.adventure;
/*
        Methods needed:
        1. Function for game start
        2. Function for user input
        3. JSON function for custom map
        4. Function for regular game activity
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import student.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Adventure {
    File file;
    Layout layout;

    public Adventure() {

    }

    /**
     * Creates a new file based on the path given by a use and converts it to a layout.
     * @param path the file path the user has chosen as the map
     * @throws IOException
     */
    public void gameInitialization(String path) throws IOException {
        file = new File(path);
        if (!file.exists()) {
            throw new IOException();
        }
        layout = new ObjectMapper().readValue(file, Layout.class);
    }

    /**
     *
     * @param adventure the instance of a new game
     * @throws IOException
     */
    public void executeGame(Adventure adventure) throws IOException {
        boolean gameActive = true;
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
                break;
            }
            userDirection = trimUserInput(userDirection);
            int tempIndex = currentRoomIndex;
            currentRoomIndex = adventure.updateLocation(userDirection, currentRoomIndex);
            if (currentRoomIndex == -1) {
                adventure.errorMessage(userDirection);
                currentRoomIndex = tempIndex;
            }
        }
    }

    /**
     * This function will take in the index of the current room and give out the directions linked to the room
     * @param index of the current room
     */
    public void getCurrentInstructions(int index) {
        System.out.print("From here, you can go: ");
        List<Directions> currentDirectionsList = layout.getRooms().get(index).getDirections();
        for (int a = 0; a < currentDirectionsList.size(); a++) {
            System.out.print(currentDirectionsList.get(a).getDirectionName() + " ");
        }
        System.out.println();
    }

    /**
     * Gets the updated location of the user after a direction is given
     * @param input the direction choice of the user
     * @param givenIndex the index of the current room
     * @return the index of the room the user is lead to based on the direction chosen
     * @throws IOException
     */
    public int updateLocation(String input, int givenIndex) throws IOException {
        String nextRoom = "";
        input = input.toUpperCase();
        List<Directions> currentDirectionsList = layout.getRooms().get(givenIndex).getDirections();
        for (int a = 0; a < currentDirectionsList.size(); a++) {
            if (input.equals(currentDirectionsList.get(a).getDirectionName().toUpperCase())) {
                nextRoom = currentDirectionsList.get(a).getRoom();
            }
        }

        for (int b = 0; b < layout.getRooms().size(); b++) {
            if (nextRoom.equals(layout.getRooms().get(b).getName())) {
                return b;
            }
        }
        return -1;
    }

    public String trimUserInput(String input) {
        if (input.toUpperCase().contains("GO")) {
            input = input.substring(3);
        }
        return input;
    }

    public void errorMessage(String input) {
        System.out.println("I can't go to " + input);
    }

    public void getCurrentLocation(int index) {
        System.out.println(layout.getRooms().get(index).getDescription());
    }

    public String getRoomName(int index) {
        return layout.getRooms().get(index).getName();
    }

    public String getCurrentRoom(int index) {
        return layout.getRooms().get(index).getName();
    }

    public String getEndRoom() {
        return layout.getEndingRoom();
    }

}