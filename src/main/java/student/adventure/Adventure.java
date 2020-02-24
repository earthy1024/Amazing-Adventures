package student.adventure;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.internal.util.xml.impl.Input;
import org.glassfish.grizzly.http.HttpPacket;
import sun.misc.IOUtils;
import sun.net.www.URLConnection;
import sun.net.www.protocol.http.HttpURLConnection;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.net.URL;
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
        System.out.println("Your journey begins here");


        while(gameActive) {
            if (adventure.getCurrentRoom(currentRoomIndex).equals(adventure.getEndRoom())) {
                adventure.getCurrentLocation(currentRoomIndex);
                System.out.println("You have reached the end room!");
                System.exit(0);
            }
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
                    } else if (userInstruction.toUpperCase().contains("REMOVE")) {
                        adventure.removeItem(userInstruction.substring(7), currentRoomIndex);
                    } else {
                        adventure.getErrorMessage(userInstruction);
                    }
                } else {
                    int tempIndex = currentRoomIndex;
                    currentRoomIndex = adventure.updateLocation(userInstruction, currentRoomIndex);
                    if (currentRoomIndex == -1) {
                        adventure.getErrorMessage(userInstruction);
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
    private int updateLocation(String input, int givenIndex) throws IOException {
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

    /**
     * Gets the list of items in the current room of the user
     * @param index of the current room
     */
    private void getCurrentItems(int index) {
        if (layout.getRooms().get(index).getItems() == null) {
            return;
        }
        System.out.print("Items visible: ");
        List<String> currentItems = layout.getRooms().get(index).getItems();
        for (String currentItem : currentItems) {
            System.out.print(currentItem + ", ");
        }
        System.out.println();
    }

    /**
     * This function will take in the index of the current room and give out the directions linked to the room
     * @param index of the current room
     */
    private void getCurrentInstructions(int index) {
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

    private String trimInputToDirection(String input) {
        if (input.toUpperCase().contains("GO ")) {
            input = input.substring(3);
        }
        return input;
    }

    private void getErrorMessage(String input) {
        System.out.println("I can't go to " + input);
    }

    private void getCurrentLocation(int index) {
        System.out.println(layout.getRooms().get(index).getDescription());
    }

    private String getRoomName(int index) {
        return layout.getRooms().get(index).getName();
    }

    private String getCurrentRoom(int index) {
        return layout.getRooms().get(index).getName();
    }

    private String getEndRoom() {
        return layout.getEndingRoom();
    }

    private void addItem(String item, int index) {
        List<String> items = layout.getRooms().get(index).getItems();
        for (int current = 0; current < items.size(); current++) {
            if (items.get(current).equals(item)) {
                System.out.println("This item is already in the room");
                return;
            }
        }
        layout.getRooms().get(index).getItems().add(item);
    }

    private void removeItem(String item, int index) {
        List<String> items = layout.getRooms().get(index).getItems();
        for (int current = 0; current < items.size(); current++) {
            if (items.get(current).equals(item)) {
                items.remove(current);
            }
        }
    }
    /*
    URL connection = new URL("http://www.purgomalum.com/service/containsprofanity?text=covfefe"
                        + userInstruction);
                HttpURLConnection languageCheck = (HttpURLConnection) connection.openConnection();
                languageCheck.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(languageCheck.getOutputStream());
                out.close();
                // BufferedReader in = new BufferedReader(new InputStreamReader(languageCheck.getInputStream()));

                InputStream in = new URL("http://www.purgomalum.com/service/containsprofanity?text=covfefe"
                        + userInstruction).openStream();
                String profanityTest = toString(in);
                if (profanityTest.equals("true")) {
                    System.out.println("Instruction contains profanity, try again");
                    // in.close();
                    continue;
                }
                // in.close();
     */
}