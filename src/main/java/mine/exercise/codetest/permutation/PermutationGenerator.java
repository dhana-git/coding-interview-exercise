package mine.exercise.codetest.permutation;

import java.util.ArrayList;
import java.util.Scanner;

public class PermutationGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the set of characters (e.g. 'ABC' without apostrophe) for finding permutations:");
        String rawInput = scanner.nextLine();
        var permutation = new Permutation();
        ArrayList<char[]> permutations = new ArrayList<>();
        permutation.permute(rawInput, permutations);
        permutation.printPermutations(permutations, rawInput);
    }
}

