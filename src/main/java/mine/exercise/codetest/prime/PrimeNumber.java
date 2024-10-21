package mine.exercise.codetest.prime;

import java.util.stream.Stream;

/**
 * A natural number greater than 1 that has no positive divisors other 1 and itself.
 * Prime numbers can only be divided by 1 and the number itself.
 * <p>
 * Prime numbers are : 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, etc.
 */
public class PrimeNumber {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String number = scanner.nextLine();
        Stream.iterate(1, n -> n + 1).limit(1000).forEach(n -> {
            if (isPrimeNumber(n)) {
                System.out.println(n);
            }
        });
    }

    private static boolean isPrimeNumber(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i < number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
