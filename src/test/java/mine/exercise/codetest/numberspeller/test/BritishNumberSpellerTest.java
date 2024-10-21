package mine.exercise.codetest.numberspeller.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BritishNumberSpellerTest implements NumberSpeller {
    private static String[] UNITS_SPELLING = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static String[] TENS_SPELLING = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    private static String[] OTHER_UNITS_SPELLING = {"hundred", "thousand", "million", "billion"};

    private static int MAX_VALUE = 999999999;

    @Test
    public void throwExceptionOnNumberGreaterThanMax() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> spellNumber(MAX_VALUE + 1));
    }

    @Test
    public void spellMinus999() {
        Assertions.assertEquals("minus nine hundred and ninety nine", spellNumber(-999));
    }

    @Test
    public void spellZero() {
        Assertions.assertEquals("zero", spellNumber(0));
    }

    @Test
    public void spellOne() {
        Assertions.assertEquals("one", spellNumber(1));
    }

    @Test
    public void spellTen() {
        Assertions.assertEquals("ten", spellNumber(10));
    }

    @Test
    public void spellTwenty() {
        Assertions.assertEquals("twenty", spellNumber(20));
    }

    @Test
    public void spellTwentyOne() {
        Assertions.assertEquals("twenty one", spellNumber(21));
    }

    @Test
    public void spell99() {
        Assertions.assertEquals("ninety nine", spellNumber(99));
    }

    @Test
    public void spell100() {
        Assertions.assertEquals("one hundred", spellNumber(100));
    }

    @Test
    public void spell119() {
        Assertions.assertEquals("one hundred and nineteen", spellNumber(119));
    }

    @Test
    public void spell999() {
        Assertions.assertEquals("nine hundred and ninety nine", spellNumber(999));
    }

    @Test
    public void spell1000() {
        Assertions.assertEquals("one thousand", spellNumber(1000));
    }

    @Test
    public void spell9999() {
        Assertions.assertEquals("nine thousand nine hundred and ninety nine", spellNumber(9999));
    }

    @Test
    public void spell56945781() {
        Assertions.assertEquals("fifty six million nine hundred and forty five thousand seven hundred and eighty one", spellNumber(56945781));
    }

    @Test
    public void spell999999999() {
        Assertions.assertEquals("nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine", spellNumber(999999999));
    }

    @Override
    public String spellNumber(int num) {
        if (num < 0) {
            return "minus " + spellNumberInternal(Math.abs(num));
        }
        if (num == 0) {
            return "zero";
        }

        if (num > MAX_NUMBER_TO_SPELL) {
            throw new IllegalArgumentException("Number can not be greater than max value " + MAX_NUMBER_TO_SPELL);
        }
        return spellNumberInternal(num);
    }

    private String spellNumberInternal(int num) {
        String words = EMPTY_STR;
        if (num / 1000000000 > 0) {
            words += spellNumberInternal(num / 1000000000) + SPACE_STR + OTHER_UNITS_SPELLING[3] + (num % 1000000000 > 0 ? SPACE_STR : EMPTY_STR);
            num %= 1000000000;
        }

        if (num / 1000000 > 0) {
            words += spellNumberInternal(num / 1000000) + SPACE_STR + OTHER_UNITS_SPELLING[2] + (num % 1000000 > 0 ? SPACE_STR : EMPTY_STR);
            num %= 1000000;
        }

        if (num / 1000 > 0) {
            words += spellNumberInternal(num / 1000) + SPACE_STR + OTHER_UNITS_SPELLING[1] + (num % 1000 > 0 ? SPACE_STR : EMPTY_STR);
            num %= 1000;
        }

        if (num / 100 > 0) {
            words += spellNumberInternal(num / 100) + SPACE_STR + OTHER_UNITS_SPELLING[0] + (num % 100 > 0 ? " and " : EMPTY_STR);
            num %= 100;
        }

        if (num > 0) {
            if (num < 20) {
                words += UNITS_SPELLING[num];
            } else {
                words += TENS_SPELLING[num / 10];
                if (num % 10 > 0) {
                    words += SPACE_STR + UNITS_SPELLING[num % 10];
                }
            }
        }

        return words;
    }
}
