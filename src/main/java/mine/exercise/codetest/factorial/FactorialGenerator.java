package mine.exercise.codetest.factorial;

import java.util.Scanner;

interface FactorialFinder {
    int factorial(int number);
}

/**
 * Product of all positive integers from 1 to n, denoted by n! = n * (n-1)!.
 * 0! = 1, 1! = 1, 2! = 2, 3! = 6, 4! = 24, 5! = 120
 * 5! = 5 * 4 * 3 * 2 * 1 = 120
 */
public class FactorialGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input non-negative number to find factorial:");
        String input = input = scanner.nextLine();
        if (!isValidNumber(input)) {
            System.out.print("Invalid input");
            return;
        }
//        FactorialFinderCreator creator = new RecursiveFactorialFinderCreator();
        FactorialFinderCreator creator = new IterativeFactorialCreator();
        int result = creator.createFactorialFinder().factorial(Integer.parseInt(input.strip()));
        System.out.println("Factorial of '" + input + "' is '" + result + "'");
    }

    private static boolean isValidNumber(String input) {
        try {
            return input != null && !input.isBlank() && Integer.parseInt(input.strip()) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

abstract class FactorialFinderCreator {
    abstract FactorialFinder createFactorialFinder();
}

class RecursiveFactorialFinderCreator extends FactorialFinderCreator {

    @Override
    FactorialFinder createFactorialFinder() {
        return new RecursiveFactorialFinder();
    }
}

class IterativeFactorialCreator extends FactorialFinderCreator {

    @Override
    FactorialFinder createFactorialFinder() {
        return new IterativeFactorialFinder();
    }
}

class RecursiveFactorialFinder implements FactorialFinder {

    @Override
    public int factorial(int number) {
        // base case
        if (number == 0 || number == 1) {
            return 1;
        }
        // recursive case
        return number * factorial(number - 1);
    }
}

class IterativeFactorialFinder implements FactorialFinder {

    @Override
    public int factorial(int number) {
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result = i * result;
        }
        return result;
    }
}

