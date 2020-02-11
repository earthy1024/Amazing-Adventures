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

public class Adventure {
File file;
Layout layout;
Rooms rooms;
Directions directions;

    public Adventure() {

    }

    public void gameInitialization(String path) throws IOException {
        file = new File(path);
        Layout layout = new ObjectMapper().readValue(file, Layout.class);
    }

    public void errorMessage(String input) {
        System.out.println("I can't go to" + input);
    }

    public void getCurrentLocation(int index) {
        System.out.println("You are in the" + layout.getRooms().get(index) + "");
    }

    public void getCurrentInstructions(int index) {
        System.out.print("From here, you can go: ");
        for (int a = 0; a < layout.getRooms().get(index).getDirections().size(); a++) {
            System.out.print(layout.getRooms().get(index).getDirections().get(a).getDirectionName() + " ");
        }
    }

    public int updateLocation(String input, int givenIndex) throws IOException {
        String nextRoom = "";
        for (int a = 0; a < layout.getRooms().get(givenIndex).getDirections().size(); a++) {
            if (input.equals(layout.getRooms().get(givenIndex).getDirections().get(a).getDirectionName())) {
                nextRoom = layout.getRooms().get(givenIndex).getDirections().get(a).getRoom();
            }
        }
        for (int b = 0; b < layout.getRooms().size(); b++) {
            if (nextRoom.equals(layout.getRooms().get(b))) {
                return b;
            }
        }
        return givenIndex;
    }


    public void userInput() {

    }
}