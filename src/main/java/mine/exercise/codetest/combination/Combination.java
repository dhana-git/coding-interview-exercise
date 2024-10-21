package mine.exercise.codetest.combination;

import java.util.*;

/**
 * A selection of items from a set where the order does not matter.
 * The number of combinations of 'n' items taken 'r' at a time is calculated using the formula C(n,r) = n!/r!(n-r)!
 */
public class Combination {
    public boolean validateInput(String input) {
        return input != null && !input.isBlank();
    }

    public void generateCombinations(String rawInput, List<Character[]> combinations) {
        if (validateInput(rawInput)) {
            char[] input = rawInput.strip().toCharArray();
            generateCombinationsInternal(input, combinations, Collections.<Character>emptySet(), 0);
        }
    }

    public void generateCombinationsInternal(char[] input, List<Character[]> combinations, Set<Character> currentSet, int currentFixedIndex) {
        combinations.add(currentSet.toArray(new Character[currentSet.size()]));
        for (int i = currentFixedIndex; i < input.length; i++) {
            var newCurrentSet = new HashSet<>(currentSet);
            newCurrentSet.add(input[i]);
            generateCombinationsInternal(input, combinations, newCurrentSet, i + 1);
        }
    }

    public void printCombinations(String rawInput, List<Character[]> combinations) {
        System.out.println("Combinations of '" + rawInput + "' ('" + combinations.size() + "'): ");
        combinations.stream().map(Arrays::toString).map(String::valueOf).forEachOrdered(System.out::println);
    }
}
