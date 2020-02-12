package student.adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.sun.xml.internal.bind.v2.util.ByteArrayOutputStreamEx;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class AdventureTest {
    Adventure adventureGame = new Adventure();

    @Before
    public void setUp() {
        // This is run before every test.

    }

    @Test
    public void sanityCheck() {
        // TODO: Remove this unnecessary test case.

    }

    @Test
    public void testCorrectStartingRoom() throws Exception {

        // ByteArrayInputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        //ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // System.setOut(new PrintStream(outputStream));
        // assertEquals(correctMessage, outputStream.toString);

        adventureGame.gameInitialization("src/main/resources/AdventureMap");
        String correctMessage = "MatthewsStreet";
        assertEquals(correctMessage, adventureGame.layout.getStartingRoom());
    }

    /*
    Checks if the initial correct user input will return the correct updated room location
     */
    @Test
    public void testInitialCorrectInput() throws Exception {
        adventureGame.gameInitialization("src/main/resources/AdventureMap");

        String input = "East";
        String correctMessage = "SiebelEntry";
        int testIndex = adventureGame.updateLocation(input, 0);
        assertEquals(correctMessage, adventureGame.getRoomName(testIndex));
    }

    /*
    Checks whether an incorrect input will give the correct index that notifies an error
    when returned by the function called.
     */
    @Test
    public void testInitialIncorrectInput() throws Exception {
        adventureGame.gameInitialization("src/main/resources/AdventureMap");
        String input = "Potatoes";
        String correctMessage = "MatthewsStreet";
        int testIndex = adventureGame.updateLocation(input, 0);
        assertEquals(-1, testIndex);
    }

    /*
    Simulates three user inputs and checks for the correct index after all three.
    The expected index should be equal to 1 since the user goes back to the room it was in after AcmOffice
     */
    @Test
    public void testMidGameCorrectIndex() throws Exception {
        adventureGame.gameInitialization("src/main/resources/AdventureMap");
        String input1 = "East";
        String input2 = "Northeast";
        String input3 = "South";
        int testIndex1 = 0;
        int testIndex2 = adventureGame.updateLocation(input1, testIndex1);
        int testIndex3 = adventureGame.updateLocation(input2, testIndex2);
        int testIndex4 = adventureGame.updateLocation(input3, testIndex3);
        assertEquals(1, testIndex4);
        assertEquals(testIndex2, testIndex4);
    }
}