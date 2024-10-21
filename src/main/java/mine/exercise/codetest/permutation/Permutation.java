package mine.exercise.codetest.permutation;

import java.util.Arrays;
import java.util.List;

/**
 * An arrangement of a set of objects in a specific order. For 'n' distinct objects, there are n! (n factorial) possible permutations.
 * If only 'r' items are taken from 'n' items then P(n,r) = n!/(n-r)!.
 */
public class Permutation {
    public boolean validateInput(String input) {
        return input != null && !input.isBlank();
    }

    public void permute(String rawInput, List<char[]> permutations) {
        if (validateInput(rawInput)) {
            char[] input = rawInput.strip().toCharArray();
            permuteInternal(input, permutations, 0);
        } else {
            System.out.println("Invalid input");
        }
    }

    private void permuteInternal(char[] input, List<char[]> permutations, int currentFixedIndex) {
        if (currentFixedIndex == input.length - 1) {
            permutations.add(Arrays.copyOf(input, input.length));
            return;
        }
        for (int i = currentFixedIndex; i < input.length; i++) {
            swap(input, currentFixedIndex, i); // swap the characters
            permuteInternal(input, permutations, currentFixedIndex + 1);
            swap(input, currentFixedIndex, i); // backtracking
        }
    }

    private void swap(char[] input, int firstIdx, int secondIdx) {
        char temp = input[firstIdx];
        input[firstIdx] = input[secondIdx];
        input[secondIdx] = temp;
    }

    public void printPermutations(List<char[]> permutations, String rawInput) {
        System.out.println("Permutations of '" + rawInput + "' ('" + permutations.size() + "'): ");
        permutations.stream().map(String::valueOf).forEachOrdered(System.out::println);
    }
}
