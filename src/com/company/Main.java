package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static com.company.MainMenu.DisplayMainMenu;
import static com.company.Profile.ReadProfileFileAndStoreToArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Profiles.txt"));
        ArrayList<String> roster = new ArrayList<String>();

        ReadProfileFileAndStoreToArrayList(roster);

        int menuinput = 0;

        CreateProfileFile();
        DisplayMainMenu();
        ReadProfileFileAndStoreToArrayList(roster);

        System.out.println(roster);
        while (menuinput == 0) {
            menuinput = MainMenuCheck(menuinput);
            while (menuinput > 0 && menuinput < 5) {
                if (menuinput == 1) {
                    BufferedReader reader = new BufferedReader(new FileReader("C:\\Profiles.txt"));
                    String line = reader.readLine();
                    ArrayList<String> names = new ArrayList<>();

//                    System.out.println("All of the player's names will be printed.");
//                    while (line != null) {
//                        int counter = 0;
//                        while (counter != 5) {
//                            if (counter == 0) {
//                                System.out.println(line);
//                                names.add(line);
//                            }
//                            line = reader.readLine();
//                            counter++;
//                            if (line == null) {
//                                break;
//                            }
//                        }
//                    }
                    reader.close();

                    Scanner keyboard = new Scanner(System.in);
                    boolean on = true;
                    int exiting = 0;
                    ArrayList<String> playerlist = new ArrayList<String>();
                    playerlist.removeAll(playerlist);

                    while (on == true) {
                        System.out.println("Please enter the names of the players who you want to include one line at a time. You will have to type their names exactly as they are listed (case sensitive).");
                        line = keyboard.nextLine();
                        if (names.contains(line)) {
                            playerlist.add(line);
                            System.out.println("Do you wish to add another player? Please enter 1 for yes or 2 for no.");
                            int yesorno = 0;
                            yesorno = YesorNo(yesorno);
                            if(yesorno == 2) {
                                on = false;
                                break;
                            } else {
                                continue;
                            }
                            //This part of the code boots you back to the main menu if you keep entering the wrong names or continues you forward.
                        } else {
                            exiting++;
                            System.out.println("That player does not exist or you typed the name incorrectly. Please try again.");
                            System.out.println("If you keep entering the wrong names (three times in a row), you will just continue with the amount of players that you have.");
                            if (exiting == 3) {
                                break;
                            }
                        }
                    }




                    //Lists the players playing and the number of players playing.
                    System.out.println("These are the list of players playing.");
                    System.out.println(playerlist);
                    System.out.println("The turn order goes from the order of the array. For example, the first person listed will start.");
                    int numberofplayers = 0;
                    numberofplayers = playerlist.size();
                    if (numberofplayers == 1) {
                        System.out.println("There is one person playing. Good luck!");
                    } else {
                        System.out.println("There are " + numberofplayers + " people playing.");
                    }





                    //This sets the maximum number for all players.
                    int turnorder = 1;
                    int realturnorder = 0;
                    int arraylistorder = 0;
                    int Maximum = 0;
                    StringBuilder rosterstring = new StringBuilder();
                    String newstring = new String();
                    StringBuilder gamename = new StringBuilder();
                    System.out.println("Player " + turnorder + " Please enter a positive integer to be the maximum number for all players");
                    Maximum = PositiveIntegerCheck(Maximum);
                    System.out.println("Your limit is from 0 to " + Maximum);




                    //This creates an arraylist of random numbers for each player
                    int[] answers;
                    answers = null;
                    answers = new int[numberofplayers];
                    for (int i = 0; i < answers.length; i++) {
                        double randomnumber = Math.random() * Maximum;
                        int number = (int)randomnumber;
                        answers[i] = number;
                    }

                    //This creates an arraylist to keep track of the number of guesses per player per game.
                    int[] guessespergame;
                    guessespergame = null;
                    guessespergame = new int[numberofplayers];
                    for (int i = 0; i < guessespergame.length; i++) {
                        int numberofguesses = 0;
                        guessespergame[i] = numberofguesses;
                    }

                    //Win conditions
                    boolean win = false;
                    StringBuilder string = new StringBuilder();





                    while (realturnorder <= numberofplayers) {
                        System.out.println("Player " + turnorder + " Please enter a postive integer to be your guess. It cannot be greater than the maximum.");
                        System.out.println("You have made " + guessespergame[realturnorder] + " guess(es) this game.");
                        int guess = 0;
                        guess = GuessCheck(guess);






                        //checks if the input is over the limit
                        while (guess > Maximum) {
                            System.out.println("You entered a number greater than your maximum, please try again.");
                            guess = PositiveIntegerCheck(guess);
                        }






                        //checks if you guessed correctly
                        if (guess == answers[realturnorder]) {

                            //This part of the code adds guesses made this game
                            guessespergame[realturnorder] = guessespergame[realturnorder] + 1;


                            //This part of the code adds guesses.
                            for (int i = arraylistorder; i <= arraylistorder + 3; i++) {
                                if (playerlist.contains(roster.get(i))) {
                                    i = i + 3;
                                    rosterstring.append(roster.get(i).toString());
                                    newstring = rosterstring.toString();
                                    int newnumber = Integer.parseInt(newstring);
                                    newnumber = newnumber + 1;
                                    newstring = Integer.toString(newnumber);
                                    rosterstring.setLength(0);
                                    rosterstring.append(newstring);
                                    roster.set(i, newstring);
                                    rosterstring.setLength(0);
                                    i = i - 3;
                                }
                            }





                            int placeholder = arraylistorder;
                            int placeholder2 = placeholder;



                            //This part of the code adds a loss to every player.
                            arraylistorder = 0;
                            for (int i = arraylistorder; i <= roster.size() - 1; i = i + 5) {
                                if (playerlist.contains(roster.get(i))) {
                                    i = i + 2;
                                    rosterstring.append(roster.get(i).toString());
                                    newstring = rosterstring.toString();
                                    int newnumber = Integer.parseInt(newstring);
                                    newnumber = newnumber + 1;
                                    newstring = Integer.toString(newnumber);
                                    rosterstring.setLength(0);
                                    rosterstring.append(newstring);
                                    roster.set(i, newstring);
                                    rosterstring.setLength(0);
                                    i = i - 2;
                                }
                            }





                            //This part of the code adds a win to the player
                            for (int i = placeholder; i <= placeholder + 1; i++) {
                                if (playerlist.contains(roster.get(i))) {
                                    i = i + 1;
                                    rosterstring.append(roster.get(i).toString());
                                    newstring = rosterstring.toString();
                                    int newnumber = Integer.parseInt(newstring);
                                    newnumber = newnumber + 1;
                                    newstring = Integer.toString(newnumber);
                                    rosterstring.setLength(0);
                                    rosterstring.append(newstring);
                                    roster.set(i, newstring);
                                    rosterstring.setLength(0);
                                    i = i - 1;
                                }
                            }





                            //This part of the code takes away the loss from the winning player because I gave everyone a loss first.
                            for (int i = placeholder2; i <= placeholder2 + 2; i++) {
                                if (playerlist.contains(roster.get(i))) {
                                    i = i + 2;
                                    rosterstring.append(roster.get(i).toString());
                                    newstring = rosterstring.toString();
                                    int newnumber = Integer.parseInt(newstring);
                                    newnumber = newnumber - 1;
                                    newstring = Integer.toString(newnumber);
                                    rosterstring.setLength(0);
                                    rosterstring.append(newstring);
                                    roster.set(i, newstring);
                                    rosterstring.setLength(0);
                                    i = i - 2;
                                }
                            }




                            RecordEverythingtotext(roster);
                            answers = null;
                            win = true;
                            break;
                        } else if (guess < answers[realturnorder]) {


                            //Adds a guess for the game.
                            //for (int i = realturnorder; i < guessespergame.length; i++) {
                            //guessespergame[i] = guessespergame[i] + 1;
                            //}
                            guessespergame[realturnorder] = guessespergame[realturnorder] + 1;


                            //This part of the code adds a guess count.
                            for (int i = arraylistorder; i <= arraylistorder + 3; i++) {
                                if (playerlist.contains(roster.get(i))) {
                                    i = i + 3;
                                    rosterstring.append(roster.get(i).toString());
                                    newstring = rosterstring.toString();
                                    int newnumber = Integer.parseInt(newstring);
                                    newnumber = newnumber + 1;
                                    newstring = Integer.toString(newnumber);
                                    rosterstring.setLength(0);
                                    rosterstring.append(newstring);
                                    roster.set(i, newstring);
                                    rosterstring.setLength(0);
                                    i = i - 3;
                                }
                            }




                            System.out.println("Your guess was too low. Please enter a higher integer.");



                            //This part of the code sets the turn order and the array list slots.
                            turnorder ++;
                            realturnorder = turnorder - 1;
                            arraylistorder = arraylistorder + 5;
                            if (turnorder > numberofplayers) {
                                turnorder = 1;
                                realturnorder = 0;
                                arraylistorder = 0;
                            }
                            continue;





                        } else if (guess > answers[realturnorder]) {

                            //Adds a guess for the game.
                            guessespergame[realturnorder] = guessespergame[realturnorder] + 1;
                            //for (int i = realturnorder; i < guessespergame.length; i++) {
                            //guessespergame[i] = guessespergame[i] + 1;
                            //}


                            //This part of the code adds a guess.
                            for (int i = arraylistorder; i <= arraylistorder + 3; i++) {
                                if (playerlist.contains(roster.get(i))) {
                                    i = i + 3;
                                    rosterstring.append(roster.get(i).toString());
                                    newstring = rosterstring.toString();
                                    int newnumber = Integer.parseInt(newstring);
                                    newnumber = newnumber + 1;
                                    newstring = Integer.toString(newnumber);
                                    rosterstring.setLength(0);
                                    rosterstring.append(newstring);
                                    roster.set(i, newstring);
                                    rosterstring.setLength(0);
                                    i = i - 3;
                                }
                            }




                            System.out.println("Your guess was too high. Please enter a lower integer.");




                            //This part of the code sets the turn order and array list slots.
                            turnorder++;
                            realturnorder = turnorder - 1;
                            arraylistorder = arraylistorder + 4;
                            if (turnorder > numberofplayers) {
                                turnorder = 1;
                                arraylistorder = 0;
                                realturnorder = 0;
                            }
                            continue;
                        }

                        //goes to the next player
                    }

                    if (win == true) {
                        System.out.println("Congratulations Player " + turnorder + "! You have won!");
                        System.out.println("You have made " + guessespergame[realturnorder] + " guess(es) this game.");
                        System.out.println("Would you like to play again? If yes, please enter the number 1.");


                        //This part of the code calculates the guess per game average.
                        //This part of the code grabs how many guesses there were
                        int guesses = 0;
                        for (int i = 0; i < roster.size() - 1; i++) {
                            if (playerlist.contains(roster.get(i))) {
                                i = i + 3;
                                StringBuilder rosterstring1 = new StringBuilder();
                                String newstring1;
                                rosterstring1.append(roster.get(i).toString());
                                newstring1 = rosterstring1.toString();
                                guesses = Integer.parseInt(newstring1);
                                rosterstring1.setLength(0);
                                i = i - 3;
                            }
                        }

                        //This part of the code grabs the wins
                        int winnings = 0;
                        for (int i = 0; i < roster.size(); i++) {
                            if (playerlist.contains(roster.get(i))) {
                                StringBuilder rosterstring2 = new StringBuilder();
                                String newstring2;
                                i = i + 1;
                                rosterstring2.append(roster.get(i).toString());
                                newstring2 = rosterstring2.toString();
                                winnings = Integer.parseInt(newstring2);
                                rosterstring2.setLength(0);
                                i = i - 1;
                            }
                        }

                        int losses = 0;
                        for(int i = 0; i < roster.size(); i++) {
                            if (playerlist.contains(roster.get(i))) {
                                StringBuilder rosterstring3 = new StringBuilder();
                                String newstring3;
                                i = i + 2;
                                rosterstring3.append(roster.get(i).toString());
                                newstring3 = rosterstring3.toString();
                                losses = Integer.parseInt(newstring3);
                                rosterstring3.setLength(0);
                                i = i - 2;
                            }
                        }

                        for(int i = 0; i< roster.size(); i++) {
                            if (playerlist.contains(roster.get(i))) {
                                StringBuilder rosterstring4 = new StringBuilder();
                                String newstring4;
                                i = i + 4;
                                rosterstring4.append(roster.get(i).toString());
                                newstring4 = rosterstring4.toString();
                                //Rounded down
                                guesses = guesses / (winnings + losses);
                                newstring4 = Integer.toString(guesses);
                                rosterstring4.setLength(0);
                                rosterstring4.append(newstring4);
                                roster.set(i, newstring4);
                                rosterstring4.setLength(0);
                                i = i - 4;
                            }
                        }


                        RecordEverythingtotext(roster);
                        menuinput = 0;
                        System.out.println(roster);
                        DisplayMainMenu();
                    }
                }

                if (menuinput == 2) {
                    //This option creates a new profile.
                    CreateAUser();
                    ReadProfileFileAndStoreToArrayList(roster);
                    menuinput = 0;
                    DisplayMainMenu();
                }

                if (menuinput == 3) {
                    DisplayHighScores();
                    menuinput = 0;
                    DisplayMainMenu();
                }

                if (menuinput == 4) {
                    //This option quits the program.
                    System.exit(0);
                }
            }
        }
    }

    //This method checks if the input is an integer that is greater than 0.
    static int PositiveIntegerCheck(int a) {
        Scanner keyboard = new Scanner(System.in);
        String wronginput = keyboard.nextLine();
        int inputnum = 0;
        boolean loop = false;

        do {
            try {
                inputnum = Integer.parseInt(wronginput);
                if (inputnum > 0) {
                    break;
                } else {
                    do {
                        inputnum = 0;
                        System.out.println("Please enter a positive integer greater than 0.");
                        wronginput = keyboard.next();
                        do {
                            try {
                                inputnum = Integer.parseInt(wronginput);
                                loop = false;
                            } catch (NumberFormatException e) {
                                loop = true;
                            }
                        } while (loop = false);
                    } while (inputnum <= 0);
                    loop = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid value!");
                do {
                    inputnum = 0;
                    System.out.println("Please enter a positive integer greater than 0.");
                    wronginput = keyboard.next();
                    do {
                        try {
                            inputnum = Integer.parseInt(wronginput);
                            loop = false;
                        } catch (NumberFormatException b) {
                            loop = true;
                        }
                    } while (loop = false);
                } while (inputnum <= 0);
                loop = true;
            }
        } while (loop = true);
        return inputnum;
    }

    //This method checks if the input is an integer that is greater than or equal to 0.
    static int GuessCheck(int a) {
        Scanner keyboard = new Scanner(System.in);
        String wronginput = keyboard.nextLine();
        int inputnum = -1;
        boolean loop = false;

        do {
            try {
                inputnum = Integer.parseInt(wronginput);
                if (inputnum >= 0) {
                    break;
                } else {
                    do {
                        inputnum = -1;
                        System.out.println("Please enter a positive integer");
                        wronginput = keyboard.next();
                        do {
                            try {
                                inputnum = Integer.parseInt(wronginput);
                                loop = false;
                            } catch (NumberFormatException e) {
                                loop = true;
                            }
                        } while (loop = false);
                    } while (inputnum < 0);
                    loop = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid value!");
                do {
                    inputnum = -1;
                    System.out.println("Please enter a positive integer.");
                    wronginput = keyboard.next();
                    do {
                        try {
                            inputnum = Integer.parseInt(wronginput);
                            loop = false;
                        } catch (NumberFormatException b) {
                            loop = true;
                        }
                    } while (loop = false);
                } while (inputnum <= 0);
                loop = true;
            }
        } while (loop = true);
        return inputnum;
    }

    //This method checks if the input is an integer that is greater than 0 but less than 4.
    static int MainMenuCheck(int a) {
        Scanner keyboard = new Scanner(System.in);
        String wronginput = keyboard.nextLine();
        int inputnum = 0;
        boolean loop = true;
        boolean once = true;

        do {
            try {
                inputnum = Integer.parseInt(wronginput);
                if (inputnum > 0 && inputnum < 5) {
                    break;
                } else {
                    do {
                        inputnum = 0;
                        System.out.println("Please enter 1, 2, 3, or 4.");
                        wronginput = keyboard.next();
                        do {
                            try {
                                inputnum = Integer.parseInt(wronginput);
                                loop = false;
                            } catch (NumberFormatException e) {
                                loop = true;
                            }
                        } while (loop = false);
                    } while (inputnum <= 0 && inputnum > 4);
                    loop = true;
                }
            } catch (NumberFormatException e) {
                //Need to only do this once
                System.out.println("Invalid value!");
                do {
                    inputnum = 0;
                    System.out.println("Please enter 1, 2, 3, or 4.");
                    wronginput = keyboard.next();
                    do {
                        try {
                            inputnum = Integer.parseInt(wronginput);
                            loop = false;
                        } catch (NumberFormatException b) {
                            loop = true;
                        }
                    } while (loop = false);
                } while (inputnum <= 0 && inputnum > 4);
                loop = true;
            }
        } while (loop = true);
        return inputnum;
    }

    //This method creates a text file that will contain all profiles.
    static void CreateProfileFile() {

        try {
            System.out.println("This program saves to the default C:\\ file. If you wish to proceed, please make the proper adjustments. If you receive the error, 'acess is denied' please run Eclipse or your standard compiler as an administrator.");
            File file = new File("C:\\Profiles.txt");

            if (!file.exists()) {
                file.createNewFile();
                CreateAUser();
            }
            System.out.println("Profiles.txt was created in your C drive.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This method creates a user in the text file.
    static void CreateAUser() {
        Scanner keyboard = new Scanner(System.in);
        File file = new File("C:\\Profiles.txt");
        FileWriter writer;

        System.out.println("Please enter a name for your user.");
        String profile = keyboard.nextLine();

        try {
            writer = new FileWriter(file, true);
            PrintWriter printer = new PrintWriter(writer);
            //This prints the name.
            printer.println(profile);
            //These are the wins.
            printer.println("0");
            //These are the losses.
            printer.println("0");
            //These are the guesses.
            printer.println("0");
            //These are the number of guesses per game.
            printer.println("0");
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Profile created for " + profile + ". Your record is 0 wins, 0 losses, 0 guesses, and you average 0 guesses per game.");
    }

    static void RecordEverythingtotext(ArrayList<String> everything) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        FileWriter writer;
        StringBuilder rosterstring = new StringBuilder();
        String pasting;

        File files = new File("C:\\Profiles.txt");

        if (!files.exists()) {
            files.createNewFile();
        }

        if(files.exists()) {
            files.delete();
        }

        for (int i = 0; i < everything.size(); i++) {
            rosterstring.append(everything.get(i));
            pasting = rosterstring.toString();

            try {
                writer = new FileWriter(files, true);
                PrintWriter printer = new PrintWriter(writer);
                printer.println(pasting);
                rosterstring.setLength(0);
                printer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //This method displays the high scores.
    static void DisplayHighScores() throws IOException {
        System.out.println("These are the high scores. The player with the most wins is ranked first. The player with the second highest wins is ranked second, and so on.");
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Profiles.txt"));
        String line = reader.readLine();
        while (line != null) {
            int counter = 0;
            while (counter != 5) {


                //Prints the name.
                if (counter == 0) {
                    System.out.println(line);
                }

                //Prints the wins.
                if (counter == 1) {
                    System.out.println("Wins " + line);
                }

                //Prints the losses.
                if (counter == 2) {
                    System.out.println("Losses " + line);
                }

                //Prints the guesses.
                if (counter == 3) {
                    System.out.println("Guesses " + line);
                }

                //Prints the average
                if (counter == 4) {
                    System.out.println("Average Guesses Per Game " + line);
                }

                line = reader.readLine();
                counter++;

                //Exits out of the loop when the reader reaches the end of the file.
                if (line == null) {
                    break;
                }
            }
        }
        reader.close();
    }

    //This method asks the user to input a 1 for yes, or a 2 for no.
    static int YesorNo(int c) {

        Scanner keyboard = new Scanner(System.in);
        String wronginput = keyboard.nextLine();
        int inputnum = 0;
        boolean loop = true;
        boolean once = true;

        do {
            try {
                inputnum = Integer.parseInt(wronginput);
                if (inputnum > 0 && inputnum < 3) {
                    break;
                } else {
                    do {
                        inputnum = 0;
                        System.out.println("Please enter 1 for 'yes' and 2 for 'no'.");
                        wronginput = keyboard.next();
                        do {
                            try {
                                inputnum = Integer.parseInt(wronginput);
                                loop = false;
                            } catch (NumberFormatException e) {
                                loop = true;
                            }
                        } while (loop = false);
                    } while (inputnum <= 0 && inputnum > 4);
                    loop = true;
                }
            } catch (NumberFormatException e) {
                //Need to only do this once
                System.out.println("Invalid value!");
                do {
                    inputnum = 0;
                    System.out.println("Please enter 1 for 'yes' or 2 for 'no'.");
                    wronginput = keyboard.next();
                    do {
                        try {
                            inputnum = Integer.parseInt(wronginput);
                            loop = false;
                        } catch (NumberFormatException b) {
                            loop = true;
                        }
                    } while (loop = false);
                } while (inputnum <= 0 && inputnum > 4);
                loop = true;
            }
        } while (loop = true);
        return inputnum;
    }

}
