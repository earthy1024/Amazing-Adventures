package student.adventure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdventureTournament {


    public AdventureTournament() {

    }

    /**
     * This executes a series of adventure games and
     * keeps track of high scores with a list of Leaderboard instances
     * @throws IOException
     */
    public void executeTournament() throws IOException {
        List<Leaderboard> leaderboardList = new ArrayList<>();
        boolean tournamentCheck = true;

        while (tournamentCheck) {
            Adventure adventure = new Adventure();
            adventure.executeGame(adventure);
            if (leaderboardList.size() == 0) {
                leaderboardList.add(new Leaderboard(adventure.getUser(), adventure.getScore()));
            } else {
                for (int current = 0; current < leaderboardList.size(); current++) {
                    if (adventure.getScore() > leaderboardList.get(current).getMoves()) {
                        leaderboardList.add(current, new Leaderboard(adventure.getUser(), adventure.getScore()));
                    } else {
                        leaderboardList.add(new Leaderboard(adventure.getUser(), adventure.getScore()));
                    }
                }
            }

            for (int person = 0; person < leaderboardList.size(); person++) {
                System.out.println(person + 1 + ". " + leaderboardList.get(person).getName());
            }

            System.out.println();
            System.out.println("Would you like to play again y/n?");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.toUpperCase().equals("N") || input.toUpperCase().equals("NO")) {
                tournamentCheck = false;
            }
        }
    }
}
