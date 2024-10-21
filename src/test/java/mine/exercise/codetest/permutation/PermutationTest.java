package mine.exercise.codetest.permutation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;

class PermutationTest {

    private Permutation permutation;

    private static Stream<Arguments> permuteData() {
        return Stream.of(Arguments.of("A", 1), Arguments.of("AB", 2), Arguments.of("ABC", 6), Arguments.of("ABCD", 24));
    }

    @BeforeEach
    void setUp() {
        permutation = new Permutation();
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t\n"})
    void validateInput(String inputAsString) {
        assertFalse(permutation.validateInput(inputAsString));
    }

    @ParameterizedTest
    @MethodSource("permuteData")
    void permute(String rawInput, int expectedNumberOfPermutations) {
        ArrayList<char[]> permutations = new ArrayList<>();
        permutation.permute(rawInput, permutations);
        Assertions.assertEquals(expectedNumberOfPermutations, permutations.size());
    }
}