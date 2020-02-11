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
    Adventure adventureGame;

    @Before
    public void setUp() {
        // This is run before every test.
       Adventure adventureGame = new Adventure();
    }

    @Test
    public void sanityCheck() {
        // TODO: Remove this unnecessary test case.

    }

    @Test


    public void testCorrectInput() throws IOException {
        String testInput = "East\nNorth\nSouth\nExit";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(testInput.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        adventureGame.gameInitialization("src/main/resources/AdventureMap");

        String correctMessage = "You are on Matthews, outside the Siebel Center\n" +
                "Your journey begins here\n" +
                "From here, you can go: East\nYou are in the west entry of Siebel Center. You can see the elevator, the ACM office, and hallways to the north and east.\n" +
                "From here, you can go: West, Northeast, North, or East\nYou are in the north hallway. You can see Siebel 1105 and Siebel 1112. From here, you can go: South\nYou are in the west entry of Siebel Center. You can see the elevator, the ACM office, and hallways to the north and east" +
                "From here, you can go: West, Northeast, North, or East";

        System.setOut(new PrintStream(outputStream));

        assertEquals(correctMessage, outputStream.toString());

    }
}