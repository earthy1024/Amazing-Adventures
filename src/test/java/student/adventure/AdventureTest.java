package student.adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.sun.xml.internal.bind.v2.util.ByteArrayOutputStreamEx;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

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
        /*
        adventureGame.gameInitialization("src/main/resources/AdventureMap");
        String correctMessage = "MatthewsStreet";
        assertEquals(correctMessage, adventureGame.layout.getStartingRoom());
         */
    }

    @Test
    public void testCorrectPlay() throws Exception {
        InputStream s = System.in;
        ByteArrayInputStream one = new ByteArrayInputStream("go east".getBytes());
        System.setIn(one);
        ByteArrayInputStream two = new ByteArrayInputStream("go east".getBytes());
        System.setIn(two);
        ByteArrayInputStream three = new ByteArrayInputStream("go south".getBytes());
        System.setIn(three);
        ByteArrayOutputStream out = new ByteArrayOutputStream();


    }

    /**
    @Test
    public void testInitialCorrectInput() throws Exception {
        adventureGame.gameInitialization("src/main/resources/AdventureMap");

        String input = "East";
        String correctMessage = "SiebelEntry";
        int testIndex = adventureGame.updateLocation(input, 0);
        assertEquals(correctMessage, adventureGame.getRoomName(testIndex));
    }


    @Test
    public void testInitialIncorrectInput() throws Exception {
        adventureGame.gameInitialization("src/main/resources/AdventureMap");
        String input = "Potatoes";
        String correctMessage = "MatthewsStreet";
        int testIndex = adventureGame.updateLocation(input, 0);
        assertEquals(-1, testIndex);
    }


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
    */
}