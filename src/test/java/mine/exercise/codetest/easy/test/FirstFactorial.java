package mine.exercise.codetest.easy.test;

import java.math.BigInteger;
import java.util.stream.Stream;

public class FirstFactorial {
    private static BigInteger factorialIterativeBig(int num) {
        return Stream.iterate(BigInteger.ONE, x -> x.add(BigInteger.ONE)).limit(num)
                .reduce((result, currElem) -> result.multiply(currElem)).get();
    }

    private static BigInteger factorial(int num) {
        return Stream.iterate(BigInteger.ONE, x -> x.add(BigInteger.ONE)).limit(num)
                .reduce((result, currElem) -> result.multiply(currElem)).get();
    }

    public static void main(String[] args) {
        // keep this function call here
        // Scanner s = new Scanner(System.in);
        // System.out.print(factorial(s.nextLine()));
        System.out.println(factorial(5));
    }

}
