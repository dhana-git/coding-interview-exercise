
package mine.exercise.codetest.numberspeller;

public class BritishNumberSpeller implements NumberSpeller {
	private static String[] OTHER_UNITS_SPELLING = { "hundred", "thousand", "million", "billion" };

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
