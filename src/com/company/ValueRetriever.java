package com.company;

import java.util.Scanner;

public class ValueRetriever {
    static int ValidateAndReturnAnInt(Scanner scanner) {

        String nextInput;
        int outputValue;

        while(true) {
            try {
                nextInput = scanner.nextLine();
                outputValue = Integer.parseInt(nextInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("You did not enter an integer. Please enter an integer.");
            }
        }

        return outputValue;
    }

    static int ValidateAndReturnOneTwoThreeOrFour(Scanner scanner) {

        int selection;

        while (true) {
            System.out.println("Please enter 1, 2, 3, or 4.");
            selection = ValidateAndReturnAnInt(scanner);
            if ((selection >= 1) && (selection <= 4)) {
                break;
            } else {
                continue;
            }
        }

        return selection;
    }
}
