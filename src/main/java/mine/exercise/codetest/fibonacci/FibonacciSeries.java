package mine.exercise.codetest.fibonacci;

import java.util.Arrays;
import java.util.Scanner;

interface FibSeriesGenerator {
    int fibonacci(int number);
}

/**
 * A series of numbers in which each number is the sum of the two preceding ones, denoted by F.
 * F (n) = F(n-1) + F(n-2);
 * F(0) = 0, F(1) = 1;
 * <p>
 * F(5) = 5 = 0, 1, 1, 2, 3, 5
 */
public class FibonacciSeries {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input non-negative number to find fibonacci:");
        String input = input = scanner.nextLine();
        if (!isValidNumber(input)) {
            System.out.print("Invalid input");
            return;
        }
//        FibGeneratorCreator creator = new RecursiveFibGeneratorCreator();
//        FibGeneratorCreator creator = new IterativeFibGeneratorCreator();
        FibGeneratorCreator creator = new TopdownFibGeneratorCreator();
        long result = creator.createFibSeriesGenerator().fibonacci(Integer.parseInt(input.strip()));
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

abstract class FibGeneratorCreator {
    abstract FibSeriesGenerator createFibSeriesGenerator();
}

class RecursiveFibGeneratorCreator extends FibGeneratorCreator {

    @Override
    FibSeriesGenerator createFibSeriesGenerator() {
        return new RecursiveFibSeriesGenerator();
    }
}

class IterativeFibGeneratorCreator extends FibGeneratorCreator {

    @Override
    FibSeriesGenerator createFibSeriesGenerator() {
        return new IterativeFibSeriesGenerator();
    }
}

class TopdownFibGeneratorCreator extends FibGeneratorCreator {

    @Override
    FibSeriesGenerator createFibSeriesGenerator() {
        return new TopdownFibSeriesGenerator();
    }
}

class RecursiveFibSeriesGenerator implements FibSeriesGenerator {

    @Override
    public int fibonacci(int number) {
        if (number == 0 || number == 1) {
            return number;
        }
        return fibonacci(number - 1) + fibonacci(number - 2);
    }
}

class IterativeFibSeriesGenerator implements FibSeriesGenerator {

    @Override
    public int fibonacci(int number) {
        if (number <= 1) {
            return number;
        }
        int[] fibs = new int[number + 1];
        Arrays.fill(fibs, -1);
        fibs[0] = 0;
        fibs[1] = 1;
        for (int i = 2; i <= number; i++) {
            fibs[i] = fibs[i - 1] + fibs[i - 2];
        }
        return fibs[number];
    }
}

class TopdownFibSeriesGenerator implements FibSeriesGenerator {

    @Override
    public int fibonacci(int number) {
        if (number <= 1) {
            return number;
        }
        int[] fibs = new int[number + 1];
        Arrays.fill(fibs, -1);
        fibs[0] = 0;
        fibs[1] = 1;
        return fibonacciInternal(number, fibs);
    }

    private int fibonacciInternal(int number, int[] resultData) {
        if (resultData[number] != -1) {
            return resultData[number];

        }
        resultData[number] = fibonacciInternal(number - 1, resultData) + fibonacciInternal(number - 2, resultData);
        return resultData[number];
    }
}