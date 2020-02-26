package student.adventure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.sun.xml.internal.bind.v2.util.ByteArrayOutputStreamEx;
import jdk.internal.util.xml.impl.Input;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class AdventureTest {
    Adventure adventureGame = new Adventure();
    private Object IOException;
    private Object Exception;
    private Object PrintStream;

    @Before
    public void setUp() {
        // This is run before every test.
        adventureGame = new Adventure();
    }


    @Test
    public void testIncorrectFilePath() throws Exception {
        String testMessage = "Failed";
        try {
            adventureGame.initializeGame("src/main/resources/fakePath");
        } catch (IOException e) {
            testMessage = "Successful";
        }
        assertEquals("Successful" , testMessage);
    }

    @Test
    public void testCorrectFilePath() throws Exception {
        String testMessage = "Successful";
        try {
            adventureGame.initializeGame("src/main/resources/AdventureMap");
        } catch (IOException e) {
            testMessage = "Failed";
        }
        assertEquals("Successful", testMessage);
    }

    @Test
    public void testCorrectStartingRoom() throws Exception {
        adventureGame.executeGame(adventureGame);
        String instruction = "src/main/resources/AdventureMap\nRaj";
        InputStream in = System.in;
        System.setIn(new ByteArrayInputStream(instruction.getBytes()));
        in.close();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        out.toString();
        String correctMessage = "You are on Matthews, outside the Siebel Center\nYour journey begins here\n" +
            "From here, you can go: East\nItems visible: coin, \n ";
        assertEquals(correctMessage, out.toString());

    }

    @Test
    public void testCorrectGameOutcome() throws Exception {
        adventureGame.executeGame(adventureGame);
        String instruction = "src/main/resources/AdventureMap\nRaj\ngo east\ngo east\ngo south";
        InputStream user = System.in;
        System.setIn(new ByteArrayInputStream(instruction.getBytes()));
        System.setIn(user);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String correctMessage = "You are in Siebel 1314.  There are happy CS 126 students doing a code review.\n" + 
                "You have reached the end room!";
        assertEquals(correctMessage, out.toString());
        
    }

}