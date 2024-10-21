package mine.exercise.codetest.combination;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CombinationGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the set of characters (e.g. 'ABC' without apostrophe) for finding combinations:");
        String rawInput = scanner.nextLine();
        var permutation = new Combination();
        List<Character[]> combinations = new ArrayList<>();
        permutation.generateCombinations(rawInput, combinations);
        permutation.printCombinations(rawInput, combinations);
    }
}
