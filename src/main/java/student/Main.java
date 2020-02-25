package student;


import student.adventure.Adventure;
import com.fasterxml.jackson.core.JsonParser;
import student.adventure.AdventureTournament;
import student.adventure.Leaderboard;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {

        AdventureTournament adventureTournament = new AdventureTournament();
        adventureTournament.executeTournament();
    }
}