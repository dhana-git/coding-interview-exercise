package mine.exercise.codetest.numberspeller;

public interface NumberSpeller {
	public static String[] UNITS_SPELLING = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
			"fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
	public static String[] TENS_SPELLING = { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
	public static int MAX_NUMBER_TO_SPELL = 999999999;
	public static String SPACE_STR = " ";
	public static String EMPTY_STR = "";

	String spellNumber(int num);
}
