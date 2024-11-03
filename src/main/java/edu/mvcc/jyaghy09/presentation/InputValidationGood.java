package edu.mvcc.jyaghy09.presentation;

import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * This example represents how we can drastically clean up our code and remove duplication
 *
 * Using a functional approach, we can create a single method to handle all of our input validation
 */
public class InputValidationGood {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            prompt("Please enter an odd integer: ", scanner::nextInt, i -> i % 2 != 0);

            prompt(
                    "Please enter a positive prime integer less than 100: ",
                    scanner::nextInt,
                    i -> i > 0 && i < 100 && isPrime(i)
            );

            prompt("Please enter an integer multiple of 2: ", scanner::nextInt, i -> i % 2 == 0);

            prompt("Please enter a negative integer: ", scanner::nextInt, i -> i < 0);

            prompt(
                    "Please enter a letter between N and V: ",
                    scanner::next,
                    i -> {
                        // Stores the first character of the inputted string
                        char character = i.charAt(0);

                        return i.length() == 1 && character > 'N' && character < 'V';
                    }
            );

            prompt("Please enter an integer that solves x^2+5x+6: ", scanner::nextInt, i -> Math.pow(i, 2) + (5*i) + 6 == 0);

        }
    }

    /**
     * Prompt the user for input, retrieve the input, then test it
     * Continously asks them until condition is met
     * @param prompt the question to ask the user, printed to std output
     * @param reader a supplier which will read the input from the user
     * @param tester a predicate which tests if the input is considered valid
     * @param <T> type of input being parsed
     */
    private static <T> void prompt(String prompt, Supplier<T> reader, Predicate<T> tester) {
        // Infinitely loop, because we will handle the exit
        while (true) {
            // Print the prompt
            System.out.print(prompt);

            // Retrieve the input
            final T input = reader.get();

            // Test if the input is considered valid
            if (tester.test(input)) {
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
