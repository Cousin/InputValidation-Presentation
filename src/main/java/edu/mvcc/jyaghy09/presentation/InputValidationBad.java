package edu.mvcc.jyaghy09.presentation;

import java.util.Scanner;

/**
 * This example represents messy, duplicated code which is considered bad practice.
 *
 * ORIGINAL LAB INSTRUCTIONS:
 *
 * Write a Java program that prompts the user, one after another in the same program, for the following inputs. If
 * any input is invalid, notify the user to try again for that input.
 * 1. An odd integer
 * 2. A positive prime integer less than 100
 * 3. An integer multiple of two
 * 4. A negative integer
 * 5. A capital letter alphabetically between N and V
 * 6. A number x such that x solves the quadratic equation: 𝑥2 + 5𝑥 + 6
 * Ensure that there is a new line in between the output for each validation check for output readability
 */
public class InputValidationBad {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Continuously prompts user for an odd integer, breaks out once condition is met.
        while (true) {
            System.out.print("Please enter an odd integer: ");
            if (scanner.nextInt() % 2 != 0) {
                System.out.println("Correct!\n");
                break;
            } else {
                System.out.println("Invalid input.  Please try again.");
            }
        }

        // Continuously prompts user for a prime positive, breaks out once condition is met.
        while (true) {
            System.out.print("Please enter a positive prime integer less than 100: ");
            int number = scanner.nextInt();

            // If number is greater than 0 and less than 100, and is prime we break out
            if (number > 0 && number < 100 && isPrime(number)) {
                System.out.println("Correct!\n");
                break;
            } else {
                System.out.println("Invalid input.  Please try again.");
            }
        }

        // Continuously prompts user for an integer multiple of 2, breaks out once condition is met.
        while (true) {
            System.out.print("Please enter an integer multiple of 2: ");
            int number = scanner.nextInt();

            if (number % 2 == 0) {
                System.out.println("Correct!\n");
                break;
            } else {
                System.out.println("Invalid input.  Please try again.");
            }
        }

        // Continuously prompts user for a negative integer, breaks out once condition is met.
        while (true) {
            System.out.print("Please enter a negative integer: ");
            int number = scanner.nextInt();

            if (number < 0) {
                System.out.println("Correct!\n");
                break;
            } else {
                System.out.println("Invalid input.  Please try again.");
            }
        }

        // Continuously prompts user for a character from N - Z exclusive, breaks out once condition is met.
        while (true) {
            System.out.print("Please enter a letter between N and V: ");

            // Store the full string they provided
            String input = scanner.next();

            // Stores the first character of the inputted string
            char character = input.charAt(0);

            // Check length to ensure they only entered 1 character, ensure it is within the expected range
            if (input.length() == 1 && character > 'N' && character < 'V') {
                System.out.println("Correct!\n");
                break;
            } else {
                System.out.println("Invalid input.  Please try again.");
            }
        }

        // Continuously prompts user for an integer to solve a quadratic, breaks out once condition is met.
        while (true) {
            System.out.print("Please enter an integer that solves x^2+5x+6: ");

            int x = scanner.nextInt();

            // If the number provided plugged into the equation evaluates to 0, it is correct
            if (Math.pow(x, 2) + (5*x) + 6 == 0) {
                System.out.println("Correct!\n");
                break;
            } else {
                System.out.println("Invalid input.  Please try again.");
            }
        }

    }

    /**
     * Checks if a number is prime
     *
     * @param number the number to check against
     * @return boolean value of if it is prime
     */
    private static boolean isPrime(int number) {
        if (number < 2) {
            return false; // 0 and 1 are not prime numbers
        }
        if (number == 2) {
            return true; // 2 is the only even prime number
        }
        if (number % 2 == 0) {
            return false; // Exclude other even numbers
        }

        // Check for factors from 3 to the square root of 'number', skipping even numbers
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

}
