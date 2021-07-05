package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    static void DisplayMainMenu() {
        System.out.println("Welcome to the Random Number Game Tournament Program!");
        System.out.println("Option 1: Start a new game.");
        System.out.println("Option 2: Create a new user.");
        System.out.println("Option 3: Display high scores.");
        System.out.println("Option 4: Quit.");
        System.out.println("Please enter the integer according to the option (1-4).");
    }

    static void PrintPlayerNames(BufferedReader reader) throws IOException {
        System.out.println("All of the player's names will be printed.");

        String line = reader.readLine();
        ArrayList<String> names = new ArrayList<>();

        while (line != null) {
            for (int i = 0; i <= 4; i++) {
                if (i == 0) {
                    System.out.println(line);
                    names.add(line);
                }
                line = reader.readLine();
                if (line == null) {
                    break;
                }
            }
        }
    }

    static void CreateNewPlayer(Scanner scanner) {
        System.out.println("Please enter a name for your user.");

        File file = new File("C:\\Profiles.txt");
        String newPlayer = scanner.nextLine();
        int wins = 0;
        int losses = 0;
        int guesses = 0;
        int guessesPerGame = 0;

        try {
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(newPlayer);
            printWriter.println(wins);
            printWriter.println(losses);
            printWriter.println(guesses);
            printWriter.println(guessesPerGame);
            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Profile created for " + newPlayer + ". Your record is "
                + wins + " " + losses + " " + guesses + " " + guessesPerGame + ".");
    }
}
