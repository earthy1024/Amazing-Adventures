package student.adventure;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        try {
            adventure.gameInitialization(inputFile);
        } catch (IOException e) {
            System.out.println("Not a valid file path");
            executeGame(adventure);
        }

        adventure.gameInitialization(inputFile);

        if (currentRoomIndex == 0) {
            System.out.println("Your journey begins here");
        }
        while(gameActive) {
            // adventure.getCurrentLocation(currentRoomIndex);


            // examine(currentRoomIndex, adventure);

            if (adventure.getCurrentRoom(currentRoomIndex).equals(adventure.getEndRoom())) {
                examine(currentRoomIndex, adventure);
                System.out.println("You have reached the end room!");
                System.exit(0);
            }

            /**
            String userInstruction = scanner.nextLine();
            if (userInstruction.toUpperCase().equals("EXIT") || userInstruction.toUpperCase().equals("QUIT")) {
                System.exit(0);
                break;
            }

            userInstruction = trimInputToDirection(userInstruction);
            int tempIndex = currentRoomIndex;
            currentRoomIndex = adventure.updateLocation(userInstruction, currentRoomIndex);
            if (currentRoomIndex == -1) {
                adventure.errorMessage(userInstruction);
                currentRoomIndex = tempIndex;
            }
             */
            // String userInstruction = scanner.nextLine();
            examine(currentRoomIndex, adventure);
            while (true) {
                String userInstruction = scanner.nextLine();
                userInstruction = trimInputToDirection(userInstruction);
                if (userInstruction.toUpperCase().equals("EXIT") || userInstruction.toUpperCase().equals("QUIT")) {
                    System.exit(0);
                } else if (updateLocation(userInstruction, currentRoomIndex) == -1) {
                    if (userInstruction.toUpperCase().contains("ADD")) {
                        adventure.addItem(userInstruction.substring(4), currentRoomIndex);
                    } else if (userInstruction.toUpperCase().equals("EXAMINE")) {
                        examine(currentRoomIndex, adventure);
                    } else {
                        adventure.errorMessage(userInstruction);
                    }
                } else {
                    int tempIndex = currentRoomIndex;
                    currentRoomIndex = adventure.updateLocation(userInstruction, currentRoomIndex);
                    if (currentRoomIndex == -1) {
                        adventure.errorMessage(userInstruction);
                        currentRoomIndex = tempIndex;
                    }
                    break;
                }

            }

        }
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
        for (Directions directions : currentDirectionsList) {
            if (input.equals(directions.getDirectionName().toUpperCase())) {
                nextRoom = directions.getRoom();
            }
        }

        for (int roomNum = 0; roomNum < layout.getRooms().size(); roomNum++) {
            if (nextRoom.equals(layout.getRooms().get(roomNum).getName())) {
                return roomNum;
            }
        }
        return -1;
    }

    public void getCurrentItems(int index) {
        System.out.print("Items visible: ");
        List<String> currentItems = layout.getRooms().get(index).getItems();
        for (int item = 0; item < currentItems.size(); item++) {
            System.out.print(currentItems.get(item) + ", ");
        }
        System.out.println();
    }

    /**
     * This function will take in the index of the current room and give out the directions linked to the room
     * @param index of the current room
     */
    public void getCurrentInstructions(int index) {
        System.out.print("From here, you can go: ");
        List<Directions> currentDirectionsList = layout.getRooms().get(index).getDirections();
        for (Directions direction : currentDirectionsList) {
            System.out.print(direction.getDirectionName() + " ");
        }
        System.out.println();
    }

    public void examine(int index, Adventure adventure) {
        adventure.getCurrentLocation(index);
        adventure.getCurrentInstructions(index);
        adventure.getCurrentItems(index);
    }

    public String trimInputToDirection(String input) {
        if (input.toUpperCase().contains("GO ")) {
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

    public void addItem(String item, int index) {
        layout.getRooms().get(index).getItems().add(item);
    }

}