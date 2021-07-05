package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Profile {

    static void CreateProfileFile(Scanner scanner) {

        try {
            System.out.println("This program saves to the default C:\\ drive.");
            File file = new File("C:\\Profiles.txt");

            if (!file.exists()) {
                file.createNewFile();
                CreateNewProfile(scanner);
            }
            System.out.println("Profiles.txt was created in your C drive.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void CreateNewProfile(Scanner scanner) {
        File file = new File("C:\\Profiles.txt");
        FileWriter writer;

        System.out.println("Please enter a name for your user.");
        String profile = scanner.nextLine();

        try {
            writer = new FileWriter(file, true);
            PrintWriter printer = new PrintWriter(writer);
            printer.println(profile);
            printer.println("0");
            printer.println("0");
            printer.println("0");
            printer.println("0");
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Profile created for " + profile);
    }

    static ArrayList<String> ReadProfileFileAndStoreToArrayList(ArrayList<String> allProfiles) throws IOException {
        allProfiles.clear();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader("C:\\Profiles.txt"));
            String entireFileToString;
            while ((entireFileToString = bufferedReader.readLine()) != null) {
                allProfiles.add(entireFileToString);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        return allProfiles;
    }
}
