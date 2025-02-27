package student.adventure;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {

    private int moves;
    private String user;

    /**
     * The constructor creates a player instance on a leaderboard with the its name and total moves
     * @param input
     * @param score
     */
    public Leaderboard(String input, int score) {
        moves = score;
        user = input;
    }

    public String getName() {
        return user;
    }

    public int getMoves() {
        return moves;
    }
}
