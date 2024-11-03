# Input Validation for CI 245 Java Programming Class

**Course**: CI 245 - Java Programming  
**Topic**: Reducing Code Duplication Using a Functional Approach

---

## Overview

This presentation focuses on improving input validation in Java by reducing code duplication through the use of functional programming techniques. The goal is to simplify and streamline repetitive validation logic using a reusable and flexible method.

---

## Problem: Code Duplication

### Example of BAD Code
The following example illustrates a common approach to input validation, which leads to duplicated code:

```java
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
        System.out.println("Invalid input. Please try again.");
    }
}
```

### Issues with the BAD Code:
- **Repetition**: The loop structure and validation logic are repeated in many parts of the codebase, making it harder to maintain and modify.
- **Hard to Test**: Each validation block is embedded in the loop, making testing and modification more cumbersome.

---

## Solution: Functional Approach

The functional approach abstracts the input validation logic into a reusable method, reducing duplication and improving readability.

### Example of GOOD Code
Using the `prompt` method we created:

```java
prompt(
    "Please enter a letter between N and V: ",
    scanner::next,
    i -> {
        // Stores the first character of the inputted string
        char character = i.charAt(0);
        return i.length() == 1 && character > 'N' && character < 'V';
    }
);
```

### Benefits of the Functional Approach:
- **Reduced Duplication**: The `prompt` method encapsulates the repetitive structure, making the code cleaner and more maintainable.
- **Better Readability**: The validation logic is more concise and easier to understand.
- **Flexibility**: You can easily adjust the `prompt` method to handle different types of input and validation rules.

---

## How It Works

The `prompt` method takes three parameters:
1. **String `prompt`**: The message to display to the user.
2. **Supplier<T> `reader`**: A function to get the input from the user.
3. **Predicate<T> `tester`**: A function to test the validity of the input.

By passing in functions as arguments, we can reuse the `prompt` method for various types of input validation, making the code more modular and efficient.

---

## Conclusion

Adopting a functional approach to input validation in Java helps reduce code duplication, enhances readability, and simplifies code maintenance. This presentation demonstrates how to refactor common input validation logic into a reusable, elegant solution using functional programming principles.

---

Thank you for attending the presentation! If you have any questions or feedback, feel free to reach out.
